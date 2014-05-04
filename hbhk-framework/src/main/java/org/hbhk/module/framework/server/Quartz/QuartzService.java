package org.hbhk.module.framework.server.Quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class QuartzService {

	private static final Logger logger = Logger.getLogger(QuartzService.class);

	private static Map<String, String> quartzStatus = new HashMap<String, String>(10);

	@Autowired
	private QuartzDAO quartzDAO;

	@Autowired
	private Scheduler quartzScheduler;

	/**
	 * 查询所有定时任务信息
	 * 
	 * @return
	 */
	public List<QuartzEntity> getQuartzJobList() {
		return quartzDAO.selectAllQuartJob();
	}

	/**
	 * 增加定时任务
	 */
	public void addParseModelJob(String jobName, List<String> topicIds,
			String description, String cronPattern, Job job)
			throws SchedulerException {
		// 初始化JobDetail
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("PARSE_MODEL_TOPIC_KEY", topicIds);
		dataMap.put("JOB_LOG_KEY", new StringBuilder());
		dataMap.put("CREATE_JOB_TIME_KEY",DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
		JobDetail jobDetail = JobBuilder.newJob(job.getClass())
				//job名称 分组名称
				.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
				.withDescription(description).usingJobData(dataMap).build();
		// JobDetailBean jobDetail = new JobDetailBean();
		// 初始化CronTrigger
		logger.info("cronPattern:"+cronPattern);
		CronTrigger trigger = TriggerBuilder.newTrigger()
				//触发名称 分组名称
				.withIdentity(jobName + "_trigger", Scheduler.DEFAULT_GROUP)
				.forJob(jobDetail)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronPattern))
				.build();
		// 添加cornjob
		quartzScheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * 删除定时任务
	 * 
	 * @param jobName
	 * @throws SchedulerException
	 */
	public void deleteJob(String jobName) throws SchedulerException {
		quartzScheduler.deleteJob(new JobKey(jobName, Scheduler.DEFAULT_GROUP));
	}

}
