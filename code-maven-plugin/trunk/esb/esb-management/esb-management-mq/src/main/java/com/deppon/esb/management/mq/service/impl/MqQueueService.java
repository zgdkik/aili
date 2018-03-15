/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.service.impl
 * FILE    NAME: QueueService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.deppon.esb.management.mq.domain.LocalQueueBean;
import com.deppon.esb.management.mq.pcf.QueueUtil;
import com.deppon.esb.management.mq.service.IMqQueueService;

/**
 * 队列服务实现.
 * 
 * @author HuangHua
 * @date 2013-1-25 下午2:32:36
 */
@Service
public class MqQueueService implements IMqQueueService {

	/**
	 * List local queue.
	 * 
	 * @param queueNameReg
	 *            队列名,可以用"QU*"来表示所有以QU开头命名的队列.
	 *            数组大小必须和hostname,port,channel的数组大小相同.
	 * @return the Map<String,List<LocalQueueBean>>
	 *         如果hostname,port,channel,queueNameReg的数组大小不一致,直接返回null.
	 *         key--value:队列管理器名--List<LocalQueueBean>
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2013-1-25 下午2:32:48
	 * @see com.deppon.esb.management.mq.service.IMqQueueService#listLocalQueue()
	 */
	public Map<String, List<LocalQueueBean>> listLocalQueues(String[] hostname,
			int[] port, String[] channel, String[] queueNameReg) {
		if (hostname.length != port.length || hostname.length != channel.length
				|| hostname.length != queueNameReg.length) {
			return null;
		}
		Map<String, List<LocalQueueBean>> map = new HashMap<String, List<LocalQueueBean>>();
		for (int i = 0; i < queueNameReg.length; i++) {
			String queueName = queueNameReg[i];
			String hostToUse = hostname[i];
			int portToUse = port[i];
			String channelToUse = channel[i];
			List<LocalQueueBean> queueBeans = QueueUtil.listLocalQueue(
					hostToUse, portToUse, channelToUse, queueName);
			if(queueBeans == null){
				
			}else if (!map.containsKey(queueBeans.get(0).getQmgrName())) {
				map.put(queueBeans.get(0).getQmgrName(), queueBeans);
			}
		}
		return map;
	}

	/**
	 * List local queue.
	 * 
	 * @param hostname
	 *            the hostname
	 * @param port
	 *            the port
	 * @param channel
	 *            the channel
	 * @param queueReg
	 *            the queue reg
	 * @return the list
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2013-1-28 下午2:40:29
	 * @see com.deppon.esb.management.mq.service.IMqQueueService#listLocalQueue(java.lang.String,
	 *      int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<LocalQueueBean> listLocalQueue(String hostname, int port,
			String channel, String queueReg) {
		return QueueUtil.listLocalQueue(hostname, port, channel, queueReg);
	}

	/**
	 * List local queue depth.
	 * 
	 * @param hostname
	 *            the hostname
	 * @param port
	 *            the port
	 * @param channel
	 *            the channel
	 * @param queueReg
	 *            the queue reg
	 * @return the list
	 */
	public List<LocalQueueBean> listLocalQueueDepth(String hostname, int port,
			String channel, String queueReg) {
		return QueueUtil.listLocalQueueDepth(hostname, port, channel, queueReg);
	}

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-5-4 下午9:19:01
	 * @see com.deppon.esb.management.mq.service.IMqQueueService#checkConnection()
	 */
	@Override
	public Map<String, Boolean> checkConnection(String[] hostname, int[] port,
			String[] channel) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		for (int i = 0; i < hostname.length; i++) {
			result.putAll(QueueUtil.checkConnection(hostname[i], port[i],
					channel[i]));
		}
		return result;
	}

}
