/*
 * PROJECT NAME: esb-management-status
 * PACKAGE NAME: com.deppon.esb.management.status.dao.impl
 * FILE    NAME: EsbStatusDao.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.management.status.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.status.dao.IEsbStatusDao;
import com.deppon.esb.management.status.domain.EsbRouteInfo;
import com.deppon.esb.management.status.domain.EsbStatusInfo;
import com.deppon.esb.management.status.view.StatusQueryBean;
import com.deppon.esb.management.status.view.StatusView;

/**
 * ESB状态处理DAO实现
 * @author HuangHua
 * @date 2013-1-10 上午11:15:42
 */
@Repository
public class EsbStatusDao extends IBatis3DaoImpl implements IEsbStatusDao {

	/** 
	 * 保存状态列表
	 * @author HuangHua
	 * @date 2013-1-10 上午11:15:50
	 * @see com.deppon.esb.management.status.dao.IEsbStatusDao#saveStatus(java.util.List)
	 */
	@Override
	public void saveStatus(List<EsbStatusInfo> esbStatusInfos) {
		
		for (EsbStatusInfo esbStatusInfo : esbStatusInfos) {
			getSqlSession().insert("com.deppon.esb.management.status.domain.EsbStatusInfo.saveStatus", esbStatusInfo);
		}
	}

	/**
	 * 获取 not complete records.
	 * 
	 * @param mi
	 *            分钟数
	 * @return the not complete records
	 * @author HuangHua
	 * @date 2013-2-1 下午1:53:34
	 * @see com.deppon.esb.management.status.dao.IEsbStatusDao#getNotCompleteRecords(java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EsbStatusInfo> getNotCompleteRecords(long mi) {
		return getSqlSession().selectList("com.deppon.esb.management.status.domain.EsbStatusInfo.getNotCompleteRecords", mi);
	}
	
	
	/**
	 * 
	 * 查询状态日志列表
	 * @author qiancheng
	 * @param 
	 * @date 2013-4-19 下午4:03:58
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StatusView> queryStatusView (StatusQueryBean bean){
		RowBounds rowBound = new RowBounds(bean.getStart(),bean.getLimit());
		return getSqlSession().selectList("com.deppon.esb.management.status.domain.EsbStatusInfo.queryStatusList", bean, rowBound);
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
		return (Integer)getSqlSession().selectOne("com.deppon.esb.management.status.domain.EsbStatusInfo.queryStatusListCount", bean);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<EsbStatusInfo> queryOriginalStatus(StatusQueryBean bean){
		RowBounds rowBound = new RowBounds(bean.getStart(),bean.getLimit());
		return getSqlSession().selectList("com.deppon.esb.management.status.domain.EsbStatusInfo.queryOriginalStatus", bean, rowBound);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-15 上午9:48:18
	 * @see com.deppon.esb.management.status.dao.IEsbStatusDao#getMultiNotCompleteRecords(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EsbStatusInfo> getMultiNotCompleteRecords(long time) {
		return getSqlSession().selectList("com.deppon.esb.management.status.domain.EsbStatusInfo.getMultiNotCompleteRecords", time);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-15 上午11:53:55
	 * @see com.deppon.esb.management.status.dao.IEsbStatusDao#queryMultiRoute()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EsbRouteInfo> queryMultiRoute() {
		return getSqlSession().selectList("com.deppon.esb.management.status.domain.EsbRouteInfo.queryMultiRoute");
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-15 下午6:16:15
	 * @see com.deppon.esb.management.status.dao.IEsbStatusDao#updateStatusByIds(java.util.List)
	 */
	@Override
	public int updateStatusByIds(List<String> ids, int flag) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		int count = 0;
		while(count < ids.size()){
			int index = count;
			count = count + 100;
			if(count > ids.size()){
				count = ids.size();
			}
			List<String> list = ids.subList(index, count);
			map.put("ids", list);
			getSqlSession().update("com.deppon.esb.management.status.domain.EsbStatusInfo.updateStatusByIds", map);
		}
		return ids.size();
	}

}
