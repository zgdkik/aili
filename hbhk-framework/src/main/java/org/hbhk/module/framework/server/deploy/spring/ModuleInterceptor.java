package org.hbhk.module.framework.server.deploy.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hbhk.module.framework.server.context.RequestContext;
import org.hbhk.module.framework.server.context.ResourceRoot;
import org.hbhk.module.framework.shared.util.MyStringUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ModuleInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if (!(handler instanceof HandlerMethod)) {
			return;
		}
		HandlerMethod m =(HandlerMethod) handler;;
		RequestMapping requestMapping = m.getBean().getClass()
				.getAnnotation(RequestMapping.class);
		String contextPath = request.getContextPath() + "/";
		String moduleName = "";
		if (requestMapping != null && modelAndView != null
				&& !StringUtils.isEmpty(modelAndView.getViewName())) {

			String viewName = modelAndView.getViewName();
			if (MyStringUtils.mulStrCount(viewName, "/") > 1) {
				if (viewName.startsWith("/")) {
					moduleName = viewName.substring(
							viewName.indexOf("/") + 1,
							viewName.substring(viewName.indexOf("/") + 1,
									viewName.length()).indexOf("/") + 1);
				} else {
					moduleName = viewName.substring(0, viewName.indexOf("/"));
				}
				modelAndView.setViewName(viewName);
			} else {
				String[] modules = requestMapping.value();
				moduleName = modules[0];
				if (viewName.startsWith("/")) {
					viewName = viewName.substring(viewName.indexOf("/") + 1,
							viewName.length());
				}
				modelAndView.setViewName(modules[0] + "/" + viewName);
			}
		}
		if (!moduleName.startsWith("/")) {
			moduleName = "/" + moduleName;
		}
		RequestContext.setCurrentContext(moduleName.substring(
				moduleName.indexOf("/") + 1, moduleName.length()));
		request.setAttribute("images", contextPath
				+ ResourceRoot.resourcePrefix + "/images" + moduleName);
		request.setAttribute("scripts", contextPath
				+ ResourceRoot.resourcePrefix + "/scripts" + moduleName);
		request.setAttribute("styles", contextPath
				+ ResourceRoot.resourcePrefix + "/styles" + moduleName);

		super.postHandle(request, response, handler, modelAndView);
	}

}
