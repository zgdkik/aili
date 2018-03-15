/*
 * PROJECT NAME: esb-management-status
 * PACKAGE NAME: com.deppon.esb.management.status.dao
 * FILE    NAME: IEsbStatusDao.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.management.status.dao;

import java.util.List;

import com.deppon.esb.management.status.domain.EsbRouteInfo;
import com.deppon.esb.management.status.domain.EsbStatusInfo;
import com.deppon.esb.management.status.view.StatusQueryBean;
import com.deppon.esb.management.status.view.StatusView;

/**
 * ESB状态处理DAO接口
 * @author HuangHua
 * @date 2013-1-10 上午10:36:36
 */
public interface IEsbStatusDao {
	
	/**
	 * 保存状态列表.
	 * 
	 * @param esbStatusInfo
	 *            the esb status info
	 * @return the int 执行条数
	 * @author HuangHua
	 * @date 2013-1-10 上午11:00:34
	 */
	void saveStatus(List<EsbStatusInfo> esbStatusInfo);

	/**
	 *
	 * @author HuangHua
	 * @date 2013-2-1 下午1:53:24
	 */
	List<EsbStatusInfo> getNotCompleteRecords(long mi);
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
	 * 获取在某个时间前有部分999情况的状态.
	 * @author HuangHua
	 * @date 2013-5-15 上午9:47:43
	 */
	List<EsbStatusInfo> getMultiNotCompleteRecords(long time);

	/**
	 * 查询所有的分发路由(临时写在这个模块)
	 * @author HuangHua
	 * @date 2013-5-15 上午11:53:30
	 */
	List<EsbRouteInfo> queryMultiRoute();

	/**
	 * 根据id修改状态
	 * @author HuangHua
	 * @param flag 
	 * @return 
	 * @date 2013-5-15 下午6:16:05
	 */
	int updateStatusByIds(List<String> ids, int flag);

}
