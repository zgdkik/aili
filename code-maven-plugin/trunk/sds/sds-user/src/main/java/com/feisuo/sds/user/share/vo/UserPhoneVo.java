package com.feisuo.sds.user.share.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class UserPhoneVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1190210408653007612L;

	private String phone;
	
	//BD或者管理员账号需要从员工表中读取电话号码
	private String phoneNumber;

	private String contactPhone;

	private String subContactPhone;
	

	public String getPhone() {
		if (StringUtils.isEmpty(phone)) {
			if (StringUtils.isEmpty(getContactPhone())) {
				return getSubContactPhone();
			} else {
				return getContactPhone();
			}
		}
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getSubContactPhone() {
		return subContactPhone;
	}

	public void setSubContactPhone(String subContactPhone) {
		this.subContactPhone = subContactPhone;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
