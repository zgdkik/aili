package org.mybatis.spring.support;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ModelInfo implements Serializable {

	private static final long serialVersionUID = 2533773647509691060L;

	private String columns;

	private List<String> columnList;

	private List<String> fieldList;

	private Field[] columnFields;

	private Class<?> cls;

	private String table;

	private String pk;

	private Field pkField;

	private String pkName;

	private Map<String, String> fieldColumnMap;
	private Map<String, Boolean> fieldLikeMap;

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public List<String> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}

	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public Field[] getColumnFields() {
		return columnFields;
	}

	public void setColumnFields(Field[] columnFields) {
		this.columnFields = columnFields;
	}

	public Class<?> getCls() {
		return cls;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public Map<String, String> getFieldColumnMap() {
		return fieldColumnMap;
	}

	public void setFieldColumnMap(Map<String, String> fieldColumnMap) {
		this.fieldColumnMap = fieldColumnMap;
	}

	public Map<String, Boolean> getFieldLikeMap() {
		return fieldLikeMap;
	}

	public void setFieldLikeMap(Map<String, Boolean> fieldLikeMap) {
		this.fieldLikeMap = fieldLikeMap;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public Field getPkField() {
		return pkField;
	}

	public void setPkField(Field pkField) {
		this.pkField = pkField;
	}

}
