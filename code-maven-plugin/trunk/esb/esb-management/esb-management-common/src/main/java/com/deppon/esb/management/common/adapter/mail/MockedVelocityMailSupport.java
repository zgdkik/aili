package com.deppon.esb.management.common.adapter.mail;


import java.util.Map;

import org.springframework.mail.MailException;

/**
 * 一期移植
 * 
 * @author HuangHua
 * @date 2012-12-27 上午9:53:13
 */
public class MockedVelocityMailSupport extends VelocityMailSupport {
	public static int count = 0;

	@Override
	public void sendMime(Map<String, Object> model, String... tos)
			throws MailException {
		count += tos.length;
	}

}
