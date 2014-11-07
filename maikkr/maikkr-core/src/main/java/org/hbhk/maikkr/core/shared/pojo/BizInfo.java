package org.hbhk.maikkr.core.shared.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Entity
@Tabel("t_mkk_biz")
public class BizInfo extends BaseInfo {
	
	private static final long serialVersionUID = -428402428904212000L;
	
	@Column("name")
	private String name;
	@Column("favorable")
	private String favorable;
	@Column("contactWay")
	private String contactWay;
	@Column("location")
	private String location;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFavorable() {
		return favorable;
	}
	public void setFavorable(String favorable) {
		this.favorable = favorable;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

	
}