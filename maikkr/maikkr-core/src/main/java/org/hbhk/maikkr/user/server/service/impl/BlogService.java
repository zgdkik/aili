package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.aili.security.server.cache.UserCache;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.user.server.dao.IAttentionDao;
import org.hbhk.maikkr.user.server.dao.IBlogDao;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.hbhk.maikkr.user.server.service.ICareService;
import org.hbhk.maikkr.user.server.service.IMsgInfoService;
import org.hbhk.maikkr.user.share.pojo.AttentionInfo;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;
import org.hbhk.maikkr.user.share.pojo.CareInfo;
import org.hbhk.maikkr.user.share.pojo.MsgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService implements IBlogService {

	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private IBlogDao blogDao;

	@Autowired
	private IAttentionDao attentionDao;
	
	@Autowired
	private ICareService careService;
	@Autowired
	private IMsgInfoService msgInfoService;

	public BlogInfo save(BlogInfo blog) {
		String id = UUIDUitl.getUuid();
		blog.setId(id);
		String user = UserContext.getCurrentContext().getCurrentUserName();
		blog.setBlogUser(user);
		blog.setCreateTime(new Date());
		blog.setCreatUser(user);
		blog.setBlogId(id);
		blog.setBlogHit(0);
		blog.setBlogReview(0);
		blog.setBlogCollect(0);
		user = user.substring(0, user.indexOf("@"));
		blog.setBlogUrl(user + "/" + System.currentTimeMillis() + ".htm");
		blogDao.save(blog);
		//查询是否被关注
		CareInfo care = new CareInfo();
		care.setCareUser(UserContext.getCurrentContext().getCurrentUserName());
		List<CareInfo> cares = careService.get(care);
		if(CollectionUtils.isNotEmpty(cares)){
			for (CareInfo careInfo : cares) {
				MsgInfo msg = new MsgInfo();
				msg.setSendUser(user);
				msg.setReceiveUser(careInfo.getCreatUser());
				msg.setType("user_type");
				String cpath = RequestContext.getRequest().getContextPath();
				String turl = "<a href='"+cpath+blog.getBlogUrl()+"'>"+blog.getBlogTitle()+"</a>";
				msg.setMsg("您关注的用户发表了新的主题:"+turl);
				msg.setUrl(blog.getBlogUrl());
				msgInfoService.save(msg);
			}
		}
		return blog;
	}

	public Pagination<BlogInfo> getBlogPage(BlogInfo blog, Page page) {
		Pagination<BlogInfo> pagination = new Pagination<BlogInfo>();
		List<BlogInfo> blogInfos = blogDao.get(blog, page);
		if (CollectionUtils.isNotEmpty(blogInfos)) {
			ICache<String, UserInfo> userCache = CacheManager.getInstance()
					.getCache(UserCache.cacheID);
			for (int i = 0; i < blogInfos.size(); i++) {
				String blogUser = blogInfos.get(i).getBlogUser();
				try {
					UserInfo user = userCache.get(blogUser);
					blogInfos.get(i).setUserHeadImg(user.getUserHeadImg());
				} catch (Exception e) {
					log.error("加载用户失败", e);
				}

			}
		}
		pagination.setItems(blogInfos);
		return pagination;
	}

	public BlogInfo getBlog(BlogInfo blog) {
		return blogDao.getOne(blog);
	}

	public List<BlogInfo> search(String q) {
		if (q == null) {
			q = "";
		} else {
			q = "%" + q + "%";
		}
		return blogDao.search(q);
	}

	public void updateBlogHit(String blogUrl) {
		blogDao.updateBlogsHit(blogUrl);
	}

	public int getUserThemeCount() {
		BlogInfo blog = new BlogInfo();
		String user = UserContext.getCurrentContext().getCurrentUserName();
		blog.setBlogUser(user);
		List<BlogInfo> blogs = blogDao.get(blog);
		if (CollectionUtils.isNotEmpty(blogs)) {
			return blogs.size();
		}
		return 0;
	}

	public int getUserAttentionCount() {
		AttentionInfo att = new AttentionInfo();
		String user = UserContext.getCurrentContext().getCurrentUserName();
		att.setCreatUser(user);
		List<AttentionInfo> atts = attentionDao.get(att);
		if (CollectionUtils.isNotEmpty(atts)) {
			return atts.size();
		}
		return 0;
	}

	public void updateBlogComment(String blogId) {
		BlogInfo blog = new BlogInfo();
		blog.setId(blogId);
		BlogInfo b = blogDao.getOne(blog);
		blog.setBlogReview(b.getBlogReview() + 1);
		blogDao.update(blog);

	}

	public BlogInfo update(BlogInfo blog) {
		blog.setUpdateTime(new Date());
		blog.setUpdateUser("admin");
		return blogDao.update(blog);
	}

	public Pagination<BlogInfo> queryBlogsByPage(Page page, Sort sort,
			Map<String, Object> params) {
		return blogDao.queryBlogsByPage(page, sort, params);
	}

}
