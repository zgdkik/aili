package com.yimidida.ows.home.share.entity;

import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

/**
 * @author RHB
 *
 * 订单实体类
 */
@Table("TB_ORDER")
public class OrderInfo extends OrderBase{
	
	
	@Column("user_id")
	private String userId; //开单用户id

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	
}
