package org.hbhk.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="server.hbhk.org")
public interface HelloWorld {
	@WebMethod
    String sayHi(String text);
}
