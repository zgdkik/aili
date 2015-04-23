package org.hbhk.aili.report.share.model;

import java.io.Serializable;

public class DailyZoom implements Serializable {
	private static final long serialVersionUID = 6601800199395529117L;
	private String name;
	private double amounts;

	public DailyZoom(String name, double amounts) {
		this.name = name;
		this.amounts = amounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmounts() {
		return amounts;
	}

	public void setAmounts(double amounts) {
		this.amounts = amounts;
	}


	
	
}
