/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.service.impl
 * FILE    NAME: ChannelService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.deppon.esb.management.mq.domain.ChannelBean;
import com.deppon.esb.management.mq.pcf.ChannelUtil;
import com.deppon.esb.management.mq.service.IChannelService;

/**
 * The Class ChannelService.
 * 
 * @author HuangHua
 * @date 2013-1-25 下午3:46:53
 */
@Service
public class ChannelService implements IChannelService {

	/**
	 * 查看所有活动通道状态,如果要列出所有的通道,请用{@link #listAllChannelNamesAndTypes(String)}.
	 * 
	 * @param hostname
	 *            HOST NAME.
	 * @param port
	 *            端口.
	 * @param channel
	 *            服务器连接通道.
	 * @param channelNameReg
	 *            the channel name reg
	 * @return the Map<String,List<LocalQueueBean>>
	 *         如果hostname,port,channel,queueNameReg的数组大小不一致,直接返回null.
	 *         key--value:队列管理器名--List<ChannelBean>
	 * @author HuangHua
	 * @date 2013-1-25 下午3:46:59
	 * @see com.deppon.esb.management.mq.service.IChannelService#listChannels()
	 */
	@Override
	public Map<String,List<ChannelBean>> listChannels(String[] hostname, int[] port,String[] channel, String[] channelNameReg) {
		if (hostname.length != port.length
				|| hostname.length != channel.length
				|| hostname.length != channelNameReg.length) {
			return null;
		}
		Map<String,List<ChannelBean>> channelBeanMap = new HashMap<String,List<ChannelBean>>();
		for (int i = 0; i < channelNameReg.length; i++) {
			String hostToUse = hostname[i];
			int portToUse = port[i];
			String channelToUse = channel[i];
			String channelNameToUse = channelNameReg[i];
			List<ChannelBean> channelBeans = ChannelUtil.listChannels(hostToUse, portToUse, channelToUse, channelNameToUse);
			if (channelBeans.size()>0) {
				channelBeanMap.put(channelBeans.get(0).getQmgrName(), channelBeans);
			}
		}
		return channelBeanMap;
	}
	
	/**
	 * 查看所有活动通道状态,如果要列出所有的通道,请用{@link #listAllChannelNamesAndTypes(String)}.
	 * 
	 * @param hostname
	 *            HOST NAME.
	 * @param port
	 *            端口.
	 * @param channel
	 *            服务器连接通道.
	 * @param channelNameReg
	 *            the channel name reg
	 * @return the Map<String,List<LocalQueueBean>>
	 *         如果hostname,port,channel,queueNameReg的数组大小不一致,直接返回null.
	 *         key--value:队列管理器名--List<ChannelBean>
	 * @author HuangHua
	 * @date 2013-1-25 下午3:46:59
	 * @see com.deppon.esb.management.mq.service.IChannelService#listChannels()
	 */
	@Override
	public Map<String,List<ChannelBean>> listChannels2(String[] hostname, int[] port,String[] channel, String[] channelNameReg) {
		if (hostname.length != port.length
				|| hostname.length != channel.length
				|| hostname.length != channelNameReg.length) {
			return null;
		}
		Map<String,List<ChannelBean>> channelBeanMap = new HashMap<String,List<ChannelBean>>();
		for (int i = 0; i < channelNameReg.length; i++) {
			String hostToUse = hostname[i];
			int portToUse = port[i];
			String channelToUse = channel[i];
			String channelNameToUse = channelNameReg[i];
			List<ChannelBean> channelNameBeans = ChannelUtil.listAllChannelNamesAndTypes(hostToUse, portToUse, channelToUse, channelNameToUse);
			List<ChannelBean> channelBeans = ChannelUtil.listChannels2(hostToUse, portToUse, channelToUse, channelNameToUse);
			if (channelBeans.size()>0) {
				for (ChannelBean channelBean : channelNameBeans) {
					for (ChannelBean channelBean2 : channelBeans) {
						if(channelBean2.getName().equals(channelBean.getName())){
							channelBean.setState("RUNNING");
						}
					}
				}
				for (ChannelBean channelBean : channelNameBeans) {
					if(channelBean.getState() == null || "".equals(channelBean.getState())){
						channelBean.setState("NOT RUNNING");
					}
				}
				channelBeanMap.put(channelNameBeans.get(0).getQmgrName(), channelNameBeans);
			}
		}
		return channelBeanMap;
	}

	/**
	 * List all channel names and types.
	 * 
	 * @param channelNameReg
	 *            the channel name reg
	 * @return the Map<String,List<LocalQueueBean>>
	 *         如果hostname,port,channel,queueNameReg的数组大小不一致,直接返回null.
	 *         key--value:队列管理器名--List<ChannelBean>
	 * @author HuangHua
	 * @date 2013-1-25 下午5:31:48
	 * @see com.deppon.esb.management.mq.service.IChannelService#listAllChannelNamesAndTypes(java.lang.String,
	 *      int, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, List<ChannelBean>> listAllChannelNamesAndTypes(String[] hostname, int[] port,String[] channel, 
			String[] channelNameReg) {
		if (hostname.length != port.length
				|| hostname.length != channel.length
				|| hostname.length != channelNameReg.length) {
			return null;
		}
		Map<String, List<ChannelBean>> map = new HashMap<String, List<ChannelBean>>();
		for (int i = 0; i < channelNameReg.length; i++) {
			String channelNameToUse = channelNameReg[i];
			String hostToUse = hostname[i];
			int portToUse = port[i];
			String channelToUse = channel[i];
			List<ChannelBean> channelBeans = ChannelUtil
					.listAllChannelNamesAndTypes(hostToUse, portToUse,
							channelToUse, channelNameToUse);
			if(channelBeans.size()>0){
				map.put(channelBeans.get(0).getQmgrName(), channelBeans);
			}
		}
		return map;
	}
	
}
