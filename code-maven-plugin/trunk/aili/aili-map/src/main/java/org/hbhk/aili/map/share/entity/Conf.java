package org.hbhk.aili.map.share.entity;

public class Conf {

	//MTK SDK中的track_name概念对应于鹰眼entity API中的entity_name概念，是产生一条轨迹的人或物的唯一标识
	//	必选，例如：testTrack01
	private String track_name;
	//Track对应的配置信息 loc_period:1:30|upload_period:1:60”
	private String conf;
	
	public String getTrack_name() {
		return track_name;
	}
	public void setTrack_name(String track_name) {
		this.track_name = track_name;
	}
	public String getConf() {
		return conf;
	}
	public void setConf(String conf) {
		this.conf = conf;
	}
	
	
}
