/*
 * PROJECT NAME: esb-management-excptn
 * PACKAGE NAME: com.deppon.esb.management.excptn.service
 * FILE    NAME: IEsbExceptionService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.excptn.service;

import java.util.List;

import com.deppon.esb.management.excptn.domain.EsbExceptionInfo;

/**
 * ESB二期异常处理Service接口.
 * 
 * @author HuangHua
 * @date 2013-1-11 上午10:58:57
 */
public interface IEsbExceptionService {

	/**
	 * 保存公共异常对象.
	 * 
	 * @param exceptionInfo
	 *            the exception info
	 * @return the int 执行条数
	 * @author HuangHua
	 * @date 2013-1-11 上午10:59:30
	 */
	int saveEsbException(EsbExceptionInfo exceptionInfo);

	/**
	 * 查询所有没有发送告警的系统异常
	 * 
	 * @author HuangHua
	 * @date 2013-5-18 上午10:16:40
	 */
	List<EsbExceptionInfo> querySysExcption();

	/**
	 *根据FID设置异常的标志字段
	 * @author HuangHua
	 * @date 2013-5-18 上午11:43:39
	 */
	int updateExceptionFlagByIds(List<String> ids, int flag);

}
