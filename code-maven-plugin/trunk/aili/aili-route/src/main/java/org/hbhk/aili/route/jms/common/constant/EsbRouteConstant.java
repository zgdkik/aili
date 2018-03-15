package org.hbhk.aili.route.jms.common.constant;

public class EsbRouteConstant {
	
	public static final String DPESB_MULT_TARGET_ADDRESS = "DPESB_MULT_TARGET_ADDRESS";
	
	/* 获取code信息 */
	public static final String SERVICE_CODE = "serviceCode";
	/* 获取消息 */
	public static final String MESSAGE_BOLB = "messageBolb";
	/* 是否日志记录 */

	/* 响应code */
	public static final String HTTP_RESPONSE_CODE = "httpResponseCode";
	/* 是    */
	public static final String TRUE = "true";
	/* 否 */
	public static final String FALSE = "false";
	/* GET 请求 */
	public static final String HTTP_GET_METHOD = "GET";
	/* POST 请求 */
	public static final String HTTP_POST_METHOD = "POST";
	/* HTTP 服务 */
	public static final String HTTP_TYPE = "http";
	/* RestFul 服务 */
	public static final String HTTP_REST_FUL = "restful";
	/* HTTP TRL */
	public static final String HTTP_URL = "httpUrl";
	/* Http-remote远程调用请求地址 */
	public static final String HTTP_REMOTE_URL = "http_remote_URL";
	
	/**
	 * @Fields IS_RECORD_AUDIT : 是否记录审计日志
	 */
	public static final String IS_RECORD_AUDIT = "is_record_audit";
	
	/**
	 * @Fields IS_RECORD_EXCEPTION : 是否记录异常日志
	 */
	public static final String IS_RECORD_EXCEPTION = "is_record_exception";
	
	/**
	 * @Fields IS_RECORD_STATUS : 是否记录状态日志
	 */
	public static final String IS_RECORD_STATUS = "is_record_status";
}
