package org.hbhk.aili.report.server.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.report.share.ex.ReportException;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

public class ReportView extends JasperReportsMultiFormatView {
	private static final Log logger = LogFactory.getLog(ReportView.class);

	public static final String ATTACHEMT_FILE_NAME_KEY = "attachmentFileName"; // 格式不为html时的下载文件名

	public static final String JR_DATA_SOURCE_KEY = "jrDataSource";
	
	public static final String EXPORTER_PARAMETERS_KEY = "exporter_parameters_key";
	
	@SuppressWarnings("unchecked")
	@Override
	protected void renderReport(JasperPrint populatedReport,
			Map<String, Object> model, HttpServletResponse response)
			throws Exception {
		//设置列表数据
		setReportDataKey(JR_DATA_SOURCE_KEY);
		//设置参数
		Map<String, Object> parameters = (Map<String,Object>) model.get(EXPORTER_PARAMETERS_KEY);
		if(parameters == null){
			parameters = new HashMap<String, Object>();
		}
		parameters.putAll((Map<? extends String, ? extends Object>) getExporterParameters());
		setExporterParameters(parameters);
		Object format = model.get(DEFAULT_FORMAT_KEY);
		if (format == null) {
			logger.error("model中未找到指定的输出格式(format:html、pdf、xls、csv)");
			throw new ReportException("model中未找到指定的输出格式(format:html、pdf、xls、csv)");
		}
		if (!format.equals("html")) {
			Object attachmentFileName = model.get(ATTACHEMT_FILE_NAME_KEY);
			if (attachmentFileName == null) {
				throw new ReportException("model中未指定输出文件名(attachmentFileName)");
			}
			Properties contentDispositionMappings = getContentDispositionMappings();
			contentDispositionMappings.put(format.toString(), "attachment; filename="+ attachmentFileName + "." + format);
		}
		super.renderReport(populatedReport, model, response);
	}

}
