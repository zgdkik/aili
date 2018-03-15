package org.hbhk.aili.esb.server.common.processor;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.interceptor.Fault;
import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;
import org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.entity.ObjectFactory;
import org.hbhk.aili.esb.server.common.exception.ESBConvertException;
import org.hbhk.aili.esb.server.common.exception.ESBDestinationNotFoundException;
import org.hbhk.aili.esb.server.common.exception.ESBSoapHeaderCheckException;
import org.hbhk.aili.esb.server.common.jmsservice.ISendMessageService;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 负责处理webservice异常.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午5:02:47
 */
public class FailureProcessor implements Processor{
	private static final Logger LOGGER = Logger.getLogger(FailureProcessor.class);
	
	/** COMMONEXCEPTION. */
	private static final String COMMONEXCEPTION = "commonExceptionInfo";
	
	/** The Constant EXCPETION_CODE. */
	private static final String EXCPETION_CODE = "exceptioncode";
	
	/** The Constant EXCEPTION_TYPE. */
	private static final String EXCEPTION_TYPE="exceptiontype";
	
	/** The Constant CREATETIME. */
	private static final String CREATETIME ="createdTime";
	
	/** The Constant DETAILINFO. */
	private static final String DETAILINFO ="detailedInfo";
	
	/** 500错误返回码. */
	private static final int HTTP_RESPONSE_CODE =500;
	
	private ISendMessageService wsExceptionMessageService;
	/**
	 * fault QName
	 */
	private static QName FAULT_CODE_BACKENDPOINT = new QName(
			"http://cxf.apache.org/faultcode", "backEndpoint");
	
	private IServiceConfigLoader configLoader;

	/**
	 * 当fromEndpoint
	 * 为cxf；在路由出现异常时，camel默认处理方式是尝试将exchange.getIn().getBody()作为返回值;
	 * 需要我们手动将异常信息返回给客户端。具体做法是获取异常，并设置到exhange.getOut()
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws Exception
	 *             the exception
	 * @author qiancheng
	 * @date 2012-12-20 下午5:06:35
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		Exception ex = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
		exchange.setException(ex);
		CommonExceptionInfo info = null;
		if(ex instanceof ESBSoapHeaderCheckException){
			ESBSoapHeaderCheckException e = (ESBSoapHeaderCheckException) ex;
			LOGGER.error("ESBSoapHeaderCheckException:" + e.getMessage());
			info = new CommonExceptionInfo();
			info.setExceptiontype(ESBServiceConstant.ESBHEADER_EXCEPTION);
			info.setCreatedTime(new Date());
			info.setMessage(e.getMessage());
			info.setExceptioncode(e.getErrorCode());
			info.setDetailedInfo(exception2String(e));
		}else if(ex instanceof ESBDestinationNotFoundException){
			ESBDestinationNotFoundException e = (ESBDestinationNotFoundException) ex;
			LOGGER.error("ESBDestinationNotFoundException:" + e.getMessage());
			info = new CommonExceptionInfo();
			info.setExceptiontype(ESBServiceConstant.ESBHEADER_EXCEPTION);
			info.setCreatedTime(new Date());
			info.setMessage(e.getMessage());
			info.setExceptioncode(ESBServiceConstant.ESBHEADER_EXCEPTION);
			info.setDetailedInfo(exception2String(e));
		}else if(ex instanceof SoapFault){
			SoapFault fault = (SoapFault)ex;
			fault.getMessage();
			
			Element el = fault.getDetail();
			Node node = null;
			if(el != null){
				//寻找CommonException节点。
				NodeList list = el.getChildNodes();
				for(int i =0;i<list.getLength();i++){
					if(COMMONEXCEPTION.equals(list.item(i).getLocalName())){
						node = list.item(i);
					}
				}
			}
			if(node != null){
				info = unmarshallCommonException(node);
				info.setExceptiontype(ESBServiceConstant.BUSINESS_EXCEPTION);
			}
		}else{
			info = new CommonExceptionInfo();
			info.setCreatedTime(new Date());
			info.setMessage(ex.getClass().getSimpleName()+":"+ex.getMessage());
			info.setExceptioncode(ESBServiceConstant.SYSTEM_EXCEPTION);
			info.setExceptiontype(ESBServiceConstant.SYSTEM_EXCEPTION);
			info.setDetailedInfo(exception2String(ex));
			
		}
		try {
			ESBHeader esbHeader = (ESBHeader)exchange.getIn().getHeader(ESBServiceConstant.ESB_HEADER);
			String exMsg = fromMessage(info);
			if (!(ex instanceof ESBSoapHeaderCheckException) &&needException(esbHeader.getEsbServiceCode())) {
				wsExceptionMessageService.send(esbHeader,exMsg);
			}
			//表明异常已处理过
			exchange.setProperty(ESBServiceConstant.EXCEPTION_PROCCESSED, true);
		} catch (Exception e) {
			LOGGER.error("exception occur when send commonExceptionInfo to exception QUEUE :" + e.getMessage());
		}
		//将faultCode message 修改一下
		if(ex instanceof Fault){
			((Fault)ex).setFaultCode(FAULT_CODE_BACKENDPOINT);
			((Fault)ex).setMessage("backEndpoint:"+ex.getMessage());
		}
		// MESSAGE模式下抛异常,必须设置设responseCode 为500，并且将Fault设置到Exchange outMessage中才能抛给客户端SoapFault消息。
		exchange.getOut().setHeader(org.apache.cxf.message.Message.RESPONSE_CODE, Integer.valueOf(HTTP_RESPONSE_CODE));
		exchange.getOut().setFault(true);
		exchange.getOut().setBody(ex);
	}
	
	private boolean needException(String code){
		if(code == null || "".equals("")){
			return false;
		}
		ESBRuntimeConfiguration config = configLoader.get(code).get(0);
		if(config.isNeedExpn()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String exception2String(Exception e){
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(byteArrayOutputStream);
		try {
			e.printStackTrace(writer);
			writer.flush();
			return byteArrayOutputStream.toString("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			LOGGER.error("failureProcessor read exception error",e1);
		}catch (Throwable e2) {
			LOGGER.error("failureProcessor read exception error",e2);
		}finally{
			try {
				writer.close();
			} catch (Exception e1) {
				//ignore
			}
		}
		return "";
	}
	
	public static void main(String[] args) {
		FailureProcessor processor = new FailureProcessor();
		System.out.println(processor.exception2String(new RuntimeException("adfasdfasdf",new RuntimeException("asdfasdfasfd"))));
	}
	
	
	
	/**
	 * 从map对象中获取值，返回ESBHeader对象.
	 * 
	 * @param header
	 *            the header
	 * @return the eSB header
	 * @author qiancheng
	 * @date 2012-12-20 下午5:05:45
	 */
	public ESBHeader fromMapToESBHeader(Map<String,Object> header){
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setBusinessId(processString(header.get(ESBServiceConstant.BUSINESSID)));
		esbHeader.setEsbServiceCode((String)header.get(ESBServiceConstant.ESB_SERVICE_CODE));
		esbHeader.setRequestId((String)header.get(ESBServiceConstant.REQUEST_ID));
		esbHeader.setResponseId(processString(header.get(ESBServiceConstant.RESPONSE_ID)));
		esbHeader.setSourceSystem((String)header.get(ESBServiceConstant.SOURCE_SYSTEM));
		esbHeader.setTargetSystem(processString(header.get(ESBServiceConstant.TARGET_SYSTEM)));
		return esbHeader;
	}
	
	/**
	 * 字符串控制判断：如果对象为null则返回""；.
	 * 
	 * @param obj
	 *            the obj
	 * @return the string
	 */
	private String processString(Object obj){
		String arg ="";
		if(obj !=null){
			arg =(String)obj;
		}
		return arg;
	}

	/**
	 * 解析CommonExceptionInfo.
	 * 
	 * @param el
	 *            the el
	 * @return the common exception info
	 * @author qiancheng
	 * @date 2012-12-20 下午5:04:53
	 */
	public CommonExceptionInfo unmarshallCommonException(Node el){
		CommonExceptionInfo  info = new CommonExceptionInfo();
		if(el != null && el.hasChildNodes()){
			Node node = el.getFirstChild();
			while(node != null){
				if(EXCPETION_CODE.equals(node.getLocalName())){
					info.setExceptioncode(node.getTextContent());
				}else if(EXCEPTION_TYPE.equals(node.getLocalName())){
					info.setExceptiontype(node.getTextContent());
				}else if(CREATETIME.equals(node.getLocalName())){
					String str = node.getTextContent();
					Date date = null;
					try {
						date=new Date(Long.getLong(str));
					} catch (Exception e) {
						//ignore
					}
					info.setCreatedTime(date);
				}
				else if(DETAILINFO.equals(node.getLocalName())){
					info.setDetailedInfo(node.getTextContent());
				}
				node = node.getNextSibling();
			}
		}
		return info;
	}
	
	public String fromMessage(Object message) throws ESBConvertException {
		CommonExceptionInfo result = (CommonExceptionInfo) message;
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

	public ISendMessageService getWsExceptionMessageService() {
		return wsExceptionMessageService;
	}

	public void setWsExceptionMessageService(
			ISendMessageService wsExceptionMessageService) {
		this.wsExceptionMessageService = wsExceptionMessageService;
	}


	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}


	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}
	
}
