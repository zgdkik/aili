package org.hbhk.mq;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.apache.cxf.transport.jms.spec.JMSSpecConstants;
import org.hbhk.service.UserService;

public final class ActiveMQWSClient {
	// WebService请求的url的路径

	private static final String JMS_ENDPOINT_URI = "jms:queue:jaxws.jmstransport.queue?timeToLive=1000"
			+ "&jndiConnectionFactoryName=ConnectionFactory"
			+ "&jndiInitialContextFactory"
			+ "=org.apache.activemq.jndi.ActiveMQInitialContextFactory";

	private static final QName SERVICE_QNAME = new QName(
			"http://ws.mq.hbhk.org/", "UserServiceImpl");
	private static final QName PORT_QNAME = new QName("http://ws.mq.hbhk.org/",
			"HelloWorldPort");

	private ActiveMQWSClient() {
	}

	/**
	 * 客户端调用WS服务
	 * 
	 * @return
	 */
	private static UserService createClientJaxWs() {
		// 创建相关的 服务
		Service service = Service.create(SERVICE_QNAME);
		// Add a port to the Service
		service.addPort(PORT_QNAME,
				JMSSpecConstants.SOAP_JMS_SPECIFICATION_TRANSPORTID,
				JMS_ENDPOINT_URI);
		return service.getPort(UserService.class);
	}

	public static void main(String[] args) throws Exception {

		UserService client = createClientJaxWs();

		//boolean reply = client.saveUser("hbhkmq");
		//System.out.println(reply);
	}

}
