package com.deppon.esb.management.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.job.cluster.support.EsbJob;

@Repository
public class TestJob implements EsbJob {

	private static final long serialVersionUID = -5614210134351170171L;
	private static final Logger LOGGER = LoggerFactory.getLogger(TestJob.class);

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-3 下午8:11:03
	 * @see com.deppon.esb.management.job.cluster.support.EsbJob#executeInternal()
	 */
	@Override
	public void executeInternal() {
		LOGGER.info("===========================Test Job: " + Thread.currentThread().getName() + " Starting...");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("===========================Test Job: " + Thread.currentThread().getName() + " Ended...");
	}

}
