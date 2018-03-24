package com.yimidida.ows.base.share.entity;

public class LocationResult {

	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	private String precise;

	private String confidence;

	private String level;

	public String getPrecise() {
		return precise;
	}

	public void setPrecise(String precise) {
		this.precise = precise;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
