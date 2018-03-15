package org.hbhk.aili.esb.server.agent.processor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.stream.FactoryConfigurationError;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.common.constant.EsbRouteConstant;
import org.hbhk.aili.esb.common.constant.ExpressConstant;
import org.hbhk.aili.esb.common.exception.ESBRunTimeException;
import org.hbhk.aili.esb.server.agent.bean.CommonError;
import org.hbhk.aili.esb.server.agent.exception.AgentEsbHeaderException;
import org.hbhk.aili.esb.server.agent.exception.AgentExceptionConstant;
import org.hbhk.aili.esb.server.agent.exception.URLSupportException;
import org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.entity.ObjectFactory;
import org.hbhk.aili.esb.server.common.exception.ESBConvertException;
import org.hbhk.aili.esb.server.common.jmsservice.ISendMessageService;
import org.hbhk.aili.esb.server.common.log.audit.AuditInfo;
import org.hbhk.aili.esb.server.security.flowcontrol.container.ICountContainer;
import org.hbhk.aili.esb.server.security.flowcontrol.counter.ICounter;
import org.springframework.beans.BeanUtils;

public class ExpressErrorProcessor {
	private static Logger LOG = Logger.getLogger(ExpressErrorProcessor.class);
	private ObjectMapper customerMapper;
	private int defaultMaxLength = 400;
	private ISendMessageService exceptionMessageService;

	private ISendMessageService rsAuditLogService;

	private ICountContainer esbInterfaceConcurrentContainer;
	private ICountContainer esbUserInterfaceConcurrentContainer;

	private String control = "true";

	/**
	 * 路由异常消息处理
	 * 
	 * @param exchange
	 */
	public void exceptionHandler(Exchange exchange) throws Exception {
		//表明异常已经处理过了。LoggingTimeCostsNotifer中不用再进行处理
		exchange.getProperties().put(ESBServiceConstant.EXCEPTION_PROCCESSED,true);

		Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
		CommonError error = new CommonError();
		ESBHeader esbHeader = ExpressHeaderUtil.getHeaderFromHeaders(exchange);
		// 获取headers信息
		Map<String, Object> headers = exchange.getIn().getHeaders();
		
		//计数器 处理
		if (EsbRouteConstant.TRUE.equals(control)) {
			// 并发计数器递增
			ICounter itfCounter = (ICounter)exchange.getProperties().get(ExpressConstant.ITF_CON_COUNTER);
			ICounter userItfCounter = (ICounter)exchange.getProperties().get(ExpressConstant.USER_ITF_CON_COUNTER);
			if (itfCounter != null) {
				itfCounter.incrementAndGet();
			}
			if (userItfCounter != null) {
				userItfCounter.incrementAndGet();
			}
		}
		
		if (exception instanceof URLSupportException) {
			error.setCreatedTime(new Date());
			error.setMessage(exception.getMessage());
			error.setExceptionCode(((URLSupportException) exception).getCode());
			error.setExceptionType(((URLSupportException) exception).getType());
			headers.put(EsbRouteConstant.HTTP_RESPONSE_CODE, "405");
			//headers.put(EsbRouteConstant.IS_LOGGING, EsbRouteConstant.FALSE);
		} else if (exception instanceof ESBRunTimeException) {
			error.setCreatedTime(new Date());
			error.setMessage("异常信息:" + exception.getMessage());

			error.setExceptionCode(((ESBRunTimeException) exception).getCode());
			error.setExceptionType(((ESBRunTimeException) exception).getType());
		}else if(exception instanceof AgentEsbHeaderException){
			AgentEsbHeaderException ex= (AgentEsbHeaderException)exception;
			error.setCreatedTime(new Date());
			error.setMessage(ex.getMessage());
			error.setExceptionCode(ex.getCode());
			error.setExceptionType(ex.getType());
			//headers.put(EsbRouteConstant.HTTP_RESPONSE_CODE, "200");
		}else {
			error.setCreatedTime(new Date());
			error.setMessage("异常信息:" + exception.getMessage());
			error.setExceptionType(AgentExceptionConstant.TYPE_SYS);
			error.setExceptionCode(AgentExceptionConstant.UNKNOW);
		}
		// 记录到ESB的数据信息带DetailInfo消息；
		String response2DB = null;
		// 给落地配代理返回的消息中不带DetailInfo 信息
		String response = customerMapper.writeValueAsString(error);
		// 发送相应消息
		returnResponseException(exchange, response, (String) headers.get(EsbRouteConstant.HTTP_RESPONSE_CODE));
		//如果URL校验不通过，或者header信息中code参数获取不到，则不记录异常日志，审计日志，只在控制台打印；
		if(exception instanceof URLSupportException || exception instanceof AgentEsbHeaderException){
			LOG.error(exception.getMessage(),exception);
			return ;
		}
		String detail = exception2String(exception);
		detail = detail.length() >= defaultMaxLength ? detail.substring(0, defaultMaxLength) : detail;
		error.setDetailedInfo(detail);
		CommonExceptionInfo commonExceptionInfo = commonErrorToCommonExceptionInfo(error);
		
		// 记录异常
		Boolean isRecordExpn=true;
		if(exchange.getProperty(EsbRouteConstant.IS_RECORD_EXCEPTION)!= null ){
			isRecordExpn = (Boolean) exchange.getProperty(EsbRouteConstant.IS_RECORD_EXCEPTION);
		}
		if(isRecordExpn){
			response2DB = fromMessage(commonExceptionInfo);		
			exceptionMessageService.send(esbHeader, response2DB);			
		}
		
		// 记录审计日志
		Boolean isNeedAudit = true;
		if(exchange.getProperty(EsbRouteConstant.IS_RECORD_AUDIT)!=null){
			isNeedAudit  = (Boolean)exchange.getProperty(EsbRouteConstant.IS_RECORD_AUDIT);
		}
		if(isNeedAudit){				
			ArrayList<AuditInfo> list = new ArrayList<AuditInfo>();
			AuditInfo request = new AuditInfo();
			request.setBody((String) exchange.getProperty(ESBServiceConstant.REQUEST_SOAP_BODY));
			request.setHeader(esbHeader);
			AuditInfo auditInfo = new AuditInfo();
			ESBHeader responseHeader = new ESBHeader();
			BeanUtils.copyProperties(esbHeader, responseHeader);
			responseHeader = ExpressHeaderUtil.getResponseHeader(exchange, responseHeader);
			auditInfo.setBody(response2DB);
			auditInfo.setHeader(responseHeader);
			list.add(request);
			list.add(auditInfo);
			rsAuditLogService.send(list);
		}
		LOG.error(exception.getMessage(), exception);
		
		//移除多余的Header信息
		headers.remove("X-Forwarded-For");
		headers.remove("X-Powered-By");
		headers.remove("breadcrumbId");
		//headers.remove(EsbRouteConstant.HTTP_RESPONSE_CODE);
		//headers.remove(EsbRouteConstant.IS_LOGGING);
	}

	private void returnResponseException(Exchange exchange, String response, String responseCode)
			throws UnsupportedEncodingException {
		// 将
		ByteArrayInputStream stream = null;
		// 将java类转换成json
		// response2DB=customerMapper.writeValueAsString(commonExceptionInfo);
		// 将java类转换成xml
		byte[] buffer = response.getBytes("UTF-8");
		stream = new ByteArrayInputStream(buffer);
		exchange.getOut().getHeaders().put(Exchange.CONTENT_LENGTH, buffer.length + "");
		if (responseCode != null && responseCode.matches("^[1-5][0-1][0-9]$")) {
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, Integer.parseInt(responseCode));
		}
		exchange.getOut().setHeader(ExpressConstant.EXPRESS_RESUTLCODE, 0);
		exchange.getOut().setBody(stream);
	}

	private String fromMessage(CommonExceptionInfo result) {
		ObjectFactory objectfactory = new ObjectFactory();
		JAXBElement<CommonExceptionInfo> jaxbRetieveResponse = objectfactory.createCommonExceptionInfo(result);
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(CommonExceptionInfo.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.marshal(jaxbRetieveResponse, stringWriter);
		} catch (PropertyException e) {
			throw new ESBConvertException("序列化CommonExceptionInfo时失败！", e);
		} catch (FactoryConfigurationError e) {
			throw new ESBConvertException("序列化CommonExceptionInfo时失败！", e);
		} catch (JAXBException e) {
			throw new ESBConvertException("序列化CommonExceptionInfo时失败！", e);
		}
		return stringWriter.getBuffer().toString();
	}

	private CommonExceptionInfo commonErrorToCommonExceptionInfo(CommonError error) {
		CommonExceptionInfo commonExceptionInfo = new CommonExceptionInfo();
		commonExceptionInfo.setExceptioncode(error.getExceptionCode());
		commonExceptionInfo.setExceptiontype(error.getExceptionType());
		commonExceptionInfo.setDetailedInfo(error.getDetailedInfo());
		commonExceptionInfo.setCreatedTime(error.getCreatedTime());
		commonExceptionInfo.setMessage(error.getMessage());
		return commonExceptionInfo;
	}

	private String exception2String(Exception e) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(byteArrayOutputStream);
		try {
			e.printStackTrace(writer);
			writer.flush();
			return byteArrayOutputStream.toString("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			LOG.error("failureProcessor read exception error", e1);
		} catch (Throwable e2) {
			LOG.error("failureProcessor read exception error", e2);
		} finally {
			try {
				writer.close();
			} catch (Exception e1) {
				// ignore
			}
		}
		return "";
	}

	public ObjectMapper getCustomerMapper() {
		return customerMapper;
	}

	public void setCustomerMapper(ObjectMapper customerMapper) {
		this.customerMapper = customerMapper;
	}

	public ISendMessageService getExceptionMessageService() {
		return exceptionMessageService;
	}

	public void setExceptionMessageService(ISendMessageService exceptionMessageService) {
		this.exceptionMessageService = exceptionMessageService;
	}

	public int getDefaultMaxLength() {
		return defaultMaxLength;
	}

	public void setDefaultMaxLength(int defaultMaxLength) {
		this.defaultMaxLength = defaultMaxLength;
	}

	public ISendMessageService getRsAuditLogService() {
		return rsAuditLogService;
	}

	public void setRsAuditLogService(ISendMessageService rsAuditLogService) {
		this.rsAuditLogService = rsAuditLogService;
	}

	public ICountContainer getEsbInterfaceConcurrentContainer() {
		return esbInterfaceConcurrentContainer;
	}

	public void setEsbInterfaceConcurrentContainer(ICountContainer esbInterfaceConcurrentContainer) {
		this.esbInterfaceConcurrentContainer = esbInterfaceConcurrentContainer;
	}

	public ICountContainer getEsbUserInterfaceConcurrentContainer() {
		return esbUserInterfaceConcurrentContainer;
	}

	public void setEsbUserInterfaceConcurrentContainer(ICountContainer esbUserInterfaceConcurrentContainer) {
		this.esbUserInterfaceConcurrentContainer = esbUserInterfaceConcurrentContainer;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

}
