package com.deppon.esb.management.svccfg.domain.extend;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 系统配置.
 * 
 * @author HuangHua
 */
public class ESBSystemConfiguration implements Serializable{

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 4434685097491613996L;
	
	/** 更新时间. */
	private Date updateTime;
	
	/** 系统编码. */
	private String systemCode;
	
	/** 作为客户端时的请求地址. */
	private String clientMQRequestURL;
	
	/** 作为客户端时的响应地址. */
	private String clientMQResponseURL;
	
	/** 作为服务端时的请求地址. */
	private String serverMQRequestURL;
	
	/** 作为服务端时的响应地址. */
	private String serverMQResponseURL;
	
	/** The service list. */
	private List<ESBServiceConfiguration> serviceList;

	/**
	 * 获取 更新时间.
	 * 
	 * @return the 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 更新时间.
	 * 
	 * @param updateTime
	 *            the new 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取 系统编码.
	 * 
	 * @return the 系统编码
	 */
	public String getSystemCode() {
		return systemCode;
	}

	/**
	 * 设置 系统编码.
	 * 
	 * @param systemCode
	 *            the new 系统编码
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * 获取 作为客户端时的请求地址.
	 * 
	 * @return the 作为客户端时的请求地址
	 */
	public String getClientMQRequestURL() {
		return clientMQRequestURL;
	}

	/**
	 * 设置 作为客户端时的请求地址.
	 * 
	 * @param clientMQRequestURL
	 *            the new 作为客户端时的请求地址
	 */
	public void setClientMQRequestURL(String clientMQRequestURL) {
		this.clientMQRequestURL = clientMQRequestURL;
	}

	/**
	 * 获取 作为客户端时的响应地址.
	 * 
	 * @return the 作为客户端时的响应地址
	 */
	public String getClientMQResponseURL() {
		return clientMQResponseURL;
	}

	/**
	 * 设置 作为客户端时的响应地址.
	 * 
	 * @param clientMQResponseURL
	 *            the new 作为客户端时的响应地址
	 */
	public void setClientMQResponseURL(String clientMQResponseURL) {
		this.clientMQResponseURL = clientMQResponseURL;
	}

	/**
	 * 获取 作为服务端时的请求地址.
	 * 
	 * @return the 作为服务端时的请求地址
	 */
	public String getServerMQRequestURL() {
		return serverMQRequestURL;
	}

	/**
	 * 设置 作为服务端时的请求地址.
	 * 
	 * @param serverMQRequestURL
	 *            the new 作为服务端时的请求地址
	 */
	public void setServerMQRequestURL(String serverMQRequestURL) {
		this.serverMQRequestURL = serverMQRequestURL;
	}

	/**
	 * 获取 作为服务端时的响应地址.
	 * 
	 * @return the 作为服务端时的响应地址
	 */
	public String getServerMQResponseURL() {
		return serverMQResponseURL;
	}

	/**
	 * 设置 作为服务端时的响应地址.
	 * 
	 * @param serverMQResponseURL
	 *            the new 作为服务端时的响应地址
	 */
	public void setServerMQResponseURL(String serverMQResponseURL) {
		this.serverMQResponseURL = serverMQResponseURL;
	}

	/**
	 * Gets the service list.
	 * 
	 * @return the service list
	 */
	public List<ESBServiceConfiguration> getServiceList() {
		return serviceList;
	}

	/**
	 * Sets the service list.
	 * 
	 * @param serviceList
	 *            the new service list
	 */
	public void setServiceList(List<ESBServiceConfiguration> serviceList) {
		this.serviceList = serviceList;
	}
}
