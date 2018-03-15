package org.hbhk.aili.sql.share.model;

import java.io.Serializable;

public class FieldColumn implements Serializable {
	private static final long serialVersionUID = 2685470419283716180L;

	private String field;

	private String column;

	private Class<?> javaType;
	/**
	 * 是否主键
	 */
	private boolean priKey;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Class<?> getJavaType() {
		return javaType;
	}

	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}

	public boolean isPriKey() {
		return priKey;
	}

	public void setPriKey(boolean priKey) {
		this.priKey = priKey;
	}

}
