package com.deppon.esb.management.svccfg.domain.extend;

import java.io.Serializable;

/**
 * ESB传入的服务配置.
 * 
 * @author HuangHua
 */
public class ESBServiceConfiguration implements Serializable {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 4620463499419790452L;
	
	/** 服务编码. */
	private String serviceCode;
	
	/** 客户端或者服务端. */
	private String clientOrServer;
	
	/** 通道. */
	private int accessChannel;

	/** 交互模式. */
	private int exchangePattern;
	
	/** 持久. */
	private int persistentLevel;
	
	/** 消息格式. */
	private String messageFormat;
	
	/** 超时时间. */
	private long serviceTimeout;
	
	/** 请求地址. */
	private String requestURL;
	
	/** 响应地址. */
	private String responseURL;
	
	/** 连接工厂. */
	private String connectionFactory;
	
	/** 请求对象转换类. */
	private String reqConvertorClass;
	
	/** 响应对象转换类. */
	private String resConvertorClass;

	/**
	 * 获取 客户端或者服务端.
	 * 
	 * @return the 客户端或者服务端
	 */
	public String getClientOrServer() {
		return clientOrServer;
	}

	/**
	 * 设置 客户端或者服务端.
	 * 
	 * @param clientOrServer
	 *            the new 客户端或者服务端
	 */
	public void setClientOrServer(String clientOrServer) {
		this.clientOrServer = clientOrServer;
	}

	/**
	 * 获取 通道.
	 * 
	 * @return the 通道
	 */
	public int getAccessChannel() {
		return accessChannel;
	}

	/**
	 * 设置 通道.
	 * 
	 * @param accessChannel
	 *            the new 通道
	 */
	public void setAccessChannel(int accessChannel) {
		this.accessChannel = accessChannel;
	}

	/**
	 * 获取 交互模式.
	 * 
	 * @return the 交互模式
	 */
	public int getExchangePattern() {
		return exchangePattern;
	}

	/**
	 * 设置 交互模式.
	 * 
	 * @param exchangePattern
	 *            the new 交互模式
	 */
	public void setExchangePattern(int exchangePattern) {
		this.exchangePattern = exchangePattern;
	}

	/**
	 * 获取 持久.
	 * 
	 * @return the 持久
	 */
	public int getPersistentLevel() {
		return persistentLevel;
	}

	/**
	 * 设置 持久.
	 * 
	 * @param persistentLevel
	 *            the new 持久
	 */
	public void setPersistentLevel(int persistentLevel) {
		this.persistentLevel = persistentLevel;
	}

	/**
	 * 获取 消息格式.
	 * 
	 * @return the 消息格式
	 */
	public String getMessageFormat() {
		return messageFormat;
	}

	/**
	 * 设置 消息格式.
	 * 
	 * @param messageFormat
	 *            the new 消息格式
	 */
	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}

	/**
	 * 获取 超时时间.
	 * 
	 * @return the 超时时间
	 */
	public long getServiceTimeout() {
		return serviceTimeout;
	}

	/**
	 * 设置 超时时间.
	 * 
	 * @param serviceTimeout
	 *            the new 超时时间
	 */
	public void setServiceTimeout(long serviceTimeout) {
		this.serviceTimeout = serviceTimeout;
	}

	/**
	 * 获取 请求地址.
	 * 
	 * @return the 请求地址
	 */
	public String getRequestURL() {
		return requestURL;
	}

	/**
	 * 设置 请求地址.
	 * 
	 * @param requestURL
	 *            the new 请求地址
	 */
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	/**
	 * 获取 响应地址.
	 * 
	 * @return the 响应地址
	 */
	public String getResponseURL() {
		return responseURL;
	}

	/**
	 * 设置 响应地址.
	 * 
	 * @param responseURL
	 *            the new 响应地址
	 */
	public void setResponseURL(String responseURL) {
		this.responseURL = responseURL;
	}

	/**
	 * 获取 连接工厂.
	 * 
	 * @return the 连接工厂
	 */
	public String getConnectionFactory() {
		return connectionFactory;
	}

	/**
	 * 设置 连接工厂.
	 * 
	 * @param connectionFactory
	 *            the new 连接工厂
	 */
	public void setConnectionFactory(String connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	/**
	 * 获取 请求对象转换类.
	 * 
	 * @return the 请求对象转换类
	 */
	public String getReqConvertorClass() {
		return reqConvertorClass;
	}

	/**
	 * 设置 请求对象转换类.
	 * 
	 * @param reqConvertorClass
	 *            the new 请求对象转换类
	 */
	public void setReqConvertorClass(String reqConvertorClass) {
		this.reqConvertorClass = reqConvertorClass;
	}

	/**
	 * 获取 响应对象转换类.
	 * 
	 * @return the 响应对象转换类
	 */
	public String getResConvertorClass() {
		return resConvertorClass;
	}

	/**
	 * 设置 响应对象转换类.
	 * 
	 * @param resConvertorClass
	 *            the new 响应对象转换类
	 */
	public void setResConvertorClass(String resConvertorClass) {
		this.resConvertorClass = resConvertorClass;
	}

	/**
	 * 获取 服务编码.
	 * 
	 * @return the 服务编码
	 */
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * 设置 服务编码.
	 * 
	 * @param serviceCode
	 *            the new 服务编码
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

}
