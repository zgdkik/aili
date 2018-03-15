/*
 * PROJECT NAME: esb-management-job
 * PACKAGE NAME: com.deppon.esb.management.cluster.support
 * FILE    NAME: BootstrapJob.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.job.cluster.support;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;

/**
 *
 */
/**
 * 引导Job，通过Spring容器获取任务的Job，根据注入的targetJob,该Job必须实现Job2接口
 * 
 * @author HuangHua
 * @date 2013-5-3 下午7:26:36
 */
public class BootstrapJob implements Serializable {

	private static final long serialVersionUID = -4754423796702905317L;
	private String targetJob;

	public void executeInternal(ApplicationContext cxt) {
		EsbJob job = (EsbJob) cxt.getBean(this.targetJob);
		job.executeInternal();
	}

	public String getTargetJob() {
		return targetJob;
	}

	public void setTargetJob(String targetJob) {
		this.targetJob = targetJob;
	}
}
