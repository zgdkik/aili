package org.hbhk.aili.esb.server.common.log.intercepter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.camel.util.CastUtils;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.exception.ESBSoapHeaderCheckException;
import org.hbhk.aili.esb.server.common.jmsservice.ISendMessageService;
import org.hbhk.aili.esb.server.common.log.audit.AuditInfo;
import org.hbhk.aili.esb.server.common.utils.ESBDomHelper;
import org.springframework.beans.BeanUtils;
import org.w3c.dom.Node;

/**
 * 审计日志拦截器，用于拦截webservice响应报文，并发送到审计日志队列.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午4:47:39
 */
public class AuditLogOutIntercepter extends AbstractPhaseInterceptor<Message> {

	/** logger. */
	private static final Logger LOGGER = Logger
			.getLogger(AuditLogOutIntercepter.class);

	/** 保存审计日志服务. */
	private ISendMessageService wsAuditLogService;

	/** 保存失败队列服务 */
	private ISendMessageService failMessageService;

	private IServiceConfigLoader configLoader;

	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

	public ISendMessageService getFailMessageService() {
		return failMessageService;
	}

	public void setFailMessageService(ISendMessageService failMessageService) {
		this.failMessageService = failMessageService;
	}

	/**
	 * Instantiates a new audit log out intercepter.
	 */
	public AuditLogOutIntercepter() {
		super(Phase.PRE_STREAM);
	}

	/**
	 * 
	 * TODO 如果解析不到会返回null值
	 * @author davidcun
	 * @date 2013-6-4 下午10:18:29
	 */
	private ESBHeader getESBHeaderFrom(SoapMessage message) {
		List<SoapHeader> soapHeaders = null;
		ESBHeader esbHeader = null;
		soapHeaders = CastUtils.cast((List<?>) message.get(Header.HEADER_LIST));
		if(soapHeaders == null){
			//return esbHeader;
			throw new ESBSoapHeaderCheckException("can not find soapHeader from response");
		}
		Node esbNode = null;
		for (SoapHeader soapHeader : soapHeaders) {
			Node node = (Node) soapHeader.getObject();
			if (ESBServiceConstant.ESB_HEADER.equals(node.getLocalName())) {
				esbNode = node;
				break;
			}
		}
//		if(esbNode == null){
//			throw new ESBSoapHeaderCheckException("can not find esbHeader from response");
//		}
		if (esbNode!=null) {
			esbHeader = ESBDomHelper.getESBHeaderFromNode(esbNode);
		}
		return esbHeader;
	}

	/**
	 * 重写handlerMessage ，获取审计报文，并发送到审计日志队列.
	 * 
	 * @param message
	 *            the message
	 * @author qiancheng
	 * @date 2012-12-20 下午4:49:06
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	 */
	public void handleMessage(Message message) {
		final OutputStream os = message.getContent(OutputStream.class);
		if (os == null) {
			return;
		}
		// Write the output while caching it for the log message
		final CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(
				os);
		message.setContent(OutputStream.class, newOut);
		newOut.registerCallback(new LoggingCallback(message, os));

	}

	class LoggingCallback implements CachedOutputStreamCallback {
		private final Message message;
		private final OutputStream origStream;

		public LoggingCallback(final Message msg, final OutputStream os) {
			this.message = msg;
			this.origStream = os;
		}

		public void onFlush(CachedOutputStream cos) {

		}

		/**
		 * @description:在将响应的IO流传递给客户端之前，将IO流的内容给读取出来，这样就能获取到完整的
		 * 响应报文，另外IO流只能读取一次，所以需要将缓存着的IO流设置到message中，否则会报IO异常滴。
		 * 这个类是参照LoggingOutInterceptor写的。目前这种方法缺点很明显，扩展性很差，不通用
		 * TODO：可以换一种思路的：不在CXF的拦截器层写这些东西，
		 *        直接重写CxfSerlvet，定义HttpServletRequest,和HttpServletResponse的包装类，
		 *        在包装类中将请求的IO流和响应的IO流缓存起来，并提供get的方法，即可获取到响应的流信息。
		 *        
		 * @author 054839
		 * @date 2014年10月28日 下午4:56:50
		 * @param cos
		 * @return
		 * @see org.apache.cxf.io.CachedOutputStreamCallback#onClose(org.apache.cxf.io.CachedOutputStream)
		 */
		public void onClose(CachedOutputStream cos) {
			// 如果调用出现异常，只记录请求审计日志
			InputStream in = null;
			try {
				// 获取完整报文
				in = cos.getInputStream();
				String rsp = IOUtils.toString(in);
				LOGGER.debug("audit soapMessage: \n" + rsp);
				String req = (String) message.getExchange().getInMessage()
						.get(ESBServiceConstant.REQUEST_SOAP_BODY);
				//如果调用正常，设置在exchange property中的esbHeader会在message属性中出现。 

				AuditInfo requestAudit = new AuditInfo();
				AuditInfo responseAudit = new AuditInfo();
				ESBHeader responseHeader = null;
				ESBHeader requestHeader = null;
				//当调用出现fault时，需要由ESB来创建响应消息的esbHeader（服务端抛异常的时候，只会给esb一个fault消息，
				//不会返回esbHeader的~~！）,并且当出现异常时，无法从message中获取exchange property中设置好的esbHeader。
				//故此时需要解析请求soap报文获取esbHeader
				if(message.getExchange().getOutFaultMessage() !=null){
					//String request = (String)message.getExchange().getInMessage().get(ESBServiceConstant.REQUEST_SOAP_BODY);
    				SoapMessage msg = (SoapMessage)message.getExchange().getInMessage();
    				requestHeader = getESBHeaderFrom(msg);
    				if (requestHeader!=null) {
    					List<ESBRuntimeConfiguration> list = configLoader.get(requestHeader.getEsbServiceCode());
    					if (list!=null && list.get(0)!=null) {
    						responseHeader = new ESBHeader();
    						BeanUtils.copyProperties(requestHeader,responseHeader);
    						responseHeader.setTargetSystem(list.get(0).getTargetSystem());
    						responseHeader.setBackServiceCode(list.get(0).getTargetServiceCode());
    						responseHeader.setResultCode(0); 
    					}else{
    						requestHeader=null;
    					}
					}
				}else {
					responseHeader = new ESBHeader();
					responseHeader = (ESBHeader) message
							.get(ESBServiceConstant.ESB_HEADER);
					if(responseHeader.getResponseId() ==null){
						responseHeader.setResponseId(UUID.randomUUID().toString());
					}
					//if(message.getExchange().getOutFaultMessage() != null){
					responseHeader.setResultCode(1);
					//}
					requestHeader = new ESBHeader();
					BeanUtils.copyProperties(responseHeader, requestHeader);
					requestHeader.setTargetSystem(null);
					requestHeader.setBackServiceCode(null);
					requestHeader.setResponseId(null);
					requestHeader.setResultCode(null);
				}
				//判断是否需要记录审计日志
				String code = requestHeader.getEsbServiceCode();
				ESBRuntimeConfiguration config = configLoader.get(code).get(0);
				if(config.isNeedAudit()){
					List<AuditInfo> list = new ArrayList<AuditInfo>();
					if (requestHeader!=null) {
						requestAudit.setBody(req);
						requestAudit.setHeader(requestHeader);
						list.add(requestAudit);
					}
					if (responseHeader!=null) {
						responseAudit.setBody(rsp);
						responseAudit.setHeader(responseHeader);
						list.add(responseAudit);
					}
					if (list.size()>0) {
						wsAuditLogService.send(list);
					}
				}
				
				// 把流写回到message中
				message.setContent(OutputStream.class, origStream);
				
			} 
			catch (Exception e) {
				LOGGER.error("Error when send audit soap  to  queue ."
						+ "\n" + e.getMessage(), e);
				if(message!=null&& message.getExchange()!=null&&message.getExchange().getInMessage()!=null){
					LOGGER.error("Error when send audit soap  to  queue message detail :"
							+ "\n" + message.getExchange().getInMessage());
				}
			}  
			finally {
				try {
					in.close();
				} catch (IOException e) {
					LOGGER.error("关闭流失败", e);
				}
				
				try {
					cos.lockOutputStream();
					cos.resetOut(null, false);
				} catch (Exception ex) {
					// ignore
					LOGGER.error("com.deppon.esb.server.common.log.intercepter.AuditLogOutIntercepter$LoggingCallback.onClose",ex);
				}
			}
		}
	}

	public ISendMessageService getWsAuditLogService() {
		return wsAuditLogService;
	}

	public void setWsAuditLogService(ISendMessageService wsAuditLogService) {
		this.wsAuditLogService = wsAuditLogService;
	}
}
