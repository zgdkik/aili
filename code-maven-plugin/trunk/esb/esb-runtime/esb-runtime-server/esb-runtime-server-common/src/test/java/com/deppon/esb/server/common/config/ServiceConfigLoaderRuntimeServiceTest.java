/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.config
 * FILE    NAME: ServiceConfigLoaderRuntimeServiceTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.server.common.config;

import org.hbhk.aili.esb.server.common.config.ServiceConfigLoaderRuntimeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.esb.server.common.mock.ServiceConfigLoader;

/**
 * ServiceConfigLoaderRuntimeService测试类
 * 
 * @author HuangHua
 * @date 2012-12-26 上午9:14:30
 */
public class ServiceConfigLoaderRuntimeServiceTest {

	/** The service. */
	private ServiceConfigLoaderRuntimeService service;

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.config.ServiceConfigLoaderRuntimeService#refreshServiceConfig()}
	 * .
	 */
	@Test
	public void testRefreshServiceConfig() {
		int beforeSize = service.getConfigLoader().getAll().size();
		service.refreshServiceConfig();
		int afterSize = service.getConfigLoader().getAll().size();
		Assert.assertNotSame(beforeSize, afterSize);
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		service = new ServiceConfigLoaderRuntimeService();
		service.setConfigLoader(new ServiceConfigLoader());
	}

}
