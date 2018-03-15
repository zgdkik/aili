/*
 * PROJECT NAME: esb-management-excptn
 * PACKAGE NAME: com.deppon.esb.management.excptn.domain
 * FILE    NAME: CommonExceptionInfo.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.excptn.domain;

import com.deppon.esb.management.common.entity.BaseEntity;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;
import com.deppon.esb.management.exptn.generate.CommonExceptionInfo;

/**
 * ESB二期异常对象.
 * 
 * @author HuangHua
 * @date 2013-1-11 上午10:45:26
 */
public class EsbExceptionInfo extends BaseEntity{
	
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 2215308551077521997L;

	/** 头信息. */
	private EsbHeader esbHeader;
	
	/**
	 * 发生异常所在的机器IP,方便异常信息定位
	 */
	private String hostIp;

	/** ESB二期公共异常. */
	private CommonExceptionInfo commonExceptionInfo;

	/**
	 * 获取 头信息.
	 * 
	 * @return the 头信息
	 */
	public EsbHeader getEsbHeader() {
		return esbHeader;
	}

	/**
	 * 设置 头信息.
	 * 
	 * @param esbHeader
	 *            the new 头信息
	 */
	public void setEsbHeader(EsbHeader esbHeader) {
		this.esbHeader = esbHeader;
	}

	/**
	 * 获取 eSB二期公共异常.
	 * 
	 * @return the eSB二期公共异常
	 */
	public CommonExceptionInfo getCommonExceptionInfo() {
		return commonExceptionInfo;
	}

	/**
	 * 设置 eSB二期公共异常.
	 * 
	 * @param commonExceptionInfo
	 *            the new eSB二期公共异常
	 */
	public void setCommonExceptionInfo(CommonExceptionInfo commonExceptionInfo) {
		this.commonExceptionInfo = commonExceptionInfo;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

}
