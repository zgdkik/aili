package org.hbhk.aili.esb.server.agent.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.hbhk.aili.esb.common.constant.EsbRouteConstant;

public class URLResolution {

	public static Map<String, Object> parsing(Map<String, Object> headers) {
		Map<String, Object> map = new HashMap<String, Object>();
		String path = (String) headers.get(Exchange.HTTP_PATH);
		String code = null;
		String msg = null;
		String[] paths = path.split("/");
		// 去除paths第一个参数为""空串
		if (paths.length > 1) {
			code = paths[1];
			msg = path.substring(code.length() + 1);
		}
		map.put(EsbRouteConstant.SERVICE_CODE, code == null ? "" : code);
		map.put(EsbRouteConstant.MESSAGE_BOLB, msg == null ? "" : msg);
		return map;
	}
	
}
