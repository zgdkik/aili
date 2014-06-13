package org.hbhk.aili.hibernate.server.controller;

import javax.annotation.Resource;

import org.hbhk.aili.core.server.annotation.SecurityFilter;
import org.hbhk.aili.hibernate.server.dao.impl.CateDao;
import org.hbhk.aili.hibernate.server.dao.impl.UsersDao1;
import org.hbhk.aili.hibernate.server.service.ICateService;
import org.hbhk.aili.hibernate.share.model.Category;
import org.hbhk.aili.hibernate.share.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HibernateTestController {

	@Resource
	UsersDao1 userDao1;
	@Resource
	ICateService cateService;

	@RequestMapping("/t1")
	@SecurityFilter(false)
	@ResponseBody
	public String t1() {
		User user = new User();
		user.setAge("12");
		user.setId("1");
		user.setUserName("hbhk");
		userDao1.save(user);
		return "";
	}

	public void t2() {
		Category c = new Category();
		c.setId(2);
		c.setName("c1");
		c.setDescription("desc");
		cateService.save(c);

	}

	public void t3() {

	}

}
