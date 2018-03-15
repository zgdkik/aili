/*
 * PROJECT NAME: esb-management-audit
 * PACKAGE NAME: com.deppon.esb.management.audit.service
 * FILE    NAME: IEsbAuditService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.management.audit.service;

import java.util.List;

import com.deppon.esb.management.audit.domain.EsbAuditInfo;
import com.deppon.esb.management.audit.view.EsbAuditInfoQueryBean;
import com.deppon.esb.management.audit.view.EsbAuditInfoView;

/**
 * 审计日志处理service接口
 * @author HuangHua
 * @date 2013-1-10 下午4:34:06
 */
public interface IEsbAuditService {

	/**
	 * 保存审计日志.
	 * 
	 * @param esbAuditInfo
	 *            the esb audit info
	 * @return the int
	 * @author HuangHua
	 * @date 2013-1-10 下午4:34:40
	 */
	int saveAudit(EsbAuditInfo esbAuditInfo);
	
	int saveAudit(List<EsbAuditInfo> list);
	
	/**
	 * 
	 * 查询审计日志列表信息
	 * @author qiancheng
	 * @param 
	 * @date 2013-2-27 下午4:15:54
	 * @return
	 */
	public List<EsbAuditInfoView> queryEsbAuditLogList(EsbAuditInfoQueryBean queryBean);
	
	/**
	 * 
	 * 查询审计日志消息
	 * @author qiancheng
	 * @param 
	 * @date 2013-2-27 下午4:17:08
	 * @return
	 */
	public String queryAuditLogBody(String fid);
	
	/**
	 * 
	 * 查询条件过滤后的记录总数
	 * @author qiancheng
	 * @param 
	 * @date 2013-2-28 下午3:01:29
	 * @return
	 */
	public Integer queryAuditLogCount(EsbAuditInfoQueryBean queryBean);
	
}