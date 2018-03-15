package org.hbhk.aili.workflow.share.entity;

import java.util.Date;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

/**
 * @Description: 请假实体类
 */
@Table("t_wf_leave")
public class Leave extends WorkflowEntity {

	private static final long serialVersionUID = -283564045340029140L;
	@Column("type")
	private String type;

	@Column("start_time")
	private Date startTime;
	@Column("end_time")
	private Date endTime;

	@Column("reason")
	private String reason;
	
	@Column("days")
	private Integer days;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	

}