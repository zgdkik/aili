package org.hbhk.aili.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.job.server.quartz.impl.JobService;
import org.junit.Test;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleJobTest {

	private static Log log = LogFactory.getLog(SimpleJobTest.class);

	static ExecutorService executorService = Executors.newCachedThreadPool();
	static ApplicationContext context = null;

	@SuppressWarnings("resource")
	@Test
	public void simpleJobTest() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:job/spring-quartz.xml");

			JobService quartzService = (JobService) context
					.getBean("jobService");
			for (int i = 11; i < 12; i++) {
				String jobName = "jobName" + i;
				List<String> topicIds = new ArrayList<String>();
				topicIds.add("topicId1");
				topicIds.add("topicId2");
				String description = "description";
				String cronPattern = "0/10 * * * * ?";
				try {
					quartzService.addJob(jobName, topicIds, description,
							cronPattern, ParseModelJob1.class);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void deleteJob() throws SchedulerException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:job/jobContext.xml");

		JobService quartzService = (JobService) context.getBean("jobService");
		for (int i = 10; i < 12; i++) {
			try {
				quartzService.deleteJob("jobName" + i);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		try {
			log.debug("1111111111");
			context = new ClassPathXmlApplicationContext(
					"classpath:job/spring-quartz.xml");
//			executorService.execute(new Runnable() {
//				@Override
//				public void run() {
//					context = new ClassPathXmlApplicationContext(
//							"classpath:job/spring-quartz.xml");
//				}
//			});
//			while (true) {
//				if (context != null) {
//
//					JobService quartzService = (JobService) context
//							.getBean("jobService");
////					quartzService.pauseJob("jobName10", "DEFAULT");
////					TimeUnit.SECONDS.sleep(30);
////					quartzService.resumeJob("jobName10", "DEFAULT");
////
////					TimeUnit.SECONDS.sleep(30);
////					quartzService.pauseJob("jobName10", "DEFAULT");
////					TimeUnit.SECONDS.sleep(30);
////					quartzService.resumeJob("jobName10", "DEFAULT");
////
////					TimeUnit.SECONDS.sleep(30);
////					quartzService.pauseJob("jobName10", "DEFAULT");
////					TimeUnit.SECONDS.sleep(30);
//     				//quartzService.resumeJob("jobName10", "DEFAULT");
//					//0/20 * * * * ?
//					TimeUnit.SECONDS.sleep(30);
//					quartzService.modifyJobTime("jobName10_trigger", "DEFAULT", "0/1 * * * * ?");
//				}
//				TimeUnit.SECONDS.sleep(5);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
