package org.hbhk.aili.esb.server.foss4sms;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Json转换器，.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午4:42:10
 */
public class SmsRequestJSONConverter {
	/**
	 * json数组转换为SmsSendRequest对象.
	 * 
	 * @param str
	 *            the str
	 * @return the sms send request
	 * @author qiancheng
	 * @date 2012-12-20 下午4:42:39
	 */
	public static SmsSendRequest json2Java(String str){
		ObjectMapper mapper = new  ObjectMapper();
		SmsSendRequest rq = null;
		try {
			rq = mapper.readValue(str, SmsSendRequest.class);
		} catch (Exception e) {	
			throw new RuntimeException("error occur when parse jackson smsSendRequest json ",e);
		}
		return rq;
	}
	
	/**
	 * SmsSendRequest 对象转换为json数组.
	 * 
	 * @param obj
	 *            the obj
	 * @return the string
	 * @author qiancheng
	 * @date 2012-12-20 下午4:43:00
	 */
	public static String pojo2Json(Object obj ){
		ObjectMapper mapper = new  ObjectMapper();
/*		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.getSerializationConfig().setDateFormat(dateFormat);*/
		String str = null;
		try {
			str = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException("error occur when jackson parse oject",e);
		} 
		return str;
	}
}
