package org.hbhk.aili.rpc.server.test.dubbo;

import org.hbhk.aili.rpc.server.dubbo.service.IProcessData;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboClient  {
	static	ClassPathXmlApplicationContext context =null;
	public static void run() {
		context = new ClassPathXmlApplicationContext(
				new String[] { "applicationConsumer.xml" });
	}

	public static void main(String[] args) {
		run();
		//testCallback();
		for (int i = 0; i <10; i++) {
			testProcess();
		}
		
	}
	
	public static void testCallback() {
//		ICallbackService processData = (ICallbackService) context.getBean("callbackService"); 
//		processData.addListener("hbhk", new ICallbackListener() {
//			@Override
//			public void changed(String msg) {
//				  System.out.println("callback:" + msg);
//			}
//		});
	}
	
	public static void testProcess() {
		IProcessData processData = (IProcessData) context.getBean("processData"); 
		System.out.println("aaaa"+processData.deal("1111111"));
		
	}
}
