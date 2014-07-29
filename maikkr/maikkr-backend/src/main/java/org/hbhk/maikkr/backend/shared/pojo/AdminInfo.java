package org.hbhk.maikkr.backend.shared.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Entity
@Tabel("t_be_admin")
public class AdminInfo extends BaseInfo {

	private static final long serialVersionUID = -5515328079324163814L;

	@Column("email")
	private String email;
	@Column("name")
	private String name;
	@Column("pwd")
	private String pwd;
	@Column("retrieveEmail")
	private String retrieveEmail;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRetrieveEmail() {
		return retrieveEmail;
	}

	public void setRetrieveEmail(String retrieveEmail) {
		this.retrieveEmail = retrieveEmail;
	}

}