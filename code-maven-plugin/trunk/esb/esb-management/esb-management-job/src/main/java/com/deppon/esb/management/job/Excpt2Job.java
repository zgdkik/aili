package com.deppon.esb.management.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.alert.service.IExcptService;
import com.deppon.esb.management.job.cluster.support.EsbJob;

@Repository
public class Excpt2Job implements EsbJob {

	private static final long serialVersionUID = -5614210134351170171L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Excpt2Job.class);
	@Resource
	private IExcptService excptService;

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-5-3 下午8:09:00
	 * @see com.deppon.esb.management.job.cluster.support.EsbJob#executeInternal()
	 */
	@Override
	public void executeInternal() {
		LOGGER.info("===========================Exception2 Job Starting...");
		excptService.sendExcptnWarning();
		LOGGER.info("===========================Exception2 Job Ended...");
	}

}
