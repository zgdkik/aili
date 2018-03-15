package org.hbhk.home.core.share.model;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.share.model.BaseModel;

@Table("t_home_customer")
public class CustomerModel extends BaseModel {

	private static final long serialVersionUID = 5929620778256880154L;
	@Column("name")
	private String name;
	@Column("type")
	private String type;
	private String counselor;
	
	@Column("nameEn")
	private String nameEn;
	@Column("location")
	private String location;
	
	@Column("sex")
	private String sex;
	
	@Column("address")
	private String address;
	
	@Column("addressEn")
	private String addressEn;
	
	@Column("intention")
	private String intention;
	@Column("email")
	private String email;
	
	@Column("company")
	private String company;
	
	@Column("network")
	private String network;
	@Column("registeredCapital")
	private String registeredCapital;
	@Column("remark")
	private String remark;
	
	@Column("industry")
	private String industry;
	@Column("source")
	private String source;
	@Column("level")
	private String level;
	@Column("big")
	private String big;
	@Column("bigOrder")
	private String bigOrder;
	@Column("open")
	private String open;
	@Column("resource")
	private String resource;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIntention() {
		return intention;
	}

	public void setIntention(String intention) {
		this.intention = intention;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}

	public String getBigOrder() {
		return bigOrder;
	}

	public void setBigOrder(String bigOrder) {
		this.bigOrder = bigOrder;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCounselor() {
		return counselor;
	}

	public void setCounselor(String counselor) {
		this.counselor = counselor;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getAddressEn() {
		return addressEn;
	}

	public void setAddressEn(String addressEn) {
		this.addressEn = addressEn;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
