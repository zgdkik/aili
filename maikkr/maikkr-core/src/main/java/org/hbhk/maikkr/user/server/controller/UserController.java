package org.hbhk.maikkr.user.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.aili.core.server.annotation.NeedLogin;
import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.core.share.util.EncryptUtil;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.security.server.cache.UserCache;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.maikkr.core.server.event.UpdateBlogHitsEvent;
import org.hbhk.maikkr.core.shared.util.AreaUtils;
import org.hbhk.maikkr.user.server.service.IAttentionService;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.hbhk.maikkr.user.server.service.ICareService;
import org.hbhk.maikkr.user.server.service.ICollectService;
import org.hbhk.maikkr.user.server.service.ICommentService;
import org.hbhk.maikkr.user.server.service.IThemeService;
import org.hbhk.maikkr.user.share.pojo.AttentionInfo;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;
import org.hbhk.maikkr.user.share.pojo.CareInfo;
import org.hbhk.maikkr.user.share.pojo.CollectInfo;
import org.hbhk.maikkr.user.share.pojo.CommentInfo;
import org.hbhk.maikkr.user.share.pojo.ThemeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

	@Autowired
	private ICareService careService;

	@RequestMapping("/main")
	public String main(Model model, Integer pageNum, BlogInfo blog) {
		model.addAttribute("ps", AreaUtils.getProvinces());
		// 获取车型
		List<ThemeInfo> themeInfos = themeService.loadUserThemeType();
		model.addAttribute("carType", themeInfos);
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

			List<CommentInfo> result = getComments(b.getBlogId(), 1,1);
			if(result==null){
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

		return "index";
	}

	private List<CommentInfo> getComments(String blogId, int pageNum,int size) {
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

	@RequestMapping("/newhit")
	public String newhit(Model model) {
		return "newhit";
	}

	@RequestMapping("/newest")
	public String newest(Model model) {
		return "newest";
	}

	@RequestMapping("/set")
	@NeedLogin
	public String set(Model model) {
		model.addAttribute("uc", UserContext.getCurrentContext()
				.getCurrentUser());
		model.addAttribute("ps", AreaUtils.getProvinces());
		return "setting";
	}


	@RequestMapping("/forget")
	public String forget(Model model) {
		return "forget";
	}

	@RequestMapping("/loginpage")
	public String loginpage(HttpServletRequest request) {
		String returnUrl = (String) RequestContext.getSession().getAttribute(
				"returnUrl");
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
			blog.setBlogUrl(blogUrl);
			blog = blogService.getBlog(blog);
			String username = blog.getBlogUser();
			ICache<String, UserInfo> userc = CacheManager.getInstance()
					.getCache(UserCache.cacheID);
			UserInfo userinfo = userc.get(username);
			String img = userinfo.getUserHeadImg().replaceAll("\\\\", "/");
			blog.setUserHeadImg(img);
			
			List<CommentInfo> commentInfos = getComments(blog.getBlogId(), 1, 10);
			model.addAttribute("comments", commentInfos);
			model.addAttribute("theme", blog);
			
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
			List<ThemeInfo> themeInfos = themeService.loadUserThemeType();
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
			List<String> sorts = new ArrayList<String>();
			sorts.add("createTime asc");
			page.setSorts(sorts);
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

	@RequestMapping("/updateHeadImg")
	@ResponseBody
	@NeedLogin
	public ResponseEntity updateHeadImg(String url) {
		try {
			UserInfo user = new UserInfo();
			user.setUserHeadImg(url);
			userService.update(user);
			return returnSuccess();
		} catch (Exception e) {
			log.error("updateHeadImg", e);
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/updateNickname")
	@ResponseBody
	@NeedLogin
	public ResponseEntity updateNickname(String nickname, String email) {
		try {
			UserInfo user = new UserInfo();
			user.setName(nickname);
			user.setRemail(email);
			userService.update(user);
			return returnSuccess();
		} catch (Exception e) {
			log.error("updateHeadImg", e);
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/updatePwd")
	@ResponseBody
	@NeedLogin
	public ResponseEntity updatePwd(String pwd, String rpwd) {
		try {
			rpwd = EncryptUtil.encodeSHA1(rpwd);
			String username = UserContext.getCurrentContext()
					.getCurrentUserName();
			String password = userService.getMe(username).getPassword();
			if (!rpwd.equals(password)) {
				return returnException("原始密码不正确!");
			}
			UserInfo user = new UserInfo();
			pwd = EncryptUtil.encodeSHA1(pwd);
			user.setPassword(pwd);
			userService.update(user);
			return returnSuccess();
		} catch (Exception e) {
			log.error("updatePwd", e);
			return returnException(e.getMessage());
		}
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

}