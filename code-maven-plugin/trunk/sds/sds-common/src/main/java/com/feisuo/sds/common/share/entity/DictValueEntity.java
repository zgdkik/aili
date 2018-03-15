package com.feisuo.sds.common.share.entity;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.feisuo.sds.base.server.tag.IDictValue;
import com.feisuo.sds.base.share.entity.BizBaseEntity;

@Table(value="t_common_dict_value",dynamicUpdate=true)
public class DictValueEntity extends BizBaseEntity implements IDictValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column("dict_key")
	private String key;
	// 名称
	@Column("dict_value")
	private String value;
	/**
	 * 数据字典类型
	 */
	@Column("dict_code")
	private String dictCode;
	// 显示顺序
	@Column("order_no")
	private Integer orderNo;
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

	@Override
	public int compareTo(IDictValue arg0) {
		return this.getOrderNo().compareTo(arg0.getOrderNo());
	}

}
