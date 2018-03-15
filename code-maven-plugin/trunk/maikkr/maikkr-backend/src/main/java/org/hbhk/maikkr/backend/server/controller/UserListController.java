package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.intercptor.QueryBeanParam;
import org.hbhk.aili.orm.server.page.bean.QueryBean;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.aili.security.server.service.IUserService;
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
	@Autowired
	private IUserService userService;
	@RequestMapping("/userDatas")
	@ResponseBody
	public Pagination<UserInfo> userList(@QueryBeanParam QueryBean queryBean) {
		
		return userService.queryUsersByPage(queryBean.getPage(), null, queryBean.getParaMap());
	}
	
	/**
	 * add, del
	 * @param oper
	 * @param user
	 * @return
	 */
	@RequestMapping("/editUser")
	@ResponseBody
	public ResponseEntity editUser(String oper, UserInfo user) {
		try {
			if(oper.equals("del")){
				user.setStatus(2);
				userService.update(user);	
			}
			return returnSuccess();
		} catch (Exception e) {
			return returnException("注册失败");
		}
	}

}