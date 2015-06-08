package org.hbhk.rss.core.shared.pojo;

import java.util.Date;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.share.model.BaseModel;

@Table("t_rss_msglog")
public class UserMsgLogEntity  extends BaseModel{
	private static final long serialVersionUID = -6835701486183270215L;
	@Column("user_name")
	private String user_name;
	@Column("create_time")
	private Date create_time;
	@Column("msg")
	private String msg;


	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}