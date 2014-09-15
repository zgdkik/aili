package org.hbhk.maikkr.user.server.service;

import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.user.share.pojo.BlogGroupInfo;
import org.hbhk.maikkr.user.share.pojo.CommentInfo;

public interface ICommentService extends ICommonService<CommentInfo> {
	Pagination<BlogGroupInfo> queryBlogGroupsByPage(Page page, Sort sort,
			 Map<String, Object> params);
}
