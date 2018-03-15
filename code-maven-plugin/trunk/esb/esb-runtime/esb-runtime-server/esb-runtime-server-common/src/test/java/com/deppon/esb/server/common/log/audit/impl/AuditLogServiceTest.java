/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.log.audit.impl
 * FILE    NAME: AuditLogServiceTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.server.common.log.audit.impl;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.camel.Exchange;
import org.apache.camel.component.jms.JmsBinding;
import org.apache.camel.component.jms.JmsMessage;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.hbhk.aili.esb.common.exception.ESBRunTimeException;
import org.hbhk.aili.esb.server.common.log.audit.impl.AuditLogService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

/**
 * The Class AuditLogServiceTest.
 * 
 * @author HuangHua
 * @date 2012-12-26 上午10:35:30
 */
@ContextConfiguration(locations = { "classpath:com/deppon/esb/server/common/META-INF/ds-spring.xml" })
public class AuditLogServiceTest extends AbstractJUnit4SpringContextTests {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuditLogServiceTest.class);

	private AuditLogService auditLogService;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-26 上午10:35:30
	 */
	@Before
	public void setUp() throws Exception {
		auditLogService = applicationContext.getBean(AuditLogService.class);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.audit.impl.AuditLogService#saveAudit(org.apache.camel.Exchange)}
	 * .
	 */
	@Test
	public void testSaveAudit() {
		JmsTemplate jmsTemplate = auditLogService.getJmsTemplate();
		DefaultCamelContext camelContext = new DefaultCamelContext();
		Exchange exchange = new DefaultExchange(camelContext);
		JmsBinding binding = new JmsBinding();
		try {
			TextMessage message = jmsTemplate.getConnectionFactory()
					.createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE)
					.createTextMessage();
			org.apache.camel.component.jms.JmsMessage jmsMessage = new JmsMessage(
					message, binding);
			message.setText("12346");
			exchange.setIn(jmsMessage);
			auditLogService.saveAudit(exchange);
		} catch (ESBRunTimeException e) {
			LOGGER.error(e.getMessage(), e);
			org.junit.Assert.fail("抛出异常,测试失败.");
		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
			org.junit.Assert.fail("抛出异常,测试失败.");
		}
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.audit.impl.AuditLogService#getAuditQueueName()}
	 * .
	 */
	@Test
	public void testGetAuditQueueName() {
		String queueName = auditLogService.getAuditQueueName();
		Assert.notNull(queueName);
		Assert.state("QU_ESB_AUDITLOG".equals(queueName));
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.audit.impl.AuditLogService#setAuditQueueName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetAuditQueueName() {
		auditLogService.setAuditQueueName("QU_ESB_AUDITLOG_TEST");
		String queueName = auditLogService.getAuditQueueName();
		Assert.notNull(queueName);
		Assert.state("QU_ESB_AUDITLOG_TEST".equals(queueName));
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.audit.impl.AuditLogService#getJmsTemplate()}
	 * .
	 */
	@Test
	public void testGetJmsTemplate() {
		JmsTemplate jmsTemplate = auditLogService.getJmsTemplate();
		Assert.notNull(jmsTemplate);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.audit.impl.AuditLogService#setJmsTemplate(org.springframework.jms.core.JmsTemplate)}
	 * .
	 */
	@Test
	public void testSetJmsTemplate() {
		JmsTemplate jmsTemplate = (JmsTemplate) applicationContext
				.getBean("jmsTemplate");
		auditLogService.setJmsTemplate(jmsTemplate);
		JmsTemplate jmsTemplate1 = auditLogService.getJmsTemplate();
		Assert.notNull(jmsTemplate1);
	}

}
