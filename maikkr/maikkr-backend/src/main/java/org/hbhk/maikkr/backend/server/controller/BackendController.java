package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.maikkr.backend.server.service.IAdminService;
import org.hbhk.maikkr.backend.shared.pojo.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backend")
public class BackendController extends BaseController {

	@Autowired
	private IAdminService adminService;

	@RequestMapping("loginpage")
	public String loginpage() {
		return "login";

	}

	@RequestMapping("login")
	public ResponseEntity login(AdminInfo admin) {
		try {
			if (adminService.get(admin) == null) {
				return returnException("用户名或密码错误");
			} else {
				return returnSuccess();
			}
		} catch (Exception e) {
			return returnException("系统异常");
		}
	}

}