package org.hbhk.module.framework.shared.domain;

import java.io.Serializable;
import java.util.Date;

public class EmpEntity implements Serializable {

	private static final long serialVersionUID = -1744610988443219188L;

	private String loginname;
	private String name;
	private int gender;
	private Long salary;
	private int age;
	private Date createtime;

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
