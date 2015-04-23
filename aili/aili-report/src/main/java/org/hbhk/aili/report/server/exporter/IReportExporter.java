package org.hbhk.aili.report.server.exporter;

import java.io.OutputStream;

import net.sf.jasperreports.engine.JRException;

import org.hbhk.aili.report.server.print.ReportPrint;

public interface IReportExporter {
	 void export(ReportPrint reportPrint, OutputStream os) throws JRException;
}
