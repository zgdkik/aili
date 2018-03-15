package com.deppon.esb.management.route.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.route.domain.RouteInfo;
import com.deppon.esb.management.route.service.IRouteService;

@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/route/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class RouteServiceTest  extends DaoDBUnitSupportUnitTests {

	@Resource
	private IRouteService routeService;

	@SuppressWarnings("unchecked")
	@Test
	public void testSelectAllRoute() {
		Map<String, Object> routeInfos = routeService.queryAllRoute(0, 20);
		Assert.assertTrue(routeInfos.size() == 2);
		Assert.assertTrue((Integer)routeInfos.get("resultCount") == 2);
		Assert.assertTrue(((List<RouteInfo>)routeInfos.get("routeInfoList")).size() == 2);
	}

}
