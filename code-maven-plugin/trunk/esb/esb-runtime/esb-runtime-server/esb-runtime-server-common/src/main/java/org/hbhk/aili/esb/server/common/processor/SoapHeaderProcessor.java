package org.hbhk.aili.esb.server.common.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.CastUtils;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.message.Message;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.exception.ESBSoapHeaderCheckException;
import org.hbhk.aili.esb.server.common.filter.BlackListContainer;
import org.hbhk.aili.esb.server.common.jmsservice.ISendMessageService;
import org.hbhk.aili.esb.server.common.utils.ESBDomHelper;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * 验证esbHeader合法性，并把esbHeader放在exchange.in.header中,如果验证失败，要把消息发往失败队列
 * 
 * @author qiancheng
 * @date 2012-12-20 下午5:10:06
 */
public class SoapHeaderProcessor implements Processor {

	/** 设置接收超时时间 RECEIVE_TIMEOUT=170000ms */
	private final static long RECEIVE_TIMEOUT = 170000L;

	private final static String CXF_MESSAGE = "camelcxfmessage";
	/** The config loader. */
	private IServiceConfigLoader configLoader;

	/** 保存失败队列服务 */
	private ISendMessageService failMessageService;

	/**
	 * 保存审计日志服务
	 */
	private ISendMessageService wsAuditLogService;
	
	private BlackListContainer backList = new  BlackListContainer() ;

	/**
	 * 从exchange中解析esbHeader属性，并校验esbServiceCode；.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws Exception
	 *             the exception
	 * @author qiancheng
	 * @date 2012-12-20 下午5:10:36
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		List<SoapHeader> soapHeaders = null;
		soapHeaders = CastUtils.cast((List<?>) exchange.getIn().getHeader(Header.HEADER_LIST));
		// 如果没有找到esbHeader node节点，则抛异常。
		Node esbNode = null;
		ESBHeader esbHeader = null;
		for (SoapHeader soapHeader : soapHeaders) {
			Node node = (Node) soapHeader.getObject();
			if (ESBServiceConstant.ESB_HEADER.equals(node.getLocalName())) {
				esbNode = node;
				break;
			}
		}
		// 拿到WS请求审计报文
		SoapMessage message = (SoapMessage) exchange.getIn().getHeader(CXF_MESSAGE);
		String reqSoapBody = (String) message.getExchange().getInMessage().get(ESBServiceConstant.REQUEST_SOAP_BODY);
		if (esbNode == null) {
			failMessageService.send(null, reqSoapBody);
			throw new ESBSoapHeaderCheckException("checkEsbHeaderError: can not  find esbHeader from soap");
		}
		if (!esbNode.hasChildNodes()) {
			failMessageService.send(null, reqSoapBody);
			throw new ESBSoapHeaderCheckException(" checkEsbHeaderError:esbHeader have no child node");
		} else {
			esbHeader = ESBDomHelper.getESBHeaderFromNode(esbNode);
			// 校验esbHeader，校验失败则发送消息到失败队列，并抛出异常
			try {
				validateEsbHeader(esbHeader);
			} catch (Exception e) {
				failMessageService.send(esbHeader, reqSoapBody);
				throw new ESBSoapHeaderCheckException(e.getMessage(), e);
			}
			//黑名单过滤
			if(backList.exists(esbHeader.getEsbServiceCode())){
				throw new ESBSoapHeaderCheckException("该接口已被管理员加入黑名单，暂时不可访问");
			}
		
			exchange.getProperties().put(ESBServiceConstant.ESB_HEADER, esbHeader);
			// 添加backServiceCode、targetSystem
			ESBRuntimeConfiguration config = configLoader.get(esbHeader.getEsbServiceCode()).get(0);
			esbHeader.setTargetSystem(config.getTargetSystem());
			esbHeader.setBackServiceCode(config.getTargetServiceCode());
			exchange.getIn().setHeader(ESBServiceConstant.ESB_HEADER, esbHeader);
			Element backServiceCodeNode = ESBDomHelper.findNodeByLocalName((Element) esbNode,
					ESBServiceConstant.BACK_SERVICE_CODE);
			Element targetSystemNode = ESBDomHelper.findNodeByLocalName((Element) esbNode,
					ESBServiceConstant.TARGET_SYSTEM);
			if (backServiceCodeNode == null) {
				String nodeName = creatNodeName(esbNode.getPrefix(), ESBServiceConstant.BACK_SERVICE_CODE);
				Element el = esbNode.getOwnerDocument().createElement(nodeName);
				el.setTextContent(config.getTargetServiceCode());
				esbNode.appendChild(el);
			} else {
				backServiceCodeNode.setTextContent(config.getTargetServiceCode());
			}
			if (targetSystemNode == null) {
				String nodeName = creatNodeName(esbNode.getPrefix(), ESBServiceConstant.TARGET_SYSTEM);
				Element el = esbNode.getOwnerDocument().createElement(nodeName);
				el.setTextContent(config.getTargetSystem());
				esbNode.appendChild(el);
			} else {
				targetSystemNode.setTextContent(config.getTargetSystem());
			}
			// 添加服务编码、后端服务编码到Exchange properties中
			exchange.getProperties().put(ESBServiceConstant.ESB_SERVICE_CODE, esbHeader.getEsbServiceCode());
			exchange.getProperties().put(ESBServiceConstant.BACK_SERVICE_CODE, esbHeader.getBackServiceCode());
			// 交互模式为单向的接口，在此记录请求审计日志

			if (esbHeader.getExchangePattern() == 3) {
				wsAuditLogService.send(esbHeader, reqSoapBody);
			}
			// 特例，更新车辆在途信息接口是单向接口
			if ("ESB_GPS2ESB_UPDATE_VEHICLETRACK".equals(esbHeader.getEsbServiceCode())) {
				wsAuditLogService.send(esbHeader, reqSoapBody);
			}
			//设置超时时间，从服务配置缓存中获取超时配置，如果获取的值为null，则不予设置；后续可以考虑接受客户端传递超时时间；
			Long timeout = config.getTimeout();
			if(timeout !=null &&timeout>0){
				exchange.getProperties().put(Message.RECEIVE_TIMEOUT, timeout);
			}
			// 为了防止http-conf配置冲突，强制设置接收响应超时，设置包涵2个接口--占用认领编号，释放认领编号
/*			// 设置接收超时时间是170000ms(实际时间2分钟50秒)
			if ("ESB_FOSS2ESB_OBTAIN_NUMBER".equals(esbHeader.getEsbServiceCode())
					|| "ESB_FOSS2ESB_RELEASE_NUMBER".equals(esbHeader.getEsbServiceCode())) {
				exchange.getProperties().put(Message.RECEIVE_TIMEOUT, RECEIVE_TIMEOUT);
			}*/
		}
	}

	/**
	 * 创建新Element的名字。.
	 * 
	 * @param prefix
	 *            the prefix
	 * @param localName
	 *            the local name
	 * @return the string
	 * @author qiancheng
	 * @date 2012-12-20 下午5:11:49
	 */
	public String creatNodeName(String prefix, String localName) {
		String str = null;
		if (prefix == null) {
			str = localName;
		} else {
			str = prefix + ":" + localName;
		}
		return str;
	}

	/**
	 * getConfigLoader.
	 * 
	 * @return the config loader
	 * @author HuangHua
	 * @date 2012-12-25 下午3:16:10
	 */
	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	/**
	 * setConfigLoader.
	 * 
	 * @param configLoader
	 *            the new config loader
	 * @author HuangHua
	 * @date 2012-12-25 下午3:16:16
	 */
	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

	/**
	 * 
	 * 如果esbHeader属性值不满足要求，则抛异常
	 * 
	 * @author qiancheng
	 * @param
	 * @date 2013-1-12 下午5:30:40
	 * @return
	 */
	public void validateEsbHeader(ESBHeader header) throws Exception {
		if (header.getEsbServiceCode() == null || "".equals(header.getEsbServiceCode())) {
			throw new ESBSoapHeaderCheckException("checkEsbHeaderError:esbServiceCode [" + header.getEsbServiceCode()
					+ "] is illegal ");
		}
		List<ESBRuntimeConfiguration> list = configLoader.get(header.getEsbServiceCode());
		if (list == null || list.size() == 0) {
			throw new ESBSoapHeaderCheckException(
					"checkEsbHeaderError:can not find config info from ServiceConfigLoader with esbServiceCode:["
							+ header.getEsbServiceCode() + "]");
		}
		if (header.getRequestId() == null || "".equals(header.getRequestId())) {
			throw new ESBSoapHeaderCheckException("checkEsbHeaderError:requestId  [" + header.getRequestId()
					+ "] is illegal ");
		}
		if (header.getExchangePattern() == null || header.getExchangePattern() != 1 && header.getExchangePattern() != 2
				&& header.getExchangePattern() != 3) {
			throw new ESBSoapHeaderCheckException(
					"checkEsbHeaderError:exchangePattern value should be [1 or 2 or3],not["
							+ header.getExchangePattern() + "]");
		}
		if (header.getBusinessId() == null || "".equals(header.getBusinessId())) {
			throw new ESBSoapHeaderCheckException("checkEsbHeaderError:businessId [" + header.getBusinessId()
					+ "] is illegal ");
		}
		if (header.getVersion() == null || "".equals(header.getVersion())) {
			throw new ESBSoapHeaderCheckException("checkEsbHeaderError:version [" + header.getVersion()
					+ "] is illegal ");
		}
		if (!"SOAP".equals(header.getMessageFormat())) {
			throw new ESBSoapHeaderCheckException("checkEsbHeaderError:messageFormat value should be soap,not ["
					+ header.getMessageFormat() + "]  ");
		}
		if (null == header.getSourceSystem() || "".equals(header.getSourceSystem())) {
			throw new ESBSoapHeaderCheckException("checkEsbHeaderError:sourceSystem value [" + header.getSourceSystem()
					+ "] is illegal  ");
		}
	}

	public ISendMessageService getFailMessageService() {
		return failMessageService;
	}

	public void setFailMessageService(ISendMessageService failMessageService) {
		this.failMessageService = failMessageService;
	}

	public ISendMessageService getWsAuditLogService() {
		return wsAuditLogService;
	}

	public void setWsAuditLogService(ISendMessageService wsAuditLogService) {
		this.wsAuditLogService = wsAuditLogService;
	}

}
