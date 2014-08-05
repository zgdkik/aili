package org.hbhk.aili.support.server;

import java.io.File;
import java.util.List;

import org.hbhk.aili.support.server.excel.ExeclModelConvertor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HBHKExcelTest {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-excel.xml");
		ExeclModelConvertor convertor = new ExeclModelConvertor();
		File file = new File(
				"D:/baocun-ws/aili/aili-support/src/test/resources/1.xls");
		List<DeptModel> deptModels = convertor.getModel(file, "deptModel");
		for (DeptModel deptModel : deptModels) {
			System.out.println(deptModel.getDeptName());
		}
	}

}
