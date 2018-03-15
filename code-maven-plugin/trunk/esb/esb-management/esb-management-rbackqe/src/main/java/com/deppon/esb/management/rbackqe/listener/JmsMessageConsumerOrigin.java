package com.deppon.esb.management.rbackqe.listener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public abstract class JmsMessageConsumerOrigin extends JmsDestinationOrigin {

	/** default receive timeOut 1000 ms */
	private static final int DEFAULT_RECEIVE_TIMEOUT = 1000;

	private Object destination;

	private String messageSelector;

	private int receiveTimeout = DEFAULT_RECEIVE_TIMEOUT;

	public int getReceiveTimeout() {
		return receiveTimeout;
	}

	public void setReceiveTimeout(int receiveTimeout) {
		this.receiveTimeout = receiveTimeout;
	}

	public Destination getDestination() {
		return (this.destination instanceof Destination ? (Destination) this.destination : null);
	}

	public void setDestination(Object destination) {
		if (destination instanceof String) {
			setDestinationName((String) destination);
		} else if (destination instanceof Destination) {
			this.destination = (Destination) destination;
		} else {
			throw new IllegalArgumentException(destination.toString() + "don't belong to String and Destination");
		}
	}

	public String getDestinationName() {
		return (this.destination instanceof String ? (String) this.destination : null);
	}

	public void setDestinationName(String destinationName) {
		this.destination = destinationName;
	}

	public String getMessageSelector() {
		return messageSelector;
	}

	public void setMessageSelector(String messageSelector) {
		this.messageSelector = messageSelector;
	}

	protected MessageConsumer createMessageConsumer(Session session) throws JMSException {
		Destination destination = getDestination();
		if (destination == null) {
			destination = resolveDestinationName(session, getDestinationName());
		}
		return createConsumer(session, destination);
	}

	protected MessageConsumer createConsumer(Session session, Destination destination) throws JMSException {
		if (getMessageSelector() == null || !(getMessageSelector() instanceof String)) {
			return session.createConsumer(destination);
		}
		return session.createConsumer(destination, getMessageSelector());
	}

	protected Message receiveMessage(MessageConsumer consumer) throws JMSException {
		return getReceiveTimeout() < 0 ? consumer.receive() : consumer.receive(getReceiveTimeout());
	}
}
