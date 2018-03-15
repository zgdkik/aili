package org.hbhk.test.foss.sql;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.core.share.util.FileAsStringUtil;
import org.junit.Test;

public class GenBuMaSql {

	public static void main(String[] args) throws IOException {

		List<String> cList = FileAsStringUtil
				.readLines("foss/sql/c.sql", "sql");

		System.out.println("处理单号个数:" + cList.size());
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PKP.T_SRV_ACTUAL_FREIGHT A SET A.STATUS = 'EFFECTIVE', A.MODIFY_TIME = SYSDATE WHERE WAYBILL_NO IN (");
		sql.append("/r");
		for (int i = 0; i < cList.size(); i++) {
			String wayBill = cList.get(i);
			if (StringUtils.isEmpty(wayBill)) {
				continue;
			}
			if (i + 1 == cList.size()) {
				sql.append("'" + wayBill + "'");
			} else {
				sql.append("'" + wayBill + "',");
			}
			sql.append("/r");
		}
		sql.append(");");
		File f = new File("D:/无法补码.txt");
		OutputStream os = new FileOutputStream(f);
		os.write(sql.toString().getBytes());
		System.out.println(sql);
		os.close();
	}

	@Test
	public void test1() throws Exception {

		List<String> cList = FileAsStringUtil
				.readLines("foss/sql/c.sql", "sql");

		StringBuilder sql = new StringBuilder();
		String str = FileAsStringUtil
				.readFileToStr("F:/java-dev/workspaces/hbhkws00/aili/aili-core/src/test/resources/foss/sql/sql.sql");
		for (int i = 0; i < cList.size(); i++) {
			str = str.replaceAll("wo", cList.get(i));
			sql.append(str);
			sql.append("\r\n");
			sql.append("\r\n");
			str = str.replaceAll(cList.get(i), "wo");
		}
		System.out.println(sql);
		File f = new File("D:/修复运单错误配载部门脚本5.txt");
		OutputStream os = new FileOutputStream(f);
		os.write(sql.toString().getBytes());

		os.close();
		System.out.println("处理单号个数:" + cList.size());
	}

	@Test
	public void test2() throws Exception {

		List<String> cList = FileAsStringUtil.readLines("foss/sql/db.sql",
				"sql");

		List<String> newList = FileAsStringUtil.readLines("foss/sql/sql-r.sql",
				"sql");
		for (String str : cList) {
			if (!newList.contains(str)) {
				System.out.println("重复单号: '" + str + "',");
			}
			System.out.println("'" + str + "',");
		}
	}

	@Test
	public void test3() throws Exception {

		List<String> cList = FileAsStringUtil
				.readLines("foss/sql/active_order.sql", "sql");
		StringBuilder sql = new StringBuilder();
		String str = FileAsStringUtil
				.readFileToStr("F:/java-dev/workspaces/hbhkws00/aili/aili-core/src/test/resources/foss/sql/sql.sql");
		for (int i = 0; i < cList.size(); i++) {
			str = str.replaceAll("wo", cList.get(i));
			sql.append(str);
			sql.append("\r");
			sql.append("\r");
			str = str.replaceAll(cList.get(i), "wo");
		}
		System.out.println(sql);
		File f = new File("D:/111/激活订单处理4.txt");
		OutputStream os = new FileOutputStream(f);
		os.write(sql.toString().getBytes());

		os.close();
		sql = new StringBuilder();
		System.out.println("处理单号个数:" + cList.size());

	}

	@Test
	public void test4() throws Exception {

		List<String> cList = FileAsStringUtil
				.readLines("foss/sql/c.sql", "sql");
		StringBuilder sql = new StringBuilder();
		for (int i = 0; i < cList.size(); i++) {
			sql.append("'" + cList.get(i) + "',");
			sql.append("\r");
		}
		System.out.println(sql);
	}
	
	@Test
	public void test5() throws Exception {

		List<String> cList1 = FileAsStringUtil
				.readLines("foss/sql/1.sql", "sql");
		
		List<String> cList2 = FileAsStringUtil
				.readLines("foss/sql/2.sql", "sql");
		List<String>  list = new ArrayList<String>();
		for (String str : cList2) {
			if(!cList1.contains(str)){
				list.add(str);
			}
		}
		
		for (String str : cList1) {
			if(!cList2.contains(str)){
				list.add(str);
			}
		}
		for (String str : list) {
			System.out.println(str);
		}
	}
}
