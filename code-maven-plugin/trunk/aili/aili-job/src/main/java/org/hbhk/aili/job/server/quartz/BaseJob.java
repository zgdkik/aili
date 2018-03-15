package org.hbhk.aili.job.server.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public abstract class BaseJob implements Job {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info(this.getClass().getName() + " 执行开始...");
		SchedulerContext schCtx;
		try {
			schCtx = context.getScheduler().getContext();
			ApplicationContext springContext = (ApplicationContext) schCtx
					.get("applicationContext");
			doExecute(context, springContext);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		logger.info(this.getClass().getName() + " 执行结束...");
	}

	public abstract void doExecute(JobExecutionContext context,
			ApplicationContext springContext) throws Exception;
}
