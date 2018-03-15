package org.hbhk.aili.esb.common.config;

import org.hbhk.aili.esb.common.entity.BaseEntity;

/**
 * ESB运行时平台的配置信息 key---------------value esbServiceCode----this.
 * 
 * @author HuangHua
 */
public class ESBRuntimeConfiguration extends BaseEntity{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2749680346313127423L;
	/**
	 * 路由使用的组件
	 */
	private String jmsComponent;
	
	
	/** 服务编码,并非一定是ESB服务编码。可能是ESB服务编码和后端服务编码. */
	private String esbServiceCode;
	
	/** 目标服务编码. */
	private String targetServiceCode;
	
	/** 目标系统编码. */
	private String targetSystem;
	
	/** 目标服务地址，MQ的写队列名,WS的写Webservice地址. */
	private String targetServiceAddr;
	
	/** 目标WS的portName. */
	private String targetPortName;
	
	/** 目标WS的服务名. */
	private String targetServiceName;
	
	/** 目标WS的命名空间. */
	private String targetNameSpace;
	
	/** 交互方式。*/
	private String agrmt;

	/** 来源系统编码。*/
	private String sourceSystem;
	
	/**
	 * @Fields needAudit : 是否需要记录审计日志
	 */
	private boolean needAudit;
	
	/**
	 * @Fields needExpn : 是否需要记录异常日志(Y/N)
	 */
	private boolean needExpn;
	
	/**
	 * @Fields needStatus : 是否需要记录状态日志(Y/N)
	 */
	private boolean needStatus;
	
	/**
	 * @Fields timeout : 请求超时时间
	 */
	private Long timeout;
	
	/**
	 * 获取 来源系统编码
	 * @return 来源系统编码
	 */
	public String getAgrmt(){
		return this.agrmt;
	}
	
	public void setAgrmt(String agrmt){
		this.agrmt = agrmt;
	}
	
	public String getSourceSystem(){
		return this.sourceSystem;
	}
	
	public void setSourceSystem(String sourceSystem){
		this.sourceSystem = sourceSystem;
	}
	
	/**
	 * Gets the 目标服务编码.
	 * 
	 * @return 目标服务编码
	 */
	public String getTargetServiceCode() {
		return targetServiceCode;
	}

	/**
	 * Sets the 目标服务编码.
	 * 
	 * @param targetServiceCode
	 *            the new 目标服务编码
	 */
	public void setTargetServiceCode(String targetServiceCode) {
		this.targetServiceCode = targetServiceCode;
	}

	/**
	 * Gets the 目标系统编码.
	 * 
	 * @return 目标系统编码
	 */
	public String getTargetSystem() {
		return targetSystem;
	}

	/**
	 * Sets the 目标系统编码.
	 * 
	 * @param backSystem
	 *            目标系统编码
	 */
	public void setTargetSystem(String backSystem) {
		this.targetSystem = backSystem;
	}

	/**
	 * Gets the 目标服务地址，MQ的写队列名,WS的写Webservice地址.
	 * 
	 * @return 目标服务地址，MQ的写队列名,WS的写Webservice地址
	 */
	public String getTargetServiceAddr() {
		return targetServiceAddr;
	}

	/**
	 * Sets the 目标服务地址，MQ的写队列名,WS的写Webservice地址.
	 * 
	 * @param backServiceAddr
	 *            目标服务地址，MQ的写队列名,WS的写Webservice地址
	 */
	public void setTargetServiceAddr(String backServiceAddr) {
		this.targetServiceAddr = backServiceAddr;
	}

	/**
	 * Gets the 目标WS的portName.
	 * 
	 * @return the 目标WS的portName
	 */
	public String getTargetPortName() {
		return targetPortName;
	}

	/**
	 * Sets the 目标WS的portName.
	 * 
	 * @param backPortName
	 *            the new 目标WS的portName
	 */
	public void setTargetPortName(String backPortName) {
		this.targetPortName = backPortName;
	}

	/**
	 * Gets the 目标WS的服务名.
	 * 
	 * @return the 目标WS的服务名
	 */
	public String getTargetServiceName() {
		return targetServiceName;
	}

	/**
	 * Sets the 目标WS的服务名.
	 * 
	 * @param backServiceName
	 *            the new 目标WS的服务名
	 */
	public void setTargetServiceName(String backServiceName) {
		this.targetServiceName = backServiceName;
	}

	/**
	 * Gets the 目标WS的命名空间.
	 * 
	 * @return the 目标WS的命名空间
	 */
	public String getTargetNameSpace() {
		return targetNameSpace;
	}

	/**
	 * Sets the 目标WS的命名空间.
	 * 
	 * @param backTargetNameSpace
	 *            the new 目标WS的命名空间
	 */
	public void setTargetNameSpace(String backTargetNameSpace) {
		this.targetNameSpace = backTargetNameSpace;
	}

	/**
	 * Gets the 服务编码,并非一定是ESB服务编码。可能是ESB服务编码和后端服务编码.
	 * 
	 * @return the 服务编码,并非一定是ESB服务编码。可能是ESB服务编码和后端服务编码
	 */
	public String getEsbServiceCode() {
		return esbServiceCode;
	}

	/**
	 * Sets the 服务编码,并非一定是ESB服务编码。可能是ESB服务编码和后端服务编码.
	 * 
	 * @param esbServiceCode
	 *            the new 服务编码,并非一定是ESB服务编码。可能是ESB服务编码和后端服务编码
	 */
	public void setEsbServiceCode(String esbServiceCode) {
		this.esbServiceCode = esbServiceCode;
	}

	public boolean isNeedAudit() {
		return needAudit;
	}

	public void setNeedAudit(boolean needAudit) {
		this.needAudit = needAudit;
	}

	public boolean isNeedExpn() {
		return needExpn;
	}

	public void setNeedExpn(boolean needExpn) {
		this.needExpn = needExpn;
	}

	public boolean isNeedStatus() {
		return needStatus;
	}

	/** 
	 * @author :054839
	 * @Description: 设置是否需要记录状态日志
	 * @param needStatus    
	 * @return void     
	 * @create 2014-5-22下午1:55:40
	 */
	public void setNeedStatus(boolean needStatus) {
		this.needStatus = needStatus;
	}

	/** 
	 * @author :054839
	 * @Description: 获取超时时间 
	 * @return    
	 * @return Long     
	 * @create 2014年10月20日下午5:36:14
	 */
	public Long getTimeout() {
		return timeout;
	}

	/** 
	 * @author :054839
	 * @Description: 设置超时时间
	 * @param timeout    
	 * @return void     
	 * @create 2014年10月20日下午5:36:16
	 */
	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	public String getJmsComponent() {
		return jmsComponent;
	}

	public void setJmsComponent(String jmsComponent) {
		this.jmsComponent = jmsComponent;
	}

	
}
