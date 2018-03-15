package com.deppon.esb.management.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.user.domain.SysUserInfo;

/**
 * @Description SysUserDao测试类
 * @author HuangHua
 * @date 2012-4-24下午10:42:30
 * @modify 2012-04-24 22:42:42 修改为dbunit方式
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/user/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class SysUserDaoTest extends DaoDBUnitSupportUnitTests {

	@Resource
	ISysUserDao sysUserDao;

	@Test
	public void querySysUserCountTest() {
		Integer count = sysUserDao.querySysUserCount(new HashMap<String, Object>());
		logger.info(count);
		Assert.assertNotNull(count);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void querySysUsersTest() {
		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setSysUserName("test");
		Map map = new HashMap<String, Object>();
		map.put("sysUserInfo", null);
		map.put("start", "0");
		map.put("limit", "20");
		List<SysUserInfo> sysUserInfos = sysUserDao.querySysUsers(map);
		logger.info(sysUserInfos.get(0).getSysUserName());
		logger.info(sysUserInfos.size());
		Assert.assertTrue(!sysUserInfos.isEmpty());
	}
	
	@Test
	public void addSysUserTest() {
		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setUserName("test");
		int count = sysUserDao.addSysUser(sysUserInfo);
		Assert.assertTrue(count > 0);
	}
	
	@Test
	public void querySysUserBySysUserNameTest(){
		String result = sysUserDao.querySysUserBySysUserName("huanghua");
		logger.info(result);
		Assert.assertTrue("huanghua".equals(result));
	}

}
