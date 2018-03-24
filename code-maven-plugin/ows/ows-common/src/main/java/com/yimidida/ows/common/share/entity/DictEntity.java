package com.yimidida.ows.common.share.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.yimidida.ows.base.share.entity.BizBaseEntity;
/**
 * 数据字典类型表对应实体
 * @author zb134373
 *
 */
@Table(value="t_common_dict")
public class DictEntity extends BizBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column("dict_code")
	@NotEmpty(message="数据字典类型编号不能为空")
	private String dictCode;
	// 名称
	@Column("dict_name")
	@NotEmpty(message="数据字典类型名称不能为空")
	private String dictName;
	// 描述
	@Column("remark")
	private String remark;
	//父节点code
	@Column("parent_dict_code")
	@NotEmpty(message="父节点编号不能为空")
	private String parentDictCode;
	//是否叶子节点
	@Column("IS_LEAF")
	private Integer isLeaf;
	
	
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

	public String getParentDictCode() {
		return parentDictCode;
	}

	public void setParentDictCode(String parentDictCode) {
		this.parentDictCode = parentDictCode;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

}
