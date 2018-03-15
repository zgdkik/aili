/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.utils
 * FILE    NAME: HeaderUtil.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.commons.lang.StringUtils;

import com.deppon.esb.management.common.constant.ESBServiceConstant;
import com.deppon.esb.management.common.entity.jms.header.AuthInfo;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;



/**
 * 
 * @author HuangHua
 * @date 2013-4-15 下午5:23:14
 */
public class EsbHeaderUtil {

	public static Map<String, Object> esbHeader2Map(EsbHeader esbHeader) {
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
			truncEsbHeader(header);
			return header;
		}
		return null;
	}
	
	public static EsbHeader map2ESBHeader(Map<String,Object> map){
		if(map == null ||map.size()==0){
			return null;
		}
		EsbHeader  esbHeader = new EsbHeader();
		AuthInfo authInfo = new AuthInfo();
		if(map.containsKey(ESBServiceConstant.ESB_SERVICE_CODE)){
			esbHeader.setEsbServiceCode((String)map.get(ESBServiceConstant.ESB_SERVICE_CODE));
		}
		if(map.containsKey(ESBServiceConstant.BACK_SERVICE_CODE)){
			esbHeader.setBackServiceCode((String)map.get(ESBServiceConstant.BACK_SERVICE_CODE));
		}
		if(map.containsKey(ESBServiceConstant.SOURCE_SYSTEM)){
			esbHeader.setSourceSystem((String)map.get(ESBServiceConstant.SOURCE_SYSTEM));
		}
		if(map.containsKey(ESBServiceConstant.TARGET_SYSTEM)){
			esbHeader.setTargetSystem((String)map.get(ESBServiceConstant.TARGET_SYSTEM));
		}
		if(map.containsKey(ESBServiceConstant.REQUEST_ID)){
			String requestId = (String)map.get(ESBServiceConstant.REQUEST_ID);
			esbHeader.setRequestId(requestId);
		}
		if(map.containsKey(ESBServiceConstant.RESPONSE_ID)){
			String responseId = (String)map.get(ESBServiceConstant.RESPONSE_ID);
			esbHeader.setResponseId(responseId);
		}
		if(map.containsKey(ESBServiceConstant.BUSINESSID)){
			String str = (String)map.get(ESBServiceConstant.BUSINESSID);
			esbHeader.setBusinessId(str);
		}
		if(map.containsKey(ESBServiceConstant.BUSINESSDESC1)){
			String str = (String)map.get(ESBServiceConstant.BUSINESSDESC1);
			esbHeader.setBusinessDesc1(str);
		}
		if(map.containsKey(ESBServiceConstant.BUSINESSDESC2)){
			String str = (String)map.get(ESBServiceConstant.BUSINESSDESC2);
			esbHeader.setBusinessDesc2(str);
		}
		if(map.containsKey(ESBServiceConstant.BUSINESSDESC3)){
			String str = (String)map.get(ESBServiceConstant.BUSINESSDESC3);
			esbHeader.setBusinessDesc3(str);
		}
		if(map.containsKey(ESBServiceConstant.MESSAGE_FORMATE)){
			esbHeader.setMessageFormat((String)map.get(ESBServiceConstant.MESSAGE_FORMATE));
		}
		if(map.containsKey(ESBServiceConstant.VERSION)){
			esbHeader.setVersion((String)map.get(ESBServiceConstant.VERSION));
		}

		if(map.containsKey(ESBServiceConstant.USER_NAME)){
			authInfo.setUsername((String)map.get(ESBServiceConstant.USER_NAME));
		}
		if(map.containsKey(ESBServiceConstant.PASSWORD)){
			authInfo.setPassword((String)map.get(ESBServiceConstant.PASSWORD));
		}
		if(map.containsKey(ESBServiceConstant.SENTSEQUENCE)){
			Object obj = map.get(ESBServiceConstant.SENTSEQUENCE);
			Integer i = null;
			if(obj instanceof Integer){
				i =(Integer)obj;
			}
			else{
				i = Integer.valueOf((String)obj);
			}
			esbHeader.setSentSequence(i);
		}
		if(map.containsKey(ESBServiceConstant.RESULT_CODE)){
			Object obj = map.get(ESBServiceConstant.RESULT_CODE);
			Integer i = null;
			if(obj instanceof Integer){
				i =(Integer)obj;
			}
			else{
				i = Integer.valueOf((String)obj);
			}
			esbHeader.setResultCode(i);
		}
		if(map.containsKey(ESBServiceConstant.EXCHANGE_PATTERN)){
			Object obj = map.get(ESBServiceConstant.EXCHANGE_PATTERN);
			Integer i = null;
			if(obj instanceof Integer){
				i =(Integer)obj;
			}
			else{
				i = Integer.valueOf((String)obj);
			}
			esbHeader.setExchangePattern(i);
		}
		truncEsbHeader(esbHeader);
		return esbHeader;
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

	public static void truncEsbHeader(Map<String, Object> header){
		
		if (header == null) {
			return;
		}
		if (header.containsKey(ESBServiceConstant.BUSINESSID)) {
			header.put(
					ESBServiceConstant.BUSINESSID,
					getLimitString(header.get(ESBServiceConstant.BUSINESSID),
							ESBServiceConstant.BUSINESSID_MAX_LENGTH));
		}
		if (header.containsKey(ESBServiceConstant.BUSINESSDESC1)) {
			header.put(
					ESBServiceConstant.BUSINESSDESC1,
					getLimitString(
							header.get(ESBServiceConstant.BUSINESSDESC1),
							ESBServiceConstant.BUSINESSDESC1_MAX_LENGTH));
		}
		if (header.containsKey(ESBServiceConstant.BUSINESSDESC2)) {
			header.put(
					ESBServiceConstant.BUSINESSDESC2,
					getLimitString(
							header.get(ESBServiceConstant.BUSINESSDESC2),
							ESBServiceConstant.BUSINESSDESC2_MAX_LENGTH));
		}
		if (header.containsKey(ESBServiceConstant.BUSINESSDESC3)) {
			header.put(
					ESBServiceConstant.BUSINESSDESC3,
					getLimitString(
							header.get(ESBServiceConstant.BUSINESSDESC3),
							ESBServiceConstant.BUSINESSDESC3_MAX_LENGTH));
		}
		if (header.containsKey(ESBServiceConstant.REQUEST_ID)) {
			header.put(
					ESBServiceConstant.REQUEST_ID,
					getLimitString(header.get(ESBServiceConstant.REQUEST_ID),
							ESBServiceConstant.REQUEST_ID_MAX_LENGTH));
		}
		if (header.containsKey(ESBServiceConstant.RESPONSE_ID)) {
			header.put(
					ESBServiceConstant.RESPONSE_ID,
					getLimitString(header.get(ESBServiceConstant.RESPONSE_ID),
							ESBServiceConstant.RESPONSE_ID_MAX_LENGTH));
		}
		
		
	}
	
	public static void truncEsbHeader(EsbHeader header){
		
		if (header == null) {
			return;
		}
		header.setRequestId(getLimitString(header.getRequestId(), ESBServiceConstant.REQUEST_ID_MAX_LENGTH));
		header.setResponseId(getLimitString(header.getResponseId(), ESBServiceConstant.RESPONSE_ID_MAX_LENGTH));
		header.setBusinessId(getLimitString(header.getBusinessId(), ESBServiceConstant.BUSINESSID_MAX_LENGTH));
		header.setBusinessDesc1(getLimitString(header.getBusinessDesc1(), ESBServiceConstant.BUSINESSDESC1_MAX_LENGTH));
		header.setBusinessDesc2(getLimitString(header.getBusinessDesc2(), ESBServiceConstant.BUSINESSDESC2_MAX_LENGTH));
		header.setBusinessDesc3(getLimitString(header.getBusinessDesc3(), ESBServiceConstant.BUSINESSDESC3_MAX_LENGTH));
	}
	
	private static String getLimitString(String str, int maxLength) {
		if (str != null && str.length() > maxLength) {
			return str.substring(0, maxLength);
		}
		return str;
	}

	private static String getLimitString(Object obj, int maxLength) {
		if (obj == null) {
			return null;
		}
		if (!(obj instanceof String)) {
			throw new IllegalArgumentException("arg illegal");
		}
		return getLimitString((String) obj, maxLength);
	}
}
