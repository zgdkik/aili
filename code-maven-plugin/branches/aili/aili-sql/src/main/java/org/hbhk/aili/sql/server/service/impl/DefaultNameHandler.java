package org.hbhk.aili.sql.server.service.impl;

import org.hbhk.aili.sql.server.service.INameHandler;

/**
 * 默认名称处理handler
 */
public class DefaultNameHandler implements INameHandler {

	/**
	 * 获取表名
	 */
	@Override
	public String getTableName(String tableName) {
		return tableName;
	}

	/**
	 * 获取主键名
	 */
	@Override
	public String getPrimaryName(String primaryName) {

		return primaryName;
	}

	/**
	 * 获取列名
	 */
	@Override
	public String getColumnName(String columnName) {

		return columnName;
	}

}
