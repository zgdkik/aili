/**
 * @Title AppContextListener.java
 * @Package com.deppon.esb.management.web.deploy.struts
 * @Description TODO
 * @author HuangHua
 * @date 2012-4-16 下午11:02:57
 */
package com.deppon.esb.management.web.deploy;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description 应用上下文监听器
 * @author HuangHua
 * @date 2012-4-16 下午11:02:57
 */
public class AppContextListener implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

    /**
     * 初始化应用上下文
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     * contextInitialized
     * @param sce
     * @since:0.7
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String staticServerAddress = sc.getInitParameter("staticServerAddress");
        AppContext.initAppContext(staticServerAddress);
        
    }

}