package org.hbhk.aili.report.server.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.report.server.exporter.IReportExporter;
import org.hbhk.aili.report.server.view.ReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportController{

	private IReportExporter reportExporter;
	
	@Autowired
	private ApplicationContext context;

	/**
	 * 显示html、下载pdf、xls、cvs等报表<br>
	 * 请求链接必须带有reportName参数，用以指定模板名<br>
	 * 请求链接必须带有format参数，用以指定生成的格式(html、pdf、xls、csv)
	 * 
	 * @param reportEntity
	 *            报表相关参数
	 * @param format
	 * @return
	 */
	@RequestMapping(value = "/report/{reportName}/{format}")
	public String showReport(@PathVariable String reportName,
			@PathVariable String format, Model mv) {
		// 显示格式：html、pdf、xls、csv
		mv.addAttribute(ReportView.DEFAULT_FORMAT_KEY, format);
		// 当为pdf、xls、csv时的附件名
		Date now = new Date();
		mv.addAttribute(ReportView.ATTACHEMT_FILE_NAME_KEY, reportName+"-"+new SimpleDateFormat("yyyyMMddHHmmss").format(now));
		mv.addAttribute("reportName", reportName);
		mv.addAttribute(ReportView.DEFAULT_FORMAT_KEY, format);
		List<Object> results = new ArrayList<Object>();
		try {
			reportExporter = (IReportExporter) context.getBean(reportName);
		} catch (Exception e) {
			reportExporter = null;
			throw new BusinessException("报表:"+reportName+"不存在");
		}
		if(reportExporter != null){
			results =reportExporter.getReportData().getDatas();
			mv.addAttribute(ReportView.EXPORTER_PARAMETERS_KEY,reportExporter.getReportData().getParams());
		}
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(results);
		mv.addAttribute(ReportView.JR_DATA_SOURCE_KEY, jrDataSource);
		return reportName;
	}
	
}
