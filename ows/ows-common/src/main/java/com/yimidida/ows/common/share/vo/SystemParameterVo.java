package com.yimidida.ows.common.share.vo;

import java.io.Serializable;

/**
 * 
 * ClassName: SystemParameterVo
 * Description: TODO
 * Author: fanhoutao
 * Date: 2015年12月5日
 */
public class SystemParameterVo implements Serializable{

	
	private static final long serialVersionUID = 3809131469064715823L;

	private String id;
	
	private String sysKey;
	
	private String sysValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
