package org.hbhk.aili.cache.mem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-jdbc-base.xml")
public class MemcachedTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private UserDao dao;
	
	@Test
	public void save(){
	
	}
	
	@Test
	public void get(){
		
	}
	
	@Test
	public void update(){
		
	}
	
	@Test
	public void delete(){
		
	}

}


