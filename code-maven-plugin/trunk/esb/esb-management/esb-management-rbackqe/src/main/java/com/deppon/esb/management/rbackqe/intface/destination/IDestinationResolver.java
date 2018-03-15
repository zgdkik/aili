package com.deppon.esb.management.rbackqe.intface.destination;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

public interface IDestinationResolver {
	
	Destination createDestination(Session session,String destinationName) throws JMSException;
}
