/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.processor
 * FILE    NAME: ExceptionProcessorTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.server.common.processor;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.camel.Exchange;
import org.apache.camel.component.jms.JmsBinding;
import org.apache.camel.component.jms.JmsMessage;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.hbhk.aili.esb.server.common.processor.ExceptionProcessor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

/**
 * The Class ExceptionProcessorTest.
 * 
 * @author HuangHua
 * @date 2012-12-26 下午1:50:13
 */
@ContextConfiguration(locations = { "classpath:com/deppon/esb/server/common/META-INF/ds-spring.xml" })
public class ExceptionProcessorTest extends AbstractJUnit4SpringContextTests {
	private ExceptionProcessor exceptionProcessor;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-26 下午1:50:13
	 */
	@Before
	public void setUp() throws Exception {
		exceptionProcessor = applicationContext
				.getBean(ExceptionProcessor.class);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.ExceptionProcessor#process(org.apache.camel.Exchange)}
	 * .
	 */
	@Test
	public void testProcess() {
		DefaultCamelContext camelContext1 = new DefaultCamelContext();
		Exchange exchange1 = new DefaultExchange(camelContext1);
		try {
			exchange1.setIn(null);
			exceptionProcessor.process(exchange1);
		} catch (Exception e) {
			assertTrue("抛出异常才正常。", true);
		}
		
		JmsTemplate jmsTemplate = exceptionProcessor.getJmsTemplate();
		DefaultCamelContext camelContext = new DefaultCamelContext();
		Exchange exchange = new DefaultExchange(camelContext);
		JmsBinding binding = new JmsBinding();
		try {
			TextMessage message = jmsTemplate.getConnectionFactory()
					.createConnection()
					.createSession(false, Session.AUTO_ACKNOWLEDGE)
					.createTextMessage();
			org.apache.camel.component.jms.JmsMessage jmsMessage = new JmsMessage(
					message, binding);
			message.setText("12346");
			exchange.setIn(jmsMessage);
			exceptionProcessor.process(exchange);
		} catch (Exception e) {
			fail("报错，测试部通过！");
		}
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.ExceptionProcessor#generateMessage(javax.jms.Session, javax.jms.Message)}
	 * .
	 */
	@Test
	public void testGenerateMessage() {
		JmsTemplate jmsTemplate = exceptionProcessor.getJmsTemplate();
		try {
			Session session = jmsTemplate.getConnectionFactory()
					.createConnection()
					.createSession(false, Session.AUTO_ACKNOWLEDGE);
			TextMessage sendMessage = session.createTextMessage();
			exceptionProcessor.generateMessage(session, sendMessage);
		} catch (JMSException e) {
			fail("报错，测试部通过！");
		}
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.ExceptionProcessor#getFailQueueName()}
	 * .
	 */
	@Test
	public void testGetFailQueueName() {
		String failQueueName = exceptionProcessor.getFailQueueName();
		Assert.notNull(failQueueName);
		org.junit.Assert.assertEquals("QU_ESB_FAILURE", failQueueName);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.ExceptionProcessor#setFailQueueName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetFailQueueName() {
		exceptionProcessor.setFailQueueName("QU_ESB_FAILURE_TEST");
		String queueName = exceptionProcessor.getFailQueueName();
		Assert.notNull(queueName);
		Assert.state("QU_ESB_FAILURE_TEST".equals(queueName));
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.ExceptionProcessor#getExceptionQueueName()}
	 * .
	 */
	@Test
	public void testGetExceptionQueueName() {
		String exceptionQueueName = exceptionProcessor.getExceptionQueueName();
		Assert.notNull(exceptionQueueName);
		org.junit.Assert.assertEquals("QU_ESB_EXCEPTION", exceptionQueueName);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.ExceptionProcessor#setExceptionQueueName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetExceptionQueueName() {
		exceptionProcessor.setExceptionQueueName("QU_ESB_EXCEPTION_TEST");
		String queueName = exceptionProcessor.getExceptionQueueName();
		Assert.notNull(queueName);
		Assert.state("QU_ESB_EXCEPTION_TEST".equals(queueName));
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.ExceptionProcessor#getJmsTemplate()}
	 * .
	 */
	@Test
	public void testGetJmsTemplate() {
		JmsTemplate jmsTemplate = exceptionProcessor.getJmsTemplate();
		Assert.notNull(jmsTemplate);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.processor.ExceptionProcessor#setJmsTemplate(org.springframework.jms.core.JmsTemplate)}
	 * .
	 */
	@Test
	public void testSetJmsTemplate() {
		JmsTemplate jmsTemplate = (JmsTemplate) applicationContext
				.getBean("jmsTemplate");
		exceptionProcessor.setJmsTemplate(jmsTemplate);
		JmsTemplate jmsTemplate1 = exceptionProcessor.getJmsTemplate();
		Assert.notNull(jmsTemplate1);
		org.junit.Assert.assertSame(jmsTemplate, jmsTemplate1);
	}

}
