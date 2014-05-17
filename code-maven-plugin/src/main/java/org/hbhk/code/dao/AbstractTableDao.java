package org.hbhk.code.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hbhk.code.domain.Column;
import org.hbhk.code.domain.Table;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public abstract class AbstractTableDao {

	public JdbcTemplate jdbcTemplate;
	
	public AbstractTableDao(DataSource dataSource) {
		this.jdbcTemplate = this.initTemplate(dataSource);
	}
	
	private JdbcTemplate initTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public static DataSource initDataSource(String jdbcUrl, String jdbcUsername, String jdbcPassword, String jdbcDriverClassName) {
		DriverManagerDataSource ds = new DriverManagerDataSource(jdbcUrl, jdbcUsername, jdbcPassword);
		ds.setDriverClassName(jdbcDriverClassName);
		return ds;
	}
	
	/**
	 * 
	 * <p>查询当前用户下所有表</p> 
	 * @author Administrator
	 * @date 2013-9-10 下午5:31:23
	 * @return
	 * @see
	 */
	
	public abstract List<Table>  queryAllTables();
	/**
	 * 
	 * <p>获取当前用户下的指定表</p> 
	 * @author 何波
	 * @date 2013-9-10 下午5:34:15
	 * @param tableName
	 * @return
	 * @see
	 */
	public abstract List<Table> queryTableByTableName(String[] tableNames);
	/**
	 * 
	 * <p>获取指定用户下所有表</p> 
	 * @author 何波
	 * @date 2013-9-10 下午5:32:45
	 * @param username
	 * @return
	 * @see
	 */
	public abstract List<Table> queryAllTablesByUser(String username);
	
	/**
	 * 
	 * <p>获取指定的用户和表</p> 
	 * @author 何波
	 * @date 2013-9-10 下午5:33:07
	 * @param username
	 * @param tableName
	 * @return
	 * @see
	 */
	public abstract List<Table> queryTable(String username,String tableName);
	
	public abstract Column queryTablePrimaryKey(String username,String tableName);
	
	public abstract Column queryTablePrimaryKey(String tableName);
	
	
	
}
