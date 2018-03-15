package org.hbhk.aili.esb.common.spring.dynamic;

/**
 * 动态bean描述对象.
 */
public abstract class DynamicBean {
	
	/** The bean name. */
	protected String beanName;
	
	/** The name space. */
	protected String nameSpace;

	/**
	 * Instantiates a new dynamic bean.
	 * 
	 * @param beanName
	 *            the bean name
	 */
	public DynamicBean(String beanName) {
		this.beanName = beanName;
	}

	/**
	 * 获取bean 的xml描述.
	 * 
	 * @return the bean xml
	 */
	protected abstract String getBeanXml();

	/**
	 * 生成完整的xml字符串.
	 * 
	 * @return the xml
	 */
	public String getXml() {
		StringBuffer buf = new StringBuffer();
		if (nameSpace != null && !"".equals(nameSpace)) {
			buf.append(nameSpace).append("\n").append(getBeanXml()).append("\n").append("</beans>");
			return buf.toString();
		} else {
			buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
					.append("<beans xmlns=\"http://www.springframework.org/schema/beans\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"")
					.append("       xmlns:p=\"http://www.springframework.org/schema/p\" xmlns:aop=\"http://www.springframework.org/schema/aop\"")
					.append("       xmlns:context=\"http://www.springframework.org/schema/context\" xmlns:jee=\"http://www.springframework.org/schema/jee\"")
					.append("       xmlns:tx=\"http://www.springframework.org/schema/tx\"")
					.append("       xsi:schemaLocation=\"")
					.append("           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd")
					.append("           http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd")
					.append("           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd")
					.append("           http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-3.0.xsd")
					.append("           http://www.springframework.org/schema/tx http://www.springframework.org/schema/schema/tx/spring-tx-3.0.xsd\">")
					.append("\n").append(getBeanXml()).append("\n").append("</beans>");
			return buf.toString();
		}
	}

	/**
	 * Gets the bean name.
	 * 
	 * @return the bean name
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * Sets the bean name.
	 * 
	 * @param beanName
	 *            the new bean name
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	/**
	 * Gets the name space.
	 * 
	 * @return the name space
	 */
	public String getNameSpace() {
		return nameSpace;
	}

	/**
	 * 请参照 getXml() 里的方法设置。小心出错！.
	 * 
	 * @param nameSpace
	 *            the new name space
	 */
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
}
