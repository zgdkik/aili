package com.deppon.esb.management.agentuser.view;

import com.deppon.esb.management.common.entity.FormEntity;

public class AgentUserQueryBean extends FormEntity{
	private static final long serialVersionUID = 1061722580231100174L;
	private String userName;
	private String agentName;
	private String status;

	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
