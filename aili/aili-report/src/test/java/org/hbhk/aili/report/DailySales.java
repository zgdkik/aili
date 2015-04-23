package org.hbhk.aili.report;

import java.io.Serializable;
import java.util.List;

public class DailySales implements Serializable {
	
	private static final long serialVersionUID = -1392195434041028912L;
	private String productNo;// 货物代号
	private String productName;// 货物名称
	private int number;
	private int money;// 货物价格
	private int id;// 货物唯一标识
	private List<DailyZoom> dailyZooms;// 货物销售地区（子报表）

	public DailySales(String productNo, String productName, int number,
			int money, List<DailyZoom> dailyZooms) {
		this.productNo = productNo;
		this.productName = productName;
		this.number = number;
		this.money = money;
		this.dailyZooms = dailyZooms;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<DailyZoom> getDailyZooms() {
		return dailyZooms;
	}

	public void setDailyZooms(List<DailyZoom> dailyZooms) {
		this.dailyZooms = dailyZooms;
	}
	
	

}
