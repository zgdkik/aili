/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.processor
 * FILE    NAME: StatusProcessTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.server.common.processor;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.log.status.IStatusService;
import org.hbhk.aili.esb.server.common.processor.StatusProcess;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * The Class StatusProcessTest.
 * 
 * @author HuangHua
 * @date 2012-12-26 下午2:20:51
 */
@ContextConfiguration(locations = { "classpath:com/deppon/esb/server/common/META-INF/ds-spring.xml" })
public class StatusProcessTest extends AbstractJUnit4SpringContextTests {
	private StatusProcess statusProcess;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-26 下午2:20:51
	 */
	@Before
	public void setUp() throws Exception {
		statusProcess = applicationContext.getBean(StatusProcess.class);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.StatusProcess#process(org.apache.camel.Exchange)}
	 * .
	 */
	@Test
	public void testProcess() {
		DefaultCamelContext camelContext = new DefaultCamelContext();
		Exchange exchange = new DefaultExchange(camelContext);
		exchange.setProperty(ESBServiceConstant.RT_HEADERS,new HashMap<String, Object>());
		try {
			statusProcess.process(exchange);
		} catch (Exception e) {
			fail("报错，意味着测试不通过。");
		}
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.StatusProcess#getStatusService()}
	 * .
	 */
	@Test
	public void testGetStatusService() {
		IStatusService statusService = statusProcess.getStatusService();
		assertNotNull("测试失败", statusService);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.StatusProcess#setStatusService(org.hbhk.aili.esb.server.common.log.status.IStatusService)}
	 * .
	 */
	@Test
	public void testSetStatusService() {
		IStatusService statusService1 = applicationContext.getBean(IStatusService.class);
		statusProcess.setStatusService(statusService1);
		IStatusService statusService2 = statusProcess.getStatusService();
		assertNotNull("测试失败", statusService2);
		assertSame("设置进去的值和取出来的值不一致。", statusService1, statusService2);
	}

}
