package org.hbhk.rss.core.server.dao;

import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.rss.core.shared.pojo.UserMsgLogEntity;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */
public interface IUserDao extends GenericEntityDao<UserMsgLogEntity, Integer> {
	

}