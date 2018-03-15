package com.deppon.esb.common.spring.test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.common.spring.dynamic.camel.jms.DpCamelJmsEndpointDynamicBean;
import org.hbhk.aili.esb.common.spring.dynamic.camel.jms.collectdata.JmsEndpointDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Class JmsEndpointTest.
 */
@Ignore
public class JmsEndpointTest {

	/** The logger. */
	private static Log logger = LogFactory.getLog(JmsEndpointTest.class);

	/** The jms endpoint dao. */
	private JmsEndpointDao jmsEndpointDao = new JmsEndpointDao();
	
	/** The data source. */
	private DataSource dataSource;
	
	/** The Constant insertSql. */
	private static final String insertSql = "insert into test_esb2_svcpoint (FID, SVCCODE, FRNTORBCK, REQUESTADDR, RESPONSEADDR, SVCAGRMT) values ('45456456456', 'FIN_FIN2ESB_UPDATEORDER', 'B', 'QU_FIN_REQUEST_IN', 'QU_FIN_RESPONSE_OUT', 'JMS')";
	
	/** The Constant deleteSql. */
	private static final String deleteSql = "delete from test_esb2_svcpoint where FID='45456456456' AND SVCCODE='FIN_FIN2ESB_UPDATEORDER' AND FRNTORBCK='B' AND REQUESTADDR='QU_FIN_REQUEST_IN' AND RESPONSEADDR='QU_FIN_RESPONSE_OUT' AND SVCAGRMT='JMS'";

	/**
	 * Test.
	 */
	@Test
	public void test(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:com/deppon/esb/common/META-INF/common-config.xml");
		Assert.assertTrue(applicationContext.containsBean("mq_fin_request_in_FIN_FIN2ESB_UPDATEORDER"));
	}
	
	/**
	 * Inits the.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void init() throws Exception {
		dataSource = jmsEndpointDao.createDataSource(JmsEndpointDao.JDBC_PROPERTY_FILE);
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(true);
		Statement statement = connection.createStatement();
		statement.execute(insertSql);
		List<DpCamelJmsEndpointDynamicBean> result = jmsEndpointDao.queryJMSconfig();
		Assert.assertNotNull(result);
		logger.info(result.size());
		Assert.assertTrue(result.size() > 0);
		statement.close();
		connection.close();
	}
	
	/**
	 * Destory.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@After
	public void destory() throws Exception {
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(true);
		Statement statement = connection.createStatement();
		statement.execute(deleteSql);
		statement.close();
		connection.close();
	}
}
