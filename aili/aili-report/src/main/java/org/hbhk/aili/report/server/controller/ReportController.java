package org.hbhk.aili.report.server.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hbhk.aili.report.server.exporter.IReportExporter;
import org.hbhk.aili.report.server.view.ReportView;
import org.hbhk.aili.report.share.model.DailyZoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

	@Autowired(required = false)
	private IReportExporter reportExporter;

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
	public ModelAndView showReport(@PathVariable String reportName,
			@PathVariable String format, ModelAndView mv) {
		// 要调用的jasperreports的模板文件名(不包括后缀)，该文件名必须以-report结尾
		mv.setViewName(reportName);
		// 显示格式：html、pdf、xls、csv
		mv.addObject(ReportView.DEFAULT_FORMAT_KEY, format);
		// 当为pdf、xls、csv时的附件名
		Date now = new Date();

		mv.addObject(ReportView.ATTACHEMT_FILE_NAME_KEY, new SimpleDateFormat(
				"yyyyMMddHHmmss").format(now));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("reportName", reportName);
		params.put("format", format);
		mv.addAllObjects(params);
		List<DailyZoom> results = new ArrayList<DailyZoom>();
		for (int i = 0; i < 10; i++) {
			results.add(new DailyZoom("hbhk" + i, i));
		}
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(results);
		mv.addObject(ReportView.JR_DATA_SOURCE, jrDataSource);
		return mv;
	}

}
