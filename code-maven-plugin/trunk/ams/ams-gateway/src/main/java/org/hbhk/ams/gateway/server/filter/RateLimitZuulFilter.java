package org.hbhk.ams.gateway.server.filter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class RateLimitZuulFilter extends ZuulFilter {

	private  RateLimiter rateLimiter = null;

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

	public boolean shouldFilter() {
		// 这里可以考虑弄个限流开启的开关，开启限流返回true，关闭限流返回false，你懂的。
		return true;
	}

	public Object run() {
		try {
			RequestContext currentContext = RequestContext.getCurrentContext();
			String url = currentContext.getRequest().getServletPath();
			rateLimiter = RateLimitCache.getRateLimiter(url);
			if(rateLimiter == null){
				return null;
			}
			if (!rateLimiter.tryAcquire()) {
				HttpServletResponse response = currentContext.getResponse();
				HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
				response.setContentType(MediaType.TEXT_PLAIN_VALUE);
				response.setStatus(httpStatus.value());
				response.getWriter().append(httpStatus.getReasonPhrase());
				currentContext.setSendZuulResponse(false);
				throw new ZuulException(httpStatus.getReasonPhrase(),
						httpStatus.value(), httpStatus.getReasonPhrase());

			}
		} catch (Exception e) {
			ReflectionUtils.rethrowRuntimeException(e);
		}
		return null;
	}
}