package com.deppon.esb.management.excptn.domain;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * ESB异常信息实体.
 * 
 * @author HuangHua
 * @date 2012-12-28 下午2:37:57
 */
public class ExceptionInfo extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -7558538087044127184L;

	/** The msg id. */
	private String msgId;

	/** The svc code. */
	private String svcCode;

	/** The create time. */
	private Date createTime;

	/** The exception code. */
	private String exceptionCode;

	/** The dtl excptn. */
	private String dtlExcptn;// 异常详细信息

	/** The biz1. */
	private String biz1;

	/** The biz2. */
	private String biz2;

	/** The orgn msg. */
	private byte[] orgnMsg;// 原始报文

	/** The retry count. */
	private Integer retryCount;// 重调次数

	/** The statstc flg. */
	private Integer statstcFlg;// 统计标记

	/** The from endpoint uri. */
	private String fromEndpointURI;// 起始节点URI

	/** The is auto resend. */
	private Boolean isAutoResend;// 是否允许重发

	/** The esb host name. */
	private String esbHostName;// esb主机名

	/** The esb ip. */
	private String esbIp;// esb主机IP

	/** The redo type. */
	private String redoType;// 重发类型

	/** The method. */
	private String method;// 接口调用方法

	/** The operation namespace. */
	private String operationNamespace;// 命名空间

	/** The request id. */
	private String requestId;

	/** The is system send. */
	private Boolean isSystemSend;// 是否系统发送的异常信息；

	/**
	 * Gets the request id.
	 * 
	 * @return the request id
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * Sets the request id.
	 * 
	 * @param requestId
	 *            the new request id
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/** 参考esb-common包下面的ExceptionType里边的常量；. */
	private Integer exceptionType;// 异常类型（可恢复、不可恢复）

	/**
	 * Gets the msg id.
	 * 
	 * @return the msg id
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * Sets the msg id.
	 * 
	 * @param msgId
	 *            the new msg id
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * Gets the svc code.
	 * 
	 * @return the svc code
	 */
	public String getSvcCode() {
		return svcCode;
	}

	/**
	 * 获取 参考esb-common包下面的ExceptionType里边的常量；.
	 * 
	 * @return the 参考esb-common包下面的ExceptionType里边的常量；
	 */
	public Integer getExceptionType() {
		return exceptionType;
	}

	/**
	 * 设置 参考esb-common包下面的ExceptionType里边的常量；.
	 * 
	 * @param exceptionType
	 *            the new 参考esb-common包下面的ExceptionType里边的常量；
	 */
	public void setExceptionType(Integer exceptionType) {
		this.exceptionType = exceptionType;
	}

	/**
	 * Sets the svc code.
	 * 
	 * @param svcCode
	 *            the new svc code
	 */
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}

	/**
	 * 获取创建时间
	 * 
	 * @author HuangHua
	 * @date 2012-12-28 下午2:39:40
	 * @see com.deppon.esb.management.common.entity.BaseEntity#getCreateTime()
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @author HuangHua
	 * @date 2012-12-28 下午2:39:40
	 * @see com.deppon.esb.management.common.entity.BaseEntity#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * Gets the exception code.
	 * 
	 * @return the exception code
	 */
	public String getExceptionCode() {
		return exceptionCode;
	}

	/**
	 * Sets the exception code.
	 * 
	 * @param exceptionCode
	 *            the new exception code
	 */
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	/**
	 * Gets the dtl excptn.
	 * 
	 * @return the dtl excptn
	 */
	public String getDtlExcptn() {
		return dtlExcptn;
	}

	/**
	 * Sets the dtl excptn.
	 * 
	 * @param dtlExcptn
	 *            the new dtl excptn
	 */
	public void setDtlExcptn(String dtlExcptn) {
		this.dtlExcptn = dtlExcptn;
	}

	/**
	 * Gets the biz1.
	 * 
	 * @return the biz1
	 */
	public String getBiz1() {
		return biz1;
	}

	/**
	 * Gets the checks if is system send.
	 * 
	 * @return the checks if is system send
	 */
	public Boolean getIsSystemSend() {
		return isSystemSend;
	}

	/**
	 * Sets the checks if is system send.
	 * 
	 * @param isSystemSend
	 *            the new checks if is system send
	 */
	public void setIsSystemSend(Boolean isSystemSend) {
		this.isSystemSend = isSystemSend;
	}

	/**
	 * Sets the biz1.
	 * 
	 * @param biz1
	 *            the new biz1
	 */
	public void setBiz1(String biz1) {
		this.biz1 = biz1;
	}

	/**
	 * Gets the biz2.
	 * 
	 * @return the biz2
	 */
	public String getBiz2() {
		return biz2;
	}

	/**
	 * Sets the biz2.
	 * 
	 * @param biz2
	 *            the new biz2
	 */
	public void setBiz2(String biz2) {
		this.biz2 = biz2;
	}

	/**
	 * Gets the orgn msg.
	 * 
	 * @return the orgn msg
	 */
	public byte[] getOrgnMsg() {
		return orgnMsg;
	}

	/**
	 * Sets the orgn msg.
	 * 
	 * @param orgnMsg
	 *            the new orgn msg
	 */
	public void setOrgnMsg(byte[] orgnMsg) {
		this.orgnMsg = orgnMsg;
	}

	/**
	 * Gets the retry count.
	 * 
	 * @return the retry count
	 */
	public Integer getRetryCount() {
		return retryCount;
	}

	/**
	 * Sets the retry count.
	 * 
	 * @param retryCount
	 *            the new retry count
	 */
	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	/**
	 * Gets the statstc flg.
	 * 
	 * @return the statstc flg
	 */
	public Integer getStatstcFlg() {
		return statstcFlg;
	}

	/**
	 * Sets the statstc flg.
	 * 
	 * @param statstcFlg
	 *            the new statstc flg
	 */
	public void setStatstcFlg(Integer statstcFlg) {
		this.statstcFlg = statstcFlg;
	}

	/**
	 * Gets the from endpoint uri.
	 * 
	 * @return the from endpoint uri
	 */
	public String getFromEndpointURI() {
		return fromEndpointURI;
	}

	/**
	 * Sets the from endpoint uri.
	 * 
	 * @param fromEndpointURI
	 *            the new from endpoint uri
	 */
	public void setFromEndpointURI(String fromEndpointURI) {
		this.fromEndpointURI = fromEndpointURI;
	}

	/**
	 * Gets the checks if is auto resend.
	 * 
	 * @return the checks if is auto resend
	 */
	public Boolean getIsAutoResend() {
		return isAutoResend;
	}

	/**
	 * Sets the checks if is auto resend.
	 * 
	 * @param isAutoResend
	 *            the new checks if is auto resend
	 */
	public void setIsAutoResend(Boolean isAutoResend) {
		this.isAutoResend = isAutoResend;
	}

	/**
	 * Gets the esb host name.
	 * 
	 * @return the esb host name
	 */
	public String getEsbHostName() {
		return esbHostName;
	}

	/**
	 * Sets the esb host name.
	 * 
	 * @param esbHostName
	 *            the new esb host name
	 */
	public void setEsbHostName(String esbHostName) {
		this.esbHostName = esbHostName;
	}

	/**
	 * Gets the esb ip.
	 * 
	 * @return the esb ip
	 */
	public String getEsbIp() {
		return esbIp;
	}

	/**
	 * Sets the esb ip.
	 * 
	 * @param esbIp
	 *            the new esb ip
	 */
	public void setEsbIp(String esbIp) {
		this.esbIp = esbIp;
	}

	/**
	 * 重写toString方法
	 * 
	 * @author HuangHua
	 * @date 2012-12-28 下午2:39:40
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("esbHostName", esbHostName).append("esbIp", esbIp)
				.append("fromEndpointURI", fromEndpointURI)
				.append("msgId", msgId).append("svcCode", svcCode)
				.append("createTime", createTime)
				.append("exceptionCode", exceptionCode)
				.append("method", method).append("redoType", redoType)
				.append("isSystemSend", isSystemSend).toString();
	}

	/**
	 * Gets the redo type.
	 * 
	 * @return the redo type
	 */
	public String getRedoType() {
		return redoType;
	}

	/**
	 * Sets the redo type.
	 * 
	 * @param redoType
	 *            the new redo type
	 */
	public void setRedoType(String redoType) {
		this.redoType = redoType;
	}

	/**
	 * Gets the method.
	 * 
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets the method.
	 * 
	 * @param method
	 *            the new method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Gets the operation namespace.
	 * 
	 * @return the operation namespace
	 */
	public String getOperationNamespace() {
		return operationNamespace;
	}

	/**
	 * Sets the operation namespace.
	 * 
	 * @param operationNamespace
	 *            the new operation namespace
	 */
	public void setOperationNamespace(String operationNamespace) {
		this.operationNamespace = operationNamespace;
	}

}
