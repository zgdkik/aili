package org.hbhk.module.framework.server.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hbhk.module.framework.server.Quartz.QuartzService;
import org.hbhk.module.framework.server.context.UserConstants;
import org.hbhk.module.framework.server.context.UserContext;
import org.hbhk.module.framework.server.listener.MultipleUserBindingListener;
import org.hbhk.module.framework.server.service.IUserService;
import org.hbhk.module.framework.shared.domain.security.UsersEntity;
import org.hbhk.module.framework.shared.exception.CacheConfigException;
import org.hbhk.module.framework.shared.util.Constants;
import org.hbhk.module.framework.shared.util.ModuleConstants;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

/* 
 * 不需要实现任何接口，也不需要继承任何的类 
 */
@Controller
@RequestMapping(ModuleConstants.MODULE_FRAMEWORK)
public class LoginController {

	private IUserService userService;

//	@Resource
//	private QuartzService quartzService;

	/**
	 * 方法都可以接受的参数(参数数量和顺序没有限制)：
	 * HttpServletRequest,HttpServletResponse,HttpSession(session必须是可用的)
	 * ,PrintWriter,Map,Model,@PathVariable(任意多个)， @RequestParam（任意多个）， @CookieValue
	 * （任意多个），@RequestHeader，Object（pojo对象） ,BindingResult等等
	 * 
	 * 返回值可以是：String(视图名)，void（用于直接response），ModelAndView，Map
	 * ，Model，任意其它任意类型的对象（默认放入model中，名称即类型的首字母改成小写），视图名默认是请求路径
	 */
	@RequestMapping("/login")
	public String login() {
		// System.out.println("login...");
		// List<QuartzEntity> qs = quartzService.getQuartzJobList();
		// try {
		// quartzService.deleteJob("jobName1");
		// } catch (SchedulerException e1) {
		// e1.printStackTrace();
		// }
		// qs = quartzService.getQuartzJobList();
		//
		// if (CollectionUtils.isEmpty(qs)) {
		// String jobName = "jobName1";
		// List<String> topicIds = new ArrayList<String>();
		// topicIds.add("topicId1");
		// topicIds.add("topicId2");
		// String description = "description";
		// String cronPattern = "0 0/1 * * * ?";
		// try {
		// Class<?> jobclass = Class
		// .forName("org.hbhk.module.framework.server.Quartz.ParseModelJob");
		// Class<?>[] superclass = jobclass.getInterfaces();
		// boolean jobFlag = false;
		// for (int i = 0; i < superclass.length; i++) {
		// Class<?> class1 = superclass[i];
		// if (class1.equals(Job.class) && class1 == Job.class) {
		// jobFlag = true;
		// break;
		// }
		// }
		// if (jobFlag) {
		// Job jobInstance = (Job) jobclass.newInstance();
		// quartzService.addParseModelJob(jobName, topicIds,
		// description, cronPattern, jobInstance);
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }

		return "/login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.invalidate();
		UserContext.remove();

		return "/login";
	}

	@RequestMapping("/userLogin")
	public ModelAndView userLogin(HttpServletRequest request,
			HttpServletResponse response) throws ModelAndViewDefiningException {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ModelAndView mavF = new ModelAndView("login");
		ModelAndView mavS = new ModelAndView("welcome");
		String userSession = (String) session
				.getAttribute(UserConstants.CURRENT_USER_NAME);
		if (StringUtils.isEmpty(userSession)) {
			if (StringUtils.isEmpty(username)) {
				mavF.addObject("errorMsg", "用户名为空!");
				return mavF;
			}
			if (StringUtils.isEmpty(password)) {
				mavF.addObject("errorMsg", "密码为空!");
				return mavF;
			}
			// 检测验证码
			if (!checkValidateCode(request, mavF)) {
				mavF.addObject("errorMsg", "验证码错误!");
				return mavF;
			}

			if (username.equals("hbhk") && password.equals("135246")) {
				session.setAttribute(UserConstants.CURRENT_USER_NAME, username);
				UserContext.setCurrentUserName(username);
				session.setAttribute(UserConstants.USER_INFO_COUNT_LISTENER,
						new MultipleUserBindingListener(username, 1));

			} else {
				mavF.addObject("errorMsg", "用户名或密码错误!");
				return mavF;
			}
		}
		return mavS;
	}

	private boolean checkValidateCode(HttpServletRequest request,
			ModelAndView mav) throws ModelAndViewDefiningException {
		HttpSession session = request.getSession();

		String sessionValidateCode = obtainSessionValidateCode(session);
		// 让上一次的验证码失效
		session.setAttribute(UserConstants.VALIDATECODE_SESSION_KEY, null);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		if (StringUtils.isEmpty(validateCodeParameter)
				|| !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			return false;
		}
		return true;
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request
				.getParameter(UserConstants.VALIDATECODE_SESSION_KEY);
		return null == obj ? "" : obj.toString();
	}

	private String obtainSessionValidateCode(HttpSession session) {
		Object obj = session
				.getAttribute(UserConstants.VALIDATECODE_SESSION_KEY);
		return null == obj ? "" : obj.toString();
	}

	@RequestMapping("/common")
	public String common() {

		return "/common";
	}

	public void setUserService(IUserService userService) {

		this.userService = userService;
	}

}