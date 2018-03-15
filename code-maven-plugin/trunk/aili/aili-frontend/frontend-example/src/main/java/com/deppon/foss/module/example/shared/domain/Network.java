package com.deppon.foss.module.example.shared.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 网点信息实体类
 * @author 129903-阮正华
 *
 */
public class Network implements Serializable{

	private static final long serialVersionUID = 2767708613968440787L;
	private String id;
	private String name;
	private String address;
	private String description;
	private Coordinate coordinate;
	private String phone;
	private String tel;
	private String type;
	private Date createTime;
	private Date modifyTime;
	private String code;
	private String isValid;
	
	@Override
	public String toString(){
		return "Network {id="+id+",name="+name+",address="+address+",description="+description
				+",coordinate:[longitude="+coordinate.getLongitude()+",latitude="+coordinate.getLatitude()+"]," +
						"phone="+phone+",tel="+tel+",type="+type+",createTime="+createTime+",modifyTime="+modifyTime+",code="+
				code+",isValid="+isValid+"}";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	
}
