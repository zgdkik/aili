package org.hbhk.aili.report;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportTest {

	public static void main(String[] args) throws Exception {

		List<DailyZoom> results = new ArrayList<DailyZoom>();
		for (int i = 0; i < 3; i++) {
			results.add(new DailyZoom("hbhk1", 1));
		}
		JRBeanCollectionDataSource javaBeanDataSource = new JRBeanCollectionDataSource(
				results);
		JasperReport jasperReport = JasperCompileManager
				.compileReport("D:/baocun-ws/aili/aili-report/src/test/resources/report4.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name1", true);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, javaBeanDataSource);
		OutputStream outputStream = new FileOutputStream("D:/baocun-ws/aili/aili-report/src/test/resources/1.pdf");
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}
