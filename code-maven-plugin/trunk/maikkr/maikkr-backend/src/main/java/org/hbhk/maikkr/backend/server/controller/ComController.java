package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.intercptor.QueryBeanParam;
import org.hbhk.aili.orm.server.page.bean.QueryBean;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.hbhk.maikkr.user.server.service.ICommentService;
import org.hbhk.maikkr.user.share.pojo.BlogGroupInfo;
import org.hbhk.maikkr.user.share.pojo.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
@RequestMapping("/backend")
@NeedLogin
public class ComController extends BaseController {

	@Autowired
	private ICommentService commentService;

	@RequestMapping("/queryBlogGroupsByPage")
	@ResponseBody
	public Pagination<BlogGroupInfo> queryThemesByPage(
			@QueryBeanParam QueryBean queryBean) {
		return commentService.queryBlogGroupsByPage(queryBean.getPage(), null,
				queryBean.getParaMap());
	}

	/**
	 * add, del
	 * 
	 * @param oper
	 * @param user
	 * @return
	 */
	@RequestMapping("/editCom")
	@ResponseBody
	public ResponseEntity editCom(String oper, BlogGroupInfo bg) {
		try {
			if (oper.equals("del")) {
				CommentInfo commentInfo = new CommentInfo();
				commentInfo.setId(bg.getId());
				commentInfo.setStatus(2);
				commentService.update(commentInfo);
			}
			return returnSuccess();
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}

}