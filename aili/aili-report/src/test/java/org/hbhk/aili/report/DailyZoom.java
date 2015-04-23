package org.hbhk.aili.report;

import java.io.Serializable;
import java.math.BigDecimal;

public class DailyZoom implements Serializable {
	private static final long serialVersionUID = 6601800199395529117L;
	private String name;
	private BigDecimal amounts;

	public DailyZoom(String name, BigDecimal amounts) {
		this.name = name;
		this.amounts = amounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmounts() {
		return amounts;
	}

	public void setAmounts(BigDecimal amounts) {
		this.amounts = amounts;
	}
	
	
}
