package com.deppon.esb.management.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.alert.service.IQueueService;
import com.deppon.esb.management.job.cluster.support.EsbJob;

/**
 * 队列管理器连接告警 定时任务
 * @author HuangHua
 * @date 2013-5-11 下午3:39:00
 */
@Repository
public class MqConnectionJob implements EsbJob {

	private static final long serialVersionUID = 2224169812840899341L;
	private static final Logger LOGGER = LoggerFactory.getLogger(MqConnectionJob.class);

	@Resource
	private IQueueService queueService;

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-3 下午8:10:10
	 * @see com.deppon.esb.management.job.cluster.support.EsbJob#executeInternal()
	 */
	@Override
	public void executeInternal() {
		try {
			LOGGER.info("===========================QueueConnJob Starting...");
			queueService.sendConnWarning();
			LOGGER.info("===========================QueueConnJob Ended...");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
