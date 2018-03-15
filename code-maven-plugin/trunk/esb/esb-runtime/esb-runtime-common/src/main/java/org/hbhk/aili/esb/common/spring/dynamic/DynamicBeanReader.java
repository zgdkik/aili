package org.hbhk.aili.esb.common.spring.dynamic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 动态bean加载器.
 * 
 * @author HuangHua
 */
//@Component
public class DynamicBeanReader implements ApplicationContextAware,
		InitializingBean {
	
	/** The logger. */
	private static Log logger = LogFactory
			.getLog(DynamicBeanReader.class);// 日记

	/** The application context. */
	private ConfigurableApplicationContext applicationContext = null;

	/** The bean definition reader. */
	private XmlBeanDefinitionReader beanDefinitionReader;

	/* 初始化方法 */
	/**
	 * Inits the.
	 */
	public void init() {
		beanDefinitionReader = new XmlBeanDefinitionReader(
				(BeanDefinitionRegistry) applicationContext.getBeanFactory());
		beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(
				applicationContext));
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}

	/**
	 * 加载bean.
	 * 
	 * @param dynamicBean
	 *            the dynamic bean
	 * @throws ESBSpringLoadException
	 *             the eSB spring load exception
	 */
	public void loadBean(DynamicBean dynamicBean) throws ESBSpringLoadException {
		long startTime = System.currentTimeMillis();
		String beanName = dynamicBean.getBeanName();
		if (applicationContext.containsBean(beanName)) {
			logger.error("bean[" + beanName + "]已经加载!");
			throw new ESBSpringLoadException("bean id[" + beanName
					+ "] already exist!");
		}
		beanDefinitionReader.loadBeanDefinitions(new DynamicResource(
				dynamicBean));
		logger.info("初始化bean[" + dynamicBean.getBeanName() + "],耗时"
				+ (System.currentTimeMillis() - startTime) + "毫秒。");
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
}
