/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.utils
 * FILE    NAME: HeaderUtil.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package org.hbhk.aili.esb.server.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.commons.lang.StringUtils;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;

/**
 * 
 * @author HuangHua
 * @date 2013-4-15 下午5:23:14
 */
public class HeaderUtil {

	public static Map<String, Object> esbHeader2Map(ESBHeader esbHeader) {
		if (esbHeader != null) {
			Map<String, Object> header = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(esbHeader.getEsbServiceCode())) {
				header.put(ESBServiceConstant.ESB_SERVICE_CODE,
						esbHeader.getEsbServiceCode());
			}
			if (StringUtils.isNotEmpty(esbHeader.getBackServiceCode())) {
				header.put(ESBServiceConstant.BACK_SERVICE_CODE,
						esbHeader.getBackServiceCode());
			}
			if (StringUtils.isNotEmpty(esbHeader.getVersion())) {
				header.put(ESBServiceConstant.VERSION, esbHeader.getVersion());
			}
			if (StringUtils.isNotEmpty(esbHeader.getRequestId())) {
				header.put(ESBServiceConstant.REQUEST_ID,
						esbHeader.getRequestId());
			}
			if (StringUtils.isNotEmpty(esbHeader.getResponseId())) {
				header.put(ESBServiceConstant.RESPONSE_ID,
						esbHeader.getResponseId());
			}
			if (StringUtils.isNotEmpty(esbHeader.getSourceSystem())) {
				header.put(ESBServiceConstant.SOURCE_SYSTEM,
						esbHeader.getSourceSystem());
			}
			if (StringUtils.isNotEmpty(esbHeader.getTargetSystem())) {
				header.put(ESBServiceConstant.TARGET_SYSTEM,
						esbHeader.getTargetSystem());
			}
			if (StringUtils.isNotEmpty(esbHeader.getMessageFormat())) {
				header.put(ESBServiceConstant.MESSAGE_FORMATE,
						esbHeader.getMessageFormat());
			}
			if (StringUtils.isNotEmpty(esbHeader.getBusinessId())) {
				header.put(ESBServiceConstant.BUSINESSID,
						esbHeader.getBusinessId());
			}
			if (StringUtils.isNotEmpty(esbHeader.getBusinessDesc1())) {
				header.put(ESBServiceConstant.BUSINESSDESC1,
						esbHeader.getBusinessDesc1());
			}
			if (StringUtils.isNotEmpty(esbHeader.getBusinessDesc2())) {
				header.put(ESBServiceConstant.BUSINESSDESC2,
						esbHeader.getBusinessDesc2());
			}
			if (StringUtils.isNotEmpty(esbHeader.getBusinessDesc3())) {
				header.put(ESBServiceConstant.BUSINESSDESC3,
						esbHeader.getBusinessDesc3());
			}
			if (esbHeader.getExchangePattern() != null) {
				header.put(ESBServiceConstant.EXCHANGE_PATTERN, esbHeader
						.getExchangePattern().intValue());
			}
			if (esbHeader.getResultCode() != null) {
				header.put(ESBServiceConstant.RESULT_CODE, esbHeader
						.getResultCode().intValue());
			}
			if (esbHeader.getAuthentication() != null) {
				header.put(ESBServiceConstant.USER_NAME, esbHeader
						.getAuthentication().getUsername());
				header.put(ESBServiceConstant.PASSWORD, esbHeader
						.getAuthentication().getPassword());
			}
			if (esbHeader.getSentSequence()!=null) {
				header.put(ESBServiceConstant.SENTSEQUENCE, esbHeader.getSentSequence());
			}
			return header;
		}
		return null;
	}

	public static void map2jmsProperties(Map<String, Object> map,
			Message message) throws JMSException {
		if (map == null) {
			message.setStringProperty(ESBServiceConstant.CONFORMITY,
					ESBServiceConstant.CONFORMITY);
			return;
		} else if (map.size() == 0) {
			message.setStringProperty(ESBServiceConstant.CONFORMITY,
					ESBServiceConstant.CONFORMITY);
			return;
		}
		Set<Entry<String, Object>> map1 = map.entrySet();
		for (Entry<String, Object> entry : map1) {
			message.setObjectProperty(entry.getKey(), entry.getValue());
		}
	}

}
