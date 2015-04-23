package org.hbhk.aili.report.server.exporter.impl;

import java.io.OutputStream;

import net.sf.jasperreports.engine.JRException;

import org.hbhk.aili.report.server.exporter.IReportExporter;
import org.hbhk.aili.report.server.print.ReportPrint;

public class ReportXlsExporter implements IReportExporter {

	@Override
	public void export(ReportPrint reportPrint, OutputStream os)
			throws JRException {
		
	}

}
