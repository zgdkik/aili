package org.hbhk.aili.security.server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.core.server.annotation.SecurityFilter;
import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.define.SecurityConstant;
import org.hbhk.aili.security.share.define.UserConstants;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(SecurityConstant.moduleName)
public class SecurityController extends BaseController {

	@Resource
	private IUserService userService;

	@Value("${login_redirect_url}")
	private String login_redirect_url;

	@Value("${login_url}")
	private String login_url;

	@RequestMapping("/login")
	@SecurityFilter(false)
	public String login(HttpServletResponse response, String username,
			String password) {
		try {
			userService.login(username, password);
			return "redirect:" + login_redirect_url;
		} catch (BusinessException e) {
			return "redirect:" + login_url;
		}

	}

	@RequestMapping("/regist")
	@SecurityFilter(false)
	@ResponseBody
	public ResponseEntity regist(HttpServletRequest request, UserInfo user,String code) {
		try {
			String scode = (String)request.getSession().getAttribute(UserConstants.VALIDATECODE_SESSION_KEY);
			if(scode==null){
				return returnException("验证码不正确");	
			}
			if(code!=null && !code.equals(scode)){
				return returnException("验证码不正确");	
			}
			userService.save(user);
			RequestContext.setSessionAttribute(UserConstants.CURRENT_USER_NAME,
					user.getMail());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/validateEmail")
	@SecurityFilter(false)
	@ResponseBody
	public ResponseEntity getUserByMail(String mail) {
		try {
			if (userService.getUserByMail(mail) == null) {
				return returnSuccess();
			} else {
				return returnException();
			}
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}

	private List<UserInfo> getUserList() {
		List<UserInfo> userList = new ArrayList<UserInfo>();
		for (int i = 0; i < 5; i++) {
			int number = new Random().nextInt(1000);
			UserInfo u = new UserInfo();
			u.setGender("男" + number);
			u.setMail(number + "@hbhk.com");
			u.setName("何波" + number);
			userList.add(u);
		}
		return userList;

	}

	@RequestMapping("/main")
	@SecurityFilter(false)
	public String main(ModelMap model) {
		model.put("userlist", getUserList());
		return "main";
	}

}