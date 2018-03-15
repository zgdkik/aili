package org.hbhk.test.proxy;


public class UserServiceImpl1 implements UserService {

	public void addUser(String id, String password) {
		System.out.println(".: 掉用了UserManagerImpl.addUser()方法！11 ");

	}

	public String delUser(String id) {
		System.out.println(".: 掉用了UserManagerImpl.delUser()方法！ 111");
		return  "hbhk";

	}
	
}