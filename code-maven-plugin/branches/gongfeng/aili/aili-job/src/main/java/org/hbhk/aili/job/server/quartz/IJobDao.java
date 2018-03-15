package org.hbhk.aili.job.server.quartz;

import java.util.List;

import org.hbhk.aili.job.share.model.JobInfo;


public interface IJobDao {

	/**
	 * 查找所有的定时任务
	 * @return
	 */
	public List<JobInfo> selectAllQuartJob(String jobName) ;
}