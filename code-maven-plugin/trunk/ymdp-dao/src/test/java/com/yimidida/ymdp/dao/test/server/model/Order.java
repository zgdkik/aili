package com.yimidida.ymdp.dao.test.server.model;

import com.yimidida.ymdp.dao.test.server.entity.BizBaseEntity;

public class Order extends BizBaseEntity{
	private static final long serialVersionUID = -8042656832011605832L;
	private double price;
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


}