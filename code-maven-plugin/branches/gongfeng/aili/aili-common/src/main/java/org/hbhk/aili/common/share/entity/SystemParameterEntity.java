package org.hbhk.aili.common.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hibernate.validator.constraints.NotEmpty;

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
