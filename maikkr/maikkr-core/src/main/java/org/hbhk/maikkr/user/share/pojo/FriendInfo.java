package org.hbhk.maikkr.user.share.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Entity
@Tabel("t_mkk_friend")
public class FriendInfo extends BaseInfo {

	private static final long serialVersionUID = 3846844061131232345L;
	@Column("friendUser")
	private String friendUser;// 评论人

}
