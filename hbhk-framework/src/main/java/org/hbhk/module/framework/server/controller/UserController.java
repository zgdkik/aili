package org.hbhk.module.framework.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hbhk.module.framework.server.service.IUserService;
import org.hbhk.module.framework.shared.domain.EmpEntity;
import org.hbhk.module.framework.shared.domain.UserEntity;
import org.hbhk.module.framework.shared.exception.CacheConfigException;
import org.hbhk.module.framework.shared.util.MessageInfo;
import org.hbhk.module.framework.shared.util.ModuleConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(ModuleConstants.MODULE_FRAMEWORK)
public class UserController {

	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/welcome")
	public String welcome() throws Exception {
		
		return "welcome";
	}

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	@ResponseBody
	public UserEntity userList() {
		int n   = 1;
		if(n==1){
			throw  new CacheConfigException("test exce");
		}
		UserEntity u = new UserEntity();
		u.setUsername("e343");
		u.setPassword("fsdfsdf");

		return u;
	}

	@RequestMapping(value = "/empList")
	@ResponseBody
	public List<EmpEntity> empList(String param) {
		List<EmpEntity> emps = new ArrayList<EmpEntity>();
		int c = 10;
		if (param != null && !param.equals("all")) {
			c = 20;
		}
		for (int i = 0; i < c; i++) {
			EmpEntity e = new EmpEntity();
			e.setAge(i);
			e.setCreatetime(new Date());
			e.setGender(1);
			e.setLoginname("0000" + i);
			e.setSalary(10000L);
			e.setName("hbhk" + i);
			emps.add(e);
		}
		return emps;
	}
	@RequestMapping(value = "/insertUser")
	@ResponseBody
	public MessageInfo insertUser(@RequestBody UserEntity user) {
		MessageInfo mv = new MessageInfo();
		if (user != null) {
			userService.insertUser(user);
			mv.setMsg("保存成功");
		}

		return mv;

	}

	@RequestMapping("/saveUser")
	public String saveUser() {
		
		return "saveUser";

	}
	
	@RequestMapping("/authorizeError")
	public String authorizeError() {
		
		return "authorizeError";

	}
}