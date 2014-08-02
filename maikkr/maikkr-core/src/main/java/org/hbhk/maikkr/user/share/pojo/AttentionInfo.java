package org.hbhk.maikkr.user.share.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Tabel("t_mkk_attention")
@Entity
public class AttentionInfo extends BaseInfo {

	private static final long serialVersionUID = 7111380540213023128L;
	@Column("user")
	private String user;
	@Column("attentionUser")
	private String attentionUser;
	
	@Column("theme")
	private String theme;
	@Column("themeUser")
	private String themeUser;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAttentionUser() {
		return attentionUser;
	}

	public void setAttentionUser(String attentionUser) {
		this.attentionUser = attentionUser;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getThemeUser() {
		return themeUser;
	}

	public void setThemeUser(String themeUser) {
		this.themeUser = themeUser;
	}

}
