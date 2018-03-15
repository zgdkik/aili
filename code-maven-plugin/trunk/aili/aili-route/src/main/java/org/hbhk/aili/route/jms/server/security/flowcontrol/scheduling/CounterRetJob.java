package org.hbhk.aili.route.jms.server.security.flowcontrol.scheduling;

import java.util.List;

import org.hbhk.aili.route.jms.server.security.flowcontrol.container.ICountContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CounterRetJob {
	private static Logger LOG = LoggerFactory.getLogger(CounterRetJob.class);
	private List<ICountContainer> list = null;
	public void execute(){
		LOG.info("开始执行重置任务");
		for(ICountContainer container:list){
			container.resetAllCounter();
		}
		LOG.info("重置任务执行完毕");
	}
	public List<ICountContainer> getList() {
		return list;
	}
	public void setList(List<ICountContainer> list) {
		this.list = list;
	}
}
