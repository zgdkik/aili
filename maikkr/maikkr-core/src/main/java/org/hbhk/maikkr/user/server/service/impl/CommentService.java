package org.hbhk.maikkr.user.server.service.impl;

import java.util.List;

import org.hbhk.maikkr.user.server.dao.ICommentDao;
import org.hbhk.maikkr.user.server.service.ICommentService;
import org.hbhk.maikkr.user.share.pojo.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentService implements ICommentService {

	@Autowired
	private ICommentDao commentDao;

	public CommentInfo save(CommentInfo o) {
		return null;
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

}
