package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.intercptor.QueryBeanParam;
import org.hbhk.aili.orm.server.page.bean.QueryBean;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.hbhk.maikkr.core.server.service.IBizInfoService;
import org.hbhk.maikkr.core.shared.pojo.BizInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/backend")
@NeedLogin
@Controller
public class BizController extends BaseController {

	@Autowired
	private IBizInfoService bizInfoService;

	@RequestMapping("/bizlist")
	public String themelist() {
		return "bizlist";
	}

	@RequestMapping("/queryBizsByPage")
	@ResponseBody
	public Pagination<BizInfo> queryBizsByPage(
			@QueryBeanParam QueryBean queryBean) {
		return bizInfoService.findEffectBizInfoListByQueryMapWithPage(
				queryBean.getPage(), null, queryBean.getParaMap());
	}

	/**
	 * add, del
	 * 
	 * @param oper
	 * @param user
	 * @return
	 */
	@RequestMapping("/editBiz")
	@ResponseBody
	public ResponseEntity editTheme(String oper, BizInfo biz) {
		try {
			if (oper.equals("del")) {
				biz.setStatus(2);
				bizInfoService.update(biz);
			}
			if (oper.equals("add")) {
				bizInfoService.save(biz);
			}
			if (oper.equals("edit")) {
				bizInfoService.update(biz);
			}
			return returnSuccess();
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}

}