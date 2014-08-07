package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.hbhk.maikkr.backend.server.service.IAdminService;
import org.hbhk.maikkr.backend.shared.pojo.AdminConstants;
import org.hbhk.maikkr.backend.shared.pojo.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/backend")
public class BackendController extends BaseController {

	@Autowired
	private IAdminService adminService;

	@RequestMapping("loginpage")
	public String loginpage() {
		return "login";
	}

	@RequestMapping("main")
	@NeedLogin
	public String main() {
		return "main";
	}

	@RequestMapping("top")
	@NeedLogin
	public String top() {
		return "top";
	}

	@RequestMapping("bloglist")
	@NeedLogin
	public String bloglist() {
		return "bloglist";
	}

	@RequestMapping("userlist")
	@NeedLogin
	public String userlist() {
		return "userlist";
	}

	@RequestMapping("left")
	@NeedLogin
	public String left() {
		return "left";
	}

	@RequestMapping("mainfra")
	@NeedLogin
	public String mainfra() {
		return "mainfra";
	}

	@RequestMapping("login")
	@ResponseBody
	public ResponseEntity login(AdminInfo admin) {
		try {
			if (adminService.login(admin) == null) {
				return returnException("用户名或密码错误");
			} else {
				RequestContext.getSession().setAttribute(
						AdminConstants.adminkey, admin.getEmail());
				return returnSuccess();
			}
		} catch (Exception e) {
			return returnException("系统异常");
		}
	}

	@RequestMapping("regist")
	@ResponseBody
	@NeedLogin
	public ResponseEntity regist(AdminInfo admin) {
		try {
			adminService.regist(admin);
			return returnSuccess("添加成功");
		} catch (Exception e) {
			return returnException("添加失败");
		}
	}

}