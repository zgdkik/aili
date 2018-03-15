package org.hbhk.aili.support.server.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.hbhk.aili.support.server.excel.poi.ExcelKit;
import org.hbhk.aili.support.server.excel.poi.ExcelWriter;
import org.hbhk.aili.support.server.excel.poi.WriteStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:excel/spring-cxcel.xml")
public class ExcelWriterTest1 {

	@Resource
	private ApplicationContext ac;

	@Autowired
	@Qualifier("report1Writer")
	private ExcelWriter report1ExcelWriter;

	@Test
	public void test1() throws Exception {
		Map<String, Object> beans = new HashMap<String, Object>();
		Map<String, Object> headMap = new HashMap<String, Object>();
		headMap.put("reportDate", new Date());
		headMap.put("reporter", "汇报者");
		beans.put("head", headMap);
		// List<Map<String, Object>> bodyList = new ArrayList<Map<String,
		// Object>>();
		List<TestEntity> bodyList = new ArrayList<TestEntity>();
		for (int i = 0; i < 10; i++) {
			// Map<String, Object> map = new HashMap<String, Object>();
			// map.put("sn", i + 1);
			// map.put("name", "名称" + i);
			// map.put("lineDate", new Date());
			// map.put("intValue", i * 2 + 1);
			// map.put("floatValue", (i * 7 + 3) / 3.0);
			// bodyList.add(map);
			TestEntity te = new TestEntity();
			te.setSn(i + 1);
			te.setName("名称" + i);
			te.setLineDate(new Date());
			te.setIntValue(i * 2 + 1);
			te.setFloatValue((float) ((i * 7 + 3) / 3.0));
			bodyList.add(te);
		}
		beans.put("bodylist", bodyList);
		System.out.println(report1ExcelWriter.getDefinition());
		WriteStatus ws = report1ExcelWriter.write(new FileOutputStream(
				new File("D:/test.xlsx")), beans);
		for (String str : ExcelKit.getInstance().getWriteStatusMessages(ws,
				Locale.CHINESE)) {
			System.out.println(str);
		}
		System.out.println("Done!");
	}

	@Test
	public void test2() throws Exception {
		Map<String, Object> beans = new HashMap<String, Object>();
		List<DemoEntity> bodyList = new ArrayList<DemoEntity>();
		for (int i = 0; i < 10; i++) {
			DemoEntity te = new DemoEntity();
			te.setName("名称" + i);
			te.setCreateTime(new Date());
			bodyList.add(te);
		}
		beans.put("demoList", bodyList);
		System.out.println(report1ExcelWriter.getDefinition());
		WriteStatus ws = report1ExcelWriter.write(new FileOutputStream(
				new File("D:/test1.xlsx")), beans);
		for (String str : ExcelKit.getInstance().getWriteStatusMessages(ws,
				Locale.CHINESE)) {
			System.out.println(str);
		}
		System.out.println("Done!");
	}

}
