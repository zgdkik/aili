package org.hbhk.aili.base.server.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.base.server.annotation.RemoveRepeatSubmit;
import org.hbhk.aili.base.server.annotation.RepeatSubmit;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RepeatSubmitInterceptor extends HandlerInterceptorAdapter {

	public final String repeatSubmit = "repeatSubmit";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
			RemoveRepeatSubmit removeRepeatSubmit = method.getAnnotation(RemoveRepeatSubmit.class);
			if (annotation != null) {
				String token = UUID.randomUUID().toString();
				request.getSession(true).setAttribute(repeatSubmit, token);
				request.setAttribute(repeatSubmit, token);
			}
			if (removeRepeatSubmit != null) {
				if (isRepeatSubmit(request)) {
					return false;
				}
				request.getSession(true).removeAttribute(repeatSubmit);
			}
			return true;
		} else {
			return true;
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(true).getAttribute(
				repeatSubmit);
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter(repeatSubmit);
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}

}
