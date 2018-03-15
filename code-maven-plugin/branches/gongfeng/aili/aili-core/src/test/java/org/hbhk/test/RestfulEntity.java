package org.hbhk.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RestfulEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private Date date;
	private BigDecimal num;
	private List<RestfulDetailEntity> details;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public List<RestfulDetailEntity> getDetails() {
		return details;
	}

	public void setDetails(List<RestfulDetailEntity> details) {
		this.details = details;
	}
}
