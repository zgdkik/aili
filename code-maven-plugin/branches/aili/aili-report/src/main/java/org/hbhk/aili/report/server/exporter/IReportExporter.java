package org.hbhk.aili.report.server.exporter;

import org.hbhk.aili.report.share.model.ReportData;

public interface IReportExporter {
	/**
	 * 
	* @author 何波
	* @Description: 返回列表数据和参数设置
	* @return   
	* ReportData   
	* @throws
	 */
	ReportData getReportData();
}
