package org.hbhk.aili.rpc.server.hessian;

import org.hbhk.aili.rpc.server.hessian.remote.DefaultRemoteServiceFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HessianClient  {
	static	ClassPathXmlApplicationContext context =null;
	public static void run() {
		context = new ClassPathXmlApplicationContext(
				new String[] { "hessian-client.xml" });
	}

	public static void main(String[] args) {
		run();
		//testCallback(); 
		testCallback1();  
	}
	
	public static void testCallback() {
		IHessianService processData = (IHessianService) context.getBean("hessianService"); 
		String str = processData.getName("hbhk");
		System.out.println(str);
		
	}
	
	public static void testCallback1() {
		IHessianService processData = DefaultRemoteServiceFactory.getService(IHessianService.class);
		String str = processData.getName("hbhk");
		System.out.println(str);
	}
}
