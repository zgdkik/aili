package com.feisuo.yhhc.module.bseservice_gis.api.shared.dto;

import java.io.Serializable;
import java.util.List;


public class TruckPointsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**司机ID*/
	private String userId;
	/**司机坐标List*/
	private List<PointDTO> points;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<PointDTO> getPoints() {
		return points;
	}
	public void setPoints(List<PointDTO> points) {
		this.points = points;
	}
	
	

	

}
