package org.hbhk.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hbhk.domain.Person;
import org.hbhk.domain.User;

@WebService(targetNamespace="service.hbhk.org")
public interface UserService {
	//public boolean findLogin(User user);
	@WebMethod
	public boolean saveUser(Person user)  ;
}
