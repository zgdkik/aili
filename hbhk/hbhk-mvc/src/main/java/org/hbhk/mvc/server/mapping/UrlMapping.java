package org.hbhk.mvc.server.mapping;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

public class UrlMapping {
	// url 方法 类
	public static Map<String, String> URLCHCHE = new ConcurrentHashMap<String, String>();
	HttpServletResponse resp;

	public UrlMapping(HttpServletResponse resp) {
		this.resp = resp;
	}

	public void matchUrl(String url) throws IOException {
		if (!URLCHCHE.containsKey(url)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "url not found");
		}
	}
}
