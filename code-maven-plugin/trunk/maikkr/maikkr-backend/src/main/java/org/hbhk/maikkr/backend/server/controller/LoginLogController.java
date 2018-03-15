package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.intercptor.QueryBeanParam;
import org.hbhk.aili.orm.server.page.bean.QueryBean;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.aili.security.server.service.ILoginLogInfoService;
import org.hbhk.aili.security.share.pojo.LoginLogInfo;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/backend")
@NeedLogin
public class LoginLogController extends BaseController {

	@Autowired
	private ILoginLogInfoService loginLogInfoService;

	@RequestMapping("/queryLogsByPage")
	@ResponseBody
	public Pagination<LoginLogInfo> queryAdminsByPage(
			@QueryBeanParam QueryBean queryBean) {

		return loginLogInfoService.queryLogsByPage(queryBean.getPage(), null,
				queryBean.getParaMap());
	}

	/**
	 * add, del
	 * 
	 * @param oper
	 * @param user
	 * @return
	 */
	@RequestMapping("/editLog")
	@ResponseBody
	public ResponseEntity editAdmin(String oper, LoginLogInfo log) {
		try {
			if (oper.equals("del")) {
				log.setStatus(2);
				loginLogInfoService.update(log);
			}
			return returnSuccess();
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}


	@RequestMapping("loglist")
	public String loglist() {
		
		return "loglist";
	}
}