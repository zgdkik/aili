package org.hbhk.aili.report.server;

import java.io.OutputStream;

import net.sf.jasperreports.engine.JRException;

public interface IReportExporter {
	 void export(ReportPrint reportPrint, OutputStream os) throws JRException;
}
