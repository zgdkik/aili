package com.deppon.esb.management.svccfg.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * 前端服务与后端服务关联实体类.
 * 
 * @author qiancheng
 */
public class SvcPointRelationInfo extends BaseEntity {
	
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -1587859988124715860L;
	
	/** The id. */
	private String id;
	
	/** 前端服务编码. */
	private String frontSvcCode;
	
	/** 后端服务编码. */
	private String backSvcCode;
	
	/** 
	 * 获取ID
	 * @author HuangHua
	 * @date 2012-12-28 下午2:48:41
	 * @see com.deppon.esb.management.common.entity.BaseEntity#getId()
	 */
	public String getId() {
		return id;
	}
	
	/** 
	 * 设置ID
	 * @author HuangHua
	 * @date 2012-12-28 下午2:48:41
	 * @see com.deppon.esb.management.common.entity.BaseEntity#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取 前端服务编码.
	 * 
	 * @return the 前端服务编码
	 */
	public String getFrontSvcCode() {
		return frontSvcCode;
	}
	
	/**
	 * 设置 前端服务编码.
	 * 
	 * @param frontSvcCode
	 *            the new 前端服务编码
	 */
	public void setFrontSvcCode(String frontSvcCode) {
		this.frontSvcCode = frontSvcCode;
	}
	
	/**
	 * 获取 后端服务编码.
	 * 
	 * @return the 后端服务编码
	 */
	public String getBackSvcCode() {
		return backSvcCode;
	}
	
	/**
	 * 设置 后端服务编码.
	 * 
	 * @param backSvcCode
	 *            the new 后端服务编码
	 */
	public void setBackSvcCode(String backSvcCode) {
		this.backSvcCode = backSvcCode;
	}
	
}
