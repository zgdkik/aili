package com.deppon.esb.propertyplaceholder;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;


/**
 * The Class SampleServer.
 */
public class SampleServer {
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception{
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setAddress("http://localhost:8088/ws/sayhi");
		factory.setServiceClass(IService.class);
		factory.setServiceBean(new IService(){
			@Override
			public void sayHi(String string) {
				System.out.println("sayHI:"+string);
			}
			
		});
		
		Server server = factory.create();
		server.start();
		System.out.println("server start");
		Thread.sleep(1000*60*5);
		System.out.println("server end");
		System.exit(0);
	}
}
