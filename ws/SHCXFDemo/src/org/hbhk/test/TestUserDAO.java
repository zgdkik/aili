package org.hbhk.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;
import org.hbhk.dao.UserDAO;
import org.hbhk.domain.Person;
import org.hbhk.domain.User;
import org.hbhk.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserDAO {
	@Test
	public void testFindByEmail() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("ssh.xml");
		UserDAO userDao = (UserDAO) ac.getBean("userDao");
		User user = userDao.findByEmail("jdbc@163.com");
		Assert.assertEquals("hbhk", user.getNickname());
		Assert.assertEquals(new Integer(1), user.getId());

		System.out.println(user.getNickname());

	}

	@Test
	public void testSaveUser() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("ssh.xml");
		UserService userService = (UserService) ac.getBean("userService");
		// User user = new User();
		// user.setId(2);
		// user.setEmail("sfsdfsdf");
		Person p = new Person();
		p.setName("hbhk");
		p.setSex("F");
		userService.saveUser(p);
		
	}
}
