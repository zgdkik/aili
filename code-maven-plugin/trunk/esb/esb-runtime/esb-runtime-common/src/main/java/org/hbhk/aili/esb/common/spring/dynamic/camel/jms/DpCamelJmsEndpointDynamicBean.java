package org.hbhk.aili.esb.common.spring.dynamic.camel.jms;

import org.hbhk.aili.esb.common.spring.dynamic.DynamicBean;

/**
 * DynamicCamelJmsEndpoint实体类.
 * 
 * @author HuangHua
 */
public class DpCamelJmsEndpointDynamicBean extends DynamicBean {

	/** The jms connection factory name. */
	private String jmsConnectionFactoryName;// 连接工厂名
	
	/** The queue name. */
	private String queueName;// 队列名/主题名。<queueName>/<topic:topicName>
	
	/** The operation. */
	private String operation;// 其他参数

	
	/**
	 * beanName can not be null.
	 * 
	 * @param beanName
	 *            the bean name
	 */
	public DpCamelJmsEndpointDynamicBean(String beanName) {
		super(beanName);
	}
	
	/**
	 * Instantiates a new dp camel jms endpoint dynamic bean.
	 * 
	 * @param beanName
	 *            the bean name
	 * @param jmsConnectionFactoryName
	 *            the jms connection factory name
	 * @param queueName
	 *            the queue name
	 * @param operation
	 *            the operation
	 */
	public DpCamelJmsEndpointDynamicBean(String beanName, String jmsConnectionFactoryName, String queueName,
			String operation) {
		super(beanName);
		this.jmsConnectionFactoryName = jmsConnectionFactoryName;
		this.queueName = queueName;
		this.operation = operation;
	}



	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:19
	 * @see org.hbhk.aili.esb.common.spring.dynamic.DynamicBean#getBeanXml()
	 */
	@Override
	protected String getBeanXml() {
		if (jmsConnectionFactoryName == null || queueName == null || "".equals(jmsConnectionFactoryName)
				|| "".equals(queueName)) {
			return null;
		} else {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("<camel:endpoint id=\"").append(beanName).append("\"")
			.append("  uri=\"")
			.append(jmsConnectionFactoryName).append(":").append(queueName);
			if(operation != null && !"".equals(operation)){
				stringBuffer.append("?").append(operation);
			}
			stringBuffer.append("\" />");
			return stringBuffer.toString();
		}
	}

	/**
	 * Gets the jms connection factory name.
	 * 
	 * @return the jms connection factory name
	 */
	public String getJmsConnectionFactoryName() {
		return jmsConnectionFactoryName;
	}

	/**
	 * Sets the jms connection factory name.
	 * 
	 * @param jmsConnectionFactoryName
	 *            the new jms connection factory name
	 */
	public void setJmsConnectionFactoryName(String jmsConnectionFactoryName) {
		this.jmsConnectionFactoryName = jmsConnectionFactoryName;
	}

	/**
	 * Gets the queue name.
	 * 
	 * @return the queue name
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * Sets the queue name.
	 * 
	 * @param queueName
	 *            the new queue name
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * Gets the operation.
	 * 
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operation
	 *            the new operation
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

}
