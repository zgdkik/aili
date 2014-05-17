package org.hbhk.code.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hbhk.code.dao.OracleTableDao;
import org.hbhk.code.domain.Column;
import org.hbhk.code.domain.Table;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class TestOracleTableDao {

	private DataSource dataSource;

	@Before
	public void before() {
		dataSource = new DriverManagerDataSource(
				"jdbc:oracle:thin:@192.168.13.125:1521:HBHK", "hbhk",
				"as135246");
		((DriverManagerDataSource) dataSource)
				.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	}

	@Test
	public void testqueryAllTables() {

		OracleTableDao oracleTableDao = new OracleTableDao(dataSource);
		List<Table> tables = oracleTableDao.queryAllTables();
		System.out.println("tables:"+tables.size());
		for (int i = 0; i < tables.size(); i++) {
			System.out.println("tab"+tables.get(i).getName() + ":");
			List<Column> columns = tables.get(i).getColumnList();
			for (int j = 0; j < columns.size(); j++) {
				System.out.println(columns.get(j).getName()+" --> "+columns.get(j).getDataType());
			}
			System.out.println("end");
		}
	}

}
