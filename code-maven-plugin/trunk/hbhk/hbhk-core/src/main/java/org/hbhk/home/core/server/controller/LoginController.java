package org.hbhk.home.core.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.hbhk.home.core.server.dao.IUserDao;
import org.hbhk.home.core.share.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private IUserDao userDao;

	@RequestMapping(value = { "/" })
	public String index(Model model, UserModel user) {
		return "login";

	}

	@RequestMapping(value = { "/logout" })
	public String logout(Model model, UserModel user, HttpServletRequest request) {
		request.getSession().removeAttribute("userName");
		return "login";

	}

	@RequestMapping(value = { "/login" })
	@ResponseBody
	public String login(Model model, UserModel user, HttpServletRequest request) {
		if (user == null || user.getUserName() == null || user.getPwd() == null) {
			return "fail";
		}
		Map<String, Object> params = BeanToMapUtil.convert(user);
		if (userDao.get(params) != null && userDao.get(params).size()>0) {
			request.getSession().setAttribute("userName", user.getUserName());
			return "success";
		}
		return "fail";

	}

}
