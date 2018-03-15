package org.hbhk.aili.common.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_send_log")
public class SendLogEntity extends BizBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8536500005686836462L;
	/**
	 * 业务编号
	 */
	@Column("biz_no")
	private String bizNo;
	@Column("body")
	private String body;
	@Column("biz_type")
	private String bizType;
	@Column("descp")
	private String descp;
	@Column("target")
	private String target;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

}
