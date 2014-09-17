package org.hbhk.maikkr.user.server.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.annotation.NeedLogin;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.maikkr.user.server.service.ICareService;
import org.hbhk.maikkr.user.share.pojo.CareInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@NeedLogin
public class CareController extends BaseController {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private ICareService careService;
	
	@RequestMapping("/friends")
	public String msg(Model model) {
		CareInfo care = new CareInfo();
		model.addAttribute("careList", careService.get(care));
		return "friends";
	}
	
	@RequestMapping("/editCare")
	@ResponseBody
	public ResponseEntity editCare(String id) {
		try {
			CareInfo care = new CareInfo();
			care.setId(id);
			care.setStatus(2);
			careService.update(care);
			return returnSuccess();
		} catch (BusinessException e) {
			log.error("editCare", e);
			return returnException(e.getMessage());
		}

	}
}
