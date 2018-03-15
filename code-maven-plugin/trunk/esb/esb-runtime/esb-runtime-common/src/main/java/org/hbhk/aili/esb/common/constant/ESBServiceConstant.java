package org.hbhk.aili.esb.common.constant;

/**
 * ESB服务常量.
 * 
 * @author HuangHua
 */
public class ESBServiceConstant {

	/** The Constant VERSION. */
	public static final String VERSION = "version";

	/** The Constant REQUEST_ID. */
	public static final String REQUEST_ID = "requestId";

	/** The Constant RESPONSE_ID. */
	public static final String RESPONSE_ID = "responseId";

	/** The Constant SOURCE_SYSTEM. */
	public static final String SOURCE_SYSTEM = "sourceSystem";

	/** The Constant TARGET_SYSTEM. */
	public static final String TARGET_SYSTEM = "targetSystem";

	/** The Constant ESB_SERVICE_CODE. */
	public static final String ESB_SERVICE_CODE = "esbServiceCode";

	/** The Constant BACK_SERVICE_CODE. */
	public static final String BACK_SERVICE_CODE = "backServiceCode";

	/** The Constant MESSAGE_FORMATE. */
	public static final String MESSAGE_FORMATE = "messageFormat";

	/** The Constant EXCHANGE_PATTERN. */
	public static final String EXCHANGE_PATTERN = "exchangePattern";

	/** The Constant AUTHENTICATION. */
	public static final String AUTHENTICATION = "authentication";
	
	/** The Constant USER_NAME. */
	public static final String USER_NAME = "username";

	/** The Constant PASSWORD. */
	public static final String PASSWORD = "password";

	/** The Constant BUSINESSID. */
	public static final String BUSINESSID = "businessId";

	/** The Constant BUSINESSDESC1. */
	public static final String BUSINESSDESC1 = "businessDesc1";

	/** The Constant BUSINESSDESC2. */
	public static final String BUSINESSDESC2 = "businessDesc2";

	/** The Constant BUSINESSDESC3. */
	public static final String BUSINESSDESC3 = "businessDesc3";
	
	public static final String SENTSEQUENCE = "sentSequence";

	/** The Constant HOST_IP. */
	public static final String HOST_IP = "hostIP";

	/** The Constant 用于在运行时表示是请求还是回调. */
	public static final String RT_REQUEST_OR_CALLBACK = "dpesb_reqOrCalbak";

	/** The Constant RT_REQUEST. */
	public static final String RT_REQUEST = "dpesb_request";

	/** The Constant RT_CALLBACK. */
	public static final String RT_CALLBACK = "dpesb_callback";
	
	/** The Constant RT_STOP. */
	public static final String RT_STOP = "dpesb_stop";
	
	/** The Constant 用于在运行时,存放Headers到property时的key. */
	public static final String RT_HEADERS = "dpesb_rt_headers";

	/** The Constant TARGET_SERVICE_ADDR. */
	public static final String TARGET_SERVICE_ADDR = "dpesb_target_service_address";

	/** The Constant EXCHANGE_BODY. */
	public static final String EXCHANGE_BODY = "dpesb_exchange_body";

	// 异常类型
	/** The Constant SYSTEM_EXCEPTION. */
	public static final String SYSTEM_EXCEPTION = "SystemException";

	/** The Constant BUSINESS_EXCEPTION. */
	public static final String BUSINESS_EXCEPTION = "BusinessException";
	
	public static final String ESBHEADER_EXCEPTION = "EsbHeaderException";
	/**
	 * 在JMS消息头里,标明这个消息是否是整合消息.
	 */
	public static final String CONFORMITY = "Conformity";
	
	/**
	 * CXF发布服务时对应的服务编码常量
	 */
	public static final String CXF_SERVICE_CODE = "dpCxfServiceCode";
	
	public static final String ESB_LOGMSG_CREATETIME  = "esbLogmsgCreateTime";

	// ===============状态 status=========================//
	//CONSUMER REQUEST
	/**
	 * <p>
	 * 可选
	 * </p>
	 * <p>
	 * 服务无消费端调用接口API的开始（Called）
	 * </p>
	 * <p>
	 * 此状态标识了业务组件与接口组件的分界点
	 * </p>
	 * .
	 */
	public static final String STATUS_AT_CONSUMER_CALLED = "ST_102";

	/**
	 * <p>
	 * 可选
	 * </p>
	 * <p>
	 * 服务无消费端生成请求消息后（Created）
	 * </p>
	 * <p>
	 * 此状态表示刚刚生成了请求消息
	 * </p>
	 * .
	 */
	public static final String STATUS_AT_CONSUMER_CREATED = "ST_105";

	/**
	 * <p>
	 * 必须
	 * </p>
	 * <p>
	 * 服务无消费端发送请求消息前（Sent）
	 * </p>
	 * <p>
	 * 此状态表示马上要发送请求消息
	 * </p>
	 * .
	 */
	public static final String STATUS_AT_CONSUMER_SENT = "ST_108";
	
	// ESB REQUEST
	/**
	 * <p>
	 * 必须
	 * </p>
	 * <p>
	 * ESB接收到请求消息后(Received)
	 * </p>
	 * .
	 */
	public static final String STATUS_AT_ESB_REQ_RECEIVED = "ST_202";

	/**
	 * <p>
	 * 必须
	 * </p>
	 * <p>
	 * ESB进行路由转换前（transforming）
	 * </p>
	 * .
	 */
	public static final String STATUS_AT_ESB_REQ_TRANSFORMING = "ST_205";

	/**
	 * <p>
	 * 必须
	 * </p>
	 * <p>
	 * ESB路由到目标队列前（Sent）
	 * </p>
	 * .
	 */
	public static final String STATUS_AT_ESB_REQ_SENT = "ST_208";
	// ESB RESPONSE
	/**
	 * <p>
	 * 必须
	 * </p>
	 * <p>
	 * ESB接收到响应消息后(Received)
	 * </p>
	 * .
	 */
	public static final String STATUS_AT_ESB_RESP_RECEIVED = "ST_402";

	/**
	 * <p>
	 * 必须
	 * </p>
	 * <p>
	 * ESB进行路由转换前（transforming）
	 * </p>
	 * .
	 */
	public static final String STATUS_AT_ESB_RESP_TRANSFORMING = "ST_405";

	/**
	 * <p>
	 * 必须
	 * </p>
	 * <p>
	 * ESB路由到目标队列前（Sent）
	 * </p>
	 * .
	 */
	public static final String STATUS_AT_ESB_RESP_SENT = "ST_408";
	
	/**
	 * 处理完成状态 ST_999
	 */
	public static final String STATUS_FINISHED="ST_999";

	/** 必填(服务端必填) 返回码. */
	public static final String RESULT_CODE = "resultCode";

	/** The Constant ESB_HEADER. */
	public static final String ESB_HEADER = "esbHeader";
	/**
	 * soapBody
	 */
	public static final String REQUEST_SOAP_BODY="request_soap_body";
	
	/**
	 * flag 
	 */
	public static final String EXCEPTION_PROCCESSED = "exception_processed";
	
}
