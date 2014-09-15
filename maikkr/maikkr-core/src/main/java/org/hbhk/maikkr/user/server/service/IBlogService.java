package org.hbhk.maikkr.user.server.service;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;

public interface IBlogService {

	BlogInfo save(BlogInfo blog);

	Pagination<BlogInfo> getBlogPage(BlogInfo blog, Page page);

	BlogInfo getBlog(BlogInfo blog);

	BlogInfo update(BlogInfo blog);

	List<BlogInfo> search(String q);

	void updateBlogHit(String blogId);

	void updateBlogComment(String blogId);

	int getUserThemeCount();

	int getUserAttentionCount();

	Pagination<BlogInfo> queryBlogsByPage(Page page, Sort sort,
			Map<String, Object> params);
}
