package com.deppon.esb.management.svccfg.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * ESB运行时平台的配置信息 key---------------value esbServiceCode----this.
 * 
 * @author HuangHua
 */
public class ESBRuntimeConfiguration extends BaseEntity{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2749680346313127423L;
	
	/** 服务编码. */
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
	 * 获取 目标服务编码.
	 * 
	 * @return 目标服务编码
	 */
	public String getTargetServiceCode() {
		return targetServiceCode;
	}

	/**
	 * 设置 目标服务编码.
	 * 
	 * @param targetServiceCode
	 *            the new 目标服务编码
	 */
	public void setTargetServiceCode(String targetServiceCode) {
		this.targetServiceCode = targetServiceCode;
	}

	/**
	 * 获取 目标系统编码.
	 * 
	 * @return 目标系统编码
	 */
	public String getTargetSystem() {
		return targetSystem;
	}

	/**
	 * 设置 目标系统编码.
	 * 
	 * @param backSystem
	 *            目标系统编码
	 */
	public void setTargetSystem(String backSystem) {
		this.targetSystem = backSystem;
	}

	/**
	 * 获取 目标服务地址，MQ的写队列名,WS的写Webservice地址.
	 * 
	 * @return 目标服务地址，MQ的写队列名,WS的写Webservice地址
	 */
	public String getTargetServiceAddr() {
		return targetServiceAddr;
	}

	/**
	 * 设置 目标服务地址，MQ的写队列名,WS的写Webservice地址.
	 * 
	 * @param backServiceAddr
	 *            目标服务地址，MQ的写队列名,WS的写Webservice地址
	 */
	public void setTargetServiceAddr(String backServiceAddr) {
		this.targetServiceAddr = backServiceAddr;
	}

	/**
	 * 获取 目标WS的portName.
	 * 
	 * @return the 目标WS的portName
	 */
	public String getTargetPortName() {
		return targetPortName;
	}

	/**
	 * 设置 目标WS的portName.
	 * 
	 * @param backPortName
	 *            the new 目标WS的portName
	 */
	public void setTargetPortName(String backPortName) {
		this.targetPortName = backPortName;
	}

	/**
	 * 获取 目标WS的服务名.
	 * 
	 * @return the 目标WS的服务名
	 */
	public String getTargetServiceName() {
		return targetServiceName;
	}

	/**
	 * 设置 目标WS的服务名.
	 * 
	 * @param backServiceName
	 *            the new 目标WS的服务名
	 */
	public void setTargetServiceName(String backServiceName) {
		this.targetServiceName = backServiceName;
	}

	/**
	 * 获取 目标WS的命名空间.
	 * 
	 * @return the 目标WS的命名空间
	 */
	public String getTargetNameSpace() {
		return targetNameSpace;
	}

	/**
	 * 设置 目标WS的命名空间.
	 * 
	 * @param backTargetNameSpace
	 *            the new 目标WS的命名空间
	 */
	public void setTargetNameSpace(String backTargetNameSpace) {
		this.targetNameSpace = backTargetNameSpace;
	}

	/**
	 * 获取 服务编码.
	 * 
	 * @return the 服务编码
	 */
	public String getEsbServiceCode() {
		return esbServiceCode;
	}

	/**
	 * 设置 服务编码.
	 * 
	 * @param esbServiceCode
	 *            the new 服务编码
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

}
