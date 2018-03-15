package org.hbhk.aili.route.jms.common.spring.dynamic.camel.jms.collectdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.route.jms.common.spring.dynamic.ESBSpringLoadException;
import org.hbhk.aili.route.jms.common.spring.dynamic.camel.jms.DpCamelJmsEndpointDynamicBean;

//@Service
/**
 * The Class JmsEndpointService.
 */
public class JmsEndpointService implements IJmsEndpointService {
	
	/** The Constant configFileName. */
	private static final String configFileName = System
			.getProperty("user.home")
			+ File.separator
			+ "esbConfig"
			+ File.separator + "jmsConfig.cfg";
	
	/** The log. */
	private static Log log = LogFactory.getLog(JmsEndpointService.class);

	/** The jms endpoint dao. */
	@Resource
	private JmsEndpointDao jmsEndpointDao;


	/**
	 * 查询所有JMS配置.
	 * 
	 * @return the list
	 * @throws ESBSpringLoadException
	 *             the eSB spring load exception
	 */
	@Override
	public List<DpCamelJmsEndpointDynamicBean> queryAll()
			throws ESBSpringLoadException {
		List<DpCamelJmsEndpointDynamicBean> beans = null;
		try {
			beans = jmsEndpointDao.queryJMSconfig();
			for (DpCamelJmsEndpointDynamicBean bean : beans) {
				log.info(bean.getBeanName());
			}
		} catch (Exception e) {
			log.warn("从数据库读取JMS配置失败，尝试使用配置文件方式读取配置信息！", e);
			try {
				beans = loadConfigFromFile(configFileName);
			} catch (IOException e1) {
				log.error("从文件读取JMS配置失败，请检查配置，并重启应用！");
				throw new ESBSpringLoadException("从文件读取JMS配置失败，请检查配置，并重启应用！", e1);
			}
		}
		// 把属性修整为符合规范的格式。如：new
		// DpCamelJmsEndpointDynamicBean("mq_crm_request_in", "ESBMQ",
		// "RQ_CRM_REQUEST_IN","selector=SERVICECODE='TESTCODE'")
		List<DpCamelJmsEndpointDynamicBean> configs = new ArrayList<DpCamelJmsEndpointDynamicBean>();
		for (DpCamelJmsEndpointDynamicBean dpCamelJmsEndpointDynamicBean : beans) {
			String queueName = dpCamelJmsEndpointDynamicBean.getQueueName()
					.substring(3);
			char letters[] = new char[queueName.length()];
			for (int i = 0; i < queueName.length(); i++) {
				char letter = queueName.charAt(i);
				if (letter >= 'A' && letter <= 'Z') {
					letter = (char) (letter + 32);
				}
				letters[i] = letter;
			}
			String beanName = "mq_" + String.copyValueOf(letters) + "_"
					+ dpCamelJmsEndpointDynamicBean.getBeanName();
			DpCamelJmsEndpointDynamicBean bean = new DpCamelJmsEndpointDynamicBean(
					beanName);
			bean.setQueueName(dpCamelJmsEndpointDynamicBean.getQueueName());
			bean.setJmsConnectionFactoryName(dpCamelJmsEndpointDynamicBean
					.getJmsConnectionFactoryName());
			bean.setOperation(dpCamelJmsEndpointDynamicBean.getOperation());
			configs.add(bean);
		}
		return configs;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.hbhk.aili.route.jms.common.spring.dynamic.camel.jms.collectdata.IJmsEndpointService#loadConfigFromFile(java.lang.String)
	 */
	@Override
	public List<DpCamelJmsEndpointDynamicBean> loadConfigFromFile(
			String configFileName) throws FileNotFoundException {
		List<DpCamelJmsEndpointDynamicBean> beans = new ArrayList<DpCamelJmsEndpointDynamicBean>();
		File file = new File(configFileName);
		if (!file.exists()) {
			throw new FileNotFoundException("文件未找到,路径:" + configFileName);
		}
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String strLine;
			while (bufferedReader.ready()) {
				strLine = bufferedReader.readLine();
				if (strLine != null) {
					String[] cfgs = strLine.split(",");
					DpCamelJmsEndpointDynamicBean bean = new DpCamelJmsEndpointDynamicBean(
							cfgs[0]);
					bean.setQueueName(cfgs[0]);
					bean.setOperation(cfgs[1]);
					beans.add(bean);
				}
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		return beans;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.hbhk.aili.route.jms.common.spring.dynamic.camel.jms.collectdata.IJmsEndpointService#saveCfg2File(java.util.List)
	 */
	@Override
	public void saveCfg2File(List<DpCamelJmsEndpointDynamicBean> beans) {
		File file = new File(configFileName);
		try {
			if (!file.getParentFile().exists()) {
				boolean mdSuccess = file.getParentFile().mkdirs();
				if (mdSuccess) {
					boolean crtSuccess = file.createNewFile();
					log.trace(crtSuccess);
				}
			} else if (!file.exists()) {
				boolean isSuccess = file.createNewFile();
				if(isSuccess){
					log.debug("文件"+file.getAbsolutePath()+"创建成功.");
				}else{
					log.debug("文件"+file.getAbsolutePath()+"创建失败.");
				}
			}
		} catch (IOException e) {
			log.error("文件读写失败！", e);
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file);
			StringBuffer content = new StringBuffer();
			for (DpCamelJmsEndpointDynamicBean bean : beans) {
				content.append(bean.getQueueName()).append(",")
						.append(bean.getOperation()).append("\n");
			}
			fileWriter.write(content.toString());
			fileWriter.flush();
		} catch (IOException e) {
			log.error("JMS配置信息写入文件失败！", e);
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

}
