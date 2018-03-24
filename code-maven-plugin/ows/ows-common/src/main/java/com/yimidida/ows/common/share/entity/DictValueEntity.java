package com.yimidida.ows.common.share.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.yimidida.ows.base.server.tag.IDictValue;
import com.yimidida.ows.base.share.entity.BizBaseEntity;

@Table(value="t_common_dict_value")
public class DictValueEntity extends BizBaseEntity implements IDictValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column("dict_key")
	@NotEmpty(message="关键字不能为空")
	private String key;
	// 名称
	@Column("dict_value")
	@NotEmpty(message="值不能为空")
	private String value;
	/**
	 * 数据字典类型
	 */
	@Column("dict_code")
	@NotEmpty(message="数据字典类型不能为空")
	private String dictCode;
	// 显示顺序
	@Column("order_no")
	@NotNull(message="序号不能为空")
	private Integer orderNo;
	
	// 显示顺序
	@Column("paramOne")
	private String paramOne;
	// 显示顺序
	@Column("paramTwo")
	private String paramTwo;
	// 显示顺序
	@Column("paramThree")
	private String paramThree;
	// 描述
	@Column("remark")
	private String remark;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the paramOne
	 */
	public String getParamOne() {
		return paramOne;
	}

	/**
	 * @param paramOne the paramOne to set
	 */
	public void setParamOne(String paramOne) {
		this.paramOne = paramOne;
	}

	/**
	 * @return the paramTwo
	 */
	public String getParamTwo() {
		return paramTwo;
	}

	/**
	 * @param paramTwo the paramTwo to set
	 */
	public void setParamTwo(String paramTwo) {
		this.paramTwo = paramTwo;
	}

	/**
	 * @return the paramThree
	 */
	public String getParamThree() {
		return paramThree;
	}

	/**
	 * @param paramThree the paramThree to set
	 */
	public void setParamThree(String paramThree) {
		this.paramThree = paramThree;
	}

	@Override
	public int compareTo(IDictValue arg0) {
		return this.getOrderNo().compareTo(arg0.getOrderNo());
	}

}
