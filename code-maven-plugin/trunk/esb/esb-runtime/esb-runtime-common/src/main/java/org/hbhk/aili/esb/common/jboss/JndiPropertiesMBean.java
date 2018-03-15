package org.hbhk.aili.esb.common.jboss;

import java.util.Properties;

import javax.naming.NamingException;

import org.jboss.system.ServiceMBean;

public interface JndiPropertiesMBean extends ServiceMBean {
	//获取jndi的名字
	public String getJndiName();
	//设置jndi名称，完成绑定的动作
    public void setJndiName(String jndiName) throws NamingException;
	//存放相关key-value属性值
    public Properties getProperties();
	//设置相关key-value属性值
    public void setProperties(Properties properties) throws NamingException;
    
}
