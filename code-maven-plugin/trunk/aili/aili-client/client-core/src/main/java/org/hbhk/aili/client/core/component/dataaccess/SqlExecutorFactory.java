package org.hbhk.aili.client.core.component.dataaccess;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *	sql语句执行器工厂，获取sql执行器
 */
public final class SqlExecutorFactory implements HsqldbDataSource {

	/**
	 * 
	 * <p>Title: getSqlExecutor</p>
	 * <p>Description: 获取SQL执行器</p>
	 * @return
	 */
	public static ISqlExecutor getSqlExecutor() {
		ISqlExecutor executor = null;
		try {
			Connection connection =  DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
			executor = new SqlExecutorImpl(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return executor;
	}
	
	/**
	 * 
	 * <p>Title: SqlExecutorFactory</p>
	 * <p>Description: 构造函数</p>
	 */
	private SqlExecutorFactory(){
		try {
			Driver driver = (Driver) Class.forName(DB_DRIVER).newInstance();
			DriverManager.registerDriver(driver);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>Title: SqlExecutorImpl</p>
	 * <p>Description: SQL执行器接口实现</p>
	 * <p>Company: DEPPON</p>
	 * @author Polo Yuan
	 * @date 2011-6-14
	 *
	 */
	static class SqlExecutorImpl implements ISqlExecutor{
		private Connection connection;
		
		/**
		 * 
		 * <p>Title: SqlExecutorImpl</p>
		 * <p>Description: 构造函数</p>
		 * @param connection 连接
		 */
		public SqlExecutorImpl(Connection connection){
			this.connection = connection;
		}
		
		/*
		 * (non-Javadoc)
		 * <p>Title: executeUpdate</p>
		 * <p>Description: 执行更新</p>
		 * @param sql SQL语句
		 * @return
		 * @throws SQLException
		 * @see com.deppon.foss.framework.client.component.dataaccess.ISqlExecutor#executeUpdate(java.lang.String)
		 */
		@Override
		public int executeUpdate(final String sql) throws SQLException{
			if (sql==null) {
				return -1;
			}
			PreparedStatement ps=null;
			try {
				ps = connection.prepareStatement(sql);
				return ps.executeUpdate();
			} finally{
				if (ps!=null) {
					ps.close();
				}
			}
		}
		
		/*
		 * (non-Javadoc)
		 * <p>Title: executeQuery</p>
		 * <p>Description: 执行查询</p>
		 * @param sql SQL语句
		 * @return
		 * @throws SQLException
		 * @see com.deppon.foss.framework.client.component.dataaccess.ISqlExecutor#executeQuery(java.lang.String)
		 */
		@Override
		public ResultSet executeQuery(final String sql) throws SQLException{
			
			if (sql==null) {
				return null;
			}
			PreparedStatement ps=null;
			try {
				ps = connection.prepareStatement(sql);
				return ps.executeQuery();
			} finally{
				if (ps!=null) {
					ps.close();
				}
			}
		}
		
		/*
		 * (non-Javadoc)
		 * <p>Title: setAutoCommit</p>
		 * <p>Description: 设置自动提交</p>
		 * @param autoCommit
		 * @throws SQLException
		 * @see com.deppon.foss.framework.client.component.dataaccess.ISqlExecutor#setAutoCommit(boolean)
		 */
		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException{
			if (autoCommit!=connection.getAutoCommit()) {
				connection.setAutoCommit(autoCommit);
			}
		}
		
		/*
		 * (non-Javadoc)
		 * <p>Title: commit</p>
		 * <p>Description: 提交</p>
		 * @throws SQLException
		 * @see com.deppon.foss.framework.client.component.dataaccess.ISqlExecutor#commit()
		 */
		@Override
		public void commit() throws SQLException {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
		}
		
		/*
		 * (non-Javadoc)
		 * <p>Title: rollback</p>
		 * <p>Description: 回滚</p>
		 * @throws SQLException
		 * @see com.deppon.foss.framework.client.component.dataaccess.ISqlExecutor#rollback()
		 */
		@Override
		public void rollback() throws SQLException{
			connection.rollback();
		}
		
		/*
		 * (non-Javadoc)
		 * <p>Title: close</p>
		 * <p>Description: 关闭连接</p>
		 * @throws SQLException
		 * @see com.deppon.foss.framework.client.component.dataaccess.ISqlExecutor#close()
		 */
		@Override
		public void close() throws SQLException {
			if (connection==null) {
				return;
			}
			if (!connection.isClosed()) {
				connection.close();
			}
		}
		
		/*
		 * (non-Javadoc)
		 * <p>Title: finalize</p>
		 * <p>Description: 结束处理</p>
		 * @throws Throwable
		 * @see java.lang.Object#finalize()
		 */
		@Override
		protected void finalize() throws Throwable {
			super.finalize();
			if (connection!=null) {
				if (!connection.isClosed()) {
					connection.close();
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * <p>Title: shutdown</p>
		 * <p>Description: 关闭处理</p>
		 * @throws SQLException
		 * @see com.deppon.foss.framework.client.component.dataaccess.ISqlExecutor#shutdown()
		 */
		@Override
		public void shutdown() throws SQLException {
			connection.createStatement().close();
		}
		
		public void closeHqsql() throws Exception{
			PreparedStatement ps=null;
			try {
				ps = connection.prepareStatement("shutdown");
				ps.executeUpdate();
			} finally{
				if (ps!=null) {
					ps.close();
				}
			}
		}
		
		public void optimizeDb() throws Exception{
			PreparedStatement ps=null;
			try {
				ps = connection.prepareStatement("checkpoint defrag");
				ps.executeUpdate();
			} finally{
				if (ps!=null) {
					ps.close();
				}
			}
		}
		
	}
}