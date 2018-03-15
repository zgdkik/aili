package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.user.server.dao.ICommentDao;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.hbhk.maikkr.user.server.service.ICommentService;
import org.hbhk.maikkr.user.share.pojo.BlogGroupInfo;
import org.hbhk.maikkr.user.share.pojo.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private ICommentDao commentDao;
	@Autowired
	private IBlogService blogService;

	public CommentInfo save(CommentInfo comm) {
		if (comm == null) {
			return null;
		}
		UserInfo user = UserContext.getCurrentContext().getCurrentUser();
		comm.setCreatUser(user.getMail());
		comm.setId(UUIDUitl.getUuid());
		comm.setCreateTime(new Date());
		comm.setCommentUser(user.getMail());
		comm.setCommentHeadImg(user.getUserHeadImg());
		blogService.updateBlogComment(comm.getBlogId());
		return commentDao.save(comm);
	}

	public CommentInfo update(CommentInfo o) {
		o.setUpdateTime(new Date());
		return commentDao.update(o);
	}

	public List<CommentInfo> get(CommentInfo o) {

		return null;
	}

	public CommentInfo getOne(CommentInfo o) {
		return null;
	}

	public List<CommentInfo> get(CommentInfo model, Page page) {
		return commentDao.get(model, page);
	}

	public Pagination<BlogGroupInfo> queryBlogGroupsByPage(Page page,
			Sort sort, Map<String, Object> params) {
		return commentDao.queryBlogGroupsByPage(page, sort, params);
	}

}
