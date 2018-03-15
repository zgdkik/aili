package org.hbhk.aili.workflow.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;

public class WorkflowEntity extends BizBaseEntity {

	private static final long serialVersionUID = 202667854497199243L;
	
	@Column("workflow_no")
	private String workflowNo;
	 
	@Column("apply_user")
	private String applyUser;
	
	@Column("dept_code")
	private String deptCode;
	
	@Column("dept_name")
	private String deptName;
	
	@Column("assignee")
	private String assignee;
	
	@Column("post")
	private String post;
	
	
	@Column("file_path")
	private String filePath;


	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getWorkflowNo() {
		return workflowNo;
	}

	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
