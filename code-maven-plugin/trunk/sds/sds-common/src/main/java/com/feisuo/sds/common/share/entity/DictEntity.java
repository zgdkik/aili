package com.feisuo.sds.common.share.entity;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.feisuo.sds.base.share.entity.BizBaseEntity;

@Table(value="t_common_dict",dynamicUpdate=true)
public class DictEntity extends BizBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column("dict_code")
	private String dictCode;
	// 名称
	@Column("dict_name")
	private String dictName;
	// 描述
	@Column("remark")
	private String remark;
	
	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
