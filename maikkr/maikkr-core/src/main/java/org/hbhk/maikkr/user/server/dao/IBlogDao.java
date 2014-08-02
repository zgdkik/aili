package org.hbhk.maikkr.user.server.dao;

import java.util.List;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.annotation.NativeUpdate;
import org.hbhk.aili.orm.server.annotation.QueryParam;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */

public interface IBlogDao extends GenericEntityDao<BlogInfo, String> {
	@NativeQuery(model = BlogInfo.class, value = "blog_search")
	List<BlogInfo> search(@QueryParam("q") String q);

	@NativeUpdate(value = "updateBlogsHit")
	void updateBlogsHit(@QueryParam("blogUrl") String blogUrl);
	
}