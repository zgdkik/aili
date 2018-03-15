package org.hbhk.aili.job.server.quartz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hbhk.aili.job.share.model.JobDetail;
import org.quartz.Job;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

public class JobGenerator implements InitializingBean {

	private JdbcTemplate jdbcTemplate;
	
	private IJobService  jobService;
	private String sql = "select * from t_job_detail";

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		
		List<JobDetail> jobDetails = jdbcTemplate.query(sql, new RowMapper<JobDetail>() {
			@Override
			public JobDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				JobDetail  jobDetail = new JobDetail();
				jobDetail.setJobName(rs.getString("Job_name"));
				jobDetail.setCron(rs.getString("cron"));
				jobDetail.setJobCls(rs.getString("job_cls"));
				jobDetail.setTopicKey(rs.getString("topic_key"));
				jobDetail.setDescp(rs.getString("descp"));
				jobDetail.setStatus(rs.getString("status"));
				jobDetail.setId(rs.getString("id"));
				jobDetail.setGroupName(rs.getString("group_name"));
				return jobDetail;
			}
		});
		if(!CollectionUtils.isEmpty(jobDetails)){
			for (JobDetail jd : jobDetails) {
				String status = jd.getStatus();
				String jobName = jd.getJobName();
				String group = jd.getGroupName();
				String description = jd.getDescp();
				String cronPattern = jd.getCron();
				String jobClsName = jd.getJobCls();
				String topicKey = jd.getTopicKey();
				Class<? extends Job> jobCls = (Class<? extends Job>) Class.forName(jobClsName);
				List<String> topicIds = new ArrayList<String>();
				if(StringUtils.isEmpty(topicKey)){
					topicIds.add(jobName+"_topic_key1");
					topicIds.add(jobName+"_topic_key2");
				}else{
					topicIds.add(topicKey);
				}
				if("1".equals(status)){
					jobService.addJob(jobName, group, topicIds, description, cronPattern, jobCls);
					jdbcTemplate.execute("update  t_job_detail  set status = 2 where Job_name ='"+jobName+"'");
				}else if("0".equals(status)){
					jobService.deleteJob(jobName);
				}else if("3".equals(status)){
					jobService.deleteJob(jobName);
					jobService.addJob(jobName, group, topicIds, description, cronPattern, jobCls);
					jdbcTemplate.execute("update  t_job_detail  set status = 2 where Job_name ='"+jobName+"'");
				}
			}
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public IJobService getJobService() {
		return jobService;
	}

	public void setJobService(IJobService jobService) {
		this.jobService = jobService;
	}

	
	
	
}
