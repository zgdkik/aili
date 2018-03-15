/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.processor
 * FILE    NAME: SpecificationsProcessTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.server.common.processor;

import static org.junit.Assert.*;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.camel.Exchange;
import org.apache.camel.component.jms.JmsBinding;
import org.apache.camel.component.jms.JmsMessage;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.common.exception.ESBRunTimeException;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;
import org.hbhk.aili.esb.server.common.log.audit.IAuditLogService;
import org.hbhk.aili.esb.server.common.processor.SpecificationsProcess;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

import com.deppon.esb.server.common.mock.ServiceConfigLoader;

/**
 * The Class SpecificationsProcessTest.
 * 
 * @author HuangHua
 * @date 2012-12-26 下午2:20:38
 */
@ContextConfiguration(locations = { "classpath:com/deppon/esb/server/common/META-INF/ds-spring.xml" })
public class SpecificationsProcessTest extends AbstractJUnit4SpringContextTests {
	private SpecificationsProcess specificationsProcess;
	
	/**
	 * Sets the up.
	 * 
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-26 下午2:20:38
	 */
	@Before
	public void setUp() throws Exception {
		specificationsProcess = applicationContext.getBean(SpecificationsProcess.class);
	}

	/**
	 * Test method for {@link org.hbhk.aili.esb.server.common.processor.SpecificationsProcess#process(org.apache.camel.Exchange)}.
	 */
	@Test
	public void testProcess() {
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		DefaultCamelContext camelContext1 = new DefaultCamelContext();
		Exchange exchange1 = new DefaultExchange(camelContext1);
		org.junit.Assert.assertTrue("exchange的headers在测试前应该为空", exchange1.getIn().getHeaders().isEmpty());
		JmsBinding binding = new JmsBinding();
		try {
			TextMessage message = jmsTemplate.getConnectionFactory()
					.createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE)
					.createTextMessage();
			org.apache.camel.component.jms.JmsMessage jmsMessage = new JmsMessage(
					message, binding);
			message.setText("12346");
			exchange1.setIn(jmsMessage);
			exchange1.getIn().getHeaders().put(ESBServiceConstant.ESB_SERVICE_CODE, "esbServiceCode");
			specificationsProcess.process(exchange1);
			String rt2 = exchange1.getProperty(ESBServiceConstant.RT_REQUEST_OR_CALLBACK,String.class);
			assertEquals(ESBServiceConstant.RT_REQUEST, rt2);
			exchange1.getIn().getHeaders().put(ESBServiceConstant.BACK_SERVICE_CODE, "BACK_SERVICE_CODE");
			specificationsProcess.process(exchange1);
			String rt1 = exchange1.getProperty(ESBServiceConstant.RT_REQUEST_OR_CALLBACK,String.class);
			assertEquals(ESBServiceConstant.RT_CALLBACK, rt1);
		} catch (ESBRunTimeException e) {
			fail("报错，意味着测试不通过。");
		} catch (JMSException e) {
			fail("报错，意味着测试不通过。");
		} catch (Exception e) {
			fail("报错，意味着测试不通过。");
		}
		
		
	}

	/**
	 * Test method for {@link org.hbhk.aili.esb.server.common.processor.SpecificationsProcess#beforeProcess(org.apache.camel.Exchange)}.
	 */
	@Test
	public void testBeforeProcess() {
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		DefaultCamelContext camelContext1 = new DefaultCamelContext();
		Exchange exchange1 = new DefaultExchange(camelContext1);
		org.junit.Assert.assertTrue("exchange的headers在测试前应该为空", exchange1.getIn().getHeaders().isEmpty());
		JmsBinding binding = new JmsBinding();
		try {
			TextMessage message = jmsTemplate.getConnectionFactory()
					.createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE)
					.createTextMessage();
			org.apache.camel.component.jms.JmsMessage jmsMessage = new JmsMessage(
					message, binding);
			message.setText("12346");
			exchange1.setIn(jmsMessage);
			specificationsProcess.beforeProcess(exchange1);
			String rt2 = exchange1.getProperty(ESBServiceConstant.RT_REQUEST_OR_CALLBACK,String.class);
			assertEquals(ESBServiceConstant.RT_REQUEST, rt2);
			exchange1.getIn().getHeaders().put(ESBServiceConstant.BACK_SERVICE_CODE, "BACK_SERVICE_CODE");
			specificationsProcess.beforeProcess(exchange1);
			String rt1 = exchange1.getProperty(ESBServiceConstant.RT_REQUEST_OR_CALLBACK,String.class);
			assertEquals(ESBServiceConstant.RT_CALLBACK, rt1);
		} catch (ESBRunTimeException e) {
			fail("报错，意味着测试不通过。");
		} catch (JMSException e) {
			fail("报错，意味着测试不通过。");
		}
	}

	/**
	 * Test method for {@link org.hbhk.aili.esb.server.common.processor.SpecificationsProcess#getAuditLogService()}.
	 */
	@Test
	public void testGetAuditLogService() {
		IAuditLogService auditLogService = specificationsProcess.getAuditLogService();
		assertNotNull("获取的对象是null", auditLogService);
	}

	/**
	 * Test method for {@link org.hbhk.aili.esb.server.common.processor.SpecificationsProcess#setAuditLogService(org.hbhk.aili.esb.server.common.log.audit.IAuditLogService)}.
	 */
	@Test
	public void testSetAuditLogService() {
		IAuditLogService auditLogService1 = applicationContext.getBean(IAuditLogService.class);
		specificationsProcess.setAuditLogService(auditLogService1);
		IAuditLogService auditLogService2 = specificationsProcess.getAuditLogService();
		assertNotNull("获取的对象是null", auditLogService2);
		assertSame(auditLogService1, auditLogService2);
	}

	/**
	 * Test method for {@link org.hbhk.aili.esb.server.common.processor.SpecificationsProcess#getConfigLoader()}.
	 */
	@Test
	public void testGetConfigLoader() {
		IServiceConfigLoader configLoader = specificationsProcess.getConfigLoader();
		assertNotNull(configLoader);
		Assert.notEmpty(configLoader.getAll());
	}

	/**
	 * Test method for {@link org.hbhk.aili.esb.server.common.processor.SpecificationsProcess#setConfigLoader(org.hbhk.aili.esb.server.common.config.IServiceConfigLoader)}.
	 */
	@Test
	public void testSetConfigLoader() {
		IServiceConfigLoader configLoader1 = new ServiceConfigLoader();
		specificationsProcess.setConfigLoader(configLoader1);
		IServiceConfigLoader configLoader2 = specificationsProcess.getConfigLoader();
		assertNotNull(configLoader2);
		assertSame(configLoader1, configLoader2);
	}

}
