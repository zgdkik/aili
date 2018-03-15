package com.deppon.esb.management.rbackqe.intface.send;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.deppon.esb.management.rbackqe.intface.destination.DestinationResolver;
import com.deppon.esb.management.rbackqe.intface.destination.IDestinationResolver;

public class JmsSenderTemplater implements IJmsSenderTemplate {

	private IDestinationResolver destinationResolver = new DestinationResolver();

	@Override
	public boolean send(Message message, Session session, String queueName)
			throws JMSException {
		Destination destination = getDestinationResolver().createDestination(
				session, queueName);
		MessageProducer producer = session.createProducer(destination);
		producer.send(message);
		return true;
	}

	public final IDestinationResolver getDestinationResolver() {
		return destinationResolver;
	}

	public final void setDestinationResolver(
			IDestinationResolver destinationResolver) {
		this.destinationResolver = destinationResolver;
	}

}
