package org.hbhk.code.util;

import junit.framework.Assert;

import org.hbhk.code.util.StringUtils;
import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testGenerateClassname() {
		Assert.assertEquals("AuthFunction", StringUtils.generateClassname("T_AUTH_FUNCTION"));
		Assert.assertEquals("AuthFunction", StringUtils.generateClassname("AUTH_FUNCTION"));
		Assert.assertEquals("TtAuthFunction", StringUtils.generateClassname("TT_AUTH_FUNCTION"));
	}
	
}
