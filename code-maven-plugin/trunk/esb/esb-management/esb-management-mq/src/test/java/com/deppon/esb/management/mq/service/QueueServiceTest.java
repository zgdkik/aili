/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.service
 * FILE    NAME: QueueServiceTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.service;

import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.mq.domain.LocalQueueBean;
import com.deppon.esb.management.mq.service.impl.MqQueueService;

/**
 * 
 * @author HuangHua
 * @date 2013-1-25 下午2:20:07
 */
public class QueueServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QueueServiceTest.class);

	final public static String hostname = "192.168.17.141";
	final public static int port = 2439;
	final public static String channel = "ADMIN.CHANNEL";

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.mq.service.IMqQueueService#listLocalQueue()}
	 * .
	 * @throws Exception 
	 */
	@Test
	public void testListLocalQueue() throws Exception {
		MqQueueService queueService = new MqQueueService();
		List<LocalQueueBean> localQueueBeans = queueService.listLocalQueue(hostname, port, channel, "QU*");
		Assert.assertNotNull(localQueueBeans);
		Assert.assertTrue(localQueueBeans.size()>0);
		for (LocalQueueBean localQueueBean : localQueueBeans) {
			LOGGER.info(ToStringBuilder.reflectionToString(localQueueBean));
		}
	}

}
