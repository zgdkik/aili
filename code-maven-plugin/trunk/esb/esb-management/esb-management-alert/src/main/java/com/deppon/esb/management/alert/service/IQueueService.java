package com.deppon.esb.management.alert.service;

import java.util.List;
import java.util.Map;

import com.deppon.esb.management.mq.domain.LocalQueueBean;

public interface IQueueService {

	/**
	 * 深度告警
	 * 
	 * @author HuangHua
	 * @date 2013-5-4 下午5:57:06
	 */
	@Deprecated
	public void sendDepthWarning() throws Exception;

	/**
	 * 连接告警
	 * 
	 * @author HuangHua
	 * @date 2013-5-4 下午5:57:06
	 */
	public void sendConnWarning() throws Exception;

	/**
	 * 
	 * key--qmgrName value--待发送的信息
	 * 
	 * @author HuangHua
	 * @date 2013-5-4 下午5:55:18
	 */
	@Deprecated
	List<Map<String, Object>> filterOverThreshold(
			Map<String, List<LocalQueueBean>> maps);
}
