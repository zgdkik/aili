/*
 * PROJECT NAME: esb-management-user
 * PACKAGE NAME: com.deppon.esb.management.user.service
 * FILE    NAME: NoticUserServiceTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.user.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.user.domain.NoticUserInfo;

/**
 * 
 * @author HuangHua
 * @date 2013-1-6 下午5:26:53
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/user/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class NoticUserServiceTest extends DaoDBUnitSupportUnitTests {

	@Resource
	private INoticUserService noticUserService;

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.user.service.INoticUserService#queryNoticUsers(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)}
	 * .
	 */
	@Test
	public void testQueryNoticUsers() {
		Map<String, Object> noticUserInfos = noticUserService.queryNoticUsers(
				null, null, null, null, 0, 20);
		assertNotNull(noticUserInfos);
		assertEquals(2, noticUserInfos.size());
	}

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.user.service.INoticUserService#deleteNoticUsersByIds(java.lang.String[])}
	 * .
	 */
	@Test
	public void testDeleteNoticUsersByIds() {
		int count = noticUserService.deleteNoticUsersByIds(new String[] {
				"36985", "36986" });
		assertEquals(2, count);
	}

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.user.service.INoticUserService#updateNoticuser(com.deppon.esb.management.user.domain.NoticUserInfo)}
	 * .
	 */
	@Test
	public void testUpdateNoticuser() {
		NoticUserInfo noticUserInfo = new NoticUserInfo();
		noticUserInfo.setCreateTime(new Date());
		noticUserInfo.setEmail("test@test.com");
		noticUserInfo.setMobilePhone("mobilePhone");
		noticUserInfo.setTelPhone("telPhone");
		noticUserInfo.setUserName("userName");
		noticUserInfo.setId("1");
		int count = noticUserService.updateNoticuser(noticUserInfo);
		noticUserInfo.setId("36985");
		assertEquals(0, count);
		int count1 = noticUserService.updateNoticuser(noticUserInfo);
		assertEquals(1, count1);
	}

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.user.service.INoticUserService#addNoticUser(com.deppon.esb.management.user.domain.NoticUserInfo)}
	 * .
	 */
	@Test
	public void testAddNoticUser() {
		NoticUserInfo noticUserInfo = new NoticUserInfo();
		noticUserInfo.setCreateTime(new Date());
		noticUserInfo.setEmail("test@test.com");
		noticUserInfo.setMobilePhone("mobilePhone");
		noticUserInfo.setTelPhone("telPhone");
		noticUserInfo.setUserName("userName");
		int count = noticUserService.addNoticUser(noticUserInfo);
		assertEquals(1, count);
	}

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.user.service.INoticUserService#querySysUsers(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)}
	 * .
	 */
	@Test
	public void testQuerySysUsers() {
		noticUserService.querySysUsers("", "", "", "", 0, 20);
	}

}
