package org.hbhk.aili.support.server;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hbhk.aili.support.server.excel.file.impl.ExcelToModelUtil;
import org.hbhk.aili.support.server.excel.file.impl.ModelToExcelUtil;

public class ExcelMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		test2();
	}

	public static void test2() throws RowsExceededException, WriteException,
			IOException {
		String excelFile = "C:/Users/jumbo/Desktop/1.xls";
		List models = new ArrayList<DeptModel>();
		for (int i = 0; i < 5; i++) {
			DeptModel model2 = new DeptModel();
			model2.setDeptName("deptName_" + (i + 1));
			model2.setDeptCode("deptCode_" + (i + 1));
			model2.setSendFileName("send_" + (i + 1));
			model2.setSendDate(new Date());
			models.add(model2);
		}
		ModelToExcelUtil.model2Excel(excelFile, "deptModel", models);
	}

	public static void test1() throws ParseException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptNo", "1");
		map.put("bgqx", "Y");
		// "D://个人资料//eclipse//workspace//readexcel//src//main//resources//test.xls"
		List modelList = ExcelToModelUtil
				.getModelList(
						"C:/Users/jumbo/Desktop/excel/read-write-excel/src/main/resources/a.xls",
						"deptModel");
		for (int i = 0; i < modelList.size(); i++) {
			Object obj = modelList.get(i);
			System.out.println(obj.getClass().getSimpleName());
			System.out.println(ToStringBuilder.reflectionToString(obj));
		}
		System.out
				.println("---------======================--------------------");
	}

}
