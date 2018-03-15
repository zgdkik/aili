package com.deppon.esb.management.alert.domain.view;

import java.util.List;

import com.deppon.esb.management.common.entity.BaseEntity;
import com.deppon.esb.management.user.domain.NoticUserInfo;

/**
 * The Class FullThresholdInfo.
 */
public class FullThresholdInfo extends BaseEntity{
	
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -7775292857090300835L;
	
	/** The svc code. */
	private String svcCode;
	
	/** The svc name. */
	private String svcName;
	
	/** The svc provd id. */
	private String svcProvdId;
	
	/** The threshold. */
	private Integer threshold;
	
	/** The channel. */
	private String channel;
	
	/** The notice user list. */
	private List<NoticUserInfo> noticeUserList;
	
	/**
	 * 获取 svc code.
	 * 
	 * @return the svc code
	 */
	public String getSvcCode() {
		return svcCode;
	}
	
	/**
	 * 设置 svc code.
	 * 
	 * @param svcCode
	 *            the new svc code
	 */
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}
	
	/**
	 * 获取 svc name.
	 * 
	 * @return the svc name
	 */
	public String getSvcName() {
		return svcName;
	}
	
	/**
	 * 设置 svc name.
	 * 
	 * @param svcName
	 *            the new svc name
	 */
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}
	
	/**
	 * 获取 svc provd id.
	 * 
	 * @return the svc provd id
	 */
	public String getSvcProvdId() {
		return svcProvdId;
	}
	
	/**
	 * 设置 svc provd id.
	 * 
	 * @param svcProvdId
	 *            the new svc provd id
	 */
	public void setSvcProvdId(String svcProvdId) {
		this.svcProvdId = svcProvdId;
	}
	
	/**
	 * 获取 threshold.
	 * 
	 * @return the threshold
	 */
	public Integer getThreshold() {
		return threshold;
	}
	
	/**
	 * 设置 threshold.
	 * 
	 * @param threshold
	 *            the new threshold
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	
	/**
	 * 获取 channel.
	 * 
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	
	/**
	 * 设置 channel.
	 * 
	 * @param channel
	 *            the new channel
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	/**
	 * 获取 notice user list.
	 * 
	 * @return the notice user list
	 */
	public List<NoticUserInfo> getNoticeUserList() {
		return noticeUserList;
	}
	
	/**
	 * 设置 notice user list.
	 * 
	 * @param noticeUserList
	 *            the new notice user list
	 */
	public void setNoticeUserList(List<NoticUserInfo> noticeUserList) {
		this.noticeUserList = noticeUserList;
	}

}
