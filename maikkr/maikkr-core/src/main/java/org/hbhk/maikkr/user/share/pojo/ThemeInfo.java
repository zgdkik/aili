package org.hbhk.maikkr.user.share.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Tabel("t_mkk_theme")
@Entity
public class ThemeInfo extends BaseInfo {

	private static final long serialVersionUID = -2472442979674900507L;
	@Column("name")
	private String name;
	@Column("type")
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
