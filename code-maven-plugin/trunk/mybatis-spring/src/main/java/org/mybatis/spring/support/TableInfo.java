package org.mybatis.spring.support;

import java.util.List;

public class TableInfo {

	// 表名
	private String name;
	private String comment;
	private String creator;
	private ColumnInfo pk;// 主键字段
	private List<ColumnInfo> columnList;
	
	private List<String> columnStrs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		if (comment == null || "".equals(comment)) {
			return name;
		}
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public ColumnInfo getPk() {
		return pk;
	}

	public void setPk(ColumnInfo pk) {
		this.pk = pk;
	}

	public List<ColumnInfo> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<ColumnInfo> columnList) {
		this.columnList = columnList;
	}

	public List<String> getColumnStrs() {
		return columnStrs;
	}

	public void setColumnStrs(List<String> columnStrs) {
		this.columnStrs = columnStrs;
	}

	
}
