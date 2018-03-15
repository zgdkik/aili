package com.deppon.esb.management.alert.service;

import com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo;
import com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo;

public interface ISendWarningService {

	/**
	 * 【DP_ESB异常预警通知】
	 * 从[${startTime}]到[${endTime}]之间，服务编码[${svcCode}]发生异常次数[${excptnCount}]次，
	 * 请注意查看ESB日志！
	 * @author HuangHua
	 * @date 2012-12-31 下午4:12:41
	 */
	void sendExcptnStatistics();

	void sendExcptnStatisticsOne(ExcptnStatisticsInfo excptnStatisticsInfo);

	/**
	 * /**
	 * 【DP_ESB性能预警通知】 ESB服务名称:${svcName}，ESB服务编码：${svcCode}。
	 * 在${startTime}到${endTime}间的平均响应时间为:${avgTime}， 已超过阀值:${threshold}。
	 * 最大响应时间为：${maxrespTime}。 请注意查看ESB日志！
	 * @author HuangHua
	 * @date 2012-12-31 上午10:40:34
	 */
	boolean sendPerformanceWarning();

	void sendPerformanceWarningOne(PfmcStatisticsInfo pfmcStatisticsInfo);

}