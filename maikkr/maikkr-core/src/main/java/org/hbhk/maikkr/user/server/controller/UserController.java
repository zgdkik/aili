package org.hbhk.maikkr.user.server.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.annotation.NeedLogin;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private IBlogService blogService;
	@Autowired
	private IUserService userService;
	
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
			log.error("sendTheme", e);
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/getPageTheme")
	@ResponseBody
	public ResponseEntity getPageTheme(BlogInfo blog, Page page) {
		try {
			page.setStart(0);
			page.setSize(10);
			Object result = blogService.getBlogPage(blog, page);
			return returnSuccess(result);
		} catch (Exception e) {
			log.error("getPageTheme", e);
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/search")
	@ResponseBody
	public ResponseEntity search(String q) {
		try {
			Object result = blogService.search(q);
			return returnSuccess(result);
		} catch (Exception e) {
			log.error("search", e);
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/{user}/{url}")
	public String getTheme(@PathVariable("user") String user,
			@PathVariable("url") String url, Model model) {
		try {
			String blogUrl = user + "/" + url + ".htm";
			BlogInfo blog = new BlogInfo();
			blog.setBlogUser(user);
			blog.setBlogUrl(blogUrl);
			model.addAttribute("theme", blogService.getBlog(blog));
			return "comment";
		} catch (Exception e) {
			log.error("getTheme", e);
			return "redirect:/core/error.htm";
		}
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public UserInfo getUser(String email) {
		try {
			UserInfo user = new UserInfo();
			user.setMail(email);
			user = userService.getUser(user);
			return user;
		} catch (Exception e) {
			log.error("getUser", e);
			return null;
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