package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.user.server.dao.IBlogDao;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService implements IBlogService {

	@Autowired
	private IBlogDao blogDao;

	public BlogInfo save(BlogInfo blog) {
		String id = UUIDUitl.getUuid();
		blog.setId(id);
		blog.setCreateTime(new Date());
		blog.setCreatUser(UserContext.getCurrentContext().getCurrentUserName());
		blog.setBlogId(id);
		blogDao.save(blog);
		return blog;
	}

	public Pagination<BlogInfo> getBlogPage(BlogInfo blog, Page page) {
		Pagination<BlogInfo> pagination = new Pagination<BlogInfo>();
		pagination.setItems(blogDao.get(blog, page));
		return pagination;
	}

}
