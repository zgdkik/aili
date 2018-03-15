package org.hbhk.aili.core.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.context.RequestContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @Description: aili框架核心处理和基本支持
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class ModuleInterceptor extends HandlerInterceptorAdapter {

	private static  Log log = LogFactory.getLog(ModuleInterceptor.class);
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug("模块处理开始...");
		if (!(handler instanceof HandlerMethod)) {
			return;
		}
		HandlerMethod m = (HandlerMethod) handler;
		RequestMapping rm = m.getBean().getClass().getAnnotation(RequestMapping.class);
		String moduleName = null;
		if (rm != null && rm.value().length > 0) {
			moduleName = rm.value()[0];
		} else {
			String clsName = m.getBean().getClass().getName();
			String[] arr = clsName.split("\\.");
			moduleName = arr[3];
		}
		// 获取模块名
		String contextPath = request.getContextPath();
		request.setAttribute("base", contextPath);
		if (modelAndView != null
				&& !StringUtils.isEmpty(modelAndView.getViewName())) {
			if (!moduleName.startsWith("/")) {
				moduleName = "/" + moduleName;
			}
			log.debug("模块名:"+moduleName);
			String viewName = modelAndView.getViewName();
			if (filter(viewName)) {
				if (!viewName.startsWith("/")) {
					viewName = "/" + viewName;
				}
				modelAndView.setViewName(moduleName + viewName);

				request.setAttribute("images", contextPath + "/images"
						+ moduleName);
				request.setAttribute("scripts", contextPath + "/scripts"
						+ moduleName);
				request.setAttribute("styles", contextPath + "/styles"
						+ moduleName);
				moduleName = moduleName.substring(moduleName.indexOf("/") + 1,
						moduleName.length());
				RequestContext.setCurrentContext(moduleName);
				log.debug("视图路径:"+(moduleName+ viewName));
			}

		}
		String lang = request.getLocale().toString();
		request.setAttribute("lang",lang);
		log.debug("当前语言:"+lang);
		log.debug("模块处理结束...");
	}

	private boolean filter(String viewName) {
		if (viewName.startsWith("redirect:")) {
			return false;
		}
		if (viewName.startsWith("http:")) {
			return false;
		}
		if (viewName.startsWith("https:")) {
			return false;
		}
//		if (MyStringUtils.mulStrCount(viewName, "/") > 1) {
//			return false;
//		}

		return true;

	}
}
