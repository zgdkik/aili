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

import com.deppon.esb.management.mq.domain.LocalQueueBean;
import com.deppon.esb.management.mq.service.IMqQueueService;
import com.deppon.esb.management.mq.utils.LocalQueueBeanComparator;

/**
 *
 * @author HuangHua
 * @date 2013-3-1 下午2:23:34
 */
public class MqLocalQueueAction implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private IMqQueueService mqQueueService;
	
	private String[] hostname;
	private int[] port;
	private String[] channel;
	private List<LocalQueueBean> resultLocalQueueList;
	
	public String listLocalQueues(){
		String[] queueNameReg = new String[hostname.length];
		for (int i = 0; i < queueNameReg.length; i++) {
			queueNameReg[i] = "QU*";
		}
		Map<String, List<LocalQueueBean>> names = mqQueueService.listLocalQueues(hostname, port, channel, queueNameReg);
		if(names == null){
			return "error";
		}
		Set<Entry<String, List<LocalQueueBean>>> nms = names.entrySet();
		for (Entry<String, List<LocalQueueBean>> entry : nms) {
			List<LocalQueueBean> chls = entry.getValue();
			getresultLocalQueueList().addAll(chls);
		}
		if(resultLocalQueueList != null){
			LocalQueueBeanComparator comparator = new LocalQueueBeanComparator();
			Collections.sort(resultLocalQueueList,comparator);
		}
		return "success";
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

	public List<LocalQueueBean> getresultLocalQueueList() {
		if(resultLocalQueueList == null){
			resultLocalQueueList = new ArrayList<LocalQueueBean>();
		}
		return resultLocalQueueList;
	}

	public void setresultLocalQueueList(List<LocalQueueBean> resultLocalQueueList) {
		this.resultLocalQueueList = resultLocalQueueList;
	}

	public IMqQueueService getMqQueueService() {
		return mqQueueService;
	}

	public void setMqQueueService(IMqQueueService mqQueueService) {
		this.mqQueueService = mqQueueService;
	}

}
