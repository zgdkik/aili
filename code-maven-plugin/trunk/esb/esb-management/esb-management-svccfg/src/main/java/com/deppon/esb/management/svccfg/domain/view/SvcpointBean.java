package com.deppon.esb.management.svccfg.domain.view;

/**
 * 封装esb服务管理模块查询结果.
 * 
 * @author qiancheng
 */
public class SvcpointBean {
	
	/** The id. */
	private String id;

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
	
	/** The back svc id. */
	private String backSvcId;//后端服务id
	
	/** The back svc name. */
	private String backSvcName;//后端服务名称
	
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
	 * 获取 id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 设置 id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取 svc name.
	 * 
	 * @return the svc name
	 */
	public String getSvcName() {
		return svcName;
	}
	
	/**
	 * 设置 svc name.
	 * 
	 * @param svcName
	 *            the new svc name
	 */
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}
	
	/**
	 * 获取 svc code.
	 * 
	 * @return the svc code
	 */
	public String getSvcCode() {
		return svcCode;
	}
	
	/**
	 * 设置 svc code.
	 * 
	 * @param svcCode
	 *            the new svc code
	 */
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}
	
	/**
	 * 获取 svc provd id.
	 * 
	 * @return the svc provd id
	 */
	public String getSvcProvdId() {
		return svcProvdId;
	}
	
	/**
	 * 设置 svc provd id.
	 * 
	 * @param svcProvdId
	 *            the new svc provd id
	 */
	public void setSvcProvdId(String svcProvdId) {
		this.svcProvdId = svcProvdId;
	}
	
	/**
	 * 获取 svc agrmt.
	 * 
	 * @return the svc agrmt
	 */
	public String getSvcAgrmt() {
		return svcAgrmt;
	}
	
	/**
	 * 设置 svc agrmt.
	 * 
	 * @param svcAgrmt
	 *            the new svc agrmt
	 */
	public void setSvcAgrmt(String svcAgrmt) {
		this.svcAgrmt = svcAgrmt;
	}
	
	/**
	 * 获取 svc addr.
	 * 
	 * @return the svc addr
	 */
	public String getSvcAddr() {
		return svcAddr;
	}
	
	/**
	 * 设置 svc addr.
	 * 
	 * @param svcAddr
	 *            the new svc addr
	 */
	public void setSvcAddr(String svcAddr) {
		this.svcAddr = svcAddr;
	}
	
	/**
	 * 获取 frnt or bck.
	 * 
	 * @return the frnt or bck
	 */
	public String getFrntOrBck() {
		return frntOrBck;
	}
	
	/**
	 * 设置 frnt or bck.
	 * 
	 * @param frntOrBck
	 *            the new frnt or bck
	 */
	public void setFrntOrBck(String frntOrBck) {
		this.frntOrBck = frntOrBck;
	}
	
	/**
	 * 获取 back svc id.
	 * 
	 * @return the back svc id
	 */
	public String getBackSvcId() {
		return backSvcId;
	}
	
	/**
	 * 设置 back svc id.
	 * 
	 * @param backSvcId
	 *            the new back svc id
	 */
	public void setBackSvcId(String backSvcId) {
		this.backSvcId = backSvcId;
	}
	
	/**
	 * 获取 back svc name.
	 * 
	 * @return the back svc name
	 */
	public String getBackSvcName() {
		return backSvcName;
	}
	
	/**
	 * 设置 back svc name.
	 * 
	 * @param backSvcName
	 *            the new back svc name
	 */
	public void setBackSvcName(String backSvcName) {
		this.backSvcName = backSvcName;
	}
	
	/**
	 * 获取 response type.
	 * 
	 * @return the response type
	 */
	public String getResponseType() {
		return responseType;
	}
	
	/**
	 * 设置 response type.
	 * 
	 * @param responseType
	 *            the new response type
	 */
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	
	/**
	 * 获取 checks if is auto resend.
	 * 
	 * @return the checks if is auto resend
	 */
	public Boolean getIsAutoResend() {
		return isAutoResend;
	}
	
	/**
	 * 设置 checks if is auto resend.
	 * 
	 * @param isAutoResend
	 *            the new checks if is auto resend
	 */
	public void setIsAutoResend(Boolean isAutoResend) {
		this.isAutoResend = isAutoResend;
	}
	
	/**
	 * 获取 checks if is rcd org body.
	 * 
	 * @return the checks if is rcd org body
	 */
	public Boolean getIsRcdOrgBody() {
		return isRcdOrgBody;
	}
	
	/**
	 * 设置 checks if is rcd org body.
	 * 
	 * @param isRcdOrgBody
	 *            the new checks if is rcd org body
	 */
	public void setIsRcdOrgBody(Boolean isRcdOrgBody) {
		this.isRcdOrgBody = isRcdOrgBody;
	}
	
	/**
	 * 获取 checks if is rcd pfmc log.
	 * 
	 * @return the checks if is rcd pfmc log
	 */
	public Boolean getIsRcdPfmcLog() {
		return isRcdPfmcLog;
	}
	
	/**
	 * 设置 checks if is rcd pfmc log.
	 * 
	 * @param isRcdPfmcLog
	 *            the new checks if is rcd pfmc log
	 */
	public void setIsRcdPfmcLog(Boolean isRcdPfmcLog) {
		this.isRcdPfmcLog = isRcdPfmcLog;
	}
	
	/**
	 * 获取 checks if is rcd err log.
	 * 
	 * @return the checks if is rcd err log
	 */
	public Boolean getIsRcdErrLog() {
		return isRcdErrLog;
	}
	
	/**
	 * 设置 checks if is rcd err log.
	 * 
	 * @param isRcdErrLog
	 *            the new checks if is rcd err log
	 */
	public void setIsRcdErrLog(Boolean isRcdErrLog) {
		this.isRcdErrLog = isRcdErrLog;
	}
	
	/**
	 * 获取 promptly notify.
	 * 
	 * @return the promptly notify
	 */
	public Boolean getPromptlyNotify() {
		return promptlyNotify;
	}
	
	/**
	 * 设置 promptly notify.
	 * 
	 * @param promptlyNotify
	 *            the new promptly notify
	 */
	public void setPromptlyNotify(Boolean promptlyNotify) {
		this.promptlyNotify = promptlyNotify;
	}	
}
