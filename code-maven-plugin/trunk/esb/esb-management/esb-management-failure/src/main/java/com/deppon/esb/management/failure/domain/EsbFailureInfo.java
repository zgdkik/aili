package com.deppon.esb.management.failure.domain;

import com.deppon.esb.management.common.entity.BaseEntity;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;

/**
 * 失败消息实体.
 */
public class EsbFailureInfo extends BaseEntity {

	private static final long serialVersionUID = 7162836962019461241L;
	
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
