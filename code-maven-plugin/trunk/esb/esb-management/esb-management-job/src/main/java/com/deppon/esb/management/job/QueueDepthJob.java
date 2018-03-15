package com.deppon.esb.management.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.alert.service.IQueueService;
import com.deppon.esb.management.job.cluster.support.EsbJob;

/**
 * 队列深度告警 定时任务
 * @author HuangHua
 * @date 2013-5-11 下午3:41:06
 * 将队列告警标识为过时方法
 */
@Repository
public class QueueDepthJob implements EsbJob {

	private static final long serialVersionUID = 2224169812840899341L;
	private static final Logger LOGGER = LoggerFactory.getLogger(QueueDepthJob.class);

	@Resource
	private IQueueService queueService;

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-3 下午8:10:10
	 * @see com.deppon.esb.management.job.cluster.support.EsbJob#executeInternal()
	 */
	@Override
	@Deprecated
	public void executeInternal() {
		try {
			LOGGER.info("===========================QueueDepthJob Starting...");
			queueService.sendDepthWarning();
			LOGGER.info("===========================QueueDepthJob Ended...");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
