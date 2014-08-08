package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;
import java.util.List;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.user.server.dao.ICommentDao;
import org.hbhk.maikkr.user.server.service.ICommentService;
import org.hbhk.maikkr.user.share.pojo.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private ICommentDao commentDao;

	public CommentInfo save(CommentInfo comm) {
		if (comm == null) {
			return null;
		}
		String user = UserContext.getCurrentContext().getCurrentUserName();
		comm.setCreatUser(user);
		comm.setId(UUIDUitl.getUuid());
		comm.setCreateTime(new Date());
		comm.setCommentUser(user);
		return commentDao.save(comm);
	}

	public CommentInfo update(CommentInfo o) {
		return null;
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

}
