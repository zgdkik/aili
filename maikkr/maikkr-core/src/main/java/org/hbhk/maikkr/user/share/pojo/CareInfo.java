package org.hbhk.maikkr.user.share.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Entity
@Tabel("t_mkk_care")
public class CareInfo extends BaseInfo {
	private static final long serialVersionUID = -6681157759614510700L;

	@Column("careUser")
	private String careUser;

	public String getCareUser() {
		return careUser;
	}

	public void setCareUser(String careUser) {
		this.careUser = careUser;
	}

}
