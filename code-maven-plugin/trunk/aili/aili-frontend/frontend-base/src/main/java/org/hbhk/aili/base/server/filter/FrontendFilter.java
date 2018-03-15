package org.hbhk.aili.base.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.base.server.context.AppContext;
import org.hbhk.aili.base.server.context.SessionContext;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.shared.define.FrontendConstants;
import org.hbhk.aili.base.shared.domain.IUser;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.core.server.listener.AppContextLoaderListener;
import org.hbhk.aili.core.share.entity.Token;
import org.springframework.http.MediaType;

public class FrontendFilter implements Filter {

	public static final String CONTENT_TYPE = "Content-Type";
	
	private static Log log =LogFactory.getLog(FrontendFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		AppContext.initAppContext("hbhk", AppContextLoaderListener.resources, filterConfig.getServletContext().getContextPath());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			SessionContext.setSession(session);
			Token token = (Token) session.getAttribute(FrontendConstants.USER_SESSION_KEY);
			String userCode = null;
			if(token != null){
				userCode = token.getUserCode();
				log.info("当前用户:"+userCode);
			}
			if(StringUtils.isNotEmpty(userCode)){
				IUser user = CacheManager.getInstance().getCache(
						FrontendConstants.USER_CACHE_UUID, userCode);
				UserContext.setCurrentUser(user);
			}
			String contentType = req.getHeader(CONTENT_TYPE);
			if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType)) {
				req = new BodyReaderHttpServletRequestWrapper(req);
			}
			try {
				chain.doFilter(req, res);
			} finally {
				UserContext.remove();
			}
		}
	}

	@Override
	public void destroy() {

	}

}
