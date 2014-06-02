package org.hbhk.mvc.server.servlet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.mvc.server.ant.AntUrlMatcher;
import org.hbhk.mvc.server.mapping.RequestMapping;
import org.hbhk.mvc.server.mapping.UrlMapping;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1783344744661641142L;
	private Log log = LogFactory.getLog(getClass());

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		AntUrlMatcher anturl = new AntUrlMatcher(req);
		UrlMapping urlMapping = new UrlMapping(resp);
		String url = anturl.getRequestUrl();
		log.debug("requestUrl:" + url);
		// 匹配url是否存在
		// urlMapping.matchUrl(url);
		// 获取参数
		// 请求处理
		doDispatcher(url, req);
	}

	private void doDispatcher(String url, HttpServletRequest req) {
		// Map<String, String> methodToClass = UrlMapping.URLCHCHE.get(url);
		// Iterator<Entry<String, String>> ites = methodToClass.entrySet()
		// .iterator();
		// Entry<String, String> entry;
		String className;
		// while (ites.hasNext()) {
		// entry = ites.next();
		// methodName = entry.getKey();
		// className = entry.getValue();
		// }
		// 执行对应的类方法
		className = "org.hbhk.mvc.server.controler.TestControler";
		try {
			Class<?> c = Class.forName(className);
			// 只获取public 方法
			Method[] methods = c.getMethods();
			Method method = null;
			for (Method m : methods) {
				RequestMapping mapping = m.getAnnotation(RequestMapping.class);
				if (mapping != null) {
					String value = mapping.value();
					if (url.endsWith(value)) {
						method = m;
						break;
					}
				}
			}

			Class<?>[] parameterTypes = method.getParameterTypes();
			Class<?>[] classes = new Class<?>[parameterTypes.length];
			String strargs = "java.lang.String";
			for (int i = 0; i < classes.length; i++) {
				classes[i] = strargs.getClass();
			}
			Object[]  args = new Object[classes.length];
			for (int i = 0; i < args.length; i++) {
				
			}
			req.getParameterMap();
			Object obj = (Object) c.newInstance();
			method.invoke(obj, args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static Object arrayGrow(Object obj, int addLength) {
		Class<?> clazz = obj.getClass();
		if (!clazz.isArray()) {
			return null;
		}
		Class<?> componentType = clazz.getComponentType();
		int length = Array.getLength(obj);
		int newLength = length + addLength;
		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(obj, 0, newArray, 0, length);
		return newArray;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
}
