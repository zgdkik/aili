package org.hbhk.test.proxy;

public class TestProxy {
	public static void main(String[] args) {
		System.out.println("jdk");
		UserProxy  proxy = new UserProxy();
		UserService service = (UserService) proxy.newProxy(new UserServiceImpl());
		service.addUser("sss", "ppp");
		service.delUser("sss");
		
		CGLibProxy cg = new CGLibProxy();
		System.out.println("cglib");
		UserService service1 = (UserService) cg.createProxyObject(new UserServiceImpl());
		service1.addUser("sss", "ppp");
		service1.delUser("sss");
	}

}
