/*
 * PROJECT NAME: esb-management-audit
 * PACKAGE NAME: com.deppon.esb.management.audit.domain
 * FILE    NAME: EsbMessage.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.audit.domain;

import com.deppon.esb.management.common.entity.BaseEntity;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;

/**
 * ESB消息实体.
 * 
 * @author HuangHua
 * @date 2013-1-8 下午5:48:46
 */
public class EsbAuditInfo extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 3382532129763841671L;

	/** 消息头. */
	private EsbHeader esbHeader;
	/**
	 * 消息体.
	 */
	private String esbBody;

	/**
	 * 获取 消息头.
	 * 
	 * @return the 消息头
	 */
	public EsbHeader getEsbHeader() {
		return esbHeader;
	}

	/**
	 * 设置 消息头.
	 * 
	 * @param esbHeader
	 *            the new 消息头
	 */
	public void setEsbHeader(EsbHeader esbHeader) {
		this.esbHeader = esbHeader;
	}

	/**
	 * 获取 消息体.
	 * 
	 * @return the 消息体
	 */
	public String getEsbBody() {
		return esbBody;
	}

	/**
	 * 设置 消息体.
	 * 
	 * @param esbBody
	 *            the new 消息体
	 */
	public void setEsbBody(String esbBody) {
		this.esbBody = esbBody;
	}

}
