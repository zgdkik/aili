package org.hbhk.aili.report.server.exporter.impl;

import org.hbhk.aili.report.server.exporter.IReportExporter;
import org.hbhk.aili.report.share.model.ReportData;

public class ReportExporter implements IReportExporter{

	@Override
	public ReportData getReportData() {
		ReportData data = new ReportData();
		return data;
	}
}
