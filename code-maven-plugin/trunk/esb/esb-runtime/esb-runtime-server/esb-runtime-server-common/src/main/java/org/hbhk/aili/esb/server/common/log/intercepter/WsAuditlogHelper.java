package org.hbhk.aili.esb.server.common.log.intercepter;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.cxf.binding.soap.SoapVersion;
import org.apache.cxf.binding.soap.SoapVersionFactory;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.utils.ESBDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * 审计日志帮助类.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午4:51:54
 */
public class WsAuditlogHelper {
	
	/** logger. */
	private static final Logger LOGGER = Logger.getLogger(WsAuditlogHelper.class);
	
	/**
	 * 从xml文档中解析出ESBHeader对象.
	 * 
	 * @param xml
	 *            the xml
	 * @return the esb headerfrom xml
	 * @throws SAXException
	 *             the sAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @author qiancheng
	 * @date 2012-12-20 下午6:55:12
	 */
	public static ESBHeader getEsbHeaderfromXml(String xml) throws SAXException, IOException, ParserConfigurationException{
		ESBHeader esbHeader = null;
		StringReader reader = null;
		try {
			//解析 soap报文
			reader = new StringReader(xml);
			Document doc = DOMUtils.readXml(reader);
			Element element = doc.getDocumentElement();
			String ns = doc.getDocumentElement().getNamespaceURI();
			SoapVersion soapVersion = SoapVersionFactory.getInstance().getSoapVersion(ns);
			QName header = soapVersion.getHeader();          
			List<Element> elemList = 
			    DOMUtils.findAllElementsByTagNameNS(element, 
			                                        header.getNamespaceURI(), 
			                                        header.getLocalPart());
			//在soap中遍历寻找esbHeader.
			Element esbEl = null;
			for (Element elem : elemList) {
			    Element hel = DOMUtils.getFirstElement(elem);
			    while(hel!=null){
			    	if(ESBServiceConstant.ESB_HEADER.equals(hel.getLocalName())){
			    		esbEl=hel;
			    		break;
			    	}
			    	hel = DOMUtils.getNextElement(hel);	       	 
			    }
			}
			
			esbHeader = null;
			if(esbEl != null){
				esbHeader = ESBDomHelper.getESBHeaderFromNode(esbEl);
			}
			else {
				LOGGER.warn("can not find element named["+ESBServiceConstant.ESB_HEADER+"] from soap");
			}
		} catch (Exception e) {
			LOGGER.error("error occur when parse xml" + e.getMessage(), e);
		}finally{
            try {
                reader.close();
            } catch (Exception ex) {
                //ignore
            	if(LOGGER.isDebugEnabled()){
            		LOGGER.info(ex.getMessage(), ex);
            	}
            }
		}
        return esbHeader;
	}
}
