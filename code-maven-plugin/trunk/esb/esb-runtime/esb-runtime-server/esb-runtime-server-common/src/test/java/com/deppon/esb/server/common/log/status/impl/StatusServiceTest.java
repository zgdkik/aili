/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.log.status.impl
 * FILE    NAME: StatusServiceTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.server.common.log.status.impl;

import java.util.HashMap;
import java.util.Map;

import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.log.status.impl.StatusService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

/**
 * The Class StatusServiceTest.
 * 
 * @author HuangHua
 * @date 2012-12-26 上午11:50:21
 */
@ContextConfiguration(locations = { "classpath:com/deppon/esb/server/common/META-INF/ds-spring.xml" })
public class StatusServiceTest extends AbstractJUnit4SpringContextTests {

	private StatusService statusService;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-26 上午11:50:21
	 */
	@Before
	public void setUp() throws Exception {
		statusService = applicationContext.getBean(StatusService.class);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.status.impl.StatusService#saveStatus(java.util.Map)}
	 * .
	 */
	@Test
	public void testSaveStatus() {
		Map<String, Object> header = new HashMap<String, Object>();
		header.put(ESBServiceConstant.BUSINESSID, "BUSINESSID");
		header.put(ESBServiceConstant.BUSINESSDESC1, "BUSINESSDESC1");
		header.put(ESBServiceConstant.BUSINESSDESC2, "BUSINESSDESC2");
		header.put(ESBServiceConstant.BUSINESSDESC3, "BUSINESSDESC3");
		header.put(ESBServiceConstant.REQUEST_ID, "REQUEST_ID");
		header.put(ESBServiceConstant.RESPONSE_ID, "RESPONSE_ID");
		header.put(ESBServiceConstant.ESB_SERVICE_CODE, "ESB_SERVICE_CODE");
		header.put(ESBServiceConstant.SOURCE_SYSTEM, "SOURCE_SYSTEM");
		header.put(ESBServiceConstant.TARGET_SYSTEM, "TARGET_SYSTEM");
		header.put(ESBServiceConstant.STATUS_AT_ESB_REQ_SENT, 123456);
		header.put(ESBServiceConstant.STATUS_AT_ESB_REQ_RECEIVED, 123456);
		header.put(ESBServiceConstant.STATUS_AT_ESB_REQ_TRANSFORMING, 123456);
		statusService.saveStatus(header);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.status.impl.StatusService#getJmsTemplate()}
	 * .
	 */
	@Test
	public void testGetJmsTemplate() {
		JmsTemplate jmsTemplate = statusService.getJmsTemplate();
		Assert.notNull(jmsTemplate);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.status.impl.StatusService#setJmsTemplate(org.springframework.jms.core.JmsTemplate)}
	 * .
	 */
	@Test
	public void testSetJmsTemplate() {
		JmsTemplate jmsTemplate = (JmsTemplate) applicationContext
				.getBean("jmsTemplate");
		statusService.setJmsTemplate(jmsTemplate);
		JmsTemplate jmsTemplate1 = statusService.getJmsTemplate();
		Assert.notNull(jmsTemplate1);
		org.junit.Assert.assertSame(jmsTemplate, jmsTemplate1);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.status.impl.StatusService#getStatusQueueName()}
	 * .
	 */
	@Test
	public void testGetStatusQueueName() {
		String queueName = statusService.getStatusQueueName();
		Assert.notNull(queueName);
		Assert.state("QU_ESB_STATUS".equals(queueName));
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.status.impl.StatusService#setStatusQueueName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetStatusQueueName() {
		statusService.setStatusQueueName("QU_ESB_STATUS_TEST");
		String queueName = statusService.getStatusQueueName();
		Assert.notNull(queueName);
		Assert.state("QU_ESB_STATUS_TEST".equals(queueName));
	}

}
