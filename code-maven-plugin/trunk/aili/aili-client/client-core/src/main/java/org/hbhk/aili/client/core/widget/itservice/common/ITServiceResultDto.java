package org.hbhk.aili.client.core.widget.itservice.common;

import java.io.Serializable;

/**
 * IT服务台上报返回结果
 *
 */
public class ITServiceResultDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5451444640831598920L;
	/**
	 * 一键上报是否成功
	 */
	private Boolean isSuccess;
	/**
	 * 失败原因
	 */
	private String failReason;
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
	
}
