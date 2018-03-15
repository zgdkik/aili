package com.deppon.esb.management.svccfg.constant;

/** 
 * @ClassName: ServiceConstant
 * @Description: 服务信息常量
 * @author k
 * @date 2014年11月15日 下午11:56:38  
 */ 
public class ServiceConstant {
	
	/** jms 交互协议  */
	public static final String SERVICE_AGREEMENT_JMS = "JMS";
	
	/** WebService 交互协议 */
	public static final String SERVICE_AGREEMENT_WS = "WebService";
	
	/** restful 交互协议 */
	public static final String SERVICE_AGREEMENT_RF = "restful";

	/** jms 交互协议状态 */
	public static final int SERVICE_AGREEMENT_STATUS_JMS = 1;
	
	/** WebService 交互协议状态 */
	public static final int SERVICE_AGREEMENT_STATUS_WS = 2;
	
	/** restful 交互协议状态 */
	public static final int SERVICE_AGREEMENT_STATUS_RF = 3;
	
	/** 服务模式:请求/响应 */
	public static final int SERVICE_PATTERN_REQUESTORRESPONSE = 1;
	
	/** 服务模式:请求/回调 */
	public static final int SERVICE_PATTERN_REQUESTORCALLBACK = 2;
	
	/** 服务模式:单向 */
	public static final int SERVICE_PATTERN_ONEWAY = 3;
	
	/** 消息格式:SOAP */
	public static final String SERVICE_MESSAGEFORMAT_SOAP = "SOAP";
	
	/** 消息格式:JSON */
	public static final String SERVICE_MESSAGEFORMAT_JSON = "JSON";
	
	/** 消息格式:XML */
	public static final String SERVICE_MESSAGEFORMAT_XML = "XML";
	
	/** 是否通过ESB:YES */
	public static final int PASS_ESB_YES = 1;
	
	/** 是否通过ESB:NO */
	public static final int PASS_ESB_NO = 0;
	
	/** 是否分发:YES */
	public static final int Distribute_ESB_YES = 1;
	
	/** 是否分发:NO */
	public static final int Distribute_ESB_NO = 0;
	
}
