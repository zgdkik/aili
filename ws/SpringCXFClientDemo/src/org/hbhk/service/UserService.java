package org.hbhk.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "service.hbhk.org")
public interface UserService {
	@WebMethod
	public boolean saveUser(Person user) throws Exception;
}
