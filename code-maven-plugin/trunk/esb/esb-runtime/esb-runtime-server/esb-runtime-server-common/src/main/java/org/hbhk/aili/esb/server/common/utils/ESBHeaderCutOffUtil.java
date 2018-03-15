package org.hbhk.aili.esb.server.common.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;

/**
 * 
 * 限制bizd,bizDesc字段长度
 * 
 * @author qiancheng
 * @date 2013-6-28 上午10:57:55
 */
public class ESBHeaderCutOffUtil {
	
	private static Log log = LogFactory.getLog(ESBHeaderCutOffUtil.class); 
	private static int maxBizidLength = 300;
	private static int maxBizDescLength = 300;
	private static int maxReqidLength = 80;
	private static int maxResidLength = 80;

	public static void truncEsbHeaderProperties(ESBHeader header) {
		if (header == null)
			return;
		header.setBusinessId(getLimitString(header.getBusinessId(),
				maxBizidLength));
		header.setBusinessDesc1(getLimitString(header.getBusinessDesc1(),
				maxBizDescLength));
		header.setBusinessDesc2(getLimitString(header.getBusinessDesc2(),
				maxBizDescLength));
		header.setBusinessDesc3(getLimitString(header.getBusinessDesc3(),
				maxBizDescLength));
		header.setRequestId(getLimitString(header.getRequestId(),
				maxReqidLength));
		header.setResponseId(getLimitString(header.getResponseId(),
				maxResidLength));
	}

	public static void truncEsbHeaderProperties(Map<String, Object> header) {
		if (header == null) {
			return;
		}
		if (header.containsKey(ESBServiceConstant.BUSINESSID)) {
			header.put(
					ESBServiceConstant.BUSINESSID,
					getLimitString(header.get(ESBServiceConstant.BUSINESSID),
							maxBizidLength));
		}
		if (header.containsKey(ESBServiceConstant.BUSINESSDESC1)) {
			header.put(
					ESBServiceConstant.BUSINESSDESC1,
					getLimitString(
							header.get(ESBServiceConstant.BUSINESSDESC1),
							maxBizDescLength));
		}
		if (header.containsKey(ESBServiceConstant.BUSINESSDESC2)) {
			header.put(
					ESBServiceConstant.BUSINESSDESC2,
					getLimitString(
							header.get(ESBServiceConstant.BUSINESSDESC2),
							maxBizDescLength));
		}
		if (header.containsKey(ESBServiceConstant.BUSINESSDESC3)) {
			header.put(
					ESBServiceConstant.BUSINESSDESC3,
					getLimitString(
							header.get(ESBServiceConstant.BUSINESSDESC3),
							maxBizDescLength));
		}
		if (header.containsKey(ESBServiceConstant.REQUEST_ID)) {
			header.put(
					ESBServiceConstant.REQUEST_ID,
					getLimitString(header.get(ESBServiceConstant.REQUEST_ID),
							maxReqidLength));
		}
		if (header.containsKey(ESBServiceConstant.RESPONSE_ID)) {
			header.put(
					ESBServiceConstant.RESPONSE_ID,
					getLimitString(header.get(ESBServiceConstant.RESPONSE_ID),
							maxResidLength));
		}
	}

	public static String getLimitString(String str, int maxLength) {
		if (str != null && str.length() > maxLength) {
			return str.substring(0, maxLength);
		}
		return str;
	}

	public static String getLimitString(Object obj, int maxLength) {
		if (obj == null) {
			return null;
		}
		if (!(obj instanceof String)) {
			throw new IllegalArgumentException("arg illegal");
		}
		return getLimitString((String) obj, maxLength);
	}

	@SuppressWarnings("unchecked")
	public static Message truncMessageProperties(Message message) {
		try {
			Enumeration<String> em = message.getPropertyNames();
			Map<String, Object> property = new HashMap<String, Object>();
			while (em.hasMoreElements()) {
				String name = em.nextElement();
				property.put(name, message.getObjectProperty(name));
			}
			
			truncEsbHeaderProperties(property);
			
			message.clearProperties();
			if (log.isInfoEnabled()) {
				log.info("----------------start set properties--------------");
			}
			
			for (Entry<String, Object> entry : property.entrySet()) {
				
				message.setObjectProperty(entry.getKey(), entry.getValue());
				if (log.isInfoEnabled()) {
					log.info(entry.getKey()+":"+entry.getValue());
				}
			}
			
			if (log.isInfoEnabled()) {
				log.info("---------------end set properties------------------");
			}

		} catch (JMSException e) {
			e.printStackTrace();
			//TODO
		}
		return message;
	}

}
