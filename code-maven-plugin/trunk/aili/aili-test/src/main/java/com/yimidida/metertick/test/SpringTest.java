package com.yimidida.metertick.test;


import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yimidida.metertick.base.server.context.UserContext;
import com.yimidida.metertick.user.share.vo.CurrentUserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test/spring.xml" })
public class SpringTest extends AbstractJUnit4SpringContextTests {
	
	@BeforeClass
	public static void init(){
		CurrentUserVo user = new CurrentUserVo();
		user.setCompCode("yimidida");
		user.setDeptCode("test");
		user.setUserName("test");
		UserContext.setCurrentUser(user);
		System.out.println("初始化当前用户完成");
	}

	public <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}

	public Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	protected ApplicationContext getContext() {
		return applicationContext;
	} 
}