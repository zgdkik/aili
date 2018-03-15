package com.deppon.esb.management.svccfg.domain.extend.r;

import com.deppon.esb.management.common.entity.BaseEntity;


/**
 * 不要问我这个操蛋的类来自哪里， 我会告诉你，这个类来自恶魔的深渊.
 * 
 * @author HuangHua
 */
public class ESBServiceConfiguration extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3193923125314830339L;
	
	/** 服务编码. */
	private String code;
	
	/** 前端或者后端. */
	private String frntOrbck;
	
	/** 服务协议. */
	private String agrmt;
	
	/** 交互模式. */
	private int expattern;
	
	/** 消息格式. */
	private String messageFormat;
	
	/** 超时时间. */
	private long timeout;
	
	/** 请求地址. */
	private String requestAddr;
	
	/** 响应地址. */
	private String responseAddr;
	
	/** 请求对象转换类. */
	private String reqConvertorClass;
	
	/** 响应对象转换类. */
	private String resConvertorClass;

	/**
	 * 获取 服务编码.
	 * 
	 * @return the 服务编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置 服务编码.
	 * 
	 * @param code
	 *            the new 服务编码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取 前端或者后端.
	 * 
	 * @return the 前端或者后端
	 */
	public String getFrntOrbck() {
		return frntOrbck;
	}

	/**
	 * 设置 前端或者后端.
	 * 
	 * @param frntOrbck
	 *            the new 前端或者后端
	 */
	public void setFrntOrbck(String frntOrbck) {
		this.frntOrbck = frntOrbck;
	}

	/**
	 * 获取 交互模式.
	 * 
	 * @return the 交互模式
	 */
	public int getExpattern() {
		return expattern;
	}

	/**
	 * 设置 交互模式.
	 * 
	 * @param expattern
	 *            the new 交互模式
	 */
	public void setExpattern(int expattern) {
		this.expattern = expattern;
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
	public long getTimeout() {
		return timeout;
	}

	/**
	 * 设置 超时时间.
	 * 
	 * @param timeout
	 *            the new 超时时间
	 */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	/**
	 * 获取 请求地址.
	 * 
	 * @return the 请求地址
	 */
	public String getRequestAddr() {
		return requestAddr;
	}

	/**
	 * 设置 请求地址.
	 * 
	 * @param requestAddr
	 *            the new 请求地址
	 */
	public void setRequestAddr(String requestAddr) {
		this.requestAddr = requestAddr;
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
	 * 获取 服务协议.
	 * 
	 * @return the 服务协议
	 */
	public String getAgrmt() {
		return agrmt;
	}

	/**
	 * 设置 服务协议.
	 * 
	 * @param agrmt
	 *            the new 服务协议
	 */
	public void setAgrmt(String agrmt) {
		this.agrmt = agrmt;
	}

	/**
	 * 获取 响应地址.
	 * 
	 * @return the 响应地址
	 */
	public String getResponseAddr() {
		return responseAddr;
	}

	/**
	 * 设置 响应地址.
	 * 
	 * @param responseAddr
	 *            the new 响应地址
	 */
	public void setResponseAddr(String responseAddr) {
		this.responseAddr = responseAddr;
	}

}
