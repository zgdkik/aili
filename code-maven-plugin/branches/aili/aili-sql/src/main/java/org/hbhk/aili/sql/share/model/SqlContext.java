package org.hbhk.aili.sql.share.model;

import java.io.Serializable;
import java.util.List;

public class SqlContext  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7325994106155658679L;

	/** 执行的sql */
	private StringBuilder sql;

	/** 主键名称 */
	private String primaryKey;

	/** 参数，对应sql中的?号 */
	private List<Object> params;
	
	private String tabName;

	public SqlContext(StringBuilder sql, String primaryKey, List<Object> params) {
		this.sql = sql;
		this.primaryKey = primaryKey;
		this.params = params;
	}

	public StringBuilder getSql() {
		return sql;
	}

	public void setSql(StringBuilder sql) {
		this.sql = sql;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	
}
