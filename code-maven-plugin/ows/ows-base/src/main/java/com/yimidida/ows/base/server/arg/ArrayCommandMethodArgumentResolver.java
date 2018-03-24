package com.yimidida.ows.base.server.arg;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.yimidida.ows.base.server.annotation.ArrayCommand;

public class ArrayCommandMethodArgumentResolver implements
		HandlerMethodArgumentResolver {
	public boolean supportsParameter(MethodParameter parameter) {
		if (!parameter.hasParameterAnnotation(ArrayCommand.class)) {
			return false;
		}
		if (!parameter.getParameterType().isArray()) {
			return false;
		}
		return true;
	}

	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		ArrayCommand c = (ArrayCommand) parameter
				.getParameterAnnotation(ArrayCommand.class);
		String parameterName = c.name();
		Class<?> itemClass = parameter.getParameterType().getComponentType();
		if ("".equals(parameterName)) {
			parameterName = parameter.getParameterName();
		}
		if (c.dataBind()) {
			String[] paramStrs = webRequest.getParameterValues(parameterName);
			if (paramStrs == null) {
				paramStrs = webRequest.getParameterValues(parameterName + "[]");
			}
			if (paramStrs == null) {
				return Array.newInstance(itemClass, 0);
			}
			Object rtn = Array.newInstance(itemClass, paramStrs.length);
			DataBinder binder = new DataBinder(null);
			int i = 0;
			for (String s : paramStrs) {
				Array.set(rtn, i,
						binder.convertIfNecessary(s, itemClass, parameter));
				i++;
			}
			return rtn;
		}
		List<String> candidateParamNames = new ArrayList<String>();
		Iterator<String> spit = webRequest.getParameterNames();
		while (spit.hasNext()) {
			String paramName = (String) spit.next();
			if (paramName.startsWith(parameterName + ".")) {
				candidateParamNames.add(paramName);
			}
		}
		if (candidateParamNames.size() == 0) {
			return Array.newInstance(itemClass, 0);
		}
		Map<String, String[]> paramValues = new HashMap<String, String[]>();
		int size = -1;
		for (String cpname : candidateParamNames) {
			String[] paramStrs = webRequest.getParameterValues(cpname);
			if (paramStrs == null) {
				paramStrs = new String[0];
			}
			if (size < 0) {
				size = paramStrs.length;
			} else if (size != paramStrs.length) {
				throw new IllegalArgumentException(
						"It seems that the number of "
								+ cpname
								+ " is not right, it is different with others: "
								+ paramStrs.length + "|" + size);
			}
			String propName = cpname.substring(parameterName.length() + 1);
			if (propName.endsWith("[]")) {
				propName = propName.substring(0, propName.length() - 2);
			}
			paramValues.put(propName, paramStrs);
		}
		Object rtn = Array.newInstance(itemClass, size);
		for (int i = 0; i < size; i++) {
			Object obj = BeanUtils.instantiateClass(itemClass);
			DataBinder db = new DataBinder(obj);
			Map<String, String> paramMap = new HashMap<String, String>();
			for (String key : paramValues.keySet()) {
				paramMap.put(key, ((String[]) paramValues.get(key))[i]);
			}
			db.bind(new MutablePropertyValues(paramMap));
			Array.set(rtn, i, db.getTarget());
		}
		return rtn;
	}
}
