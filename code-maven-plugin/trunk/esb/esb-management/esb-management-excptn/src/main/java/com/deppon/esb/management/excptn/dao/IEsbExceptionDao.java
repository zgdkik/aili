/*
 * PROJECT NAME: esb-management-excptn
 * PACKAGE NAME: com.deppon.esb.management.excptn.dao
 * FILE    NAME: IEsbExceptionDao.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.excptn.dao;

import java.util.List;

import com.deppon.esb.management.excptn.domain.EsbExceptionInfo;

/**
 * ESB二期异常处理DAO接口
 * 
 * @author HuangHua
 * @date 2013-1-11 上午10:53:40
 */
public interface IEsbExceptionDao {

	/**
	 * 保存公共异常对象.
	 * 
	 * @param exceptionInfo
	 *            the exception info
	 * @return the int 执行条数
	 * @author HuangHua
	 * @date 2013-1-11 上午10:56:47
	 */
	int saveEsbException(EsbExceptionInfo exceptionInfo);

	/**
	 * 查询所有没有发送告警的系统异常
	 * 
	 * @author HuangHua
	 * @date 2013-5-18 上午10:17:44
	 */
	List<EsbExceptionInfo> querySysExcption();

	/**
	 * 根据FID设置异常的标志字段
	 * 
	 * @author HuangHua
	 * @date 2013-5-18 上午10:50:17
	 */
	int updateExceptionFlagByIds(List<String> ids, int flag);

}
