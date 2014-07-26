package org.hbhk.maikkr.user.server.controller;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.security.server.annotation.NeedLogin;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IBlogService blogService;

	@RequestMapping("/main")
	@NeedLogin
	public String main() {
		return "mainnew";
	}

	@RequestMapping("/loginpage")
	public String loginpage() {
		return "loginpage";
	}

	@RequestMapping("/sendTheme")
	@ResponseBody
	public ResponseEntity sendTheme(BlogInfo blog) {
		try {
			blogService.save(blog);
			return returnSuccess("发布成功!");
		} catch (Exception e) {
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/getPageTheme")
	@ResponseBody
	public ResponseEntity getPageTheme(BlogInfo blog, Page page) {
		try {
			Object result = blogService.getBlogPage(blog, page);
			return returnSuccess(result);
		} catch (Exception e) {
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	@RequestMapping("/test")
	public String test() {
		return "main";
	}

}