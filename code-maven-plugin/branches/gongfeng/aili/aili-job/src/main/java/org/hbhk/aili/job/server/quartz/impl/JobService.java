package org.hbhk.aili.job.server.quartz.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.hbhk.aili.job.server.quartz.IJobService;
import org.hbhk.aili.job.share.model.JobInfo;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class JobService implements IJobService {

	private static final Logger logger = LoggerFactory
			.getLogger(JobService.class);
	private JobDao jobDao;
	private Scheduler scheduler;

	/**
	 * 查询所有定时任务信息
	 */
	public List<JobInfo> getQuartzJobList(String jobName) {
		return jobDao.selectAllQuartJob(jobName);
	}

	/**
	 * 增加定时任务
	 */
	public void addJob(String jobName, List<String> topicIds,
			String description, String cronPattern, Class<? extends Job> jobCls)
			throws Exception {
		// 初始化JobDetail
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("PARSE_MODEL_TOPIC_KEY", topicIds);
		dataMap.put("JOB_LOG_KEY", new StringBuilder());
		dataMap.put("CREATE_JOB_TIME_KEY",
				DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
		Job jobInstance = (Job) jobCls.newInstance();
		JobDetail jobDetail = JobBuilder.newJob(jobInstance.getClass())
				// job名称 分组名称
				.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
				.withDescription(description).usingJobData(dataMap).build();
		// JobDetailBean jobDetail = new JobDetailBean();
		// 初始化CronTrigger
		logger.info("cronPattern:" + cronPattern);
		CronTrigger trigger = TriggerBuilder
				.newTrigger()
				// 触发名称 分组名称
				.withIdentity(jobName + "_trigger")
				// , Scheduler.DEFAULT_GROUP
				.forJob(jobDetail)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronPattern))
				.build();
		// 添加cornjob
		scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * 增加定时任务
	 */
	public void addJob(String jobName, String group, List<String> topicIds,
			String description, String cronPattern, Class<? extends Job> jobCls)
			throws Exception {
		// 初始化JobDetail
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("PARSE_MODEL_TOPIC_KEY", topicIds);
		dataMap.put("JOB_LOG_KEY", new StringBuilder());
		dataMap.put("CREATE_JOB_TIME_KEY",
				DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
		if (StringUtils.isEmpty(group)) {
			group = Scheduler.DEFAULT_GROUP;
		}
		Job jobInstance = (Job) jobCls.newInstance();
		JobDetail jobDetail = JobBuilder.newJob(jobInstance.getClass())
				// job名称 分组名称
				.withIdentity(jobName, group).withDescription(description)
				.usingJobData(dataMap).build();
		// JobDetailBean jobDetail = new JobDetailBean();
		// 初始化CronTrigger
		logger.info("cronPattern:" + cronPattern);
		CronTrigger trigger = TriggerBuilder
				.newTrigger()
				// 触发名称 分组名称
				.withIdentity(jobName + "_trigger")
				// , Scheduler.DEFAULT_GROUP
				.forJob(jobDetail)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronPattern))
				.build();
		// 添加cornjob
		scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * 删除定时任务
	 */
	public void deleteJob(String jobName) throws SchedulerException {
		scheduler.deleteJob(new JobKey(jobName, Scheduler.DEFAULT_GROUP));
	}

	/**
	 * 删除定时任务
	 */
	public void deleteJob(String jobName, String group)
			throws SchedulerException {
		scheduler.deleteJob(new JobKey(jobName, group));
	}

	@Override
	public void pauseJob(String jobName, String group)
			throws SchedulerException {
		scheduler.pauseJob(new JobKey(jobName, group));

	}

	@Override
	public void resumeJob(String jobName, String group)
			throws SchedulerException {
		scheduler.resumeJob(new JobKey(jobName, group));

	}

	@Override
	public void modifyJobTime(String triggerName, String triggerGroup,
			String time) throws Exception {
		Trigger trigger = scheduler.getTrigger(new TriggerKey(triggerName,
				triggerGroup));
		if (trigger != null) {
			CronTriggerImpl ct = (CronTriggerImpl) trigger;
			ct.setCronExpression(time);
			// 重启触发器
			scheduler.resumeTrigger(new TriggerKey(triggerName, triggerGroup));
		}
	}

	public JobDao getJobDao() {
		return jobDao;
	}

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}
