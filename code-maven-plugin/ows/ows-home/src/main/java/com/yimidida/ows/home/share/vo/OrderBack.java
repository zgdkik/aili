package com.yimidida.ows.home.share.vo;

import com.yimidida.ows.home.share.entity.OrderBase;

public class OrderBack {
	private String error;
	private OrderBase orderInfoObj;
	private boolean success;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public OrderBase getOrderInfoObj() {
		return orderInfoObj;
	}
	public void setOrderInfoObj(OrderBase orderInfoObj) {
		this.orderInfoObj = orderInfoObj;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	

}
