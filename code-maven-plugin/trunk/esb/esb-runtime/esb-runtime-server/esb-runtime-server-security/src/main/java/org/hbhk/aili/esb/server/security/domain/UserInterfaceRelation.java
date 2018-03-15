package org.hbhk.aili.esb.server.security.domain;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInterfaceRelation", propOrder = { "fid", "createTime",
		"user", "esbServiceCode", "active" })
public class UserInterfaceRelation implements Serializable {

	private final static long serialVersionUID = 11082011L;
	@XmlElement(required = true)
	protected String fid;
	@XmlElement(required = true, type = String.class)
	@XmlSchemaType(name = "dateTime")
	protected Date createTime;
	@XmlElement(required = true)
	protected String user;
	@XmlElement(required = true)
	protected String esbServiceCode;
	@XmlElement(required = true)
	protected String active;

	/**
	 * 获取fid属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFid() {
		return fid;
	}

	/**
	 * 设置fid属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFid(String value) {
		this.fid = value;
	}

	/**
	 * 获取createTime属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置createTime属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	/**
	 * 获取user属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 设置user属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUser(String value) {
		this.user = value;
	}

	/**
	 * 获取esbServiceCode属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEsbServiceCode() {
		return esbServiceCode;
	}

	/**
	 * 设置esbServiceCode属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEsbServiceCode(String value) {
		this.esbServiceCode = value;
	}

	/**
	 * 获取active属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getActive() {
		return active;
	}

	/**
	 * 设置active属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setActive(String value) {
		this.active = value;
	}

}
