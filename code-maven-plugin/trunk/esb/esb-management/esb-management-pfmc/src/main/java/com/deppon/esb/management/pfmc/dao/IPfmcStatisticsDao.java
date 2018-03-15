package com.deppon.esb.management.pfmc.dao;

import java.util.List;

import com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo;

/**
 * @Description 性能统计DAO
 * @author HuangHua
 * @date 2012-5-7下午8:15:01
 */
public interface IPfmcStatisticsDao {

	/**
	 * @Description 从性能日志表中收集数据到性能统计表中
	 * 
	 */
	@Deprecated
	public void collectData();

	/**
	 * @Description 查询达到阀值，却还没发出预警的性能统计信息
	 * @return
	 */
	public List<PfmcStatisticsInfo> queryPfmcStatistics2Notice();

	/**
	 * @Description 根据ID，把ISSEND的设置为1
	 * @param ids
	 * @return
	 */
	public int updatePfmcStatistics(List<String> ids);
	
	public PfmcStatisticsInfo getPfmcStatistic(String id);
}
