package org.hbhk.aili.support.server.excel;

import java.util.Date;

public class TestEntity {
	private int sn;
	private String name;

	private Date lineDate;
	private int intValue;
	private float floatValue;

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLineDate() {
		return lineDate;
	}

	public void setLineDate(Date lineDate) {
		this.lineDate = lineDate;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}

}
