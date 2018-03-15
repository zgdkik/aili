package org.hbhk.aili.esb.test;

import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.utils.XmlUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class TestJms2 {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jms2.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");

		AsynGoodsBillReceiveRequest receiveRequest = new AsynGoodsBillReceiveRequest();
		receiveRequest.setOrderNumber("order123");
		final String str = XmlUtil.toStr(receiveRequest,AsynGoodsBillReceiveRequest.class);
		System.out.println(str);
		for (int i = 0; i < 1000; i++) {
			jmsTemplate.send("QU_FOSS_REQUEST_YC_IN", new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					TextMessage message = session.createTextMessage();
					message.setText(str);
					header2JMSProperties(message);
					System.out.println(message);
					return message;
				}
			});
			TimeUnit.SECONDS.sleep(1);
		}
		
	}

	public static void header2JMSProperties(TextMessage message)
			throws JMSException {
		// 头转换
		message.setStringProperty(ESBServiceConstant.ESB_SERVICE_CODE, "ESB_CRM2ESB_ORDER_ABOUT");
		message.setStringProperty(ESBServiceConstant.BACK_SERVICE_CODE, "FOSS_ESB2FOSS_ORDER_ABOUT");
		String uuid = System.currentTimeMillis() + "";
		message.setStringProperty(ESBServiceConstant.VERSION, uuid);
		message.setStringProperty(ESBServiceConstant.BUSINESSID, uuid);
		message.setStringProperty(ESBServiceConstant.REQUEST_ID, uuid);
		message.setStringProperty(ESBServiceConstant.SOURCE_SYSTEM, "hbhk1");
		message.setIntProperty(ESBServiceConstant.MESSAGE_FORMATE, 1);
		message.setIntProperty(ESBServiceConstant.EXCHANGE_PATTERN, 1);
	}

}
