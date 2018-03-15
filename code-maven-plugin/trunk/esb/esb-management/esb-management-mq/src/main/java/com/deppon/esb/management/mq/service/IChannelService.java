/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.service
 * FILE    NAME: IChannelService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.service;

import java.util.List;
import java.util.Map;

import com.deppon.esb.management.mq.domain.ChannelBean;

/**
 * 通道服务.
 * 
 * @author HuangHua
 * @date 2013-1-25 下午2:18:45
 */
public interface IChannelService {

	/**
	 * List channels.
	 * 
	 * @param hostname
	 *            the hostname
	 * @param port
	 *            the port
	 * @param channel
	 *            the channel
	 * @param channelNameReg
	 *            the channel name reg
	 * @return the Map<String,List<LocalQueueBean>>
	 *         如果hostname,port,channel,queueNameReg的数组大小不一致,直接返回null.
	 *         key--value:队列管理器名--List<ChannelBean>
	 * @author HuangHua
	 * @date 2013-1-28 下午3:31:28
	 */
	Map<String, List<ChannelBean>> listChannels(String[] hostname, int[] port,
			String[] channel, String[] channelNameReg);

	/**
	 * List all channel names and types.
	 * 
	 * @param hostname
	 *            the hostname
	 * @param port
	 *            the port
	 * @param channel
	 *            the channel
	 * @param channelNameReg
	 *            the channel name reg
	 * @return the Map<String,List<LocalQueueBean>>
	 *         如果hostname,port,channel,queueNameReg的数组大小不一致,直接返回null.
	 *         key--value:队列管理器名--List<ChannelBean>
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2013-1-28 下午3:32:25
	 */
	Map<String, List<ChannelBean>> listAllChannelNamesAndTypes(
			String[] hostname, int[] port, String[] channel,
			String[] channelNameReg) ;

	Map<String, List<ChannelBean>> listChannels2(String[] hostname, int[] port,
			String[] channel, String[] channelNameReg);

}
