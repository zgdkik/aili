package org.hbhk.aili.route.jms.common.spring.dynamic.camel.jms.collectdata;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.common.spring.dynamic.camel.jms.DpCamelJmsEndpointDynamicBean;
import org.springframework.core.io.ClassPathResource;

//@Repository
/**
 * The Class JmsEndpointDao.
 */
public class JmsEndpointDao {
	
	/** The Constant JDBC_PROPERTY_FILE. */
	public static final String JDBC_PROPERTY_FILE = "com/deppon/esb/common/META-INF/jdbc.properties";
	
	/** The log. */
	private static Log log = LogFactory.getLog(JmsEndpointDao.class);

	/**
	 * Query jm sconfig.
	 * 
	 * @return the list
	 * @throws SQLException
	 *             the sQL exception
	 */
	public List<DpCamelJmsEndpointDynamicBean> queryJMSconfig()
			throws SQLException {
		// TODO Auto-generated method stub
		// 查询出服务编码和对于的队列名，并标记是接收消息还是发送消息。接收消息的话，需要配置Operation。

		List<DpCamelJmsEndpointDynamicBean> dynamicBeans = new ArrayList<DpCamelJmsEndpointDynamicBean>();
		List<SvcAddrBean> svcAddrBeans = loadConfig();
		for (SvcAddrBean svcAddrBean : svcAddrBeans) {
			DpCamelJmsEndpointDynamicBean requestBean = new DpCamelJmsEndpointDynamicBean(
					svcAddrBean.getSvcCode());
			DpCamelJmsEndpointDynamicBean responseBean = new DpCamelJmsEndpointDynamicBean(
					svcAddrBean.getSvcCode());
			if ("F".equals(svcAddrBean.getFrntOrBck())) {// ESB提供给前端的服务配置
				requestBean.setQueueName(svcAddrBean.getReqeustAddr());// 接收
				requestBean.setOperation("selector="
						+ ESBServiceConstant.ESB_SERVICE_CODE + "='"
						+ svcAddrBean.getSvcCode()
						+ "'&amp;disableReplyTo=true");

				responseBean.setQueueName(svcAddrBean.getResponseAddr());// 发送
				responseBean.setOperation("disableReplyTo=true");
			} else if ("B".equals(svcAddrBean.getFrntOrBck())) {// ESB提供给后端的服务配置
				requestBean.setQueueName(svcAddrBean.getResponseAddr());// 发送
				requestBean.setOperation("disableReplyTo=true");

				responseBean.setQueueName(svcAddrBean.getReqeustAddr());// 接收
				responseBean.setOperation("selector="
						+ ESBServiceConstant.BACK_SERVICE_CODE + "='"
						+ svcAddrBean.getSvcCode()
						+ "'&amp;disableReplyTo=true");
			}

			dynamicBeans.add(requestBean);
			dynamicBeans.add(responseBean);
		}

		return dynamicBeans;

	}

	/**
	 * Load config.
	 * 
	 * @return the list
	 */
	public List<SvcAddrBean> loadConfig() {
		List<SvcAddrBean> svcAddrBeans = new ArrayList<SvcAddrBean>();
		DataSource datasource = null;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			datasource = createDataSource(JDBC_PROPERTY_FILE);
			connection = datasource.getConnection();
			st = connection.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT ");
			stringBuilder
					.append(" SVCCODE, FRNTORBCK, REQUESTADDR, RESPONSEADDR");
			stringBuilder
					.append(" FROM TEST_ESB2_SVCPOINT T WHERE T.SVCAGRMT='JMS'");
			rs = st.executeQuery(stringBuilder.toString());
			while (rs != null && rs.next()) {
				int i = 1;//sonar说3和4是幻数，所以我就这样做了
				String svcCode = rs.getString(i++);
				String frntOrBck = rs.getString(i++);
				String reqeustAddr = rs.getString(i++);
				String responseAddr = rs.getString(i++);
				SvcAddrBean svcAddrBean = new SvcAddrBean();
				svcAddrBean.setFrntOrBck(frntOrBck);
				svcAddrBean.setReqeustAddr(reqeustAddr);
				svcAddrBean.setResponseAddr(responseAddr);
				svcAddrBean.setSvcCode(svcCode);
				
				svcAddrBeans.add(svcAddrBean);
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		}
		return svcAddrBeans;
	}

	/**
	 * 创建datasource.
	 * 
	 * @param propertyFilename
	 *            the property filename
	 * @return the data source
	 * @throws Exception
	 *             the exception
	 */
	public DataSource createDataSource(String propertyFilename)
			throws Exception {
		DataSource datasource = null;
		Properties properties = new Properties();
		ClassPathResource resource = new ClassPathResource(propertyFilename);
		properties.load(resource.getInputStream());
		//datasource = BasicDataSourceFactory.createDataSource(properties);
		return datasource;
	}

}

class SvcAddrBean implements Serializable {
	private static final long serialVersionUID = 4513399643384542967L;
	private String svcCode;
	private String frntOrBck;
	private String reqeustAddr;
	private String responseAddr;

	public String getSvcCode() {
		return svcCode;
	}

	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}

	public String getFrntOrBck() {
		return frntOrBck;
	}

	public void setFrntOrBck(String frntOrBck) {
		this.frntOrBck = frntOrBck;
	}

	public String getReqeustAddr() {
		return reqeustAddr;
	}

	public void setReqeustAddr(String reqeustAddr) {
		this.reqeustAddr = reqeustAddr;
	}

	public String getResponseAddr() {
		return responseAddr;
	}

	public void setResponseAddr(String responseAddr) {
		this.responseAddr = responseAddr;
	}
}
