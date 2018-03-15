package com.deppon.esb.management.pfmc.dao;

import java.util.List;

import com.deppon.esb.management.pfmc.domain.PerformanceInfo;

/**
 * 性能日志信息DAO接口.
 * 
 * @author HuangHua
 * @date 2012-03-08 15:39:22
 */
public interface IPerformanceDao {
	
	/**
	 * Query performance list.根据服务名和服务提供者查询性能信息列表
	 * 
	 * @param svcName
	 *            服务名
	 * @param svcProvider
	 *            服务提供者
	 * @return List<PerformanceInfo>
	 */
	public List<PerformanceInfo> queryPerformanceList(String svcName, String svcProvider);

	/**
	 * Save performance.
	 * 
	 * @param performanceInfo
	 *            the performance info
	 */
	public void savePerformance(PerformanceInfo performanceInfo);
	
	/**
	 * 保存性能信息列表.
	 * 
	 * @param list
	 *            性能信息LIST
	 */
	public void storePerformanceInfoList(List<PerformanceInfo> list);
	
}
