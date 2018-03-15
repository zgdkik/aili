package org.hbhk.aili.common.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;

public class JobDetail extends BizBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3854129136852283899L;

	private String id;

	private String jobName;
	private String groupName;
	private String jobCls;

	private String cron;

	private String descp;
	/**
	 * 1 有效 0 删除  2 已添加
	 * 3删除再添加
	 */

	private String topicKey;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getJobCls() {
		return jobCls;
	}

	public void setJobCls(String jobCls) {
		this.jobCls = jobCls;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}


	public String getTopicKey() {
		return topicKey;
	}

	public void setTopicKey(String topicKey) {
		this.topicKey = topicKey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
