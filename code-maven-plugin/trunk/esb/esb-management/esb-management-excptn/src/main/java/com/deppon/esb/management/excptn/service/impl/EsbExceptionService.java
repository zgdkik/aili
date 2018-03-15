/*
 * PROJECT NAME: esb-management-excptn
 * PACKAGE NAME: com.deppon.esb.management.excptn.service.impl
 * FILE    NAME: EsbExceptionService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.excptn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.excptn.dao.IEsbExceptionDao;
import com.deppon.esb.management.excptn.domain.EsbExceptionInfo;
import com.deppon.esb.management.excptn.service.IEsbExceptionService;

/**
 * ESB二期异常处理Service接口实现
 * 
 * @author HuangHua
 * @date 2013-1-11 上午11:00:06
 */
@Transactional
@Service
public class EsbExceptionService implements IEsbExceptionService {
	
	@Resource
	private IEsbExceptionDao esbExceptionDao;

	/**
	 * 保存公共异常对象.
	 * 
	 * @param exceptionInfo
	 *            the exception info
	 * @return the int 执行条数
	 * @author HuangHua
	 * @date 2013-1-11 上午11:00:14
	 * @see com.deppon.esb.management.excptn.service.IEsbExceptionService#saveEsbException(com.deppon.esb.management.excptn.domain.EsbExceptionInfo)
	 */
	@Override
	public int saveEsbException(EsbExceptionInfo exceptionInfo) {
		return esbExceptionDao.saveEsbException(exceptionInfo);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-18 上午10:17:25
	 * @see com.deppon.esb.management.excptn.service.IEsbExceptionService#querySysExcption()
	 */
	@Override
	public List<EsbExceptionInfo> querySysExcption() {
		return esbExceptionDao.querySysExcption();
	}
	
	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-18 上午11:44:14
	 * @see com.deppon.esb.management.excptn.service.IEsbExceptionService#updateExceptionFlagByIds(java.util.List, int)
	 */
	@Override
	public int updateExceptionFlagByIds(List<String> ids, int flag) {
		return esbExceptionDao.updateExceptionFlagByIds(ids, flag);
	}

}
