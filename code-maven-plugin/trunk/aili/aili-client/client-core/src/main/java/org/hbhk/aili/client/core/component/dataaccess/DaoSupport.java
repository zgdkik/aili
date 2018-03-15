package org.hbhk.aili.client.core.component.dataaccess;

import java.sql.Connection;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;

/**
 *
 *	提供了SqlSession对象的获取方法。
 */
public abstract class DaoSupport {

	/**
	 * 获取需要自己提交事务的SqlSession
	 * getSqlSession
	 * @return SqlSession
	 * @since:0.6
	 */
	protected SqlSession getSqlSession() {
		return DefaultTransactionFactory.getSqlSessionFactory().openSession();
	}
	/**
	 * 获取SqlSession对象可以指定此对象是否自动提交事务
	 * getSqlSession
	 * @param autoCommit
	 * 布尔值，表示SqlSession是否自动提交
	 * @return SqlSession
	 * @since:0.6
	 */
	protected SqlSession getSqlSession(boolean autoCommit) {
		return DefaultTransactionFactory.getSqlSessionFactory().openSession(
				autoCommit);
	}
	/**
	 * 通过传入数据库链接来创建SqlSession对象
	 * getSqlSession
	 * @param connection
	 * 链接对象想
	 * @return SqlSession
	 * @since:0.6
	 */
	protected SqlSession getSqlSession(Connection connection) {
		return DefaultTransactionFactory.getSqlSessionFactory()
		.openSession(connection);
	}

	/**
	 * 
	 * getSqlSession
	 * @param execType
	 * ExecutorType是一个枚举类型，它包括三个值：
	 * ExecutorType.SIMPLE表示
	 * 这个执行器类型不做特殊的事情。它为每个语句的执行创建一个新的预处理语句。
	 * ExecutorType.REUSE
	 * 这个执行器类型会复用预处理语句。
	 * ExecutorType.BATCH
	 * 这个执行器会批量执行所有更新语句，如果 SELECT 在它们中间执行还会标定它们是
	 * 必须的，来保证一个简单并易于理解的行为。
	 * @return SqlSession
	 * @since:0.6
	 */
	protected SqlSession getSqlSession(ExecutorType execType) {
		return DefaultTransactionFactory.getSqlSessionFactory().openSession(execType);
	}
	/**
	 * 
	 * getSqlSession
	 * @param level
	 * 。 MyBat is 为事务隔离级别调用使用一个Java枚举包装器，称为TransactionIsolationLevel，
	 * 否 则 它 们 按 预 期 的 方 式 来 工 作 ， 并 有 JDBC 支持的 5 级
	 * （NONE,READ_UNCOMMITTED,READ_COMMITTED,REPEATABLE_READ,SERIALIZABLE）
	 * @return SqlSession
	 * @since:0.6
	 */
	protected SqlSession getSqlSession(TransactionIsolationLevel level) {
		return DefaultTransactionFactory.getSqlSessionFactory().openSession(level);
	}

	protected SqlSession getSqlSession(ExecutorType execType, boolean autoCommit) {
		return DefaultTransactionFactory.getSqlSessionFactory().openSession(execType,autoCommit);
	}

	protected SqlSession getSqlSession(ExecutorType execType, Connection connection) {
		return DefaultTransactionFactory.getSqlSessionFactory().openSession(execType, connection);
	}

	protected SqlSession getSqlSession(ExecutorType execType, TransactionIsolationLevel level) {
		return DefaultTransactionFactory.getSqlSessionFactory().openSession(execType, level);
	}
}
