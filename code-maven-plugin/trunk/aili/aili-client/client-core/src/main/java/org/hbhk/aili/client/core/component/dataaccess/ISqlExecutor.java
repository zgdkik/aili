package org.hbhk.aili.client.core.component.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *	sql语句执行器接口
 */
public interface ISqlExecutor {

	/**
	 * 执行更新语句
	 * executeUpdate
	 * @param sql
	 * 传入的碎sql语句
	 * @return
	 * @throws SQLException int
	 * @since:0.6
	 */
	public abstract int executeUpdate(String sql) throws SQLException;

	/**
	 * 执行查询语句
	 * executeQuery
	 * @param sql
	 * @return
	 * @throws SQLException ResultSet
	 * @since:0.6
	 */
	public abstract ResultSet executeQuery(String sql) throws SQLException;
	/**
	 * 设置数据库链接是否自动提交
	 * setAutoCommit
	 * @param autoCommit
	 * 布尔值确定当前链接是否自动提交事务
	 * @throws SQLException void
	 * @since:0.6
	 */
	public abstract void setAutoCommit(boolean autoCommit) throws SQLException;

	/**
	 * 提交事务
	 * commit
	 * @throws SQLException void
	 * @since:0.6
	 */
	public abstract void commit() throws SQLException;

	/**
	 * 回滚事务
	 * rollback
	 * @throws SQLException void
	 * @since:0.6
	 */
	public abstract void rollback() throws SQLException;
	/**
	 * 关闭链接
	 * close
	 * @throws SQLException void
	 * @since:0.6
	 */
	public abstract void close() throws SQLException;
	/**
	 * 关闭数据库
	 * shutdown
	 * @throws SQLException void
	 * @since:0.6
	 */
	void shutdown() throws SQLException;
	/**
	 * 关闭hqsqldb
	 * @throws SQLException
	 */
	void closeHqsql() throws Exception;

	void optimizeDb() throws Exception;
}