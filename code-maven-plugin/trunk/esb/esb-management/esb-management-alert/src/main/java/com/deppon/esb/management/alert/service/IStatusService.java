/*
 * PROJECT NAME: esb-management-alert
 * PACKAGE NAME: com.deppon.esb.management.alert.service
 * FILE    NAME: IStatusService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.alert.service;

import java.util.List;

import com.deppon.esb.management.alert.domain.NoticeInfo;
import com.deppon.esb.management.status.domain.EsbStatusInfo;

/**
 * 
 * @author HuangHua
 * @date 2013-5-11 下午4:29:44
 */
public interface IStatusService {
	/**
	 * Send status no complete warn.
	 * 
	 * @param statusInfo
	 *            ESB状态实体
	 * 
	 * @author HuangHua
	 * @date 2013-5-11 下午4:33:54
	 */
	List<NoticeInfo> sendStatusNoCompleteWarnOne(EsbStatusInfo statusInfo);

	/**
	 *
	 * @author HuangHua
	 * @date 2013-5-11 下午4:38:13
	 */
	String[] getStatusNoCompleteWarningTargets(String serviceCode, String channelEmail);

	/**
	 * Send status no complete warn.
	 * 
	 * @author HuangHua
	 * @date 2013-5-13 上午10:23:07
	 */
	void sendStatusNoCompleteWarn();

	/**
	 *
	 * @author HuangHua
	 * @date 2013-5-15 下午4:54:03
	 */
	void sendMultiStatusNoCompleteWarn();

}
