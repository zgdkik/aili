package org.hbhk.maikkr.core.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionResolver implements HandlerExceptionResolver {

	private Log log = LogFactory.getLog(getClass());

	private String error404;
	private String excption;

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView modelAndView = new ModelAndView("error404.html");
		if (ex != null) {
			log.error("内部错误:", ex);
			modelAndView = new ModelAndView(excption);
		} else {
			modelAndView = new ModelAndView(error404);
		}
		return modelAndView;
	}

	public String getError404() {
		return error404;
	}

	public void setError404(String error404) {
		this.error404 = error404;
	}

	public String getExcption() {
		return excption;
	}

	public void setExcption(String excption) {
		this.excption = excption;
	}

}
