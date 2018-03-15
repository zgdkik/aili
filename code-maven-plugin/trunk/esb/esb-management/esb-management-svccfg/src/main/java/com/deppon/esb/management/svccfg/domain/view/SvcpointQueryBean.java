package com.deppon.esb.management.svccfg.domain.view;

/**
 * 封装esb服务管理模块查询条件.
 * 
 * @author qiancheng
 */
public class SvcpointQueryBean {
	
	/** 前端服务配置名称. */
	private String frontSvcName;
	
	/** 后端服务配置名称. */
	private String backSvcName;
	
	/** 服务接入协议. */
	private String svcAgrmt;
	
	/** 服务接入接入地址. */
	private String svcAddr;
	
	/** 客户系统. */
	private String clientSystem;
	
	/** The start. */
	private Integer start;
	
	/** The limit. */
	private Integer limit;
	
	/**
	 * 获取 start.
	 * 
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}
	
	/**
	 * 设置 start.
	 * 
	 * @param start
	 *            the new start
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	
	/**
	 * 获取 limit.
	 * 
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}
	
	/**
	 * 设置 limit.
	 * 
	 * @param limit
	 *            the new limit
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	/**
	 * 获取 客户系统.
	 * 
	 * @return the 客户系统
	 */
	public String getClientSystem() {
		return clientSystem;
	}
	
	/**
	 * 设置 客户系统.
	 * 
	 * @param clientSystem
	 *            the new 客户系统
	 */
	public void setClientSystem(String clientSystem) {
		this.clientSystem = clientSystem;
	}
	
	/**
	 * 获取 前端服务配置名称.
	 * 
	 * @return the 前端服务配置名称
	 */
	public String getFrontSvcName() {
		return frontSvcName;
	}
	
	/**
	 * 设置 前端服务配置名称.
	 * 
	 * @param frontSvcName
	 *            the new 前端服务配置名称
	 */
	public void setFrontSvcName(String frontSvcName) {
		this.frontSvcName = frontSvcName;
	}
	
	/**
	 * 获取 后端服务配置名称.
	 * 
	 * @return the 后端服务配置名称
	 */
	public String getBackSvcName() {
		return backSvcName;
	}
	
	/**
	 * 设置 后端服务配置名称.
	 * 
	 * @param backSvcName
	 *            the new 后端服务配置名称
	 */
	public void setBackSvcName(String backSvcName) {
		this.backSvcName = backSvcName;
	}
	
	/**
	 * 获取 服务接入协议.
	 * 
	 * @return the 服务接入协议
	 */
	public String getSvcAgrmt() {
		return svcAgrmt;
	}
	
	/**
	 * 设置 服务接入协议.
	 * 
	 * @param svcAgrmt
	 *            the new 服务接入协议
	 */
	public void setSvcAgrmt(String svcAgrmt) {
		this.svcAgrmt = svcAgrmt;
	}
	
	/**
	 * 获取 服务接入接入地址.
	 * 
	 * @return the 服务接入接入地址
	 */
	public String getSvcAddr() {
		return svcAddr;
	}
	
	/**
	 * 设置 服务接入接入地址.
	 * 
	 * @param svcAddr
	 *            the new 服务接入接入地址
	 */
	public void setSvcAddr(String svcAddr) {
		this.svcAddr = svcAddr;
	}
	
	
}
