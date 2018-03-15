package com.deppon.esb.management.agentuser.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * 代理用户信息
 * @author qiancheng
 *
 */
public class AgentUserInfo  extends BaseEntity {
	private static final long serialVersionUID = -4854586051573061283L;
	/**
	 * 代理编码
	 */
	private String userName;
	
	/**
	 * 代理名称
	 */
	private String agentName;
	
	/**
	 * 密码
	 */
	private String passwd;
	/**
	 * 状态：Y/N 可用/不可用
	 */
	private String status;

	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}	
}
