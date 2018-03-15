/*
 * PROJECT NAME: esb-management-web
 * PACKAGE NAME: com.deppon.esb.management.web.action.mq
 * FILE    NAME: ChannelAction.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.management.web.action.infobull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.deppon.esb.management.mq.domain.ChannelBean;
import com.deppon.esb.management.mq.service.IChannelService;
import com.deppon.esb.management.mq.utils.ChannelBeanComparator;

/**
 *
 * @author HuangHua
 * @date 2013-3-1 下午2:23:34
 */
public class ChannelAction2 implements Serializable{
	
	private static final long serialVersionUID = -7618897013822704016L;
	
	private IChannelService channelService;
	
	private String[] hostname;
	private int[] port;
	private String[] channel;
	private List<ChannelBean> resultChannelList;
	
	public String listChannelStatus(){
		String[] channelNameReg = new String[hostname.length];
		for (int i = 0; i < channelNameReg.length; i++) {
			channelNameReg[i] = "*";
		}
		Map<String, List<ChannelBean>> channels = channelService.listChannels2(hostname, port, channel, channelNameReg);
		Set<Entry<String, List<ChannelBean>>> nms = channels.entrySet();
		for (Entry<String, List<ChannelBean>> entry : nms) {
			List<ChannelBean> chls = entry.getValue();
			getResultChannelList().addAll(chls);
		}
		if(resultChannelList != null){
			ChannelBeanComparator comparator = new ChannelBeanComparator();
			Collections.sort(resultChannelList,comparator);
		}
		return "success";
	}

	public IChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	public String[] getHostname() {
		return hostname;
	}

	public void setHostname(String[] hostname) {
		this.hostname = hostname;
	}

	public int[] getPort() {
		return port;
	}

	public void setPort(int[] port) {
		this.port = port;
	}

	public String[] getChannel() {
		return channel;
	}

	public void setChannel(String[] channel) {
		this.channel = channel;
	}

	public List<ChannelBean> getResultChannelList() {
		if(resultChannelList == null){
			resultChannelList = new ArrayList<ChannelBean>();
		}
		return resultChannelList;
	}

	public void setResultChannelList(List<ChannelBean> resultChannelList) {
		this.resultChannelList = resultChannelList;
	}

}
