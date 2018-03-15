/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.log.exception.impl
 * FILE    NAME: ExceptionServiceTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.server.common.log.exception.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo;
import org.hbhk.aili.esb.server.common.log.exception.impl.ExceptionService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

/**
 * The Class ExceptionServiceTest.
 * 
 * @author HuangHua
 * @date 2012-12-26 上午11:30:09
 */
@ContextConfiguration(locations = { "classpath:com/deppon/esb/server/common/META-INF/ds-spring.xml" })
public class ExceptionServiceTest extends AbstractJUnit4SpringContextTests {
	private ExceptionService exceptionService;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-26 上午11:30:09
	 */
	@Before
	public void setUp() throws Exception {
		exceptionService = applicationContext.getBean(ExceptionService.class);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.exception.impl.ExceptionService#saveException(java.util.Map, org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo)}
	 * .
	 */
	@Test
	public void testSaveException() {
		 Map<String, Object> header = new HashMap<String, Object>();
		header.put(ESBServiceConstant.ESB_SERVICE_CODE,"ESB_SERVICE_CODE");
		header.put(ESBServiceConstant.BACK_SERVICE_CODE,"BACK_SERVICE_CODE");
		header.put(ESBServiceConstant.BUSINESSID,"BUSINESSID");
		header.put(ESBServiceConstant.BUSINESSDESC1,"BUSINESSDESC1");
		header.put(ESBServiceConstant.BUSINESSDESC2,"BUSINESSDESC2");
		header.put(ESBServiceConstant.BUSINESSDESC3,"BUSINESSDESC3");
		header.put(ESBServiceConstant.REQUEST_ID,"REQUEST_ID");
		header.put(ESBServiceConstant.RESPONSE_ID,"RESPONSE_ID");
		CommonExceptionInfo commonExceptionInfo = new CommonExceptionInfo();
		commonExceptionInfo.setCreatedTime(new Date());
		commonExceptionInfo.setDetailedInfo("没有找到相应的服务。可能是没有在ESB中注册，也可能是传入的服务编码有误！");
		commonExceptionInfo.setExceptioncode("EXCEPTION_CODE");
		commonExceptionInfo.setExceptiontype(ESBServiceConstant.SYSTEM_EXCEPTION);
		commonExceptionInfo.setMessage("没找到服务！");
		exceptionService.saveException(header, commonExceptionInfo);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.exception.impl.ExceptionService#generateMessage(javax.jms.Session, java.util.Map)}
	 * .
	 */
	@Test
	public void testGenerateMessage() {
		TextMessage message = generateMessage();
		assertNotNull(message);
	}
	
	private TextMessage generateMessage(){
		JmsTemplate jmsTemplate = exceptionService.getJmsTemplate();
		try {
			Session session = jmsTemplate.getConnectionFactory().createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
			 Map<String, Object> header = new HashMap<String, Object>();
			header.put(ESBServiceConstant.ESB_SERVICE_CODE,"ESB_SERVICE_CODE");
			header.put(ESBServiceConstant.BACK_SERVICE_CODE,"BACK_SERVICE_CODE");
			header.put(ESBServiceConstant.BUSINESSID,"BUSINESSID");
			header.put(ESBServiceConstant.BUSINESSDESC1,"BUSINESSDESC1");
			header.put(ESBServiceConstant.BUSINESSDESC2,"BUSINESSDESC2");
			header.put(ESBServiceConstant.BUSINESSDESC3,"BUSINESSDESC3");
			header.put(ESBServiceConstant.REQUEST_ID,"REQUEST_ID");
			header.put(ESBServiceConstant.RESPONSE_ID,"RESPONSE_ID");
			TextMessage message = exceptionService.generateMessage(session, header);
			Assert.notNull(message);
			return message;
		} catch (JMSException e) {
			org.junit.Assert.fail("报错了,测试不通过.");
		}
		return null;
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.exception.impl.ExceptionService#getJmsTemplate()}
	 * .
	 */
	@Test
	public void testGetJmsTemplate() {
		JmsTemplate jmsTemplate = exceptionService.getJmsTemplate();
		Assert.notNull(jmsTemplate);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.exception.impl.ExceptionService#setJmsTemplate(org.springframework.jms.core.JmsTemplate)}
	 * .
	 */
	@Test
	public void testSetJmsTemplate() {
		JmsTemplate jmsTemplate = (JmsTemplate) applicationContext
				.getBean("jmsTemplate");
		exceptionService.setJmsTemplate(jmsTemplate);
		JmsTemplate jmsTemplate1 = exceptionService.getJmsTemplate();
		Assert.notNull(jmsTemplate1);
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.exception.impl.ExceptionService#getExceptionQueueName()}
	 * .
	 */
	@Test
	public void testGetExceptionQueueName() {
		String queueName = exceptionService.getExceptionQueueName();
		Assert.notNull(queueName);
		Assert.state("QU_ESB_EXCEPTION".equals(queueName));
	}

	/**
	 * Test method for
	 * {@link org.hbhk.aili.esb.server.common.log.exception.impl.ExceptionService#setExceptionQueueName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetExceptionQueueName() {
		exceptionService.setExceptionQueueName("QU_ESB_EXCEPTION_TEST");
		String queueName = exceptionService.getExceptionQueueName();
		Assert.notNull(queueName);
		Assert.state("QU_ESB_EXCEPTION_TEST".equals(queueName));
	}

}
