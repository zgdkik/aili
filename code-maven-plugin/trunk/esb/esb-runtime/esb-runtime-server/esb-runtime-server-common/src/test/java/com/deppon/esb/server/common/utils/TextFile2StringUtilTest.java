/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.utils
 * FILE    NAME: TextFile2StringUtilTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.server.common.utils;

import static org.junit.Assert.*;

import org.hbhk.aili.esb.server.common.utils.TextFile2StringUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * TextFile2StringUtil测试类.
 * 
 * @author HuangHua
 * @date 2012-12-25 下午2:01:08
 */
public class TextFile2StringUtilTest {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TextFile2StringUtilTest.class);

	/**
	 * Test method for {@link org.hbhk.aili.esb.server.common.utils.TextFile2StringUtil#readFile2String(java.lang.String)}.
	 */
	@Test
	public void testReadFile2String() {
		String pathName = "com/deppon/esb/server/common/utils/data/TextFile2StringUtilText.txt";
		String content = TextFile2StringUtil.readFile2String(pathName);
		LOGGER.info(content);
		assertNotNull(content);
	}
}
