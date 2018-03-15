/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.dynamicroute
 * FILE    NAME: ValidateDynamicRouterTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.server.common.dynamicroute;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.dynamicroute.ValidateDynamicRouter;
import org.hbhk.aili.esb.server.common.utils.ValidateHeaderUtil;
import org.junit.Before;
import org.junit.Test;

import com.deppon.esb.server.common.mock.ServiceConfigLoader;

/**
 * TODO（描述类的职责）
 * 
 * @author HuangHua
 * @date 2012-12-26 上午9:56:23
 */
public class ValidateDynamicRouterTest {

	private ValidateDynamicRouter validateDynamicRouter;

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.dynamicroute.ValidateDynamicRouter#route(java.util.Map, java.lang.String)}
	 * .
	 */
	@Test
	public void testRoute() {
		Map<String, Object> header = new HashMap<String, Object>();
		String endpoint1 = validateDynamicRouter.route(header, null);
		assertEquals("direct:exception", endpoint1);
		
		header.put(ESBServiceConstant.BACK_SERVICE_CODE, "esbServiceCode");
		String endpoint2 = validateDynamicRouter.route(header, null);
		assertEquals("direct:exception", endpoint2);
		
		header.clear();
		header.put(ESBServiceConstant.ESB_SERVICE_CODE, "esbServiceCode");
		header.put(ESBServiceConstant.VERSION,"0.1");
		header.put(ESBServiceConstant.BUSINESSID,"45679");
		header.put(ESBServiceConstant.REQUEST_ID,"3234234");
		header.put(ESBServiceConstant.SOURCE_SYSTEM,"CRM");
		header.put(ESBServiceConstant.MESSAGE_FORMATE,"XML");
		header.put(ESBServiceConstant.EXCHANGE_PATTERN,2);
		String endpoint3 = validateDynamicRouter.route(header, null);
		assertEquals("direct:normal", endpoint3);
		
		String endpoint5 = validateDynamicRouter.route(header, "notNull");
		assertNull(endpoint5);
		
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.dynamicroute.ValidateDynamicRouter#getValidateHeaderUtil()}
	 * .
	 */
	@Test
	public void testGetValidateHeaderUtil() {
		ValidateHeaderUtil validateHeaderUtil = validateDynamicRouter.getValidateHeaderUtil();
		assertNotNull(validateHeaderUtil);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.dynamicroute.ValidateDynamicRouter#setValidateHeaderUtil(org.hbhk.aili.esb.server.common.utils.ValidateHeaderUtil)}
	 * .
	 */
	@Test
	public void testSetValidateHeaderUtil() {
		ValidateHeaderUtil validateHeaderUtil = new ValidateHeaderUtil();
		validateHeaderUtil
				.setConfigLoader(new ServiceConfigLoader());
		validateDynamicRouter.setValidateHeaderUtil(validateHeaderUtil);
		assertNotNull(validateDynamicRouter.getValidateHeaderUtil());
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		validateDynamicRouter = new ValidateDynamicRouter();
		ValidateHeaderUtil validateHeaderUtil = new ValidateHeaderUtil();
		validateHeaderUtil
				.setConfigLoader(new ServiceConfigLoader());
		validateDynamicRouter.setValidateHeaderUtil(validateHeaderUtil);
	}
}
