package org.hbhk.maikkr.backend.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.hbhk.maikkr.backend.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/backend")
@NeedLogin
public class UserListController extends BaseController {

	@Autowired
	private IAdminService adminService;

	@RequestMapping("/userDatas")
	@ResponseBody
	public Pagination<UserInfo> userList() {
		Pagination<UserInfo> page = new Pagination<UserInfo>();
		List<UserInfo> users = new ArrayList<UserInfo>();
		for (int i = 0; i < 50; i++) {
			UserInfo user = new UserInfo();
			user.setName("name"+i);
			user.setMail("email"+i);
			user.setRemail("remail"+i);
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setStatus(1);
			users.add(user);
			
		}
		page.setItems(users);
		page.setCurrentPage(1);
		page.setTotalPages(10);
		page.setCount(50);
		return page;
	}

}