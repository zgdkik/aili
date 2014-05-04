package org.hbhk.module.framework.server.deploy;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class FrameworkFilter implements Filter {
	
    private static ServletContext servletContext;
    
    public static ServletContext getServletContext() {
        return servletContext;
    }
    
    public static void getServletContext(FilterConfig config) {
    	servletContext = config.getServletContext();
    }

	public void init(FilterConfig filterConfig) throws ServletException {
		getServletContext(filterConfig);
        ModuleManager.export(servletContext);
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}
    
   
}
