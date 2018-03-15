package org.hbhk.aili.esb.common.jboss;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;

import org.jboss.system.ServiceMBeanSupport;
import org.jboss.util.naming.NonSerializableFactory;

/**
 * 
         最后修改时间： 2013-4-15
         描        述：
    <pre>
    JBOSSMBean的扩展，为了获取属性的配置，在jboss部署目录下相关的ds文件配置示例如下：
    </pre>
    <pre>
    	 <mbean code="com.deppon.esb.util.jboss.JndiProperties" 
           name="Jndi.Properties:service=JndiProperties">
			<attribute name="JndiName">jndi/properties</attribute>
			<depends>jboss:service=Naming</depends>
			<attribute name="Properties">
				queueManager=MQM1
				hostName=192.168.17.141
				channel=ESB.INTFACE.CLIENT
				port=1429
				CCSID=1208
			</attribute>
		</mbean>
    </pre>
         更新纪录：
 */
public class JndiProperties extends ServiceMBeanSupport implements JndiPropertiesMBean {

	//jndi名字
	private String jndiName;
	
	//存放相关key-value属性值
	private Properties properties;
	
	public Properties getProperties() {
		return properties;
	}
	
	public void setProperties(Properties properties) throws NamingException {
		this.properties = properties;
	}
	
	public String getJndiName() {
		return jndiName;
	}
	
	/**
	 * 
	 * @descriptor:设置jndi名称，完成绑定的动作
	 * @author davidcun
	 * @date 2013-4-15 上午11:19:04
	 * @param jnidName String类型的参数，表示Jboss 服务Bean向外发布的服务名
	 * @return
	 * @see com.deppon.esb.util.jboss.JndiPropertiesMBean#setJndiName(java.lang.String)
	 */
	public void setJndiName(String jndiName) throws NamingException {
		String oldName = this.jndiName;
		this.jndiName = jndiName;
		if (super.getState() == STARTED) {
			unbind(oldName);
			try {
				rebind();
			} catch (Exception e) {
				NamingException ne = new NamingException(
						"Failed to update jndiName");
				ne.setRootCause(e);
				throw ne;
			}
		}
	}

	public void startService() throws Exception {
		rebind();
	}

	public void stopService() {
		unbind(jndiName);
	}

	/**
	 * 
	 * @descriptor:绑定相关的命名服务到指定的对象
	 * @author davidcun
	 * @date 2013-4-15 上午11:20:36
	 * @param
	 * @return
	 */
	private void rebind() throws NamingException {
		InitialContext rootCtx = new InitialContext();
		Name fullName = rootCtx.getNameParser("").parse(jndiName);
		log.info("fullName=" + fullName);
		//下面这个代码是重点，表示把相应的对象绑定指定
		NonSerializableFactory.rebind(fullName, properties, true);
		
	}

	/**
	 * 
	 * @descriptor:卸载相关的命名服务
	 * @author davidcun
	 * @date 2013-4-15 上午11:21:50
	 * @param
	 * @return
	 */
	private void unbind(String jndiName) {
		try {
			InitialContext rootCtx = new InitialContext();
			rootCtx.unbind(jndiName);
			NonSerializableFactory.unbind(jndiName);
		} catch (NamingException e) {
			log.error("Failed to unbind map", e);
		}
	}

}
