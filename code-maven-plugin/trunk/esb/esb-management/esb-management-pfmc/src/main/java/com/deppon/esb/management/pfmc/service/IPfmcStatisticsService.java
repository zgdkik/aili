package com.deppon.esb.management.pfmc.service;

import java.util.List;

import com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo;



/**
 * @Description 性能统计Service
 * @author HuangHua
 * @date 2012-5-7下午9:57:00
 */
public interface IPfmcStatisticsService {

	/**
	 * 收集异常统计数据
	 */
	@Deprecated
	void collectData();

	void updatePfmcStatistics(List<String> ids);

	List<PfmcStatisticsInfo> queryPfmcStatistics2Notice();
}
