package com.deppon.esb.management.rbackqe.listener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.util.Assert;

import com.deppon.esb.management.rbackqe.intface.destination.DestinationResolver;
import com.deppon.esb.management.rbackqe.intface.destination.IDestinationResolver;


public abstract class JmsDestinationOrigin extends JmsConnectOrigin {

	private IDestinationResolver destinationResolver = new DestinationResolver();

	public IDestinationResolver getDestinationResolver() {
		return destinationResolver;
	}

	public void setDestinationResolver(IDestinationResolver destinationResolver) {
		Assert.notNull(destinationResolver, "destinationResolver must nor null");
		this.destinationResolver = destinationResolver;
	}

	protected Destination resolveDestinationName(Session session, String destinationName) throws JMSException {
		return getDestinationResolver().createDestination(session, destinationName);
	}
}
