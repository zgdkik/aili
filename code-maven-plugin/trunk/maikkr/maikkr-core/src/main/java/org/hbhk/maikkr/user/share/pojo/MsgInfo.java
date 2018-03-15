package org.hbhk.maikkr.user.share.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Entity
@Tabel("t_mkk_msg")
public class MsgInfo extends BaseInfo {
	private static final long serialVersionUID = 5878989025445014539L;

	@Column("sendUser")
	private String sendUser;
	@Column("receiveUser")
	private String receiveUser;
	@Column("msg")
	private String msg;
	@Column("isRead")
	private Integer isRead;
	@Column("type")
	private String type;
	@Column("url")
	private String url;

	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
