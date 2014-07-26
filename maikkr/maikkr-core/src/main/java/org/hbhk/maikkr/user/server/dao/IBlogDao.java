package org.hbhk.maikkr.user.server.dao;

import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;

/**
 * Dao接口开发规范
 * 1.类名必须以I开头、以Dao结尾
 */

public interface IBlogDao extends GenericEntityDao<BlogInfo, String>{
	
	
}