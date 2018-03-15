package com.deppon.esb.management.rbackqe.listener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.beans.factory.InitializingBean;

public abstract class JmsConnectOrigin implements InitializingBean {

	private ConnectionFactory connectionFactory;

	private boolean sessionTransacted = true;

	private int sessionAcknowledgeMode = Session.AUTO_ACKNOWLEDGE;

	public int getSessionAcknowledgeMode() {
		return sessionAcknowledgeMode;
	}

	public void setSessionAcknowledgeMode(int sessionAcknowledgeMode) {
		this.sessionAcknowledgeMode = sessionAcknowledgeMode;
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public boolean isSessionTransacted() {
		return sessionTransacted;
	}

	public void setSessionTransacted(boolean sessionTransacted) {
		this.sessionTransacted = sessionTransacted;
	}

	// 加载判断
	public void afterPropertiesSet() {
		if (getConnectionFactory() == null) {
			throw new IllegalArgumentException("Property 'connectionFactory' is required");
		}
	}

	// 创建链接
	protected Connection createConnection() throws JMSException {
		afterPropertiesSet();
		return getConnectionFactory().createConnection();
	}

	// 获取session
	protected Session createSession(Connection connection) throws JMSException {
		return connection.createSession(isSessionTransacted(), getSessionAcknowledgeMode());
	}

}
