package com.deppon.ws.bostest;

import javax.jws.WebService;

@WebService(targetNamespace = "http://bostest.ws.deppon.com/")
public interface ITestBOS {

	public String getUserName(String name);

}
