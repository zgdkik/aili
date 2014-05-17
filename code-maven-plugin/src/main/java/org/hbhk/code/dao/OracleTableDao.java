package org.hbhk.code.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.code.domain.Column;
import org.hbhk.code.domain.Table;
import org.hbhk.code.util.StringUtils;
import org.springframework.jdbc.core.RowCallbackHandler;

public class OracleTableDao extends AbstractTableDao {

	Log log = LogFactory.getLog(OracleTableDao.class);

	public OracleTableDao(DataSource dataSource) {
		super(dataSource);
	}

	public Table initTable(ResultSet rs, String tableName) throws SQLException {
		Table table = new Table();
		table.setName(tableName);
		table.setTypeName(StringUtils.generateClassname(table.getName()));
		table.setComment(rs.getString("tabComm"));

		Column col1 = queryTablePrimaryKey(tableName);
		table.setPk(col1);

		return table;
	}

	@Override
	public List<Table> queryAllTables() {

		log.info("jdbcTemplate：" + this.jdbcTemplate);
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT USER_TAB_COLS.TABLE_NAME  tabName,");
		sql.append(" USER_TAB_COMMENTS.COMMENTS tabComm,");
		sql.append(" USER_TAB_COLS.COLUMN_NAME colName,");
		sql.append(" USER_TAB_COLS.DATA_TYPE   colType,");
		sql.append(" USER_TAB_COLS.DATA_LENGTH colLength,");
		sql.append(" USER_TAB_COLS.NULLABLE   nullable,");
		sql.append(" USER_COL_COMMENTS.COMMENTS colComm");
		sql.append(" FROM USER_TAB_COLS ");
		sql.append(" INNER JOIN USER_COL_COMMENTS");
		sql.append(" ON  USER_COL_COMMENTS.TABLE_NAME = USER_TAB_COLS.TABLE_NAME");
		sql.append(" INNER JOIN USER_TAB_COMMENTS ");
		sql.append(" ON  USER_TAB_COMMENTS.TABLE_NAME = USER_TAB_COLS.TABLE_NAME");
		sql.append(" AND USER_COL_COMMENTS.COLUMN_NAME = USER_TAB_COLS.COLUMN_NAME");
		sql.append(" AND USER_TAB_COLS.TABLE_NAME ");
		sql.append(" IN (SELECT TABLE_NAME  FROM USER_TABLES)");

		final Map<String, Table> maptab = new HashMap<String, Table>();
		jdbcTemplate.query(sql.toString(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String tableName = rs.getString("tabName");
				Table table = null;
				if (maptab.containsKey(tableName)) {
					table = maptab.get(tableName);
				} else {
					table = initTable(rs, tableName);

				}
				Column col = new Column();
				// 列名
				col.setName(rs.getString("colName"));
				// 列备注
				col.setComment(rs.getString("colComm"));
				// 列类型
				col.setDataType(rs.getString("colType"));
				// 列长度
				col.setLength(rs.getInt("colLength"));
				// 列是否允许null Y N
				col.setNullable(rs.getString("nullable"));

				if (table.getColumnList() == null) {
					table.setColumnList(new ArrayList<Column>());
				}
				table.getColumnList().add(col);
				maptab.put(tableName, table);
			}
		});

		if (maptab.values() == null || maptab.values().size() == 0) {
			return new ArrayList<Table>(0);
		}

		return new ArrayList<Table>(maptab.values());
	}

	@Override
	public List<Table> queryTableByTableName(String[] tableNames) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT USER_TAB_COLS.TABLE_NAME  tabName,");
		sql.append(" USER_TAB_COMMENTS.COMMENTS tabComm,");
		sql.append(" USER_TAB_COLS.COLUMN_NAME colName,");
		sql.append(" USER_TAB_COLS.DATA_TYPE   colType,");
		sql.append(" USER_TAB_COLS.DATA_LENGTH colLength,");
		sql.append(" USER_TAB_COLS.NULLABLE   nullable,");
		sql.append(" USER_COL_COMMENTS.COMMENTS colComm");
		sql.append(" FROM USER_TAB_COLS ");
		sql.append(" INNER JOIN USER_COL_COMMENTS");
		sql.append(" ON  USER_COL_COMMENTS.TABLE_NAME = USER_TAB_COLS.TABLE_NAME");
		sql.append(" INNER JOIN USER_TAB_COMMENTS ");
		sql.append(" ON  USER_TAB_COMMENTS.TABLE_NAME = USER_TAB_COLS.TABLE_NAME");
		sql.append(" AND USER_COL_COMMENTS.COLUMN_NAME = USER_TAB_COLS.COLUMN_NAME");
		sql.append(" AND USER_TAB_COLS.TABLE_NAME ");
		sql.append(" in ( ");
		for (int i = 0; i < tableNames.length; i++) {
			if (i == tableNames.length - 1) {
				sql.append("?");
				continue;
			}
			sql.append("?,");
		}
		sql.append(" )");
		System.out.println(sql.toString());
		final Map<String, Table> maptab = new HashMap<String, Table>();
		Object[]  objs = tableNames;
		jdbcTemplate.query(sql.toString(), objs,
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						String tableName = rs.getString("tabName");
						Table table = null;
						if (maptab.containsKey(tableName)) {
							table = maptab.get(tableName);
						} else {
							table = initTable(rs, tableName);
						}

						Column col = new Column();
						// 列名
						col.setName(rs.getString("colName"));
						// 列备注
						col.setComment(rs.getString("colComm"));
						// 列类型
						col.setDataType(rs.getString("colType"));
						// 列长度
						col.setLength(rs.getInt("colLength"));
						// 列是否允许null Y N
						col.setNullable(rs.getString("nullable"));

						if (table.getColumnList() == null) {
							table.setColumnList(new ArrayList<Column>());
						}
						table.getColumnList().add(col);
						maptab.put(tableName, table);
					}
				});

		if (maptab.values() == null || maptab.values().size() == 0) {
			return new ArrayList<Table>(0);
		}
		List<Table> tabs = new ArrayList<Table>(maptab.values());
		return tabs;
	}

	@Override
	public List<Table> queryAllTablesByUser(String username) {
		return null;
	}

	@Override
	public List<Table> queryTable(String username, String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Column queryTablePrimaryKey(String username, String tableName) {
		return null;
	}

	@Override
	public Column queryTablePrimaryKey(String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select col.column_name prikey");
		sql.append(" from user_constraints con,  user_cons_columns col");
		sql.append(" where con.constraint_name = col.constraint_name ");
		sql.append(" and con.constraint_type='P'");
		sql.append(" and col.table_name = ? ");
		final Column col = new Column();
		jdbcTemplate.query(sql.toString(), new Object[] { tableName },
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						String prikey = rs.getString("prikey");
						col.setName(prikey);
					}
				});
		return col;
	}

}
