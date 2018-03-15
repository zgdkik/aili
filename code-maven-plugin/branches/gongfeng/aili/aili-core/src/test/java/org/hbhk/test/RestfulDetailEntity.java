package org.hbhk.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RestfulDetailEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private  int id;
	private String name;
	private  Date  date;
	private BigDecimal num;
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
	
	

}
