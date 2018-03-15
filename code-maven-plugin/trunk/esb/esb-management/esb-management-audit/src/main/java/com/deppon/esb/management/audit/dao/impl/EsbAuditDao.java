/*
 * PROJECT NAME: esb-management-audit
 * PACKAGE NAME: com.deppon.esb.management.audit.dao.impl
 * FILE    NAME: EsbAuditDao.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.management.audit.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.audit.dao.IEsbAuditDao;
import com.deppon.esb.management.audit.domain.EsbAuditInfo;
import com.deppon.esb.management.audit.view.EsbAuditInfoQueryBean;
import com.deppon.esb.management.audit.view.EsbAuditInfoView;
import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;

/**
 * 审计日志处理DAO实现
 * @author HuangHua
 * @date 2013-1-10 上午11:15:07
 */
@Repository
public class EsbAuditDao extends IBatis3DaoImpl implements IEsbAuditDao{

	/**
	 * 保存审计日志.
	 * 
	 * @param esbAuditInfo
	 *            the esb audit info
	 * @return the int 执行条数
	 * @author HuangHua
	 * @date 2013-1-10 上午11:15:20
	 * @see com.deppon.esb.management.audit.dao.IEsbAuditDao#saveAudit(com.deppon.esb.management.audit.domain.EsbAuditInfo)
	 */
	@Override
	public int saveAudit(EsbAuditInfo esbAuditInfo) {
		return getSqlSession().insert("com.deppon.esb.management.audit.domain.EsbAuditInfo.saveAudit", esbAuditInfo);
	}
	
	public int saveAudit(List<EsbAuditInfo> list){
		int count =0;
		for(EsbAuditInfo info :list){
			int i = getSqlSession().insert("com.deppon.esb.management.audit.domain.EsbAuditInfo.saveAudit", info);
			count = count+i;
		}
		return count;
	}

	/**
	 * 
	 * 查询审计日志列表信息
	 * @author qiancheng
	 * @param 
	 * @date 2013-2-27 下午4:15:54
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EsbAuditInfoView> queryEsbAuditLogList(
			EsbAuditInfoQueryBean queryBean) {
		RowBounds rowBounds = new RowBounds(queryBean.getStart(), queryBean.getLimit());
		return getSqlSession().selectList("com.deppon.esb.management.audit.domain.EsbAuditInfo.queryAuditLogList", queryBean, rowBounds);
	}

	/**
	 * 
	 * 查询审计日志消息
	 * @author qiancheng
	 * @param 
	 * @date 2013-2-27 下午4:17:08
	 * @return
	 */
	@Override
	public String queryAuditLogBody(String fid) {
		return (String)getSqlSession().selectOne("com.deppon.esb.management.audit.domain.EsbAuditInfo.queryAuditLogBody", fid);
	}
	
	/**
	 * 
	 * 查询条件过滤后的记录总数
	 * @author qiancheng
	 * @param 
	 * @date 2013-2-28 下午3:01:29
	 * @return
	 */
	public Integer queryAuditLogCount(EsbAuditInfoQueryBean queryBean){
		return (Integer)getSqlSession().selectOne("com.deppon.esb.management.audit.domain.EsbAuditInfo.queryAuditLogCount", queryBean);
	}
}
