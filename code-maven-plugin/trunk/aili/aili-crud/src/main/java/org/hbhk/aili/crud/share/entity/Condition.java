package org.hbhk.aili.crud.share.entity;

import org.hbhk.aili.base.share.entity.BaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_curd_condition")
public class Condition extends  BaseEntity{

	
	private static final long serialVersionUID = 3254923146681535064L;
	@Column("crud_code")
	private String crudCode;
	
	@Column("condition_lable")
	private String conditionLabel;
	
	@Column("condition_name")
	private String conditionName;
	@Column("condition_type")
	private String conditionType;

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public String getCrudCode() {
		return crudCode;
	}

	public void setCrudCode(String crudCode) {
		this.crudCode = crudCode;
	}

	public String getConditionLabel() {
		return conditionLabel;
	}

	public void setConditionLabel(String conditionLabel) {
		this.conditionLabel = conditionLabel;
	}

	
	
}
