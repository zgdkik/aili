package org.hbhk.aili.rpc.server.test.thrift;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			ZkClient zkClient  = new ZkClient("127.0.0.1:2181");
//			zkClient.createPersistent("/hbhk12");
			ApplicationContext context = new ClassPathXmlApplicationContext("thrift/spring-context-thrift-server.xml");
			Thread.sleep(3000000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
