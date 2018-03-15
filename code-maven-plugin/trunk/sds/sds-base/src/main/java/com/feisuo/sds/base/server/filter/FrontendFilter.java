package com.feisuo.sds.base.server.filter;

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
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.core.share.entity.Token;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.springframework.http.MediaType;

import com.feisuo.sds.base.server.context.AppContext;
import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.share.constants.FrontendConstants;
import com.feisuo.sds.base.share.entity.IUser;

public class FrontendFilter implements Filter {


	private static Log log =LogFactory.getLog(FrontendFilter.class);
	
	private String env = "dev";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		env = filterConfig.getServletContext().getInitParameter("env");
		if("${env}".equals(env)){
			env = "dev";
		}
		log.info("env:"+env);
		AppContext.initAppContext("sds", "", filterConfig.getServletContext().getContextPath(),env);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			log.info("请求地址为："+req.getServletPath()+"  请求参数为:"+JsonUtil.toJson(req.getParameterMap()));
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			Token token = (Token) session.getAttribute(FrontendConstants.USER_SESSION_KEY);
			String userCode = null;
			if(token != null){
				userCode = token.getUserCode();
				log.info("当前用户:"+userCode);
			}
			if(StringUtils.isEmpty(userCode)){
				userCode = req.getHeader(FrontendConstants.API_USER_CODE);
				log.info("从请求头中获取当前用户:"+userCode);
			}
			if(StringUtils.isNotEmpty(userCode)){
				log.info("设置当前用户信息开始:"+userCode);
				IUser user = CacheManager.getInstance().getCache(FrontendConstants.USER_CACHE_UUID, userCode);
				log.info("设置当前用户信息中...:"+userCode);
				UserContext.setCurrentUser(user);
				log.info("设置当前用户信息结束:"+userCode);
			}
			String contentType = req.getHeader(FrontendConstants.CONTENT_TYPE);
			if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType)) {
				req = new BodyReaderHttpServletRequestWrapper(req);
			}
			try {
				chain.doFilter(req, res);
			}catch (Exception e) {
				log.error(e.getMessage(),e);
				throw new RuntimeException(e);
			} finally {
				log.info("清除当前用户信息:"+userCode);
				UserContext.remove();
			}
		}
	}

	@Override
	public void destroy() {

	}

}
