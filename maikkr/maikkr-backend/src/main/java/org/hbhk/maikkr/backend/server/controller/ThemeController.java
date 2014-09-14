package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.intercptor.QueryBeanParam;
import org.hbhk.aili.orm.server.page.bean.QueryBean;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("blogController")
@RequestMapping("/backend")
@NeedLogin
public class ThemeController extends BaseController {

	@Autowired
	private IBlogService blogService;

	@RequestMapping("/queryThemesByPage")
	@ResponseBody
	public Pagination<BlogInfo> queryThemesByPage(
			@QueryBeanParam QueryBean queryBean) {

		return blogService.queryBlogsByPage(queryBean.getPage(), null,
				queryBean.getParaMap());
	}

	/**
	 * add, del
	 * 
	 * @param oper
	 * @param user
	 * @return
	 */
	@RequestMapping("/editBlog")
	@ResponseBody
	public ResponseEntity editBlog(String oper, BlogInfo blog) {
		try {
			if (oper.equals("del")) {
				blog.setStatus(2);
				blogService.update(blog);
			}
			return returnSuccess();
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}

}