package com.yimidida.ymdp.dao.server.support;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class ModelInfo implements Serializable {

	private static final long serialVersionUID = 2533773647509691060L;

	private String columns;

	private List<String> columnList;

	private List<String> fieldList;

	private Field[] columnFields;

	private Class<?> cls;

	private String table;
	private Field pkField;
	private String pk;
	private String pkName;
	private String version;
	private String versionName;
	
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	

}
