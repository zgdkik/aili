package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.orm.server.intercptor.QueryBeanParam;
import org.hbhk.aili.orm.server.page.bean.QueryBean;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.hbhk.maikkr.user.server.service.IThemeService;
import org.hbhk.maikkr.user.share.pojo.ThemeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/backend")
@NeedLogin
@Controller
public class CommThemeController extends BaseController {

	@Autowired
	private IThemeService themeService;
	
	@RequestMapping("/themelist")
	public String themelist(){
		return "themelist";
	}

	@RequestMapping("/queryCommThemesByPage")
	@ResponseBody
	public Pagination<ThemeInfo> findThemeInfoListByQueryMapWithPage(
			@QueryBeanParam QueryBean queryBean) {
		return themeService.findThemeInfoListByQueryMapWithPage(queryBean.getPage(), null,
				queryBean.getParaMap());
	}


	/**
	 * add, del
	 * 
	 * @param oper
	 * @param user
	 * @return
	 */
//	@RequestMapping("/editTheme")
//	@ResponseBody
//	public ResponseEntity editTheme(String oper, ThemeInfo theme) {
//		try {
//			if (oper.equals("del")) {
//				theme.setStatus(2);
//				themeService.update(theme);
//			}
//			if (oper.equals("add")) {
//				themeService.save(theme);
//			}
//			if (oper.equals("edit")) {
//				themeService.update(theme);
//			}
//			return returnSuccess();
//		} catch (BusinessException e) {
//			return returnException(e.getMessage());
//		}
//	}

}