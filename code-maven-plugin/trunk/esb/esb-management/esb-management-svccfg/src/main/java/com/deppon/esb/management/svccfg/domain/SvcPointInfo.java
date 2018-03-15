package com.deppon.esb.management.svccfg.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * 服务信息.
 * 
 * @author HuangHua
 * @date 2012-12-28 下午2:50:53
 */
public class SvcPointInfo extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -591541223516578458L;
	
	/** 服务名. */
	private String svcName;
	
	/** 服务编码. */
	private String svcCode;
	
	/** 服务提供者ID. */
	private String svcProvdId;
	
	/** 服务协议. */
	private String svcAgrmt;
	
	/** 服务地址. */
	private String svcAddr;
	
	/** 是属于前端还是后端. */
	private String frntOrBck;
	
	/** 响应对象类. */
	private String responseType;
	
	/** 是否运行ESB自动重发. */
	private Boolean isAutoResend;
	
	/** 是否保存原始报文. */
	private Boolean isRcdOrgBody;
	
	/** 是否记录性能日志. */
	private Boolean isRcdPfmcLog;
	
	/** 是否记录异常日志. */
	private Boolean isRcdErrLog;
	/**
	 * 是否需要及时通知，true-在failureProcess里就会发送通知.
	 */
	private Boolean promptlyNotify;

	/**
	 * 获取 响应对象类.
	 * 
	 * @return the 响应对象类
	 */
	public String getResponseType() {
		return responseType;
	}

	/**
	 * 设置 响应对象类.
	 * 
	 * @param responseType
	 *            the new 响应对象类
	 */
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	/**
	 * 获取 服务名.
	 * 
	 * @return the 服务名
	 */
	public String getSvcName() {
		return svcName;
	}

	/**
	 * 设置 服务名.
	 * 
	 * @param svcName
	 *            the new 服务名
	 */
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}

	/**
	 * 获取 服务编码.
	 * 
	 * @return the 服务编码
	 */
	public String getSvcCode() {
		return svcCode;
	}

	/**
	 * 设置 服务编码.
	 * 
	 * @param svcCode
	 *            the new 服务编码
	 */
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}

	/**
	 * 获取 服务提供者ID.
	 * 
	 * @return the 服务提供者ID
	 */
	public String getSvcProvdId() {
		return svcProvdId;
	}

	/**
	 * 设置 服务提供者ID.
	 * 
	 * @param svcProvdId
	 *            the new 服务提供者ID
	 */
	public void setSvcProvdId(String svcProvdId) {
		this.svcProvdId = svcProvdId;
	}

	/**
	 * 获取 服务协议.
	 * 
	 * @return the 服务协议
	 */
	public String getSvcAgrmt() {
		return svcAgrmt;
	}

	/**
	 * 设置 服务协议.
	 * 
	 * @param svcAgrmt
	 *            the new 服务协议
	 */
	public void setSvcAgrmt(String svcAgrmt) {
		this.svcAgrmt = svcAgrmt;
	}

	/**
	 * 获取 服务地址.
	 * 
	 * @return the 服务地址
	 */
	public String getSvcAddr() {
		return svcAddr;
	}

	/**
	 * 设置 服务地址.
	 * 
	 * @param svcAddr
	 *            the new 服务地址
	 */
	public void setSvcAddr(String svcAddr) {
		this.svcAddr = svcAddr;
	}

	/**
	 * 获取 是属于前端还是后端.
	 * 
	 * @return the 是属于前端还是后端
	 */
	public String getFrntOrBck() {
		return frntOrBck;
	}

	/**
	 * 设置 是属于前端还是后端.
	 * 
	 * @param frntOrBck
	 *            the new 是属于前端还是后端
	 */
	public void setFrntOrBck(String frntOrBck) {
		this.frntOrBck = frntOrBck;
	}

	/**
	 * 获取 是否运行ESB自动重发.
	 * 
	 * @return the 是否运行ESB自动重发
	 */
	public Boolean getIsAutoResend() {
		return isAutoResend;
	}

	/**
	 * 设置 是否运行ESB自动重发.
	 * 
	 * @param isAutoResend
	 *            the new 是否运行ESB自动重发
	 */
	public void setIsAutoResend(Boolean isAutoResend) {
		this.isAutoResend = isAutoResend;
	}

	/**
	 * 获取 是否保存原始报文.
	 * 
	 * @return 是否记录原始报文
	 */
	public Boolean getIsRcdOrgBody() {
		return isRcdOrgBody;
	}

	/**
	 * 获取 是否记录性能日志.
	 * 
	 * @return 是否记录性能日志
	 */
	public Boolean getIsRcdPfmcLog() {
		return isRcdPfmcLog;
	}

	/**
	 * 获取 是否记录异常日志.
	 * 
	 * @return 是否记录异常日志
	 */
	public Boolean getIsRcdErrLog() {
		return isRcdErrLog;
	}

	/**
	 * 设置 是否保存原始报文.
	 * 
	 * @param isRcdOrgBody
	 *            the new 是否保存原始报文
	 */
	public void setIsRcdOrgBody(Boolean isRcdOrgBody) {
		this.isRcdOrgBody = isRcdOrgBody;
	}

	/**
	 * 设置 是否记录性能日志.
	 * 
	 * @param isRcdPfmcLog
	 *            the new 是否记录性能日志
	 */
	public void setIsRcdPfmcLog(Boolean isRcdPfmcLog) {
		this.isRcdPfmcLog = isRcdPfmcLog;
	}

	/**
	 * 设置 是否记录异常日志.
	 * 
	 * @param isRcdErrLog
	 *            the new 是否记录异常日志
	 */
	public void setIsRcdErrLog(Boolean isRcdErrLog) {
		this.isRcdErrLog = isRcdErrLog;
	}

	/**
	 * 获取 是否需要及时通知，true-在failureProcess里就会发送通知.
	 * 
	 * @return the 是否需要及时通知，true-在failureProcess里就会发送通知
	 */
	public Boolean getPromptlyNotify() {
		return promptlyNotify;
	}

	/**
	 * 设置 是否需要及时通知，true-在failureProcess里就会发送通知.
	 * 
	 * @param promptlyNotify
	 *            the new 是否需要及时通知，true-在failureProcess里就会发送通知
	 */
	public void setPromptlyNotify(Boolean promptlyNotify) {
		this.promptlyNotify = promptlyNotify;
	}

	/** 
	 *  重写toString构造方法
	 * @author HuangHua
	 * @date 2012-12-28 下午3:06:40
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("svcName", svcName).append("svcCode", svcCode)
				.append("svcProvdId", svcProvdId).append("svcAgrmt", svcAgrmt)
				.append("svcAddr", svcAddr).append("frntOrBck", frntOrBck)
				.append("responseType", responseType)
				.append("isAutoResend", isAutoResend)
				.append("isRcdOrgBody", isRcdOrgBody)
				.append("isRcdPfmcLog", isRcdPfmcLog)
				.append("isRcdErrLog", isRcdErrLog).toString();
	}
}
