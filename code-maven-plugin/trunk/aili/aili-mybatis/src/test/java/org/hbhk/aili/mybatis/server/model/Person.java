package org.hbhk.aili.mybatis.server.model;

import java.util.List;

import org.hbhk.aili.mybatis.server.entity.BizBaseEntity;

public class Person extends BizBaseEntity{

	private static final long serialVersionUID = 5710475763489918931L;
	private String name;
	private List<Order> orderList;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}


}

