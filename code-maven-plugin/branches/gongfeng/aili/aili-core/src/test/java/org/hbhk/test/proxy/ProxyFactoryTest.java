package org.hbhk.test.proxy;

import org.hbhk.aili.core.server.proxy.ProxyFactory;

public class ProxyFactoryTest {

	public static void main(String[] args) {
		ProxyFactory proxyFactory = new ProxyFactory();
		ProxyFactory proxyFactory1 = new ProxyFactory();
		UserService userService1 = proxyFactory.newInstance(UserServiceImpl.class);
		UserService userService2 = proxyFactory1.newInstance(UserServiceImpl1.class);
		userService1.delUser("1111111");
		userService2.delUser("1111111");
	}
}
