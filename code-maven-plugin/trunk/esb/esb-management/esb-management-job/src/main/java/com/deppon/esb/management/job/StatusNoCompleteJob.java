/*
 * PROJECT NAME: esb-management-job
 * PACKAGE NAME: com.deppon.esb.management.job
 * FILE    NAME: StatusNoCompleteJob.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.alert.service.IStatusService;
import com.deppon.esb.management.job.cluster.support.EsbJob;

/**
 * 
 * @author HuangHua
 * @date 2013-5-13 上午10:20:30
 */
@Repository
public class StatusNoCompleteJob implements EsbJob {

	private static final long serialVersionUID = -7915895989015831426L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(StatusNoCompleteJob.class);

	@Resource
	private IStatusService statusService;

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-5-13 上午10:21:03
	 * @see com.deppon.esb.management.job.cluster.support.EsbJob#executeInternal()
	 */
	@Override
	public void executeInternal() {
		LOGGER.info("===========================StatusNoCompleteJob Starting...");
		try {
			statusService.sendStatusNoCompleteWarn();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		try {
			statusService.sendStatusNoCompleteWarn();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("===========================StatusNoCompleteJob Ended...");
	}

}
