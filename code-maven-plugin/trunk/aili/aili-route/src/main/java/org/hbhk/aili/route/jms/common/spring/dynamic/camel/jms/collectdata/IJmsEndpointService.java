package org.hbhk.aili.route.jms.common.spring.dynamic.camel.jms.collectdata;

import java.io.IOException;
import java.util.List;

import org.hbhk.aili.route.jms.common.spring.dynamic.ESBSpringLoadException;
import org.hbhk.aili.route.jms.common.spring.dynamic.camel.jms.DpCamelJmsEndpointDynamicBean;

/**
 * The Interface IJmsEndpointService.
 * 
 * @author HuangHua
 */
public interface IJmsEndpointService {

	/**
	 * 查询所有JMS配置.
	 * 
	 * @return the list
	 * @throws ESBSpringLoadException
	 *             the eSB spring load exception
	 */
	List<DpCamelJmsEndpointDynamicBean> queryAll() throws ESBSpringLoadException;

	/**
	 * 把配置信息保存到数据库.
	 * 
	 * @param beans
	 *            the beans
	 */
	void saveCfg2File(List<DpCamelJmsEndpointDynamicBean> beans);

	/**
	 * 从文件中加载配置信息.
	 * 
	 * @param configFileName
	 *            the config file name
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	List<DpCamelJmsEndpointDynamicBean> loadConfigFromFile(String configFileName) throws IOException;

}
