/*
 * PROJECT NAME: esb-management-audit
 * PACKAGE NAME: com.deppon.esb.management.audit.service.impl
 * FILE    NAME: EsbAuditService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.management.audit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.audit.dao.IEsbAuditDao;
import com.deppon.esb.management.audit.domain.EsbAuditInfo;
import com.deppon.esb.management.audit.service.IEsbAuditService;
import com.deppon.esb.management.audit.view.EsbAuditInfoQueryBean;
import com.deppon.esb.management.audit.view.EsbAuditInfoView;
import com.deppon.esb.management.common.util.EsbHeaderUtil;

/**
 *
 * @author HuangHua
 * @date 2013-1-10 下午4:35:23
 */
@Transactional
@Service
public class EsbAuditService implements IEsbAuditService {
	
	@Resource
	private IEsbAuditDao esbAuditDao;

	/** 
	 * 保存审计日志.
	 * @author HuangHua
	 * @date 2013-1-10 下午4:35:23
	 * @see com.deppon.esb.management.audit.service.IEsbAuditService#saveAudit(com.deppon.esb.management.audit.domain.EsbAuditInfo)
	 */
	@Override
	public int saveAudit(EsbAuditInfo esbAuditInfo) {
		
		EsbHeaderUtil.truncEsbHeader(esbAuditInfo.getEsbHeader());
		
		return esbAuditDao.saveAudit(esbAuditInfo);
	}

	public int saveAudit(List<EsbAuditInfo> list){
		
		for (EsbAuditInfo esbAuditInfo : list) {
			EsbHeaderUtil.truncEsbHeader(esbAuditInfo.getEsbHeader());
		}
		
		return esbAuditDao.saveAudit(list);
	}
	/**
	 * 
	 * 查询审计日志列表信息
	 * @author qiancheng
	 * @param 
	 * @date 2013-2-27 下午4:15:54
	 * @return
	 */
	@Override
	public List<EsbAuditInfoView> queryEsbAuditLogList(
			EsbAuditInfoQueryBean queryBean) {
		return esbAuditDao.queryEsbAuditLogList(queryBean);
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
		return esbAuditDao.queryAuditLogBody(fid);
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
		return esbAuditDao.queryAuditLogCount(queryBean);
	}
	
}
