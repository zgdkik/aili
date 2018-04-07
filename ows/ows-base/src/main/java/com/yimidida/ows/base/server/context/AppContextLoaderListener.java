package com.yimidida.ows.base.server.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.yimidida.ymdp.core.server.deploy.ModuleManager;
import com.yimidida.ymdp.core.share.consts.AppConst;



/**
 * 扩展spring的ContextLoaderListener
 * 在容器启动时增加配置参数
 * 
 * 配合使用方法：
 * web.xml中增加
 * <context-param>
 *      <param-name>override</param-name>
 *      <param-value>false</param-value>
 * </context-param>
 * <listener>
 *      <listener-class>org.hbhk.aili.core.server.listener.AppContextLoaderListener</listener-class>
 * </listener>
 */
public class AppContextLoaderListener extends ContextLoaderListener {
	
	private final Log log = LogFactory.getLog(getClass());
	
	private static ServletContext servletContext;
	
	public static String resources;

	public static ServletContext getServletContext() {
		return servletContext;
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("appcontext",
				sce.getServletContext().getContextPath());
		log.info("appcontext:" + sce.getServletContext().getContextPath());
		servletContext = sce.getServletContext();
        ModuleManager.export(servletContext);
        resources = sce.getServletContext().getInitParameter("resources");
        String activeProfile= sce.getServletContext().getInitParameter("spring.profiles.active");
        if("${env}".equals(activeProfile)){
        	activeProfile = "dev";
        }
    	System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, activeProfile);
		super.contextInitialized(sce);
	}
	
    /** 
     * 自定义扩展context参数
     */
    @Override
    protected void customizeContext(ServletContext servletContext,
            ConfigurableWebApplicationContext applicationContext) {
        XmlWebApplicationContext context = (XmlWebApplicationContext) applicationContext;  
        String override = servletContext.getInitParameter(AppConst.ALLOW_BEAN_DEFINITION_OVERRIDING);
        if(StringUtils.isNotBlank(override)) {
            context.setAllowBeanDefinitionOverriding(Boolean.parseBoolean(override));
        } else {
            context.setAllowBeanDefinitionOverriding(true); 
        }
    }

}