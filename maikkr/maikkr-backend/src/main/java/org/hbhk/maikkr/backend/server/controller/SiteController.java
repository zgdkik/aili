package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.intercptor.QueryBeanParam;
import org.hbhk.aili.orm.server.page.bean.QueryBean;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.hbhk.maikkr.core.server.interceptor.SecurityInterceptor;
import org.hbhk.maikkr.core.server.service.ISiteInfoService;
import org.hbhk.maikkr.core.shared.pojo.SiteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/backend")
@NeedLogin
@Controller
public class SiteController extends BaseController {

	@Autowired
	private ISiteInfoService siteInfoService;
	
	@RequestMapping("/sitelist")
	public String list(){
		return "sitelist";
	}

	@RequestMapping("/querySitesByPage")
	@ResponseBody
	public Pagination<SiteInfo> querySitesByPage(
			@QueryBeanParam QueryBean queryBean) {
		return siteInfoService.findSiteInfoListByQueryMapWithPage(queryBean.getPage(), null,
				queryBean.getParaMap());
	}


	/**
	 * add, del
	 * 
	 * @param oper
	 * @param user
	 * @return
	 */
	@RequestMapping("/editSite")
	@ResponseBody
	public ResponseEntity editSite(String oper, SiteInfo theme) {
		try {
			if (oper.equals("del")) {
				theme.setStatus(2);
				siteInfoService.update(theme);
			}
			if (oper.equals("add")) {
				siteInfoService.save(theme);
			}
			if (oper.equals("edit")) {
				siteInfoService.update(theme);
			}
			SecurityInterceptor.siteInfo = null;
			return returnSuccess();
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}

}