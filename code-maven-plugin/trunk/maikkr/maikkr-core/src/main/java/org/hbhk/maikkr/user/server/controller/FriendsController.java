package org.hbhk.maikkr.user.server.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.annotation.NeedLogin;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.security.server.dao.IUserDao;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.maikkr.user.server.service.IFriendInfoService;
import org.hbhk.maikkr.user.server.service.IMsgInfoService;
import org.hbhk.maikkr.user.share.pojo.MsgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@NeedLogin
public class FriendsController extends BaseController {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private IFriendInfoService friendInfoService;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IMsgInfoService msgInfoService;
	
	@RequestMapping("/friends")
	public String toFriends(Model model) {
		UserInfo user = new UserInfo();
		List<UserInfo> users = userDao.get(user);
		model.addAttribute("fs", users);
		MsgInfo msg = new MsgInfo();
		List<MsgInfo> msgs = msgInfoService.get(msg);
		model.addAttribute("msgs", msgs);
		return "friends";
	}

}
