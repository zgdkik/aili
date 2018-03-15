package org.hbhk.aili.workflow.share.vo;

import org.hbhk.aili.base.share.entity.BizBaseEntity;

/**
 * 用户任务实体类
 * 
 */
public class UserTaskVo extends BizBaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1626961511745418793L;
	/** 执行ID **/
	private String executionId;
	/** 流程ID **/
	private String procinstId;
	/** 流程KEY **/
	private String prodefId;
	/** 当前处理人 **/
	private String assignee;
	/** 提交人  **/
	private String userName;
	/** 记录ID **/
	private String recordId;

	/** 提交动作 **/
	private String action;
	/** 审批意见 **/
	private String opinion;
	/** 流程定义key **/
	private String definitionId;
	/** 流程ID **/
	private String instanceId;

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getProcinstId() {
		return procinstId;
	}

	public void setProcinstId(String procinstId) {
		this.procinstId = procinstId;
	}

	public String getProdefId() {
		return prodefId;
	}

	public void setProdefId(String prodefId) {
		this.prodefId = prodefId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
}
