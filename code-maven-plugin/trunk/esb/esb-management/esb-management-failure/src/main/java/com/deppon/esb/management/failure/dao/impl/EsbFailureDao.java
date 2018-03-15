/*
 * PROJECT NAME: esb-management-Failure
 * PACKAGE NAME: com.deppon.esb.management.Failure.dao.impl
 * FILE    NAME: EsbFailureDao.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.management.failure.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.failure.dao.IEsbFailureDao;
import com.deppon.esb.management.failure.domain.EsbFailureInfo;
import com.deppon.esb.management.failure.view.EsbFailureInfoQueryBean;
import com.deppon.esb.management.failure.view.EsbFailureInfoView;

/**
 * 失败处理DAO实现
 */
@Repository
public class EsbFailureDao extends IBatis3DaoImpl implements IEsbFailureDao{

	/**
	 * 保存失败日志.
	 */
	@Override
	public int saveFailure(EsbFailureInfo esbFailureInfo) {
		return getSqlSession().insert("com.deppon.esb.management.failure.domain.EsbFailureInfo.saveFailure", esbFailureInfo);
	}

	/**
	 * 按条件查询失败日志
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EsbFailureInfoView> queryEsbFailureLogList(
			EsbFailureInfoQueryBean queryBean) {
		RowBounds rowBounds = new RowBounds(queryBean.getStart(), queryBean.getLimit());
		return getSqlSession().selectList("com.deppon.esb.management.failure.domain.EsbFailureInfo.queryFailureLogList", queryBean, rowBounds);
	}

	/**
	 * 查询详细失败信息
	 */
	@Override
	public String queryFailureLogBody(String fid) {
		return (String)getSqlSession().selectOne("com.deppon.esb.management.failure.domain.EsbFailureInfo.queryFailureLogBody", fid);
	}
	
	/**
	 * 查询总记录数 分页使用
	 */
	public Integer queryFailureLogCount(EsbFailureInfoQueryBean queryBean){
		return (Integer)getSqlSession().selectOne("com.deppon.esb.management.failure.domain.EsbFailureInfo.queryFailureLogCount", queryBean);
	}

	/** 
	 * 保存失败日志.
	 * @author HuangHua
	 * @date 2013-6-3 下午4:58:53
	 * @see com.deppon.esb.management.failure.dao.IEsbFailureDao#saveFailure(java.util.List)
	 */
	@Override
	public int saveFailure(List<EsbFailureInfo> esbFailureList) {
		int count =0;
		for(EsbFailureInfo info :esbFailureList){
			int i = getSqlSession().insert("com.deppon.esb.management.failure.domain.EsbFailureInfo.saveFailure", info);
			count = count + i;
		}
		return count;
	}
}
