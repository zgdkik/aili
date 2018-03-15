package org.hbhk.aili.workflow.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_wf_publish")
public class WorkflowPublish  extends BizBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6739143659977846492L;
	
	@Column("type")
	private String type;
	
	@Column("name")
	private String name;
	
	@Column("content")
	private String content;
	
	@Column("process_definition_id")
	private String processDefinitionId;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	

}
