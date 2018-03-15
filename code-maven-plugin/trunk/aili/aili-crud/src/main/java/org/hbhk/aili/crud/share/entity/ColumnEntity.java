package org.hbhk.aili.crud.share.entity;

import org.hbhk.aili.base.share.entity.BaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_curd_column")
public class ColumnEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4166977411526132421L;

	@Column("crud_code")
	private String crudCode;

	@Column("field")
	private String field;

	@Column("title")
	private String title;

	@Column("width")
	private Integer width;
	
	@Column("type")
	private String type;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getCrudCode() {
		return crudCode;
	}

	public void setCrudCode(String crudCode) {
		this.crudCode = crudCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
