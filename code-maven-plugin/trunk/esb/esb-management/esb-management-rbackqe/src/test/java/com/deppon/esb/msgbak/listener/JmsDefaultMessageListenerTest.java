package com.deppon.esb.msgbak.listener;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.support.JmsUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.rbackqe.config.IServiceConfigLoader;
import com.deppon.esb.management.rbackqe.listener.JmsDefaultMessageListener;
import com.ibm.msg.client.jms.JmsConnectionFactory;

@ContextConfiguration(locations = { "classpath*:com/deppon/esb/jms/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class JmsDefaultMessageListenerTest extends DaoDBUnitSupportUnitTests {

	private static final Logger LOGGER = Logger.getLogger(JmsDefaultMessageListenerTest.class);

	private JmsDefaultMessageListener messageListener;

	private JmsConnectionFactory connectionFactory;

	private IServiceConfigLoader configLoader;

	@Before
	public void init() {
		messageListener = (JmsDefaultMessageListener) applicationContext.getBean("jmsDefaultMessageListener");
		connectionFactory = (JmsConnectionFactory) applicationContext.getBean("connectionFactory");
		configLoader = (IServiceConfigLoader) applicationContext.getBean("serviceConfigLoader");
	}

	@Test
	public void listener() {
		messageListener.setRefreshTimeOut(60);
		messageListener.setReceiveTimeout(500);
		/**FOSS */
//		messageListener.setMessageSelector("esbServiceCode in ('ESB_FOSS2ESB_SYNC_MOTORCADEINFO','ESB_FOSS2ESB_GENERATE_PAY_WORKFLOW','ESB_FOSS2ESB_MODIFYORDER')");
//		messageListener.setMessageSelector("esbServiceCode='ESB_FOSS2ESB_RECEIVE_CREDITUSED'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_FOSS2ESB_EDI_FILEUP_SUMBILL'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_FOSS2ESB_SYNC_SALES_DEPARTMENT'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_FOSS2ESB_SYNC_ORGANIZATION'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_FOSS2ESB_DISCARD_WORKFLOW'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_FOSS2ESB_UPDATE_VEHICLESTATE'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_FOSS2ESB_NOTIFY_CLAIMS_STATE'");
		/** CRM  */
//		messageListener.setMessageSelector("esbServiceCode='ESB_CRM2ESB_RECIEVE_NONFIXEDCUSTOMER'");
		/** EIS  */
//		messageListener.setMessageSelector("esbServiceCode='ESB_EIS2ESB_CREATE_IMAGES'");
		/** FIN_SELF */
//		messageListener.setMessageSelector("esbServiceCode='ESB_FIN_SELF2ESB_SEND_CASHIER'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_FIN_SELF2ESB_UPDATE_INVOICEAMOUNT'");
		/** FSSC */
//		messageListener.setMessageSelector("esbServiceCode='ESB_FSSC2ESB_RECEIVE_BENEFICIARY_FSSC'");
		/** UUMS */
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_USERINFO'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_ADMINORG_EIS'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_ADMINORG'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_EMPLOYEE'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_ADMINORG_LSP'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_ADMINORG_BPMS'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_EMPLOYEE_LSP'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_ADMINORG_FSSC'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_USERINFO_FSSC'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_USERINFO_LSP'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_EMPLOYEE_BPMS'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_USERINFO_BPMS'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_EMPLOYEE_EIS'");
//		messageListener.setMessageSelector("esbServiceCode='ESB_UUMS2ESB_SEND_EMPLOYEE_FSSC'");
		messageListener.start();
		while(true){
			
		}
//		messageListener.shutdown();
	}

	@Test
	public void receiveMessage() {
		boolean start = true;
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			Destination destination = session.createQueue("QU_ESB_COM_BK");
			MessageConsumer consumer = session.createConsumer(destination);
			int i = 0;
			while (start) {
				Message message = consumer.receive();
				if (message == null) {
					start = false;
				}
				LOGGER.info(i++);
				if(i%10 == 0){
				JmsUtils.rollbackIfNecessary(session);
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void configLoader() {
		Assert.assertNotNull(configLoader.getAll());
	}

}
