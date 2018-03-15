package org.hbhk.zlj.backend.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_crud_base")
public class CrudEntity extends BizBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5904582848110742914L;

	@Column("name")
	private String name;

	@Column("code")
	private String code;

	@Column("q_sql")
	private String sql;

	@Column("add_sql")
	private String addSql;

	@Column("up_sql")
	private String upSql;

	@Column("del_sql")
	private String delSql;

	@Column("parent_menu_code")
	private String parentMenuCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentMenuCode() {
		return parentMenuCode;
	}

	public void setParentMenuCode(String parentMenuCode) {
		this.parentMenuCode = parentMenuCode;
	}

}
