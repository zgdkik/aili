package com.deppon.esb.management.excptn.dao;

import java.util.List;

import com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo;

/**
 * @Description 异常数据统计Dao接口
 * @author HuangHua
 * @date 2012-5-8上午11:44:35
 */
public interface IExcptnStatisticsDao {

	/**
	 * @Description 从异常日志表中收集数据到异常统计表中(这个方法的功能已经用数据库的jobs来运行了)
	 * 
	 */
	@Deprecated
	public void collectData();

	/**
	 * @Description 从数据库中查询所有没有发送的异常统计信息
	 * @return
	 */
	public List<ExcptnStatisticsInfo> queryExcptnStatistics();
	
	public int markProcessed(List<String> ids);

	public ExcptnStatisticsInfo getExceptionStatistic(String id);
}
