package com.feisuo.sds.common.share.request.push;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PushTargetGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8377451077901355209L;

	/** "Priority" : 1,--优化级升序，0-10，必填 */
	@JsonProperty("Priority")
	private int priority;

	/** 分组推送间隔时长(秒)，必填 */
	@JsonProperty("Interval")
	private int interval;

	/** 推送声音 */
	@JsonProperty("Sound")
	private String sound;

	/** 推送用户Id */
	@JsonProperty("Targets")
	private List<UserTarget> targets;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public List<UserTarget> getTargets() {
		return targets;
	}

	public void setTargets(List<UserTarget> targets) {
		this.targets = targets;
	}

}
