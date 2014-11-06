package org.hbhk.maikkr.user.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import org.hbhk.maikkr.user.server.service.ICareService;
import org.hbhk.maikkr.user.server.service.ICollectService;
import org.hbhk.maikkr.user.server.service.ICommentService;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;
import org.hbhk.maikkr.user.share.pojo.CareInfo;
import org.hbhk.maikkr.user.share.pojo.CollectInfo;
import org.hbhk.maikkr.user.share.pojo.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@Autowired
	private ICareService careService;
	@Autowired
	private ICollectService collectService;

	@Autowired
	private ICommentService commentService;

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
	public String myTheme(Model model, Integer pageNum, BlogInfo blog) {
		blog.setBlogUser(UserContext.getCurrentContext().getCurrentUserName());
		List<BlogInfo> bs = getThemes(blog, pageNum);
		for (BlogInfo b : bs) {
			CareInfo care = new CareInfo();
			care.setCareUser(b.getBlogUser());
			List<CareInfo> careList = careService.get(care);
			List<UserInfo> userList = new ArrayList<UserInfo>();
			if (careList != null) {
				b.setCareCount(careList.size());
				for (int i = 0; i < careList.size(); i++) {
					if (i == 10) {
						break;
					}
					UserInfo user = userService.getMe(careList.get(i)
							.getCreatUser());
					userList.add(user);
				}
			}
			b.setCareList(userList);

			List<CommentInfo> result = getComments(b.getBlogId(), 1, 1);
			if (result == null) {
				result = new ArrayList<CommentInfo>();
			}
			b.setCommentList(result);
		}
		model.addAttribute("bs", bs);
		if (pageNum == null) {
			model.addAttribute("pageNum", 1);
		} else {
			model.addAttribute("pageNum", pageNum);
		}

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

	@RequestMapping(value = "/care")
	@ResponseBody
	@NeedLogin
	public ResponseEntity care(String user) {
		try {
			CareInfo care = new CareInfo();
			care.setCareUser(user);
			careService.save(care);
			return returnSuccess();
		} catch (Exception e) {
			log.error("care", e);
			return returnException(e.getMessage());
		}
	}

	@RequestMapping(value = "/loadCollects", params = { "pageNum" })
	@ResponseBody
	@NeedLogin
	public ResponseEntity loadCollects(int pageNum) {
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
			CollectInfo collect = new CollectInfo();
			collect.setCreatUser(UserContext.getCurrentContext()
					.getCurrentUserName());
			Object result = collectService.get(collect, page);
			return returnSuccess(result);
		} catch (Exception e) {
			log.error("loadCollects", e);
			return returnException(e.getMessage());
		}
	}

	@RequestMapping(value = "/delCollect")
	@ResponseBody
	@NeedLogin
	public ResponseEntity delCollect(String id) {
		try {
			CollectInfo model = new CollectInfo();
			model.setBlogId(id);
			collectService.update(model);
			return returnSuccess();
		} catch (Exception e) {
			log.error("delCollect", e);
			return returnException(e.getMessage());
		}
	}

	@RequestMapping(value = "/myCare", params = { "pageNum" })
	@ResponseBody
	@NeedLogin
	public ResponseEntity myCare(int pageNum) {
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
			CareInfo model = new CareInfo();
			String user = UserContext.getCurrentContext().getCurrentUserName();
			model.setCreatUser(user);
			Object result = careService.get(model, page);
			return returnSuccess(result);
		} catch (Exception e) {
			log.error("myCare", e);
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/myCare")
	@NeedLogin
	public String myCare(Integer pageNum, Model model) {

		String user = UserContext.getCurrentContext().getCurrentUserName();
		CareInfo care = new CareInfo();
		care.setCreatUser(user);
		List<CareInfo> careList = careService.get(care);
		List<BlogInfo> all = new ArrayList<BlogInfo>();
		List<String> careUsers = new ArrayList<String>();
		if (careList != null) {
			for (CareInfo c : careList) {
				String cuser =c.getCareUser();
				if(careUsers.contains(cuser)){
					continue;
				}
				careUsers.add(cuser);
				BlogInfo blog = new BlogInfo();
				blog.setBlogUser(c.getCareUser());
				List<BlogInfo> bs = getThemes(blog, pageNum);
				all.addAll(bs);
			}
		}
		for (BlogInfo b : all) {
			List<UserInfo> userList = new ArrayList<UserInfo>();
			if (careList != null) {
				b.setCareCount(careList.size());
				for (int i = 0; i < careList.size(); i++) {
					if (i == 10) {
						break;
					}
					UserInfo users = userService.getMe(careList.get(i)
							.getCreatUser());
					userList.add(users);
				}
			}
			b.setCareList(userList);
			List<CommentInfo> result = getComments(b.getBlogId(), 1, 1);
			if (result == null) {
				result = new ArrayList<CommentInfo>();
			}
			b.setCommentList(result);
		}
		
		
		model.addAttribute("bs", all);
		if (pageNum == null) {
			model.addAttribute("pageNum", 1);
		} else {
			model.addAttribute("pageNum", pageNum);
		}
		return "myCare";
	}

	@RequestMapping("/aboutus")
	public String aboutus() {
		return "aboutus";
	}

	@RequestMapping("/jyh")
	public String jyh() {
		return "hui";
	}

	List<BlogInfo> getThemes(BlogInfo blog, Integer pageNum) {
		Page page = new Page();
		page.setSize(10);
		if (pageNum == null || pageNum == 1) {
			page.setStart(0);
		} else {
			page.setStart(2 * pageNum);
		}
		List<String> sorts = new ArrayList<String>();
		sorts.add("createTime desc");
		page.setSorts(sorts);
		List<BlogInfo> result = blogService.getBlogPage(blog, page).getItems();

		return result;

	}

	private List<CommentInfo> getComments(String blogId, int pageNum, int size) {
		try {
			if (StringUtils.isEmpty(blogId)) {
				return null;
			}
			CommentInfo model = new CommentInfo();
			model.setBlogId(blogId);
			Page page = new Page();
			page.setSize(size);
			if (pageNum > 5) {
				pageNum = 5;
			}
			if (pageNum == 1) {
				page.setStart(0);
			} else {
				page.setStart(8 * pageNum);
			}
			List<String> sorts = new ArrayList<String>();
			sorts.add("createTime asc");
			page.setSorts(sorts);
			List<CommentInfo> result = commentService.get(model, page);
			return result;
		} catch (Exception e) {
			log.error("loadComment", e);
			return null;
		}
	}
	
	@RequestMapping("/collect")
	@NeedLogin
	public String collect(Model model,Integer pageNum) {
		
		List<String> sorts = new ArrayList<String>();
		sorts.add("createTime desc");
		Page page = new Page();
		page.setSorts(sorts);
		page.setSize(10);
		CollectInfo collect = new CollectInfo();
		collect.setCreatUser(UserContext.getCurrentContext()
				.getCurrentUserName());
		List<CollectInfo> result = collectService.get(collect, page);
		List<BlogInfo> all = new ArrayList<BlogInfo>();
		if(result != null){
			for (CollectInfo c : result) {
				BlogInfo q = new BlogInfo();
				q.setId(c.getBlogId());
				q = blogService.getBlog(q);
				all.add(q);
			}
		}
		model.addAttribute("bs", all);
		if (pageNum == null) {
			model.addAttribute("pageNum", 1);
		} else {
			model.addAttribute("pageNum", pageNum);
		}
		return "collect";
	}
}
