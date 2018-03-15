package com.deppon.esb.management.rbackqe.intface.destination;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.springframework.util.Assert;


public class DestinationResolver implements IDestinationResolver {

	@Override
	public Destination createDestination(Session session, String destinationName) throws JMSException {
		Assert.notNull(session, "createDestination's session is null!");
		Assert.notNull(destinationName, "createDestination's queueName is null");
		return resolveQueue(session, destinationName);
	}

	protected Destination resolveQueue(Session session, String queueName) throws JMSException {
		if (session instanceof QueueSession) {
			return ((QueueSession) session).createQueue(queueName);
		} else {
			return session.createQueue(queueName);
		}
	}

}
