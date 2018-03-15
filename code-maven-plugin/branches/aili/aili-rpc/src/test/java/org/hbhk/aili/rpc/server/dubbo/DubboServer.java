package org.hbhk.aili.rpc.server.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.container.Main;

public class DubboServer {
	
	private static Logger logger = LoggerFactory.getLogger(DubboServer.class);
	
	 
	public static void main(String[] args) throws Exception {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//				new String[] { "spring-dubbo.xml" });
//		context.start();
//		logger.info("startstartstartstartstartstartstartstart");
		//System.in.read();
		Main.main(args);
		
	}
}
