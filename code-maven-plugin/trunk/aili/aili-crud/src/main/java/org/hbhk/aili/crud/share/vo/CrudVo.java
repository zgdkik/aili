package org.hbhk.aili.crud.share.vo;

import java.io.Serializable;
import java.util.List;

import org.hbhk.aili.crud.share.entity.ColumnEntity;
import org.hbhk.aili.crud.share.entity.Condition;
import org.hbhk.aili.crud.share.entity.CrudEntity;

public class CrudVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8369749196369567613L;
	

	private CrudEntity crud;

	private List<ColumnEntity> columns;

	private List<Condition> conditions;

	
	public CrudEntity getCrud() {
		return crud;
	}

	public void setCrud(CrudEntity crud) {
		this.crud = crud;
	}

	public List<ColumnEntity> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnEntity> columns) {
		this.columns = columns;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

}
