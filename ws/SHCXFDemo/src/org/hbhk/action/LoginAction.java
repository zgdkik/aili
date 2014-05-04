package org.hbhk.action;

import org.hbhk.domain.User;
import org.hbhk.service.UserService;


public class LoginAction {
	//接收表单
	private User user;
	
	private UserService userService;


	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String execute(){
		// if(userService.findLogin(user)){
		// return "success";
		// }
		return "login";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
