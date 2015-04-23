package org.hbhk.aili.report.server.view;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.report.server.exporter.IReportExporter;
import org.hbhk.aili.report.server.exporter.impl.ReportCsvExporter;
import org.hbhk.aili.report.server.exporter.impl.ReportHtmlExporter;
import org.hbhk.aili.report.server.exporter.impl.ReportPdfExporter;
import org.hbhk.aili.report.server.exporter.impl.ReportXlsExporter;
import org.hbhk.aili.report.server.print.ReportPrint;
import org.springframework.web.servlet.view.AbstractView;

public class ReportView extends AbstractView {
	private static final Log logger = LogFactory.getLog(ReportView.class);
	private static final String XLS = "xls";
	private static final String PDF = "pdf";
	private static final String CSV = "csv";
	private static final String REPORT_NAME = "reportName";
	private static final String FORMAT = "format";
	private static final String REPORT_PRINT = "reportPrint";
	private static final String HTML = "html";

	private static Map<String, IReportExporter> EXPORTER_MAP = new HashMap<String, IReportExporter>();

	static {
		EXPORTER_MAP.put(XLS, new ReportXlsExporter());
		EXPORTER_MAP.put(PDF, new ReportPdfExporter());
		EXPORTER_MAP.put(CSV, new ReportCsvExporter());
		EXPORTER_MAP.put(HTML, new ReportHtmlExporter());
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) {
		String reportName = (String) model.get(REPORT_NAME);// 报表的文件名
		String format = (String) model.get(FORMAT);// 报表的格式pdf xls .....
		ReportPrint reportPrint = (ReportPrint) model.get(REPORT_PRINT);// 这就是之前生成的中间文件
		response.setContentType("application/x-msdown;charset=utf-8");
		try {
			/*
			 * http头里的文件名貌似不支持utf-8，gbk之类的编码，需要转换一下 另外发现如果用new
			 * String(reportName.getBytes("UTF-8"), "iso-8859-1")的话Chrome和FF的
			 * 下载对话框的文件名是正常的，IE却是乱码，只能用GBK才正常
			 */
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ new String(reportName.getBytes("GBK"), "iso-8859-1")
					+ "\"");
		} catch (UnsupportedEncodingException e) {
			logger.error(null, e);
		}
		exportFile(reportPrint, format, response);
	}

	private void exportFile(ReportPrint reportPrint, String format,
			HttpServletResponse response) {
		OutputStream buffOS = null;
		try {
			buffOS = new BufferedOutputStream(response.getOutputStream());
			IReportExporter exporter = null;

			if (EXPORTER_MAP.containsKey(format)) {
				exporter = EXPORTER_MAP.get(format);// 获取需要格式的导出类
				exporter.export(reportPrint, buffOS);
			} else {
				logger.error("错误的报表格式:" + format);
			}
		} catch (JRException e) {
			logger.error("导出报表异常", e);
		} catch (IOException e) {
			logger.error(null, e);
		} finally {
			if (buffOS != null) {
				try {
					buffOS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
