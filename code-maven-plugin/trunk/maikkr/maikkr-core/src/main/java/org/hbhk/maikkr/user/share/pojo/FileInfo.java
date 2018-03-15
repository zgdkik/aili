package org.hbhk.maikkr.user.share.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Tabel("t_mkk_file")
@Entity
public class FileInfo extends BaseInfo {
	private static final long serialVersionUID = 4037447656789407410L;

	@Column("name")
	private String name;
	@Column("origName")
	private String origName;
	@Column("url")
	private String url;
	@Column("type")
	private String type;
	@Column("user")
	private String user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigName() {
		return origName;
	}

	public void setOrigName(String origName) {
		this.origName = origName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
