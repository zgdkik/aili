package com.deppon.esb.management.common.dataaccess.ibatis.dialect;

/**
 ******************************************* 
 * Copyright deppon.com. All rights reserved. Description: MySQL的分页方言 HISTORY ID
 * DATE PERSON REASON
 ******************************************** 
 * 1 2011-3-7 vincent.chen 新增
 * 2 2011-6-24 陈兴波 修改
 ******************************************** 
 */
public class MySQLDialect extends Dialect {

	private static final String LIMIT = " limit ";
	
	public boolean supportsLimitOffset() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	/**
	 * 
	 * @see com.deppon.esb.management.common.dataaccess.ibatis.dialect.foss.framework.server.components.dataaccess.dialect.Dialect#getLimitString(java.lang.String,
	 *      int, java.lang.String, int, java.lang.String) getLimitString
	 * @param sql
	 * @param offset
	 * @param offsetPlaceholder
	 * @param limit
	 * @param limitPlaceholder
	 * @return
	 * @since:0.6
	 */
	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {
			return sql + LIMIT + offsetPlaceholder + "," + limitPlaceholder;
		} else {
			return sql + LIMIT + limitPlaceholder;
		}
	}

	/**
	 * 为mysql 的语句添加limit限制
	 * @see com.deppon.esb.management.common.dataaccess.ibatis.dialect.foss.framework.server.components.dataaccess.dialect.Dialect#getLimitString(java.lang.String, int)
	 * getLimitString
	 * @param sql
	 * @param limit
	 * @return
	 * @since JDK1.6
	 */
	@Override
	public String getLimitString(String sql, int limit) {
		if(!checkLimit(sql)){
			return sql + LIMIT + limit;
		}
		return sql;
	}
	
	/**
	 * 判断sql语句是否已经加了limit限制
	 * checkLimit
	 * @param sql
	 * @return
	 * @return boolean
	 * @since JDK1.6
	 */
	private boolean checkLimit(String sql){
		boolean isLimited = false;
		if(sql.toLowerCase().indexOf(LIMIT)!=-1){
			isLimited = true;
		}
		return isLimited;
	}
}