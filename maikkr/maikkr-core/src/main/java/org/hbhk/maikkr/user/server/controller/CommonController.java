package org.hbhk.maikkr.user.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.annotation.NeedLogin;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.core.share.util.EncryptUtil;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.define.UserConstants;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.aili.support.server.email.IEmailService;
import org.hbhk.aili.support.server.email.impl.EmailInfo;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class CommonController extends BaseController {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IUserService userService;
	@Autowired
	private IEmailService emailService;
	@Autowired
	private IBlogService blogService;

	@RequestMapping("/findPwd")
	@ResponseBody
	public ResponseEntity findPwd(HttpServletRequest request, String user,
			String email, String code) {
		String scode = (String) request.getSession().getAttribute(
				UserConstants.VALIDATECODE_SESSION_KEY);
		if (scode == null) {
			return returnException("验证码不正确");
		}
		if (!scode.equals(code)) {
			return returnException("验证码不正确");
		}
		UserInfo quser = new UserInfo();
		quser.setMail(user);
		if (userService.getUser(quser) == null) {
			return returnException("用户名不存在");
		}
		quser.setRemail(email);
		if ((quser = userService.getUser(quser)) == null) {
			return returnException("用户名与绑定邮箱不一致");
		}
		UserInfo puser = new UserInfo();
		puser.setId(quser.getId());
		String pwd = EncryptUtil.encodeSHA1("123456");
		puser.setPassword(pwd);
		userService.update(puser);
		EmailInfo qemail = new EmailInfo();
		qemail.setSubject("买客买家网-密码找回");
		qemail.setEmail(email);
		qemail.setContext("你的用户名[" + user + "]重置密码:123456\r"
				+ "官网地址:www.maikkr.com");
		try {
			emailService.sendEmail(qemail);
		} catch (MessagingException e) {
			log.error("send email error", e);
		} catch (IOException e) {
			log.error("send email error", e);
		}
		return returnSuccess("找回成功,系统重新生成密码发到了你的邮箱，请注意查收");
	}

	@RequestMapping(value = "/myTheme", params = { "pageNum" })
	@ResponseBody
	@NeedLogin
	public ResponseEntity myTheme(BlogInfo blog, int pageNum) {
		try {
			Page page = new Page();
			page.setSize(5);
			if (pageNum > 20) {
				pageNum = 20;
			}
			if (pageNum == 1) {
				page.setStart(0);
			} else {
				page.setStart(2 * pageNum);
			}
			List<String> sorts = new ArrayList<String>();
			sorts.add("createTime desc");
			page.setSorts(sorts);
			String blogUser = UserContext.getCurrentContext()
					.getCurrentUserName();
			blog.setBlogUser(blogUser);
			Object result = blogService.getBlogPage(blog, page);
			return returnSuccess(result);
		} catch (Exception e) {
			log.error("getPageTheme", e);
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/myTheme")
	@NeedLogin
	public String myTheme() {
		return "myTheme";
	}

	@RequestMapping("/delTheme")
	@ResponseBody
	@NeedLogin
	public ResponseEntity delTheme(String tid) {
		try {
			BlogInfo blog = new BlogInfo();
			blog.setId(tid);
			blog.setStatus(0);
			blogService.update(blog);
			return returnSuccess();
		} catch (Exception e) {
			return returnException();
		}

	}
	
	@RequestMapping(value = "/newhit", params = { "pageNum" })
	@ResponseBody
	public ResponseEntity newhit(BlogInfo blog, int pageNum) {
		try {
			Page page = new Page();
			page.setSize(10);
			if (pageNum > 20) {
				pageNum = 20;
			}
			if (pageNum == 1) {
				page.setStart(0);
			} else {
				page.setStart(2 * pageNum);
			}
			List<String> sorts = new ArrayList<String>();
			sorts.add("blogHit desc");
			page.setSorts(sorts);
			Object result = blogService.getBlogPage(blog, page);
			return returnSuccess(result);
		} catch (Exception e) {
			log.error("getPageTheme", e);
			return returnException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/newest", params = { "pageNum" })
	@ResponseBody
	public ResponseEntity newest(BlogInfo blog, int pageNum) {
		try {
			Page page = new Page();
			page.setSize(10);
			if (pageNum > 20) {
				pageNum = 20;
			}
			if (pageNum == 1) {
				page.setStart(0);
			} else {
				page.setStart(2 * pageNum);
			}
			List<String> sorts = new ArrayList<String>();
			sorts.add("createTime desc");
			page.setSorts(sorts);
			Object result = blogService.getBlogPage(blog, page);
			return returnSuccess(result);
		} catch (Exception e) {
			log.error("getPageTheme", e);
			return returnException(e.getMessage());
		}
	}

}
