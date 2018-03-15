package com.deppon.esb.management.job;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.alert.service.IQueueAlertService;
import com.deppon.esb.management.job.cluster.support.EsbJob;

@Repository
public class QueueAlertJob implements EsbJob {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4961087680196162126L;
	private static final Logger LOGGER = Logger.getLogger(QueueAlertJob.class);

	@Resource
	private IQueueAlertService queueAlertService;

	@Override
	public void executeInternal() {
		try {
			LOGGER.info("===========================QueueAlertJob Starting...");
			queueAlertService.earlyQueueAlert();
			LOGGER.info("===========================QueueAlertJob Ended...");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
