package org.hbhk.module.framework.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private UrlMatcher urlMatcher = new AntUrlPathMatcher();

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public MyInvocationSecurityMetadataSource() {
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca = new SecurityConfig("ROLE_USER");
		atts.add(ca);
		resourceMap.put("/framework/saveUser", atts);
		resourceMap.put("/mo1/index", atts);
		resourceMap.put("/hbhk-m2/index.action", atts);
		resourceMap.put("/framework/insertUser", atts);
		resourceMap.put("/framework/welcome", atts);
		resourceMap.put("/framework/uploadFile", atts);
		resourceMap.put("/framework/fileupload", atts);
		resourceMap.put("/framework/download", atts);
		
		Collection<ConfigAttribute> attsno = new ArrayList<ConfigAttribute>();
		ConfigAttribute cano = new SecurityConfig("ROLE_NO");
		attsno.add(cano);
		resourceMap.put("/user/empList", attsno);
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;

	}

	public Collection<ConfigAttribute> getAttributes(Object o)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) o).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;

	}

	public boolean supports(Class<?> arg0) {
		
		return true;
	}

}
