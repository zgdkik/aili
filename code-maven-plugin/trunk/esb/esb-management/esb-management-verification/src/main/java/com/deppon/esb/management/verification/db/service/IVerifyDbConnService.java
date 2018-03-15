/*
 * PROJECT NAME: esb-management-verification
 * PACKAGE NAME: com.deppon.esb.management.verification.service.mq
 * FILE    NAME: IVerifyDbConnService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.management.verification.db.service;

import javax.sql.DataSource;

/**
 * The Interface IVerifyDbConnService.
 * 
 * @author HuangHua
 * @date 2013-2-28 下午4:37:06
 */
public interface IVerifyDbConnService {

	/**
	 * 检查是否连接.
	 * 
	 * @param dataSource
	 *            the data source
	 * @return true, if is connect
	 */
	boolean isConnect(DataSource dataSource);
}
