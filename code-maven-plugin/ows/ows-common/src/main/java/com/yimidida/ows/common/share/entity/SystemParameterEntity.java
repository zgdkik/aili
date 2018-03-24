package com.yimidida.ows.common.share.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.yimidida.ows.base.share.entity.BizBaseEntity;

/**
 * 
 * ClassName: SystemParameterEntity
 * Description: TODO
 * Author: fanhoutao
 * Date: 2015年12月5日
 */
@Table(value="t_system_parameter")
public class SystemParameterEntity extends BizBaseEntity  {

	private static final long serialVersionUID = 5531497822690503L;
	//系统参数的健
	@Column("sys_key")
	@NotEmpty(message="参数关键字不能为空")
	private String sysKey;
	//系统参数的值
	@Column("sys_value")
	private String sysValue;
	public String getSysKey() {
		return sysKey;
	}
	public void setSysKey(String sysKey) {
		this.sysKey = sysKey;
	}
	public String getSysValue() {
		return sysValue;
	}
	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}

}
