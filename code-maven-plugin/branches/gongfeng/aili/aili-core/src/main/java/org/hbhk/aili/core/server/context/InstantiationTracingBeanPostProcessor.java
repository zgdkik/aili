package org.hbhk.aili.core.server.context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

	private Log log = LogFactory.getLog(getClass());

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	// 在创建bean后输出bean的信息
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		log.info("bean name : " + beanName + " class: "
				+ bean.getClass().getName());
		return bean;
	}
}
