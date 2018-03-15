package org.hbhk.aili.esb.server.foss.csair;

import java.util.UUID;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.helpers.DOMUtils;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.AuthInfo;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 帮助类，生成esbHeader, 使用方法： 客户端 1、在业务处理方法中使用setClientHeaderConfig 设置HeaderConfig；
 * 2、在客户端拦截器类中使用createClientOutHeader 方法创建header； 服务端
 * 1、在消息进入拦截器中使用processServerInHeader处理soapHeader；
 * 2、在消息出去拦截器中使用createServerOutHeader方法创建outHeader;.
 *  @author songshishuai
 *  @date 2013-2-20 下午6:20:00
 */
public class WSHeaderHelper {

	/** header 命名空间. */
	private final static String HEADER_NS = "http://www.deppon.com/esb/header";

	/**
	 * 创建客户端out header.
	 * 
	 * @return the header
	 */
	public static SoapHeader createClientOutHeader(ESBHeader esbHeader) {
		// 生成header
		QName qName = new QName(ESBServiceConstant.ESB_HEADER);
		Document doc = DOMUtils.createDocument();
		Element root = doc.createElementNS(HEADER_NS, ESBServiceConstant.ESB_HEADER);
		// 必填 参数不填写则抛异常。
		root.appendChild(createElement(ESBServiceConstant.VERSION, esbHeader.getVersion(), doc));
		if (isNull(esbHeader.getBusinessId())) {
			root.appendChild(createElement(ESBServiceConstant.BUSINESSID, esbHeader.getBusinessId(), doc));
		}
		if (isNull(esbHeader.getBusinessDesc1())) {
			root.appendChild(createElement(ESBServiceConstant.BUSINESSDESC1, esbHeader.getBusinessDesc1(), doc));
		}
		if (isNull(esbHeader.getBusinessDesc2())) {
			root.appendChild(createElement(ESBServiceConstant.BUSINESSDESC2, esbHeader.getBusinessDesc3(), doc));
		}
		if (isNull(esbHeader.getBusinessDesc3())) {
			root.appendChild(createElement(ESBServiceConstant.BUSINESSDESC3, esbHeader.getBusinessDesc3(), doc));
		}
		root.appendChild(createElement(ESBServiceConstant.REQUEST_ID, UUID.randomUUID().toString(), doc));
		root.appendChild(createElement(ESBServiceConstant.SOURCE_SYSTEM, esbHeader.getSourceSystem(), doc));
		root.appendChild(createElement(ESBServiceConstant.ESB_SERVICE_CODE, esbHeader.getEsbServiceCode(), doc));
		root.appendChild(createElement(ESBServiceConstant.MESSAGE_FORMATE, esbHeader.getMessageFormat(), doc));
		root.appendChild(createElement(ESBServiceConstant.EXCHANGE_PATTERN, esbHeader.getExchangePattern() + "", doc));
		if (null != esbHeader.getAuthentication()) {
			AuthInfo auth = esbHeader.getAuthentication();
			if (auth.getUsername() != null && !"".equals(auth.getUsername()) && auth.getPassword() != null
					&& !"".equals(auth.getPassword())) {
				Element element = doc.createElementNS("", ESBServiceConstant.AUTHENTICATION);
				element.appendChild(createElement(ESBServiceConstant.USER_NAME, auth.getUsername(), doc));
				element.appendChild(createElement(ESBServiceConstant.PASSWORD, auth.getPassword(), doc));
				root.appendChild(element);
			}
		}
		SoapHeader header = new SoapHeader(qName, root);
		return header;
	}

	/**
	 * 判断是否空值，若为空值，则返回false，若不为空值则返回true。.
	 * 
	 * @param arg
	 *            the arg
	 * @return true, if is null
	 */
	private static boolean isNull(String arg) {
		boolean b = false;
		if (!"".equals(arg) && arg != null) {
			b = true;
		}
		return b;
	}

	/**
	 * 创建Element.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @param doc
	 *            the doc
	 * @return the element
	 */
	protected static Element createElement(String key, String value, Document doc) {
		Element element = doc.createElementNS("", key);
		element.setTextContent(value);
		return element;
	}
}
