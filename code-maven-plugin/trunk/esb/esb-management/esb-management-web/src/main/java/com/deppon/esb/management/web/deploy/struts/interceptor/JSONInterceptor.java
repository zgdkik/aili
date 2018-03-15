package com.deppon.esb.management.web.deploy.struts.interceptor;

import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.deppon.esb.management.web.deploy.struts.result.json.JSONPopulator;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @Description JSON反序列化拦截器对JSON格式提交的数据反序列化并绑定到Action
 * @author HuangHua
 * @Date 2012-4-12
 *
 */
@Component("jsonInterceptor")
public class JSONInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = -3423290391156261612L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JSONInterceptor.class);
	
	/**
	 * 序列化及缓存类
	 */
	protected static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * 指定反序列化对象名
	 */
	private String root;

	/**
	 * JSON格式反序列化并绑定到Action中的属性上
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 * intercept
	 * @param invocation
	 * @return
	 * @throws Exception
	 * @since:
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String contentType = request.getHeader("content-type");
		if (contentType != null) {
			int iSemicolonIdx;
			if ((iSemicolonIdx = contentType.indexOf(";")) != -1)
				contentType = contentType.substring(0, iSemicolonIdx);
		}

		Object rootObject;
		if (this.root != null) {
			ValueStack stack = invocation.getStack();
			rootObject = stack.findValue(this.root);

			if (rootObject == null) {
				throw new RuntimeException("Invalid root expression: '"
						+ this.root + "'.");
			}
		} else {
			rootObject = invocation.getAction();
		}

		Class<?> clazz = invocation.getAction().getClass();
		String methodName = invocation.getProxy().getMethod();
		Method method = ReflectionUtils.findMethod(clazz, methodName);
		//如果在Action方法上加入JSON的注解，那么就可以在应用这个方法的时候，把错误的结果信息转换为json
	    if ("application/json".equalsIgnoreCase(contentType) || AnnotationUtils.isAnnotationDeclaredLocally(JSON.class, clazz)
	        	|| (method != null && method.isAnnotationPresent(JSON.class))) {
			BufferedReader reader = request.getReader();
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			if (stringBuilder.toString().length() > 0) {
				@SuppressWarnings("rawtypes")
				Map json = MAPPER.readValue(stringBuilder.toString(), Map.class);
				JSONPopulator.populateObject(rootObject, json);
			}
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Content type must be 'application/json' or 'application/json-rpc'. Ignoring request with content type "
						+ contentType);
			}
		}
		return invocation.invoke();
	}

}