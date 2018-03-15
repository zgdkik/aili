package org.hbhk.aili.rpc.server.hessian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.share.util.BASE64Util;

public class HessianFilter  implements Filter{

	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request ;
		//Authorization
		String authorization = httpRequest.getHeader("Authorization");
		if(StringUtils.isNotEmpty(authorization)){
			String[] arr = authorization.split(" ");
			String userPass = BASE64Util.decode(arr[1]);
			logger.debug("hessian当前用户:"+userPass.split(":")[0]);
		}
		chain.doFilter(httpRequest, response);
	}

	@Override
	public void destroy() {
		
	}

}
