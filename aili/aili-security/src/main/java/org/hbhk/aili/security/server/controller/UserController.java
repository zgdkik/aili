package org.hbhk.aili.security.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hbhk.aili.core.server.annotation.SecurityFilter;
import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.define.SecurityConstant;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(SecurityConstant.moduleName)
public class UserController {

	@Resource
	private IUserService userService;
	
	@RequestMapping("/login")
	@SecurityFilter(false)
	public String login(String username , String password ){
		userService.login(username, password);
		return "redirect:"+SecurityConstant.moduleName+"/main.ctrl";
	}
	private List<UserInfo>  getUserList(){
		
		 List<UserInfo> userList = new ArrayList<UserInfo>();
		 for (int i = 0; i < 5; i++) {
			 UserInfo u = new UserInfo();
			 u.setGender("男"+i);
			 u.setMail(i+"@hbhk.com");
			 u.setName("何波"+i);
			 userList.add(u);
		}
		return userList;
		
	}
	
	@RequestMapping("/main")
	@SecurityFilter(false)
	public String main(ModelMap model){
		model.put("userlist", getUserList());
		return "main";
	}


}