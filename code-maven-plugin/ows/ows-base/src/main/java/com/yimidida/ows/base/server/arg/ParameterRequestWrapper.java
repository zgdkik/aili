package com.yimidida.ows.base.server.arg;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {

	private Map<String, String[]> params = new LinkedHashMap<String, String[]>(
			16);;

	public ParameterRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return params;
	}

	@Override
	public Enumeration<Object> getParameterNames() {
		Vector<Object> l = new Vector<Object>(params.keySet());
		return l.elements();
	}

	@Override
	public String[] getParameterValues(String name) {
		Object v = params.get(name);
		if (v == null) {
			return null;
		} else if (v instanceof String[]) {
			return (String[]) v;
		} else if (v instanceof String) {
			return new String[] { (String) v };
		} else {
			return new String[] { v.toString() };
		}
	}

	@Override
	public String getParameter(String name) {
		Object v = params.get(name);
		if (v == null) {
			return null;
		} else if (v instanceof String[]) {
			String[] strArr = (String[]) v;
			if (strArr.length > 0) {
				return strArr[0];
			} else {
				return null;
			}
		} else if (v instanceof String) {
			return (String) v;
		} else {
			return v.toString();
		}
	}

	public void setParameter(String name, String value) {
		if (name == null) {
			throw new RuntimeException("Parameter name must not be null");
		}
		params.put(name, new String[] { value });
	}

	public void setParameter(String name, String[] values) {
		if (name == null) {
			throw new RuntimeException("Parameter name must not be null");
		}
		params.put(name, values);
	}

}
