package org.hbhk.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * JDK动态代理类
 */
public class UserProxy implements InvocationHandler {
	
	// 需要代理的目标对象
	private Object targetObject;

	@SuppressWarnings("unchecked")
	public <T> T newProxy(Object targetObject) {
		// 将目标对象传入进行代理
		this.targetObject = targetObject;
		return (T) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
				targetObject.getClass().getInterfaces(), this);// 返回代理对象
	}

	public Object invoke(Object proxy, Method method, Object[] args)// invoke方法
			throws Throwable {
		// 一般我们进行逻辑处理的函数比如这个地方是模拟检查权限
		checkPopedom();
		// 调用invoke方法，ret存储该方法的返回值
		Object ret = method.invoke(targetObject, args); 
		return ret;
	}

	private void checkPopedom() {
		// 模拟检查权限的例子
		System.out.println(".:检查权限  checkPopedom()!");
	}

}
