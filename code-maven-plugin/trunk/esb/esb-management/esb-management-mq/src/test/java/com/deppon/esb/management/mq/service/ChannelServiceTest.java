/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.service.impl
 * FILE    NAME: ChannelServiceTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.mq.domain.ChannelBean;
import com.deppon.esb.management.mq.service.impl.ChannelService;
import com.ibm.mq.MQException;

/**
 * 
 * @author HuangHua
 * @date 2013-1-25 下午4:29:12
 */
public class ChannelServiceTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChannelServiceTest.class);

	final public static String hostname = "192.168.17.141";
	final public static int port = 2438;
	final public static String channel = "ADMIN.CHANNEL";
	private static String padding;

	private void t() {
		char[] space = new char[64];

		Arrays.fill(space, 0, space.length, ' ');

		padding = new String(space);
	}

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.mq.service.impl.ChannelService#listChannels(java.lang.String, int, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws MQException
	 */
	@Test
	public void testListChannels() throws MQException, IOException,
			ParseException {
		ChannelService channelService = new ChannelService();

		Map<String, List<ChannelBean>> channelBeans = channelService
				.listChannels(new String[] { hostname }, new int[] { port },
						new String[] { channel }, new String[] { "*" });
		for (Entry<String, List<ChannelBean>> entry : channelBeans.entrySet()) {
			List<ChannelBean> channels = entry.getValue();
			for (ChannelBean channelBean : channels) {
				LOGGER.info(ToStringBuilder.reflectionToString(channelBean));
			}
			LOGGER.info("\n");
		}
	}

	@Test
	public void testListAllChannelNamesAndTypes() throws Exception {
		t();
		ChannelService channelService = new ChannelService();
		Map<String, List<ChannelBean>> channelBeans = channelService
				.listAllChannelNamesAndTypes(new String[] { hostname },
						new int[] { port }, new String[] { channel },
						new String[] { "*" });
		LOGGER.info("+----------+------------------------------------------------+----------+");
		LOGGER.info("|   QMGR   |                  Channel Name                  |   Type   |");
		LOGGER.info("+----------+------------------------------------------------+----------+");
		for (Entry<String, List<ChannelBean>> entry : channelBeans.entrySet()) {
			List<ChannelBean> channels = entry.getValue();
			for (ChannelBean channelBean : channels) {
				LOGGER.info("|"
						+ (channelBean.getQmgrName() + padding).substring(0, 10)
						+ "|"
						+ (channelBean.getName() + padding).substring(0, 48)
						+ "|"
						+ (channelBean.getType() + padding).substring(0, 10)
						+ "|");
			}
		}
		LOGGER.info("+------------------------------------------------+----------+");
	}

}
