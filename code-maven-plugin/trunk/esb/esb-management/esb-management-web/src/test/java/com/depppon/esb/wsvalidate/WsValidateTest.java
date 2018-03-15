package com.depppon.esb.wsvalidate;

import org.junit.Test;

import com.deppon.esb.management.verification.ws.service.impl.WsAddressStatusService;

public class WsValidateTest {
	private String u1="http://192.168.13.67:8080/foss/accountService?wsdl";
	@Test
	public void test(){
		WsAddressStatusService service = new WsAddressStatusService();
		String str =service.getStatus(u1);
		System.out.println(str);
	}
}
