/*
 * PROJECT NAME: esb-management-verification
 * PACKAGE NAME: com.deppon.esb.management.verification.service.mq.impl
 * FILE    NAME: VerifyMqConnService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.verification.mq.service.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.slf4j.LoggerFactory;

import com.deppon.esb.management.verification.mq.service.IVerifyMqConnService;

/**
 * The Class VerifyMqConnService.
 * 
 * @author HuangHua
 * @date 2013-2-28 下午4:20:21
 */
public class VerifyMqConnService implements IVerifyMqConnService {

	/** 常量定义 LOGGER. */
	private static final org.slf4j.Logger LOGGER = LoggerFactory
			.getLogger(VerifyMqConnService.class);

	/** JMS JNDI名称. */
	private String jndiName;

	/**
	 * 检查是否 connect.
	 * 
	 * @return true, if is connect
	 * @author HuangHua
	 * @date 2013-2-28 下午4:20:49
	 * @see com.deppon.esb.management.verification.service.mq.impl.IVerifyMqConnService#isConnect()
	 */
	@Override
	public boolean isConnect(ConnectionFactory connectionFactory) {
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			return true;
		} catch (JMSException e) {
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.warn(
								"JMS连接失败,JNDI:" + jndiName + "."
										+ e.getMessage(), e);
					}
				}
			}
		}
	}

	/**
	 * 获取 jMS JNDI名称.
	 * 
	 * @return the jMS JNDI名称
	 */
	public String getJndiName() {
		return jndiName;
	}

	/**
	 * 设置 jMS JNDI名称.
	 * 
	 * @param jndiName
	 *            the new jMS JNDI名称
	 */
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

}
