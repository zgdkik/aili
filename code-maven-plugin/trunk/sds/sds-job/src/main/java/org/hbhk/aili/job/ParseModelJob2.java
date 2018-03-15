package org.hbhk.aili.job;

import org.hbhk.aili.job.server.quartz.BaseJob;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;

public class ParseModelJob2  extends BaseJob{

	@Override
	public void doExecute(JobExecutionContext context,
			ApplicationContext springContext) throws Exception {
		
		System.out.println("qqqqqqqqqqqq");
		
	}

}
