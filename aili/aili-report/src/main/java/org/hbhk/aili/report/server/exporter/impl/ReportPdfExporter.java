package org.hbhk.aili.report.server.exporter.impl;

import java.io.OutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;

import org.hbhk.aili.report.server.exporter.IReportExporter;
import org.hbhk.aili.report.server.print.ReportPrint;

public class ReportPdfExporter implements IReportExporter {

	@Override
	public void export(ReportPrint reportPrint, OutputStream outputStream)
			throws JRException {
		JasperExportManager.exportReportToPdfStream(reportPrint.getJasperPrint(), outputStream);
	}

}
