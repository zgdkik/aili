/*
 * PROJECT NAME: esb-management-verification
 * PACKAGE NAME: com.deppon.esb.management.verification.service.db.impl
 * FILE    NAME: VerifyDbConnService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.verification.db.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.verification.db.service.IVerifyDbConnService;

/**
 * The Class VerifyDbConnService.
 * 
 * @author HuangHua
 * @date 2013-2-28 下午4:40:38
 */
public class VerifyDbConnService implements IVerifyDbConnService {

	/** 常量定义 LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(VerifyDbConnService.class);

	/**
	 * 检查是否 connect.
	 * 
	 * @param dataSource
	 *            the data source
	 * @return true, if is connect
	 * @author HuangHua
	 * @date 2013-2-28 下午4:40:50
	 * @see com.deppon.esb.management.verification.service.db.IVerifyDbConnService#isConnect()
	 */
	@Override
	public boolean isConnect(DataSource dataSource) {
		String sql = "select 1 from dual";
		Connection connection = null;
		Statement st = null;
		try {
			connection = dataSource.getConnection();
			st = connection.createStatement();
			boolean result = st.execute(sql);
			return result;
		} catch (SQLException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.warn("连接DB失败." + e.getMessage(), e);
			}
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.warn(e.getMessage(), e);
					}
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.warn(e.getMessage(), e);
					}
				}
			}
		}
		return false;
	}

}
