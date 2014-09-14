package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.core.share.util.EncryptUtil;
import org.hbhk.aili.orm.server.intercptor.QueryBeanParam;
import org.hbhk.aili.orm.server.page.bean.QueryBean;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.hbhk.maikkr.backend.server.service.IAdminService;
import org.hbhk.maikkr.backend.shared.pojo.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/backend")
@NeedLogin
public class AdminController extends BaseController {

	@Autowired
	private IAdminService adminService;

	@RequestMapping("/queryAdminsByPage")
	@ResponseBody
	public Pagination<AdminInfo> queryAdminsByPage(
			@QueryBeanParam QueryBean queryBean) {

		return adminService.queryAdminsByPage(queryBean.getPage(), null,
				queryBean.getParaMap());
	}

	/**
	 * add, del
	 * 
	 * @param oper
	 * @param user
	 * @return
	 */
	@RequestMapping("/editAdmin")
	@ResponseBody
	public ResponseEntity editAdmin(String oper, AdminInfo admin) {
		try {
			if (oper.equals("del")) {
				admin.setStatus(2);
				adminService.update(admin);
			}
			if (oper.equals("add")) {
				admin.setPwd("123456");
				adminService.regist(admin);
			}
			if (oper.equals("edit")) {
				admin.setPwd(EncryptUtil.encodeSHA1(admin.getPwd()));
				adminService.update(admin);
			}
			return returnSuccess();
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}

}