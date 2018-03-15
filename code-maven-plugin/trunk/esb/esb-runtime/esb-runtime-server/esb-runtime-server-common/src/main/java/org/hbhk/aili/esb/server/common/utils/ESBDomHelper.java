package org.hbhk.aili.esb.server.common.utils;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.util.CastUtils;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.AuthInfo;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 帮助类，用于从exchange中获取ESBheader对象。.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午5:00:32
 */
public class ESBDomHelper {
	
	/**
	 * 从Exchange中解析出ESBHeader对象。.
	 * 
	 * @param exchange
	 *            the exchange
	 * @return the eSB header from exchange
	 * @author qiancheng
	 * @date 2012-12-20 下午5:00:53
	 */
	public static ESBHeader getESBHeaderFromExchange(Exchange exchange){
		List<SoapHeader> soapHeaders = null;
		soapHeaders = CastUtils.cast((List<?>) exchange.getIn().getHeader(
				Header.HEADER_LIST));
		Node esbNode = null;
		for(SoapHeader soapHeader :soapHeaders){
			Node node = (Node) soapHeader.getObject();
			if (ESBServiceConstant.ESB_HEADER.equals(node.getLocalName())) {
				esbNode = node;
				break;
			}
		}
		return getESBHeaderFromNode(esbNode);
	}
	
	/**
	 * Gets the eSB header from node.
	 * 
	 * @param esbNode
	 *            the esb node
	 * @return the eSB header from node
	 */
	public static ESBHeader getESBHeaderFromNode(Node esbNode){
		ESBHeader esbHeader = null;
		if(esbNode == null ||!esbNode.hasChildNodes()){
			return null;
		}
		NodeList list = esbNode.getChildNodes();
		esbHeader = new ESBHeader();
		AuthInfo authinfo = new AuthInfo();
		esbHeader.setAuthentication(authinfo);
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (ESBServiceConstant.ESB_SERVICE_CODE.equals(node.getLocalName())) {
				esbHeader.setEsbServiceCode(node.getTextContent());
			}else if(ESBServiceConstant.BACK_SERVICE_CODE.equals(node.getLocalName())){
				esbHeader.setBackServiceCode(node.getTextContent());
			}else if (ESBServiceConstant.REQUEST_ID.equals(node.getLocalName())) {
				esbHeader.setRequestId(node.getTextContent());
			}else if (ESBServiceConstant.RESPONSE_ID.equals(node.getLocalName())) {
				esbHeader.setResponseId(node.getTextContent());
			}else if (ESBServiceConstant.BUSINESSID.equals(node.getLocalName())){
				esbHeader.setBusinessId(node.getTextContent());
			}else if(ESBServiceConstant.BUSINESSDESC1.equals(node.getLocalName())){
				esbHeader.setBusinessDesc1(node.getTextContent());
			}else if(ESBServiceConstant.BUSINESSDESC2.equals(node.getLocalName())){
				esbHeader.setBusinessDesc2(node.getTextContent());
			}else if(ESBServiceConstant.BUSINESSDESC3.equals(node.getLocalName())){
				esbHeader.setBusinessDesc3(node.getTextContent());
			}else if (ESBServiceConstant.VERSION.equals(node.getLocalName())) {			
				esbHeader.setVersion(node.getTextContent());
			}else if (ESBServiceConstant.SOURCE_SYSTEM.equals(node.getLocalName())) {				
				esbHeader.setSourceSystem(node.getTextContent());
			}else if(ESBServiceConstant.TARGET_SYSTEM.equals(node.getLocalName())){
				esbHeader.setTargetSystem(node.getTextContent());
			}else if (ESBServiceConstant.USER_NAME.equals(node.getLocalName())) {					
				authinfo.setUsername(node.getTextContent());
			}else if (ESBServiceConstant.PASSWORD.equals(node.getLocalName())) {
				authinfo.setPassword(node.getTextContent());
			}else if(ESBServiceConstant.EXCHANGE_PATTERN.equals(node.getLocalName())){
				Integer arg=null;
				try{ 
					arg =Integer.valueOf(node.getTextContent());
				}
				catch(Exception e){
					//ignore
				}
				esbHeader.setExchangePattern(arg);
			}else if(ESBServiceConstant.MESSAGE_FORMATE.equals(node.getLocalName())){
				esbHeader.setMessageFormat(node.getTextContent());
			}else if(ESBServiceConstant.RESULT_CODE.equals(node.getLocalName())){
				Integer arg=null;
				try{ 
					arg =Integer.valueOf(node.getTextContent());
				}
				catch(Exception e){
					//ignore
				}
				esbHeader.setResultCode(arg);
			}
		}
		return esbHeader;
	}
	/**
	 * 在子节点中根据名字查找Element
	 * fuanction（方法详细描述说明、方法参数的具体涵义）
	 * @author qiancheng
	 * @param 
	 * @date 2013-4-11 上午9:37:17
	 * @return
	 */
	public static Element findNodeByLocalName(Element parent ,String localName){
		if(parent ==null ||localName ==null ){
			return null;
		}
		Element target = null;
		NodeList list = parent.getChildNodes();
		for(int i=0;i<list.getLength();i++){
			Node child =list.item(i);
			if(localName.equals(child.getLocalName()) && child.getNodeType() == Node.ELEMENT_NODE){
				target = (Element)child;
				break;
			}
		}
		return target;
	}
	
}
