package com.deppon.esb.management.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.alert.service.ISendWarningService;
import com.deppon.esb.management.job.cluster.support.EsbJob;

@Repository
public class PfmcJob implements EsbJob {

	private static final long serialVersionUID = 7451602127190761368L;
	private static final Logger LOGGER = LoggerFactory.getLogger(QueueDepthJob.class);
	@Resource
	private ISendWarningService sendWarningService;

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-3 下午8:09:53
	 * @see com.deppon.esb.management.job.cluster.support.EsbJob#executeInternal()
	 */
	@Override
	public void executeInternal() {
		LOGGER.info("==========================================Performance Job Starting...");
		sendWarningService.sendPerformanceWarning();
		LOGGER.info("==========================================Performance Job Ended...");
	}
}
