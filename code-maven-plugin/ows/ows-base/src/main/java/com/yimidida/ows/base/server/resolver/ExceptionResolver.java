package com.yimidida.ows.base.server.resolver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.yimidida.ymdp.core.share.ex.BusinessException;

public class ExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger log = LoggerFactory
			.getLogger(ExceptionResolver.class);

	@Override
	protected ModelAndView getModelAndView(String viewName,
			Exception exception, HttpServletRequest request) {

		log.error(exception.getMessage(), exception);

		ModelAndView mv = new ModelAndView(viewName);
		Map<String, Object> exceptionMap = new HashMap<String, Object>();
		if (exception instanceof BusinessException) {
			BusinessException businessException = (BusinessException) exception;
			exceptionMap.put("code", businessException.getErrCode());
			exceptionMap.put("success", false);
			exceptionMap.put("msg", exception.getMessage());
		} else {
			exceptionMap.put("code", "500");
			exceptionMap.put("msg", "系统内部错误");
			exceptionMap.put("success", false);
			Writer w = new StringWriter();
			exception.printStackTrace(new PrintWriter(w));
			exceptionMap.put("stackTrace", w.toString());
		}
		if (request.getHeader("X-Requested-With") != null) {
			mv.setView(new MappingJackson2JsonView());
		}
		mv.addAllObjects(exceptionMap);
		return mv;
	}

}
