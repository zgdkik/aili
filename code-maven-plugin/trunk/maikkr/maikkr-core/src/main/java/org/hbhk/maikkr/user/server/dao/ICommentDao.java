package org.hbhk.maikkr.user.server.dao;

import java.util.Map;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.annotation.QueryParam;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.user.share.pojo.BlogGroupInfo;
import org.hbhk.maikkr.user.share.pojo.CommentInfo;

public interface ICommentDao  extends GenericEntityDao<CommentInfo, String> {
	@NativeQuery(model = BlogGroupInfo.class, value = "queryBlogGroupsByPage")
	Pagination<BlogGroupInfo> queryBlogGroupsByPage(Page page, Sort sort,
			@QueryParam Map<String, Object> params);
}
