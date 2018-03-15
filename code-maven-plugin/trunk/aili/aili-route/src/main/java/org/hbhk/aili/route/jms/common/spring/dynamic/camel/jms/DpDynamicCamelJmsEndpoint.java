package org.hbhk.aili.route.jms.common.spring.dynamic.camel.jms;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.route.jms.common.spring.dynamic.DynamicBeanReader;
import org.hbhk.aili.route.jms.common.spring.dynamic.ESBSpringLoadException;
import org.hbhk.aili.route.jms.common.spring.dynamic.camel.jms.collectdata.IJmsEndpointService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * DynamicCamelJmsEndpoint加载类.
 * 
 * @author HuangHua
 */
//@Component
public class DpDynamicCamelJmsEndpoint implements ApplicationContextAware, InitializingBean {
	
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"
			+ "<beans xmlns=\"http://www.springframework.org/schema/beans\""
			+ " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:camel=\"http://camel.apache.org/schema/spring\""
			+ "\n xsi:schemaLocation=\" \n"
			+ " http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd \n"
			+ " http://camel.apache.org/schema/spring http://camel.apache.org/schema/spring/camel-spring.xsd \">";
	
	/** The Constant JMS_CONN_FACT_NAME. */
	private static final String JMS_CONN_FACT_NAME = "ESBMQ";
	
	/** The logger. */
	private static Log logger = LogFactory.getLog(DpDynamicCamelJmsEndpoint.class);
	
	/** The application context. */
	private ApplicationContext applicationContext;
	
	/** The jms endpoint service. */
	@Resource
	private IJmsEndpointService jmsEndpointService;

	/**
	 * Builds the data.
	 * 
	 * @param nameSpace
	 *            the name space
	 * @param jmsConnectionFactoryName
	 *            the jms connection factory name
	 * @return the dp camel jms endpoint dynamic bean[]
	 * @throws ESBSpringLoadException
	 *             the eSB spring load exception
	 */
	private DpCamelJmsEndpointDynamicBean[] buildData(String nameSpace,String jmsConnectionFactoryName) throws ESBSpringLoadException {
		List<DpCamelJmsEndpointDynamicBean> dynamicBeans = jmsEndpointService.queryAll();
		DpCamelJmsEndpointDynamicBean[] beans = dynamicBeans.toArray(new DpCamelJmsEndpointDynamicBean[dynamicBeans.size()]);
		for (int i = 0; i < beans.length; i++) {
			beans[i].setNameSpace(nameSpace);
			beans[i].setJmsConnectionFactoryName(jmsConnectionFactoryName);
		}
		return beans;
	}

	/**
	 * Inits the.
	 * 
	 * @throws ESBSpringLoadException
	 *             the eSB spring load exception
	 */
	public void init() throws ESBSpringLoadException {
		loadBean2SpringContext(buildData(NAMESPACE,JMS_CONN_FACT_NAME));
	}

	/**
	 * Load bean2 spring context.
	 * 
	 * @param dynamicBean
	 *            the dynamic bean
	 * @throws ESBSpringLoadException
	 *             the eSB spring load exception
	 */
	public void loadBean2SpringContext(DpCamelJmsEndpointDynamicBean dynamicBean) throws ESBSpringLoadException {
		this.loadBean(new DpCamelJmsEndpointDynamicBean[] { dynamicBean });
	}

	/**
	 * Load bean2 spring context.
	 * 
	 * @param dynamicBean
	 *            the dynamic bean
	 * @throws ESBSpringLoadException
	 *             the eSB spring load exception
	 */
	public void loadBean2SpringContext(DpCamelJmsEndpointDynamicBean... dynamicBean) throws ESBSpringLoadException {
		this.loadBean(dynamicBean);
	}

	/**
	 * Load bean.
	 * 
	 * @param dynamicBeans
	 *            the dynamic beans
	 * @throws ESBSpringLoadException
	 *             the eSB spring load exception
	 */
	public void loadBean(DpCamelJmsEndpointDynamicBean[] dynamicBeans) throws ESBSpringLoadException {
		DynamicBeanReader reader = applicationContext.getBean("dynamicBeanReader", DynamicBeanReader.class);
		if (dynamicBeans != null) {
			Assert.noNullElements(dynamicBeans, "DyanamicBean must not be null");
			for (int i = 0; i < dynamicBeans.length; i++) {
				reader.loadBean(dynamicBeans[i]);
			}
		} else {
			logger.error("DyanamicBeans is null, load bean faild!");
		}

	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:15
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:15
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

}
