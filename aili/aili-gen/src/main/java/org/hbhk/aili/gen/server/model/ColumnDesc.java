package org.hbhk.aili.gen.server.model;

public class ColumnDesc {

	/**
	 * 属性字段名称
	 */
	private String fieldName;

	private String colName;
	

	public ColumnDesc() {
	}

	public ColumnDesc(String fieldName, String colName) {
		this.fieldName = fieldName;
		this.colName = colName;
	}


	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	

	
}
