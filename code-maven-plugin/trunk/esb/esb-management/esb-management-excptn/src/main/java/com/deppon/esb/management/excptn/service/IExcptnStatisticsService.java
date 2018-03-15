package com.deppon.esb.management.excptn.service;

import java.util.List;

import com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo;


/**
 * @Description 异常统计Service
 * @author HuangHua
 * @date 2012-5-7下午9:57:33
 */
public interface IExcptnStatisticsService {

	/**
	 * 收集异常统计数据
	 */
	@Deprecated
	void collectData();

	void markProcessed(List<String> ids);

	List<ExcptnStatisticsInfo> queryExcptnStatistics();
}
