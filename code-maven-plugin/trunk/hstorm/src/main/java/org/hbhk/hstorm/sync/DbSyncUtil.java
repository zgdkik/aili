package org.hbhk.hstorm.sync;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class DbSyncUtil {

	public static Map<String, TabInfo> tabMap = new ConcurrentHashMap<String, TabInfo>();

	public static void intiDbInfo(Connection conn) throws SQLException {
		List<String> tables = new ArrayList<String>();
		DatabaseMetaData dbMetaData = conn.getMetaData();
		// 可为:"TABLE", "VIEW", "SYSTEM   TABLE",
		// "GLOBAL   TEMPORARY", "LOCAL   TEMPORARY", "ALIAS", "SYNONYM"
		String[] types = { "TABLE" };
		ResultSet tabs = dbMetaData.getTables(null, null, null, types);
		while (tabs.next()) {
			// 只要表名这一列TMS_SCAN_RECORD
			tables.add(tabs.getString("TABLE_NAME"));
		}
		System.out.println(tables);
		Statement statement = conn.createStatement();
		TabInfo tabInfo = new TabInfo();
		for (String tab : tables) {
			tabInfo = new TabInfo();
			tabInfo.setTabName(tab);
			ResultSet resultSet = null;
			try {
				resultSet = statement.executeQuery("select * from " + tab);
				ResultSetMetaData metaData = resultSet.getMetaData();

				ResultSet pkRSet = dbMetaData.getPrimaryKeys(null, null, tab);
				while (pkRSet.next()) {
					String pk = pkRSet.getString("COLUMN_NAME");
					tabInfo.setPrimaryKey(pk);
				}
				List<ColumnInfo> cols = new ArrayList<ColumnInfo>();
				// 获取列名
				ColumnInfo col = null;
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					// resultSet数据下标从1开始
					String columnName = metaData.getColumnName(i + 1);
					int type = metaData.getColumnType(i + 1);
					int len = 0;
					col = new ColumnInfo(columnName, type + "", len);
					cols.add(col);
				}
				tabInfo.setCols(cols);
				System.out.println("table:" + tab);
				tabMap.put(tab.toLowerCase(), tabInfo);
				System.out.println();
			} catch (Exception e) {
				System.out.println("table ERR:" + tab);
			}

		}
		System.out.println(tabMap.keySet());
	}

	public static void intiDbInfo(Connection conn, String tabName)
			throws SQLException {
		List<String> tables = new ArrayList<String>();
		DatabaseMetaData dbMetaData = conn.getMetaData();
		tables.add(tabName);
		System.out.println(tables);
		Statement statement = conn.createStatement();
		TabInfo tabInfo = new TabInfo();
		for (String tab : tables) {
			tabInfo = new TabInfo();
			tabInfo.setTabName(tab);
			ResultSet resultSet = null;
			try {
				resultSet = statement.executeQuery("select * from " + tab);
				ResultSetMetaData metaData = resultSet.getMetaData();

				ResultSet pkRSet = dbMetaData.getPrimaryKeys(null, null, tab);
				while (pkRSet.next()) {
					String pk = pkRSet.getString("COLUMN_NAME");
					tabInfo.setPrimaryKey(pk);
				}
				List<ColumnInfo> cols = new ArrayList<ColumnInfo>();
				// 获取列名
				ColumnInfo col = null;
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					// resultSet数据下标从1开始
					String columnName = metaData.getColumnName(i + 1);
					int type = metaData.getColumnType(i + 1);
					int len = 0;
					col = new ColumnInfo(columnName, type + "", len);
					cols.add(col);
				}
				tabInfo.setCols(cols);
				System.out.println("table:" + tab);
				tabMap.put(tab, tabInfo);
				System.out.println();
			} catch (Exception e) {
				System.out.println("table ERR:" + tab);
			}

		}
		System.out.println(tabMap.keySet());
	}

	public static List<Map<String, Object>> select(Connection conn,
			String tabName) throws Exception {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		PreparedStatement st = conn
				.prepareStatement("select  *from " + tabName);
		ResultSet rs = st.executeQuery();
		ResultSetMetaData me = rs.getMetaData();
		while (rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 1; i < me.getColumnCount() + 1; i++) {
				String col = me.getColumnName(i);
				Object val = rs.getObject(i);
				if (val instanceof Boolean) {
					val = rs.getInt(i);
					map.put(col, val);
				} else {
					map.put(col, val);
				}
			}
			dataList.add(map);
		}
		return dataList;
	}

	public static void insert(Connection conn,
			String tabName, List<Map<String, Object>> dataList, int batch)
			throws SQLException {
		PreparedStatement pst = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into " + tabName + "(");
			TabInfo tabInfo = tabMap.get(tabName);
			List<ColumnInfo> cols = tabInfo.getCols();
			for (ColumnInfo col : cols) {
				sql.append(col.getCol() + ",");
			}
			sql = sql.delete(sql.length() - 1, sql.length());
			sql.append(")  values ( ");
			Map<String, Integer> colType = new HashMap<String, Integer>();
			for (ColumnInfo col : cols) {
				sql.append("?,");
				colType.put(col.getCol(), Integer.parseInt(col.getType()));
			}
			sql = sql.delete(sql.length() - 1, sql.length());
			sql.append(")");
			System.out.println(sql);
			pst = conn.prepareStatement(sql.toString());
			for (int i = 0; i < dataList.size(); i++) {
				Map<String, Object> data = dataList.get(i);
				int num = 1;
				for (ColumnInfo col : cols) {
					String key = col.getCol().toLowerCase();
					Object val = data.get(key);
					if (val == null) {
						pst.setString(num, null);
						num++;
						continue;
					}
					if (val instanceof Integer) {
						// System.out.println(key+" Integer val:"+val);
						pst.setInt(num, (int) val);
					} else if (val instanceof Long) {
						// System.out.println(key+" long val:"+val);
						pst.setLong(num, (Long) val);
					} else if (val instanceof Timestamp) {
						// System.out.println(key+" Timestamp val:"+val);
						Timestamp ts = (Timestamp) val;
						// Date date = new Date(ts.getTime());
						pst.setTimestamp(num, ts);
						// String dateStr = DateFormatUtils.format(date,
						// "yyyy-MM-dd HH:mm:ss");
						// dateStr
						// ="to_date('"+dateStr+"','yyyy-MM-dd HH24:mi:ss')";
					} else if (val instanceof java.sql.Date) {
						// System.out.println(key+" java.sql.Date val:"+val);

						pst.setDate(num, (java.sql.Date) val);

					} else if (val instanceof Boolean) {
						// System.out.println(key+" Boolean val:"+val);
						boolean ff = (boolean) val;
						if (ff) {
							pst.setInt(num, 1);
						} else {
							pst.setInt(num, 0);
						}
					} else if (val instanceof BigDecimal) {
						// System.out.println(key+" BigDecimal val:"+val);
						pst.setBigDecimal(num, (BigDecimal) val);
					} else if (val instanceof String) {
						// System.out.println(key+" String val:"+val);
						pst.setString(num, (String) val);
					} else {
						pst.setString(num, String.valueOf(val));
					}
					num++;
				}
				pst.addBatch();
				if (i % batch == 0) {
					TimeUnit.SECONDS.sleep(5);
					System.out.println("开始数据提交:" + batch);
					pst.executeBatch();
					System.out.println("结束数据提交:" + batch);

				}
			}
			pst.executeBatch();
			System.out.println("完成数据提交:" + dataList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCPUtils.closeAll(null, pst, conn);
		}

		// int[] updatedCountArray = jdbcTemplate.batchUpdate(sql.toString(),
		// new BatchPreparedStatementSetter() {
		//
		// public void setValues(PreparedStatement ps, int i)
		// throws SQLException {
		// //要注意，下标从1开始
		// // ps.setLong(1, stockList.get(i).getId());
		// // ps.setString(2, stockList.get(i).getCode());
		// // ps.setString(3, stockList.get(i).getName());
		// }
		//
		// public int getBatchSize() {
		//
		// return 1;
		// }
		// });
	}
}
