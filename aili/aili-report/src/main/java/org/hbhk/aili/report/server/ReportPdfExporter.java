package org.hbhk.aili.report.server;

import java.io.OutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ReportPdfExporter implements IReportExporter{

	@Override
	public void export(ReportPrint reportPrint, OutputStream os)
			throws JRException {
		JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportPrint.getJasperPrint());
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
        exporter.exportReport();
	}

}
