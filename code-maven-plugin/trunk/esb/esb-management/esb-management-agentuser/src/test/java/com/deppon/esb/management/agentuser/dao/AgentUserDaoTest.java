package com.deppon.esb.management.agentuser.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.agentuser.domain.AgentUserInfo;
import com.deppon.esb.management.agentuser.view.AgentUserQueryBean;
import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.security.domain.UserInfo;
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/agentuser/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class AgentUserDaoTest extends DaoDBUnitSupportUnitTests {
	
	@Resource
	private IAgentUserDao agentUserDao;

	
	@Test
	public void addAgentUserTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUser("test");
		userInfo.setDesc("agentUser");
		userInfo.setPasswd("test");
		userInfo.setActive("Y");
		int addCount = agentUserDao.addAgentUser(userInfo);
		Assert.assertEquals(1, addCount);
	}
	
	@Test
	public void canelAgenUserTest() {
		int count = countRowsInTable("T_ESB2_USER");
		List<String> list = new ArrayList<String>();
		list.add("测试落地配代理");
		//list.add("USERNAME2");
		//list.add("USERNAME3");
		int canelCount = agentUserDao.canelAgenUser(list);
		//Assert.assertEquals(count, canelCount);
	}
	
	@Test
	public void activeAgenUserTest() {
		int count = countRowsInTable("T_ESB2_USER");
		List<String> list = new ArrayList<String>();
		list.add("测试落地配代理");
		//list.add("USERNAME2");
		//list.add("USERNAME3");
		int activeCount = agentUserDao.canelAgenUser(list);
		//Assert.assertEquals(count, activeCount);
	}
	
	@Test
	public void queryAgentUser() {
		//int count = countRowsInTable("T_ESB2_EXPRESS_AGENT");
		queryAllAgentUserTest();
		AgentUserQueryBean agentUserQueryBean = new AgentUserQueryBean();
		agentUserQueryBean.setUserName("落地配代理1");
		agentUserQueryBean.setAgentName("LDP00001");
		agentUserQueryBean.setStatus("Y");
		List<UserInfo> list = agentUserDao.queryAgentUser(agentUserQueryBean);
		//Assert.assertEquals(count, list.size());
		Assert.assertNotNull(list.get(0).getUser());
	}
	
	@Test
	public void queryAllAgentUserTest() {
		int count = countRowsInTable("T_ESB2_USER");
		List<UserInfo> list = agentUserDao.queryAllAgentUser();
		Assert.assertEquals(count, list.size());
	}
	
	@Test
	public void updateAgentUserTest() {
		List<UserInfo> list = agentUserDao.queryAllAgentUser();
		UserInfo userInfo = list.get(0);
		userInfo.setDesc("xxbb");
		userInfo.setCreateTime(null);
		userInfo.setPasswd("abc");
		userInfo.setActive(null);
		userInfo.setUser("abcde");
		int updateCount = agentUserDao.updateAgentUser(userInfo);
		Assert.assertEquals(1, updateCount);
	}
	
	
	@Test
	public void deleteUserTest() {
		int totalCount = countRowsInTable("T_ESB2_USER");
		List<UserInfo> list = agentUserDao.queryAllAgentUser();
		List<String> ids = new ArrayList<String>();
		for(UserInfo item:list){
			ids.add(item.getFid());
		}
		int deleteCount = agentUserDao.deleteAgentUser(ids);
		Assert.assertEquals(totalCount, deleteCount);
	}
}
