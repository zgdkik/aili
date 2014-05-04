package org.hbhk.service;

import javax.jws.WebService;

@WebService(targetNamespace="service.hbhk.person1")
public class Person {
	private String  name ;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	

}
