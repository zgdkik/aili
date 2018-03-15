package org.hbhk.aili.mybatis.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestPostgreSQLDB {

	public static void main(String[] args) {
		System.out.print(" this is a test ");
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = " jdbc:postgresql://10.224.68.31:5432/postgres";
			Connection con = DriverManager.getConnection(url, "postgres",
					"135246");
			Statement st = con.createStatement();
			String sql = " select * from testtable ";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.print(rs.getInt(1));
				System.out.println(rs.getString(2));
			}
			rs.close();
			st.close();
			con.close();

		} catch (Exception ee) {
			System.out.print(ee.getMessage());
		}
	}
}
