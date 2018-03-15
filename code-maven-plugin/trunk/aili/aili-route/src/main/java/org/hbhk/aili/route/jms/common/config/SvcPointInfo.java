package org.hbhk.aili.route.jms.common.config;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hbhk.aili.route.jms.common.entity.BaseEntity;

/**
 * The Class SvcPointInfo.
 * 
 * @Description 服务信息
 * @author HuangHua
 * @date 2012-03-08 14:43:13 create
 * @date 2012-03-19 19:40:29 modify ==>> modify [isSaveOrgMsg->isRcdOrgBody],
 *       add [isRcdPfmcLog],[isRcdErrLog]
 */
public class SvcPointInfo extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -591541223516578458L;
	
	/** The svc name. */
	private String svcName;// 服务名
	
	/** The svc code. */
	private String svcCode;// 服务编码
	
	/** The svc provd id. */
	private String svcProvdId;// 服务提供者ID
	
	/** The svc agrmt. */
	private String svcAgrmt;// 服务协议
	
	/** The svc addr. */
	private String svcAddr;// 服务地址
	
	/** The frnt or bck. */
	private String frntOrBck;// 是属于前端还是后端
	
	/** The response type. */
	private String responseType;
	
	/** The is auto resend. */
	private Boolean isAutoResend;// 是否运行ESB自动重发
	
	/** The is rcd org body. */
	private Boolean isRcdOrgBody;// 是否保存原始报文
	
	/** The is rcd pfmc log. */
	private Boolean isRcdPfmcLog;// 是否记录性能日志
	
	/** The is rcd err log. */
	private Boolean isRcdErrLog;// 是否记录异常日志
	
	/** The promptly notify. */
	private Boolean promptlyNotify;// 是否需要及时通知，true-在failureProcess里就会发送通知。

	/**
	 * Gets the response type.
	 * 
	 * @return the response type
	 */
	public String getResponseType() {
		return responseType;
	}

	/**
	 * Sets the response type.
	 * 
	 * @param responseType
	 *            the new response type
	 */
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	/**
	 * Gets the svc name.
	 * 
	 * @return the svc name
	 */
	public String getSvcName() {
		return svcName;
	}

	/**
	 * Sets the svc name.
	 * 
	 * @param svcName
	 *            the new svc name
	 */
	public void setSvcName(String svcName) {
		this.svcName = svcName;
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
	 * Sets the svc code.
	 * 
	 * @param svcCode
	 *            the new svc code
	 */
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}

	/**
	 * Gets the svc provd id.
	 * 
	 * @return the svc provd id
	 */
	public String getSvcProvdId() {
		return svcProvdId;
	}

	/**
	 * Sets the svc provd id.
	 * 
	 * @param svcProvdId
	 *            the new svc provd id
	 */
	public void setSvcProvdId(String svcProvdId) {
		this.svcProvdId = svcProvdId;
	}

	/**
	 * Gets the svc agrmt.
	 * 
	 * @return the svc agrmt
	 */
	public String getSvcAgrmt() {
		return svcAgrmt;
	}

	/**
	 * Sets the svc agrmt.
	 * 
	 * @param svcAgrmt
	 *            the new svc agrmt
	 */
	public void setSvcAgrmt(String svcAgrmt) {
		this.svcAgrmt = svcAgrmt;
	}

	/**
	 * Gets the svc addr.
	 * 
	 * @return the svc addr
	 */
	public String getSvcAddr() {
		return svcAddr;
	}

	/**
	 * Sets the svc addr.
	 * 
	 * @param svcAddr
	 *            the new svc addr
	 */
	public void setSvcAddr(String svcAddr) {
		this.svcAddr = svcAddr;
	}

	/**
	 * Gets the frnt or bck.
	 * 
	 * @return the frnt or bck
	 */
	public String getFrntOrBck() {
		return frntOrBck;
	}

	/**
	 * Sets the frnt or bck.
	 * 
	 * @param frntOrBck
	 *            the new frnt or bck
	 */
	public void setFrntOrBck(String frntOrBck) {
		this.frntOrBck = frntOrBck;
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
	 * Gets the checks if is rcd org body.
	 * 
	 * @return 是否记录原始报文
	 */
	public Boolean getIsRcdOrgBody() {
		return isRcdOrgBody;
	}

	/**
	 * Gets the checks if is rcd pfmc log.
	 * 
	 * @return 是否记录性能日志
	 */
	public Boolean getIsRcdPfmcLog() {
		return isRcdPfmcLog;
	}

	/**
	 * Gets the checks if is rcd err log.
	 * 
	 * @return 是否记录异常日志
	 */
	public Boolean getIsRcdErrLog() {
		return isRcdErrLog;
	}

	/**
	 * Sets the checks if is rcd org body.
	 * 
	 * @param isRcdOrgBody
	 *            the new checks if is rcd org body
	 */
	public void setIsRcdOrgBody(Boolean isRcdOrgBody) {
		this.isRcdOrgBody = isRcdOrgBody;
	}

	/**
	 * Sets the checks if is rcd pfmc log.
	 * 
	 * @param isRcdPfmcLog
	 *            the new checks if is rcd pfmc log
	 */
	public void setIsRcdPfmcLog(Boolean isRcdPfmcLog) {
		this.isRcdPfmcLog = isRcdPfmcLog;
	}

	/**
	 * Sets the checks if is rcd err log.
	 * 
	 * @param isRcdErrLog
	 *            the new checks if is rcd err log
	 */
	public void setIsRcdErrLog(Boolean isRcdErrLog) {
		this.isRcdErrLog = isRcdErrLog;
	}

	/**
	 * Gets the promptly notify.
	 * 
	 * @return the promptly notify
	 */
	public Boolean getPromptlyNotify() {
		return promptlyNotify;
	}

	/**
	 * Sets the promptly notify.
	 * 
	 * @param promptlyNotify
	 *            the new promptly notify
	 */
	public void setPromptlyNotify(Boolean promptlyNotify) {
		this.promptlyNotify = promptlyNotify;
	}

	/** 
	 * toString
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("svcName", svcName).append("svcCode", svcCode)
				.append("svcProvdId", svcProvdId).append("svcAgrmt", svcAgrmt).append("svcAddr", svcAddr).append("frntOrBck", frntOrBck)
				.append("responseType", responseType).append("isAutoResend", isAutoResend).append("isRcdOrgBody", isRcdOrgBody)
				.append("isRcdPfmcLog", isRcdPfmcLog).append("isRcdErrLog", isRcdErrLog).toString();
	}
}
