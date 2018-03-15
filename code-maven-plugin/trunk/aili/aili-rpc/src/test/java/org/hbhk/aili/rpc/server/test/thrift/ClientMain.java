package org.hbhk.aili.rpc.server.test.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// simple();
		spring();
	}

	public static void spring() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("thrift/spring-context-thrift-client.xml");
			ThriftService.Iface userService = (ThriftService.Iface) context.getBean("userService");
			Thread.sleep(5000);
			for (int i = 0; i < 10; i++) {
				TThread t = new TThread(userService);
				t.start();
			}
			//Thread.sleep(3000000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class TThread extends Thread {
		ThriftService.Iface userService;

		TThread(ThriftService.Iface service) {
			userService = service;
		}

		public void run() {
			try{
				for (int i = 0; i < 1000; i++) {
//					User user = new User();
//					user.setId(1010101);
//					user.setName("zhangsan..." + i);
					System.out.println(userService.add(i,i+1));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public static void simple() {
		try {
			TTransport transport = new TSocket("localhost", 9090);
			TProtocol protocol = new TBinaryProtocol(transport);
			ThriftService.Client client = new ThriftService.Client(protocol);
			transport.open();
//			User user = new User();
//			user.setId(1010101);
//			user.setName("zhangsan...");
			//client.show(user);
			System.out.println(client.add(1,1+1));
			// //
			//Thread.sleep(3000);
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
