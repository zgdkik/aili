/*
 * PROJECT NAME: esb-management-alert
 * PACKAGE NAME: com.deppon.esb.management.alert.service
 * FILE    NAME: IExcptService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.alert.service;

import java.util.List;

import com.deppon.esb.management.alert.domain.NoticeInfo;
import com.deppon.esb.management.excptn.domain.EsbExceptionInfo;

/**
 * 
 * @author HuangHua
 * @date 2013-5-18 上午9:32:30
 */
public interface IExcptService {

	/**
	 * 发送异常告警
	 * 
	 * @author HuangHua
	 * @date 2013-5-18 上午9:32:39
	 */
	void sendExcptnWarning();

	/**
	 * 发送单个异常告警
	 * @author HuangHua
	 * @date 2013-5-18 上午11:39:01
	 */
	List<NoticeInfo> sendExcptnWarningOne(EsbExceptionInfo esbExceptionInfo);

	/**
	 *
	 * @author HuangHua
	 * @date 2013-5-18 上午11:59:25
	 */
	String[] getExcptn2WarningTargets(String serviceCode, String channelEmail);

}
