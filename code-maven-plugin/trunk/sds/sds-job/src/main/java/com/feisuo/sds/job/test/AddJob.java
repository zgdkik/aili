package com.feisuo.sds.job.test;

import org.hbhk.aili.job.server.quartz.IJobService;
import org.hbhk.aili.job.server.quartz.impl.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class AddJob implements InitializingBean {
	private static final Logger logger = LoggerFactory
			.getLogger(JobService.class);
	@Autowired
	IJobService jobService;

	@Override
	public void afterPropertiesSet() throws Exception {
//		useCarPlanJob();
//		stopDemandAutoJob();
//		stopCarPlanAutoJob();
	}


}
