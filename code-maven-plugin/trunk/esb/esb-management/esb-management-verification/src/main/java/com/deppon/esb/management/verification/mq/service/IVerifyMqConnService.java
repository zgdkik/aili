/*
 * PROJECT NAME: esb-management-verification
 * PACKAGE NAME: com.deppon.esb.management.verification.service.mq
 * FILE    NAME: IVerifyMqConnection.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.verification.mq.service;

import java.io.IOException;

import javax.jms.ConnectionFactory;

/**
 * The Interface IVerifyMqConnection.
 * 
 * @author HuangHua
 * @date 2013-2-28 下午3:08:26
 */
public interface IVerifyMqConnService {

	/**
	 * 检查是否连接.
	 * 
	 * @return true, if is connect
	 * @throws IOException 
	 */
	boolean isConnect(ConnectionFactory connectionFactory);

}
