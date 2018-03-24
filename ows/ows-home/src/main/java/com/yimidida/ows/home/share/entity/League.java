package com.yimidida.ows.home.share.entity;

import java.io.Serializable;
import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

/**
 * 网点加盟
 * @author
 *
 */
@Table("tb_league")
public class League implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@Column("id")
	private String id;//网点编号
	@Column("name")
    private String name;//网点名称
	@Column("link_Man")
    private String linkMan;//联系人
	@Column("link_Phone")
    private String linkPhone;//联系电话
	@Column("allot")
    private String allot;//所属分拨
	@Column("deliver_Scope")
    private String deliverScope;//派送范围
	@Column("address")   
    private String address;//网点地址
	@Column("othor_Message")
    private String othorMessage;//其他信息
	@Column("province")
    private String province;//省
	@Column("city")
    private String city;//市
	@Column("district")
    private String district;//区
	@Column("longitude")
    private String longitude;//经度
	@Column("latitude")
    private String latitude;//纬度
	@Column("create_Date")
    private Date createDate;//创建时间
	@Column("create_User")
    private String createUser;//创建人
	@Column("change_Date")
    private Date changeDate;//修改时间
	@Column("change_User")
    private String changeUser;//修改人
    private String provincename;
    private String cityname;
    private String districtname;
	@Column("is_Delete")
    private Integer isDelete;//0是未删除  1是删除
	
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public League() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone == null ? null : linkPhone.trim();
    }

    public String getAllot() {
        return allot;
    }

    public void setAllot(String allot) {
        this.allot = allot == null ? null : allot.trim();
    }

  
    public String getDeliverScope() {
		return deliverScope;
	}

	public void setDeliverScope(String deliverScope) {
		this.deliverScope = deliverScope;
	}

	public String getOthorMessage() {
        return othorMessage;
    }

    public void setOthorMessage(String othorMessage) {
        this.othorMessage = othorMessage == null ? null : othorMessage.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeUser() {
        return changeUser;
    }

    public void setChangeUser(String changeUser) {
        this.changeUser = changeUser == null ? null : changeUser.trim();
    }
}