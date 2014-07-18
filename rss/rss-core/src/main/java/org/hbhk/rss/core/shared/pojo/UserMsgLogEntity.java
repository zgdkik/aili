package org.hbhk.rss.core.shared.pojo;

import java.util.Date;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.PrimaryKey;
import org.hbhk.aili.orm.server.annotation.Tabel;

@Tabel("t_rss_msglog")
public class UserMsgLogEntity {
	@Column("id")
	@PrimaryKey
	private Integer id;
	@Column("user_name")
	private String user_name;
	@Column("create_time")
	private Date create_time;
	@Column("msg")
	private String msg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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