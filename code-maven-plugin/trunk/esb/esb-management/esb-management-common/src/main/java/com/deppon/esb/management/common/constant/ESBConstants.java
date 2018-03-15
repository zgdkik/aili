package com.deppon.esb.management.common.constant;

import java.io.Serializable;

/**
 * 一期移植
 * @author HuangHua
 * @date 2012-12-27 上午9:53:58
 */
public class ESBConstants implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1808445407116208928L;

	/**
	 * ESB处理状态标识：处理成功 
	 */
	public static final String SUCCESS = "SUCCESS";
	
	/**
	 * ESB处理状态标识：ESB端错误
	 */
	public static final String ESB_ERROR = "ESB_ERROR";
	
	/**
	 * ESB处理状态标识:Server端错误
	 */
	public static final String SERVER_ERROR= "SERVER_ERROR";
	
	/**
	 * ESB处理状态标识：业务错误；
	 */
	public static final String BUSINESS_ERROR = "BUSINESS_ERROR";
	
	/**
	 * 系统代码定义
	 * 
	 * EBM: 电商；
	 * BHO：网厅；
	 * CC: 呼叫中心；
	 **/
	public static final String SYSTEM_OA = "OA";
	public static final String SYSTEM_ERP = "ERP";
	public static final String SYSTEM_FOSS = "FOSS";
	public static final String SYSTEM_FOSS_CRM="FOSS_CRM";
	public static final String SYSTEM_CRM = "CRM";
	public static final String SYSTEM_EBM = "EBM";
	public static final String SYSTEM_BHO = "BHO";
	public static final String SYSTEM_CC = "CC";
	// ----------- 结束系统代码定义--------------------------
	
	/**
	 * @Description 日志配置信息
	 * @author HuangHua
	 * @date 2012-03-16 14:05:57
	 */
	public static final String LogConfig = "com.deppon.esb.logPerformanceConfiguration";

	/**
	 * @Description 服务编码
	 * @author HuangHua
	 * @date 2012-03-16 14:05:57
	 */
	public static final String SERVICECODE = "com.deppon.esb.serviceCode";
	
	/**
	 * 
	 */
	public static final String BACK_SERVICECODE="com.depppon.esb.backServiceCode";
	
	public static final String BACK_SERVICE_CONFIG = "com.deppon.esb.backServiceConfig";
	/**
	 * 业务字段1
	 */
	public static final String Client_Business_Biz1="Client_Business_Biz1";
	
	/**
	 * 业务字段2
	 */
	public static final String Client_Business_Biz2="Client_Business_Biz2";
	
	/**
	 *异常类型 
	 */
	
	public static final String ESB_Exception_Type="ESB_Exception_Type";
	
	/**
	 * 客户端消息唯一编码
	 */
	public static final String Client_ReRequestID ="Client_ReRequestID";
	
	/**
	 * 客户端系统名称
	 */
	public static final String Client_SystemName="Client_SystemName";
	
	/**
	 * request请求
	 */
	public static final String Client_Request="Client_Request";
	
	/**
	 * 服务配置
	 */
	public static final String ESB_svcpoint="ESB_svcpoint";
	
	/**
	 * 重发类型(jms or webservice)
	 */
	public static final String REDO_TYPE_JMS="JMS";
	
	public static final String REDO_TYPE_WEBSERVICE="webservice";
	
	/**
	 * 系统重发
	 */
	public static final String REDO_SYSTEMSEND ="Redo_systemSend";
	
	/**
	 * MQM2回退远程队列名称
	 * 
	 * RQ_ESB_COM_BK
	 * 
	 */
	
	public static final String QUEUE_APP_BK_NAME = "://RQ_ESB_COM_BK";
	
	/**
	 * MQM1路由消息回退队列名称
	 * 
	 * QU_ESB_COM_BK
	 * 
	 */
	
	public static final String QUEUE_ESB_BK_NAME = "://QU_ESB_COM_BK";
	
	/**
	 * 状态回退队列名称
	 * 
	 * QU_STATUS_COM_BK
	 * 
	 */
	
	public static final String QUEUE_STATUS_BK_NAME = "://QU_STATUS_COM_BK";
	
	/**
	 * 异常回退队列名称
	 * 
	 * QU_EXCEPTION_COM_BK
	 * 
	 */
	
	public static final String QUEUE_EXCEPTION_BK_NAME = "://QU_EXCEPTION_COM_BK";
	
	/**
	 * 失败回退队列名称
	 * 
	 * QU_FAILURE_COM_BK
	 */
	
	public static final String QUEUE_FAILURE_BK_NAME = "://QU_FAILURE_COM_BK";
	
	/**
	 * 审计日志回退队列名称
	 * 
	 * QU_AUDITLOG_COM_BK
	 */
	
	public static final String QUEUE_AUDITLOG_BK_NAME = "://QU_AUDITLOG_COM_BK";
	
	
	/**
	 * 状态队列名称
	 * 
	 * QU_ESB_STATUS
	 * 
	 */
	
	public static final String QUEUE_STATUS_NAME = "QU_ESB_STATUS";
	
	/**
	 * 异常队列名称
	 * 
	 * QU_ESB_EXCEPTION
	 * 
	 */
	
	public static final String QUEUE_EXCEPTION_NAME = "QU_ESB_EXCEPTION";
	
	/**
	 * 失败队列名称
	 * 
	 * QU_FAILURE_COM_BK
	 */
	
	public static final String QUEUE_FAILURE_NAME = "QU_ESB_FAILURE";
	
	/**
	 * 审计日志队列名称
	 * 
	 * QU_AUDITLOG_COM_BK
	 */
	
	public static final String QUEUE_AUDITLOG_NAME = "QU_ESB_AUDITLOG";
}
