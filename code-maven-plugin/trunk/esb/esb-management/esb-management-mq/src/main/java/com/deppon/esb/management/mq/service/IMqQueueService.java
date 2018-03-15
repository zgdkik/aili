/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.service
 * FILE    NAME: IQueueService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.service;

import java.util.List;
import java.util.Map;

import com.deppon.esb.management.mq.domain.LocalQueueBean;

/**
 * 队列服务.
 * 
 * @author HuangHua
 * @date 2013-1-25 下午1:55:33
 */
public interface IMqQueueService {

	/**
	 * 获取多个本地队列信息.
	 * 
	 * @param hostname
	 *            主机名,建议直接写IP
	 * @param port
	 *            端口号
	 * @param channel
	 *            服务器连接通道
	 * @param queueReg
	 *            队列名
	 * @return the list
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2013-1-25 下午2:04:19
	 */
	List<LocalQueueBean> listLocalQueue(String hostname, int port,
			String channel, String queueReg) throws Exception;

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
	 * 
	 * @param queueNameReg
	 *            the queue name reg
	 * @return the map
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2013-1-28 下午2:40:02
	 */
	Map<String, List<LocalQueueBean>> listLocalQueues(String[] hostname,
			int[] port, String[] channel, String[] queueNameReg);

	/**
	 * key = "hostname:" + hostname +",port:" + port + ",channel:" + channel;
	 * value = true--connection;false--disconnection check mq connection
	 * 
	 * @author HuangHua
	 * @return
	 * @date 2013-5-4 下午9:18:34
	 */
	Map<String, Boolean> checkConnection(String[] hostname, int[] port,
			String[] channel);

}
