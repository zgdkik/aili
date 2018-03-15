/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.dynamicroute
 * FILE    NAME: DynamicRouterBeanTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.server.common.dynamicroute;

import static org.junit.Assert.*;

import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;
import org.hbhk.aili.esb.server.common.dynamicroute.DynamicRouterBean;
import org.junit.Before;
import org.junit.Test;

import com.deppon.esb.server.common.mock.ServiceConfigLoader;

/**
 * The Class DynamicRouterBeanTest.
 * 
 * @author HuangHua
 * @date 2012-12-26 上午9:56:02
 */
public class DynamicRouterBeanTest {

	private DynamicRouterBean dynamicRouterBean;

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.dynamicroute.DynamicRouterBean#route(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testRoute() {
		String endpoint1 = dynamicRouterBean.route(ESBServiceConstant.RT_REQUEST, "", "esbServiceCode", null);
		assertEquals(dynamicRouterBean.getConfigLoader().getSvcPointInfo("hbhk").getJmsComponent() + ":TARGETADDR?disableReplyTo=true", endpoint1);
		String endpoint2 = dynamicRouterBean.route(ESBServiceConstant.RT_CALLBACK, "esbServiceCode", "", null);
		assertEquals(dynamicRouterBean.getConfigLoader().getSvcPointInfo("hbhk").getJmsComponent() + ":TARGETADDR?disableReplyTo=true", endpoint2);
		String endpoint3 = dynamicRouterBean.route(ESBServiceConstant.RT_CALLBACK, "esbServiceCode", "", "notNull");
		assertNull(endpoint3);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.dynamicroute.DynamicRouterBean#getConfigLoader()}
	 * .
	 */
	@Test
	public void testGetConfigLoader() {
		IServiceConfigLoader configLoader = dynamicRouterBean.getConfigLoader();
		assertNotNull(configLoader);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.dynamicroute.DynamicRouterBean#setConfigLoader(org.hbhk.aili.esb.server.common.config.IServiceConfigLoader)}
	 * .
	 */
	@Test
	public void testSetConfigLoader() {
		dynamicRouterBean.setConfigLoader(new ServiceConfigLoader());
		assertNotNull(dynamicRouterBean.getConfigLoader());
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		dynamicRouterBean = new DynamicRouterBean();
		dynamicRouterBean.setConfigLoader(new ServiceConfigLoader());
	}

}
