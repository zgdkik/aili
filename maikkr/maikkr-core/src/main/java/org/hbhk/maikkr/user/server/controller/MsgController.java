package org.hbhk.maikkr.user.server.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.annotation.NeedLogin;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.maikkr.user.server.service.IMsgInfoService;
import org.hbhk.maikkr.user.share.pojo.MsgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@NeedLogin
public class MsgController extends BaseController {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private IMsgInfoService msgInfoService;

	
	@RequestMapping("/msg")
	public String msg(Model model) {
		MsgInfo msg = new MsgInfo();
		model.addAttribute("msgList", msgInfoService.get(msg));
		return "msg";
	}
	
	@RequestMapping("/msgList")
	@ResponseBody
	public List<MsgInfo> msgList(MsgInfo msg, Page page) {
		return msgInfoService.get(msg, page);
	}

	@RequestMapping("/editMsg")
	@ResponseBody
	public ResponseEntity editMsg(String id) {
		try {
			MsgInfo msg = new MsgInfo();
			msg.setId(id);
			msg.setIsRead(1);
			msgInfoService.update(msg);
			return returnSuccess();
		} catch (BusinessException e) {
			log.error("editMsg", e);
			return returnException(e.getMessage());
		}

	}
}
