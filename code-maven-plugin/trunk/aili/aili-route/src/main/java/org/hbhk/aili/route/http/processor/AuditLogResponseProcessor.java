package org.hbhk.aili.route.http.processor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.route.common.exception.AgentExceptionConstant;
import org.hbhk.aili.route.common.trans.CommonError2CommonExceptionTrans;
import org.hbhk.aili.route.http.bean.CommonError;
import org.hbhk.aili.route.jms.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.common.constant.ExpressConstant;
import org.hbhk.aili.route.jms.server.common.CommonExceptionInfo;
import org.hbhk.aili.route.jms.server.common.ESBHeader;
import org.hbhk.aili.route.jms.server.common.config.IServiceConfigLoader;
import org.hbhk.aili.route.jms.server.common.jmsservice.ISendMessageService;
import org.hbhk.aili.route.jms.server.common.log.audit.AuditInfo;
import org.hbhk.aili.route.jms.server.common.transformer.CommonExceptionInfoTrans;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuditLogResponseProcessor implements Processor{
	/** 保存审计日志服务. */
	private ISendMessageService rsAuditLogService;
	
	private IServiceConfigLoader configLoader;

	private ISendMessageService exceptionMessageService;
	
	private int defaultBufferSize =1024;
	private String defaultCharset="UTF-8";
	
	private  ObjectMapper customerMapper ; 
	
	private static  CommonExceptionInfoTrans  commonExceptionInfoTrans = new CommonExceptionInfoTrans();
	/**
	 * 记录请求和响应消息
	 * 当ResultCode=0时，请将响应消息记录到异常表一份（如果是系统异常，请将系统异常记录到数据库，给落地配返回未知异常错误）;
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		
		InputStream  stream = exchange.getIn().getBody(InputStream.class);	
		String responseBody = inputStream2String(stream,defaultBufferSize,defaultCharset);
		stream.reset();
		
		List<AuditInfo> list = new ArrayList<AuditInfo>();
		AuditInfo request = new AuditInfo();
		request.setBody((String)exchange.getProperty(ESBServiceConstant.REQUEST_SOAP_BODY));
		ESBHeader header = ExpressHeaderUtil.getHeaderFromHeaders(exchange);
		request.setHeader(header);
		
		AuditInfo response = new AuditInfo();
		ESBHeader responseHeader = new ESBHeader();
		BeanUtils.copyProperties(header, responseHeader);
		responseHeader = ExpressHeaderUtil.getResponseHeader(exchange, responseHeader);
		response.setBody(responseBody);
		response.setHeader(responseHeader);
		
		list.add(request);
		list.add(response);
		//根据配置决定是否需要记录审计日志
		ESBRuntimeConfiguration config = configLoader.get(header.getEsbServiceCode()).get(0);
		if(config.isNeedAudit()){			
			rsAuditLogService.send(list);
		}
		
		String resultCode = (String)exchange.getIn().getHeader(ExpressConstant.EXPRESS_RESUTLCODE);
		
		//如果resultCode==null ,也把响应信息发送到异常队列；
		//如果返回信息里"0".equals(resultCode)（业务异常），会把这个记录发送到异常队列；
		if(resultCode == null){
			CommonExceptionInfo info = new CommonExceptionInfo();
			info.setCreatedTime(new Date());
			info.setDetailedInfo(responseBody);
			info.setExceptioncode(AgentExceptionConstant.UNKNOW);
			info.setExceptiontype(AgentExceptionConstant.TYPE_SYS);
			info.setMessage("response have no header named:ESB-ResultCode and httpResonseCode: "+exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
			if(config.isNeedExpn()){			
				exceptionMessageService.send(responseHeader, commonExceptionInfoTrans.fromMessage(info));
			}
			//给客户端返回CommonError json字符串
			CommonError error = new CommonError();
			error.setCreatedTime(new Date());
			error.setMessage("未知异常");
			error.setExceptionType(AgentExceptionConstant.TYPE_SYS);
			error.setExceptionCode(AgentExceptionConstant.UNKNOW);	
			String msg = customerMapper.writeValueAsString(error);
			byte[] buffer = msg.getBytes("UTF-8");
			stream = new ByteArrayInputStream(buffer);
			exchange.getOut().getHeaders().put(Exchange.CONTENT_LENGTH, buffer.length+"");
			exchange.getOut().setHeader(ExpressConstant.EXPRESS_RESUTLCODE, 0);
			exchange.getOut().setBody(stream);
		}else if("0".equals(resultCode)){
			CommonError error = customerMapper.readValue(responseBody, CommonError.class);
			CommonExceptionInfo info = CommonError2CommonExceptionTrans.trans(error);
			if(config.isNeedExpn()){
				exceptionMessageService.send(responseHeader, commonExceptionInfoTrans.fromMessage(info));				
			}
		}
		
		
		Map<String,Object> headers = exchange.getIn().getHeaders();
		headers.remove("X-Forwarded-For");
		headers.remove("X-Powered-By");
		headers.remove("breadcrumbId");
		
	}	

	private String inputStream2String(InputStream stream,int bufferSize,String charset)throws Exception{
		byte[] buffer = new byte[bufferSize];
		String str ="";
		while(true){
			int size = stream.read(buffer, 0, bufferSize);
			if(size ==-1){
				break;
			}
			str =str+new String(buffer,0,size,charset);			
		}
		return str;
	}
	
	public ISendMessageService getRsAuditLogService() {
		return rsAuditLogService;
	}
	public void setRsAuditLogService(ISendMessageService rsAuditLogService) {
		this.rsAuditLogService = rsAuditLogService;
	}
	

	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}
	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}
	public ISendMessageService getExceptionMessageService() {
		return exceptionMessageService;
	}
	public void setExceptionMessageService(
			ISendMessageService exceptionMessageService) {
		this.exceptionMessageService = exceptionMessageService;
	}
	public ObjectMapper getCustomerMapper() {
		return customerMapper;
	}
	public void setCustomerMapper(ObjectMapper customerMapper) {
		this.customerMapper = customerMapper;
	}
	
	
	
}
