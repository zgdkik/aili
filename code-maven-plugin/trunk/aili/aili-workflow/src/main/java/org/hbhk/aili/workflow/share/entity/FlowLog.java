package org.hbhk.aili.workflow.share.entity;

import org.hbhk.aili.base.share.entity.BaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_wf_flow_log")
public class FlowLog extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6108011882360179786L;
	/** 审核用户 **/
	@Column("username")
	private String userName;
	/** 用户类型(0:超级管理员、1:普通管理员、2:普通用户、3:开发者) **/
	@Column("user_type")
	private String userType;
	/** 操作类型(1:提交成功、2:审批通过、3:审批不通过) **/
	@Column("action")
	private String action;
	/** 意见 **/
	@Column("opinion")
	private String opinion;
	/** 任务Id **/
	@Column("task_id")
	private String taskId;
	/** 任务名称 **/
	@Column("task_name")
	private String taskName;
	/** 流程Id **/
	@Column("flow_id")
	private String flowId;
	/** 记录Id **/
	@Column("record_id")
	private String recordId;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

}
