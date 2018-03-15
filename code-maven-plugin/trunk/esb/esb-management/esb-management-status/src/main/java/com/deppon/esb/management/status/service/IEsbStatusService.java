/*
 * PROJECT NAME: esb-management-status
 * PACKAGE NAME: com.deppon.esb.management.status.service
 * FILE    NAME: IEsbStatusService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.status.service;

import java.util.List;

import com.deppon.esb.management.status.domain.EsbRouteInfo;
import com.deppon.esb.management.status.domain.EsbStatusInfo;
import com.deppon.esb.management.status.view.StatusQueryBean;
import com.deppon.esb.management.status.view.StatusView;

/**
 * ESB状态处理服务接口.
 * 
 * @author HuangHua
 * @date 2013-1-10 下午6:04:59
 */
public interface IEsbStatusService {

	/**
	 * 保存状态列表.
	 * 
	 * @param esbStatusInfo
	 *            the esb status info
	 * @return the int
	 */
	void saveStatus(List<EsbStatusInfo> esbStatusInfo);
	
	/**
	 * 标记所有已完成的状态日志记录.
	 * 已废弃,改由数据库存储过程实现.
	 * 
	 * @author HuangHua
	 * @date 2013-2-1 上午9:17:09
	 */
	@Deprecated
	void markComplete();
	
	/**
	 * 获取在某个时间前所有没有完成的状态.
	 * 
	 * @param time
	 *            分钟数
	 * @return the all note complete
	 * @author HuangHua
	 * @date 2013-2-1 上午9:17:03
	 */
	List<EsbStatusInfo> getNotCompleteRecords(long time);
	
	/**
	 * 获取在某个时间前有部分999情况的状态.
	 * 
	 * @param time
	 *            分钟数
	 * @return the all note complete
	 * @author HuangHua
	 * @date 2013-2-1 上午9:17:03
	 */
	List<EsbStatusInfo> getMultiNotCompleteRecords(long time);
	
	/**
	 * 
	 * 查询状态日志列表
	 * @author qiancheng
	 * @param 
	 * @date 2013-4-19 下午4:03:58
	 * @return
	 */
	public List<StatusView> queryStatusView (StatusQueryBean bean);
	/**
	 * 
	 * 查询状态日志条数
	 * @author qiancheng
	 * @param 
	 * @date 2013-4-19 下午4:04:11
	 * @return
	 */
	public Integer queryStatusViewCount(StatusQueryBean bean);
	
	public List<EsbStatusInfo> queryOriginalStatus(StatusQueryBean bean);

	/**
	 * 通过id更新Status flag=2
	 * @author HuangHua
	 * @param flag 
	 * @date 2013-5-14 下午5:35:52
	 */
	void updateStatusByIds(List<String> ids, int flag);
	
	/**
	 * 查询所有的分发路由(临时写在这个模块)
	 * @author HuangHua
	 * @date 2013-5-15 上午11:52:19
	 */
	List<EsbRouteInfo> queryMultiRoute();

}
