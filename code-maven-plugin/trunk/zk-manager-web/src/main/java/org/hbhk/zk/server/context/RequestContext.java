package org.hbhk.zk.server.context;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.zk.server.controller.ZkClientController;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Classname:RequestContext
 * @Description:请求的上下文信息
 * 
 */
public final class RequestContext {
	private static Log log = LogFactory.getLog(RequestContext.class);

	private static ThreadLocal<RequestContext> context = new ThreadLocal<RequestContext>() {
		@Override
		protected RequestContext initialValue() {
			return new RequestContext();
		}
	};

	private String zkHost;
	private String root;

	public static RequestContext getCurrentContext() {
		return context.get();

	}

	public static void remove() {
		context.remove();
	}

	public static HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra)
				.getRequest();
		return request;
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static void setSessionAttribute(String name, Object value) {
		getSession().setAttribute(name, value);
	}

	public static void setSessionAttributes(Map<String, Object> attrs) {
		if (attrs == null || attrs.size() == 0) {
			log.warn("attrs is null");
			return;
		}
		for (Entry<String, Object> entry : attrs.entrySet()) {
			String key = entry.getKey();
			if (StringUtils.isEmpty(key)) {
				log.error("attrs is null");
			}
			Object value = entry.getValue();
			getSession().setAttribute(key, value);
		}
	}

	public static void setRequestAttribute(String name, Object value) {
		getRequest().setAttribute(name, value);
	}

	public static void setRequestAttributes(Map<String, Object> attrs) {
		if (attrs == null || attrs.size() == 0) {
			log.warn("attrs is null");
			return;
		}
		for (Entry<String, Object> entry : attrs.entrySet()) {
			String key = entry.getKey();
			if (StringUtils.isEmpty(key)) {
				log.error("attrs is null");
			}
			Object value = entry.getValue();
			getRequest().setAttribute(key, value);
		}
	}

	public static String getZkHost() {
		RequestContext requestContext = getCurrentContext();
		return requestContext.zkHost;
	}

	public static void setZkHost(String zkHost) {
		RequestContext requestContext = getCurrentContext();
		requestContext.zkHost = zkHost;
	}

	public static String getRoot() {
		RequestContext requestContext = getCurrentContext();
		return requestContext.root;
	}

	public static void setRoot(String root) {
		RequestContext requestContext = getCurrentContext();
		requestContext.root = root;
	}

	public static ZkClient getZkClient() {
		ZkClient zkClient = (ZkClient) RequestContext.getSession()
				.getAttribute("zkClient");
		if(zkClient == null){
			zkClient = ZkClientController.zkMap.get(getZkHost());
		}
		if(zkClient == null){
			return zkClient;
		}
		zkClient.setZkSerializer(new ZkSerializer() {
			public byte[] serialize(Object data) throws ZkMarshallingError {
				return data.toString().getBytes();
			}

			public Object deserialize(byte[] bytes) throws ZkMarshallingError {
				return new String(bytes);
			}
		});
		return zkClient;
	}

	public static void setZkClient(ZkClient zkClient) {
		setSessionAttribute("zkClient", zkClient);
	}

}
