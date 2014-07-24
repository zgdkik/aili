package org.hbhk.aili.security.server.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.define.SecurityConstant;
import org.hbhk.aili.security.share.define.UserConstants;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(SecurityConstant.moduleName)
public class SecurityController extends BaseController {

	@Resource
	private IUserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public ResponseEntity login(HttpServletResponse response, String email,
			String pwd) {
		try {
			if (userService.login(email, pwd)) {
				return returnSuccess();
			} else {
				return returnException();
			}
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}

	}

	@RequestMapping("/regist")
	@ResponseBody
	public ResponseEntity regist(HttpServletRequest request, UserInfo user,
			String code) {
		try {
			String scode = (String) request.getSession().getAttribute(
					UserConstants.VALIDATECODE_SESSION_KEY);
			if (scode == null) {
				return returnException("验证码不正确");
			}
			if (code != null && !code.equals(scode)) {
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
	@ResponseBody
	public ResponseEntity getUserByMail(String mail) {
		try {
			UserInfo u = new UserInfo();
			u.setMail(mail);
			if (userService.getUser(u) == null) {
				return returnSuccess();
			} else {
				return returnException();
			}
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/validateName")
	@ResponseBody
	public ResponseEntity getUserByName(String name) {
		try {
			UserInfo u = new UserInfo();
			u.setName(name);
			if (userService.getUser(u) == null) {
				return returnSuccess();
			} else {
				return returnException();
			}
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}



}