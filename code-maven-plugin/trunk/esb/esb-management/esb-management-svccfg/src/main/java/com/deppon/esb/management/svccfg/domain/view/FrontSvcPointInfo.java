package com.deppon.esb.management.svccfg.domain.view;

import com.deppon.esb.management.common.entity.BaseEntity;
import com.deppon.esb.management.svccfg.domain.SvcPointInfo;

/**
 * The Class FrontSvcPointInfo.
 * 
 * @Description 前端服务信息
 * @author HuangHua
 * @date 2012-03-08 14:43:13 create
 * @date 2012-03-19 19:40:29 modify ==>> modify [isSaveOrgMsg->isRcdOrgBody],
 *       add [isRcdPfmcLog],[isRcdErrLog]
 */
public class FrontSvcPointInfo extends BaseEntity {

	/** 常量定义 serialVersionUID. */
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
	
	/** The is auto resend. */
	private Boolean isAutoResend;// 是否运行ESB自动重发
	
	/** The is rcd org body. */
	private Boolean isRcdOrgBody;// 是否保存原始报文
	
	/** The is rcd pfmc log. */
	private Boolean isRcdPfmcLog;// 是否记录性能日志
	
	/** The is rcd err log. */
	private Boolean isRcdErrLog;// 是否记录异常日志
	
	/** The backsvc. */
	private SvcPointInfo backsvc;//关联后端服务

	/**
	 * 获取 backsvc.
	 * 
	 * @return the backsvc
	 */
	public SvcPointInfo getBacksvc() {
		return backsvc;
	}

	/**
	 * 设置 backsvc.
	 * 
	 * @param backsvc
	 *            the new backsvc
	 */
	public void setBacksvc(SvcPointInfo backsvc) {
		this.backsvc = backsvc;
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
	 * Checks if is auto resend.
	 * 
	 * @return the boolean
	 */
	public Boolean isAutoResend() {
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
	 * Checks if is rcd org body.
	 * 
	 * @return 是否记录原始报文
	 */
	public Boolean isRcdOrgBody() {
		return isRcdOrgBody;
	}

	/**
	 * Checks if is rcd pfmc log.
	 * 
	 * @return 是否记录性能日志
	 */
	public Boolean isRcdPfmcLog() {
		return isRcdPfmcLog;
	}

	/**
	 * Checks if is rcd err log.
	 * 
	 * @return 是否记录异常日志
	 */
	public Boolean isRcdErrLog() {
		return isRcdErrLog;
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
	 * 设置 checks if is rcd pfmc log.
	 * 
	 * @param isRcdPfmcLog
	 *            the new checks if is rcd pfmc log
	 */
	public void setIsRcdPfmcLog(Boolean isRcdPfmcLog) {
		this.isRcdPfmcLog = isRcdPfmcLog;
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

}
