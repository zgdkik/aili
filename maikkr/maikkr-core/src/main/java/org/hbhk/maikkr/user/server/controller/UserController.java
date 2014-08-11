package org.hbhk.maikkr.user.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.annotation.NeedLogin;
import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.maikkr.core.server.event.UpdateBlogHitsEvent;
import org.hbhk.maikkr.user.server.service.IAttentionService;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.hbhk.maikkr.user.server.service.ICollectService;
import org.hbhk.maikkr.user.server.service.ICommentService;
import org.hbhk.maikkr.user.server.service.IThemeService;
import org.hbhk.maikkr.user.share.pojo.AttentionInfo;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;
import org.hbhk.maikkr.user.share.pojo.CollectInfo;
import org.hbhk.maikkr.user.share.pojo.CommentInfo;
import org.hbhk.maikkr.user.share.pojo.ThemeInfo;
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
	@Autowired
	private IThemeService themeService;
	@Autowired
	private IAttentionService attentionService;
	@Autowired
	private ICommentService commentService;
	@Autowired
	private ICollectService collectService;
	
	@RequestMapping("/main")
	public String main(Model model) {
		return "index";
	}

	@RequestMapping("/newhit")
	public String newhit(Model model) {
		return "newhit";
	}

	@RequestMapping("/newest")
	public String newest(Model model) {
		return "newest";
	}

	@RequestMapping("/msg")
	public String msg(Model model) {
		return "msg";
	}

	@RequestMapping("/set")
	public String set(Model model) {
		return "setting";
	}

	@RequestMapping("/collect")
	public String collect(Model model) {
		return "collect";
	}

	@RequestMapping("/friends")
	public String friends(Model model) {
		return "friends";
	}

	@RequestMapping("/loginpage")
	public String loginpage(HttpServletRequest request) {
		String returnUrl = (String) RequestContext.getSession().getAttribute("returnUrl");
		 request.setAttribute("returnUrl", returnUrl);
		return "loginpage";
	}

	@RequestMapping("/sendTheme")
	@ResponseBody
	@NeedLogin
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
	public ResponseEntity getPageTheme(BlogInfo blog, int pageNum) {
		try {
			Page page = new Page();
			page.setSize(2);
			if (pageNum > 5) {
				pageNum = 5;
			}
			if (pageNum == 1) {
				page.setStart(0);
			} else {
				page.setStart(2 * pageNum);
			}
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
			String blogUrl = user + "/" + url + "";
			BlogInfo blog = new BlogInfo();
			blog.setBlogUrl(blogUrl);
			model.addAttribute("theme", blogService.getBlog(blog));
			// 修改对应主题的点击数供最热查询
			UpdateBlogHitsEvent blogHitsEvent = new UpdateBlogHitsEvent(blogUrl);
			getWebApplicationContext().publishEvent(blogHitsEvent);
			return "comment";
		} catch (Exception e) {
			log.error("getTheme", e);
			return "redirect:/core/error";
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

	@RequestMapping("/saveTheme")
	@ResponseBody
	@NeedLogin
	public ResponseEntity saveTheme(ThemeInfo theme) {
		try {
			String user = UserContext.getCurrentContext().getCurrentUserName();
			if (user != null) {
				theme.setCreatUser(user);
				if (themeService.get(theme) == null) {
					themeService.save(theme);
				}
			}
			return returnSuccess();
		} catch (Exception e) {
			log.error("saveTheme", e);
			return returnException();
		}

	}

	@RequestMapping("/loadUserTheme")
	@ResponseBody
	public ResponseEntity loadUserTheme() {
		try {
			List<ThemeInfo> themeInfos = new ArrayList<ThemeInfo>();
			for (int i = 0; i < 5; i++) {
				ThemeInfo t = new ThemeInfo();
				t.setName("theme" + i);
				t.setType("user_type" + i);
				themeInfos.add(t);
			}
			themeService.loadUserTheme();
			return returnSuccess(themeInfos);
		} catch (Exception e) {
			log.error("loadUserTheme", e);
			return returnSuccess(new ArrayList<ThemeInfo>());
		}

	}

	@RequestMapping("/attenUser")
	@ResponseBody
	@NeedLogin
	public ResponseEntity attenUser(AttentionInfo atten) {
		try {
			attentionService.save(atten);
			return returnSuccess();
		} catch (Exception e) {
			log.error("attenUser", e);
			return returnException();
		}

	}

	@RequestMapping("/sendComment")
	@ResponseBody
	@NeedLogin
	public ResponseEntity sendComment(CommentInfo comm) {
		try {
			if (commentService.save(comm) == null) {
				return returnException();
			}
			return returnSuccess();
		} catch (Exception e) {
			log.error("sendComment", e);
			return returnException();
		}

	}

	@RequestMapping("/loadComment")
	@ResponseBody
	public ResponseEntity loadComment(String blogId, int pageNum) {
		try {
			if (StringUtils.isEmpty(blogId)) {
				return returnException();
			}
			CommentInfo model = new CommentInfo();
			model.setBlogId(blogId);
			Page page = new Page();
			page.setSize(8);
			if (pageNum > 5) {
				pageNum = 5;
			}
			if (pageNum == 1) {
				page.setStart(0);
			} else {
				page.setStart(8 * pageNum);
			}
			Object result = commentService.get(model, page);
			return returnSuccess(result);
		} catch (Exception e) {
			log.error("loadComment", e);
			return returnException();
		}

	}
	
	@RequestMapping("/collectComment")
	@ResponseBody
	@NeedLogin
	public ResponseEntity collectComment(CollectInfo comm) {
		try {
			if (collectService.save(comm) == null) {
				return returnException();
			}
			return returnSuccess();
		} catch (Exception e) {
			log.error("collect", e);
			return returnException(e.getMessage());
		}

	}

}