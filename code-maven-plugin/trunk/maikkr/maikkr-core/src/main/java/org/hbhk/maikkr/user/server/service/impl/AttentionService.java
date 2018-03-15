package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;

import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.user.server.dao.IAttentionDao;
import org.hbhk.maikkr.user.server.service.IAttentionService;
import org.hbhk.maikkr.user.share.pojo.AttentionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */
@Service
public class AttentionService implements IAttentionService {

	@Autowired
	private IAttentionDao attentionDao;

	public AttentionInfo save(AttentionInfo attention) {
		String user = UserContext.getCurrentContext().getCurrentUserName();
		attention.setCreatUser(user);
		attention.setId(UUIDUitl.getUuid());
		attention.setCreateTime(new Date());

		return attentionDao.save(attention);
	}

}