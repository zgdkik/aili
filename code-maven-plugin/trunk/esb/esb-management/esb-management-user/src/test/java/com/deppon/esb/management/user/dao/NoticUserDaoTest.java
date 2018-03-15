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
import com.deppon.esb.management.user.domain.NoticUserInfo;

/**
 * @Description NoticUserDao测试类
 * @author HuangHua
 * @date 2012-4-24下午10:41:04
 * @modify 2012-04-24 22:41:24 修改为dbunit方式
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/user/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class NoticUserDaoTest extends DaoDBUnitSupportUnitTests {

	@Resource
	INoticUserDao noticUserDao;

	@Test
	public void queryNoticUserCountTest() {
		Integer count = noticUserDao.queryNoticUserCount(new HashMap<String, Object>());
		logger.info(count);
		Assert.assertTrue(2 == count);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void queryNoticUsersTest() {
		Map map = new HashMap<String, Object>();
		map.put("start", "0");
		map.put("limit", "20");
		List<NoticUserInfo> noticUserInfos = noticUserDao.queryNoticUsers(map);
		Assert.assertTrue(noticUserInfos.size() == 2);
	}

	@Test
	public void deleteNoticUsersByIdsTest() {
		String[] strings = { "36985", "36986" };
		int result = noticUserDao.deleteNoticUsersByIds(strings);
		Assert.assertTrue(result != 0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void querySysUsersTest() {
		Map map = new HashMap<String, Object>();
		map.put("start", "0");
		map.put("limit", "20");
		List<NoticUserInfo> noticUserInfos = noticUserDao.querySysUsers(map);
		Assert.assertTrue(noticUserInfos.size() == 2);
	}

	@Test
	public void queryEmailByIdTest() {
		String[] results = noticUserDao.queryEmailById(new String[] { "36985", "36986" });
		Assert.assertEquals(2, results.length);
	}
	
	@Test
	public void queryNoticeUserTest() {
		NoticUserInfo user = noticUserDao.queryNoticeUser("36986");
		Assert.assertEquals("deppon@deppon.com11", user.getEmail());
	}

	@Test
	public void updateNoticeUserTest(){
		NoticUserInfo user = noticUserDao.queryNoticeUser("36986");
		user.setPjVersion("ESB1");
		int result = noticUserDao.updateNoticuser(user);
		Assert.assertEquals(1, result);
	}
}
