package com.deppon.esb.management.web.deploy.struts.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.deppon.esb.management.web.deploy.AppContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Component("resourceInterceptor")
public class ResourceInterceptor extends AbstractInterceptor {

//	private Log logger = LogFactory.getLog(getClass());
//	private static Properties properties = new Properties();

	private static final long serialVersionUID = -2975703083847796781L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		InputStream in = getClass().getResourceAsStream("/resources.properties");
//		properties.load(in);
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
//		Enumeration<Object> maps = properties.keys();
//		while (maps.hasMoreElements()) {
//			String key = (String) maps.nextElement();
//			request.setAttribute(key, properties.get(key).toString());
//			if (logger.isDebugEnabled()) {
//				logger.debug("key:" + key + " , value:" + properties.get(key).toString());
//			}
//		}
		//设置静态资源服务器地址
        request.setAttribute("extjs4", AppContext.getAppContext().getStaticServerAddress());
		return invocation.invoke();
	}
}
