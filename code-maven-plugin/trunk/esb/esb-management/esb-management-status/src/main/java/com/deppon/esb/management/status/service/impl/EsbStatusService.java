/*
 * PROJECT NAME: esb-management-status
 * PACKAGE NAME: com.deppon.esb.management.status.service.impl
 * FILE    NAME: EsbStatusService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.status.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.status.dao.IEsbStatusDao;
import com.deppon.esb.management.status.domain.EsbRouteInfo;
import com.deppon.esb.management.status.domain.EsbStatusInfo;
import com.deppon.esb.management.status.service.IEsbStatusService;
import com.deppon.esb.management.status.view.StatusQueryBean;
import com.deppon.esb.management.status.view.StatusView;

/**
 * ESB状态处理服务接口实现
 * 
 * @author HuangHua
 * @date 2013-1-10 下午6:06:41
 */
@Transactional
@Service
public class EsbStatusService implements IEsbStatusService {

	@Resource
	private IEsbStatusDao esbStatusDao;

	/**
	 * 保存状态列表.
	 * 
	 * @author HuangHua
	 * @date 2013-1-10 下午6:06:53
	 * @see com.deppon.esb.management.status.service.IEsbStatusService#saveStatus(java.util.List)
	 */
	@Override
	public void saveStatus(List<EsbStatusInfo> esbStatusInfos) {
		esbStatusDao.saveStatus(esbStatusInfos);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-2-1 上午11:53:02
	 * @see com.deppon.esb.management.status.service.IEsbStatusService#markComplete()
	 */
	@Override
	@Deprecated
	public void markComplete() {
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-2-1 上午11:53:02
	 * @see com.deppon.esb.management.status.service.IEsbStatusService#getNotCompleteRecords(java.util.Date)
	 */
	@Override
	public List<EsbStatusInfo> getNotCompleteRecords(long time) {
		return esbStatusDao.getNotCompleteRecords(time);
	}

	
	/**
	 * 
	 * 查询状态日志列表
	 * @author qiancheng
	 * @param 
	 * @date 2013-4-19 下午4:03:58
	 * @return
	 */
	public List<StatusView> queryStatusView (StatusQueryBean bean){
		return esbStatusDao.queryStatusView(bean);
	}
	/**
	 * 
	 * 查询状态日志条数
	 * @author qiancheng
	 * @param 
	 * @date 2013-4-19 下午4:04:11
	 * @return
	 */
	public Integer queryStatusViewCount(StatusQueryBean bean){
		return esbStatusDao.queryStatusViewCount(bean);
	}
	
	public List<EsbStatusInfo> queryOriginalStatus(StatusQueryBean bean){
		return esbStatusDao.queryOriginalStatus(bean);
	}

	/** 
	 * 根据id修改状态
	 * @author HuangHua
	 * @date 2013-5-14 下午5:36:16
	 * @see com.deppon.esb.management.status.service.IEsbStatusService#updateStatusByIds(java.util.List)
	 */
	@Override
	public void updateStatusByIds(List<String> ids, int flag) {
		esbStatusDao.updateStatusByIds(ids,flag);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-15 上午9:42:32
	 * @see com.deppon.esb.management.status.service.IEsbStatusService#getMultiNotCompleteRecords(long)
	 */
	@Override
	public List<EsbStatusInfo> getMultiNotCompleteRecords(long time) {
		return esbStatusDao.getMultiNotCompleteRecords(time);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-15 上午11:52:56
	 * @see com.deppon.esb.management.status.service.IEsbStatusService#queryMultiRoute()
	 */
	@Override
	public List<EsbRouteInfo> queryMultiRoute() {
		return esbStatusDao.queryMultiRoute();
	}
	

}
