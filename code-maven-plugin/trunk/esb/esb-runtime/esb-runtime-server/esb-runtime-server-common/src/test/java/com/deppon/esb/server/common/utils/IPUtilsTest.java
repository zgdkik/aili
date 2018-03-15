/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.utils
 * FILE    NAME: IPUtilsTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.server.common.utils;

import static org.junit.Assert.assertTrue;

import org.hbhk.aili.esb.server.common.utils.IPUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * IPUtils测试.
 * 
 * @author HuangHua
 * @date 2012-12-25 下午1:53:18
 */
public class IPUtilsTest {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IPUtilsTest.class);

	/**
	 * Test method for {@link org.hbhk.aili.esb.server.common.utils.IPUtils#getSystemIP()}.
	 */
	@Test
	public void testGetSystemIP() {
		String ip = IPUtils.getSystemIP();
		LOGGER.info(ip);
		String regex = "([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		assertTrue("IP地址格式不正确，可能是获取IP地址失败！",ip.matches(regex));
	}

}
