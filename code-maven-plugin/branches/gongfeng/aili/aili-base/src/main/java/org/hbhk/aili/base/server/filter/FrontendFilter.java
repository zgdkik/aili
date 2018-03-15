package org.hbhk.aili.base.server.filter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.base.server.context.AppContext;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.base.share.entity.IUser;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.core.share.consts.AppConst;
import org.hbhk.aili.core.share.entity.Token;
import org.hbhk.aili.core.share.util.CookieUtil;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.springframework.http.MediaType;

public class FrontendFilter implements Filter {


	private static Log log =LogFactory.getLog(FrontendFilter.class);
	
	private String env = "dev";
	
	public static  int  expire = 30*60;
	/**
	 * 不能拦截 url 前缀,多个以,分隔
	 */
	private String notUrlprefix;
	
	private String session_cookie = "T";

	
	private static Map<String, String> notUrlprefixCachetKey = new ConcurrentHashMap<String, String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		env = filterConfig.getServletContext().getInitParameter("env");
		if("${env}".equals(env)){
			env = "dev";
		}
		log.info("env:"+env);
		AppContext.initAppContext("aili", "", filterConfig.getServletContext().getContextPath(),env);
		String exp = filterConfig.getInitParameter("expire");
		if(StringUtils.isNotEmpty(exp)){
			expire = Integer.parseInt(exp);
		}
		notUrlprefix = filterConfig.getInitParameter("notUrlprefix");
		if(StringUtils.isNotEmpty(notUrlprefix)){
			String[] arr = notUrlprefix.split(",");
			for (String str : arr) {
				notUrlprefixCachetKey.put(str, str);
			}
		}
		String session_cookie = filterConfig.getInitParameter("session_cookie");
		if(StringUtils.isNotEmpty(session_cookie)){
			this.session_cookie = session_cookie;
		}
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
			//取 cookie
			if(StringUtils.isEmpty(userCode)){
				if("T".equals(session_cookie)){
					Cookie cookie = CookieUtil.getCookie(req,  AppConst.SESSION_COOKIE_KYE);
					if (cookie != null) {
						String accessToken =  cookie.getValue();
						if(StringUtils.isNotEmpty(accessToken)){
							token = new Token();
							userCode = accessToken;
							token.setUserCode(accessToken);
							session.setAttribute(FrontendConstants.USER_SESSION_KEY, token);
						}
					} 
				}
			}
			if(StringUtils.isEmpty(userCode)){
				userCode = req.getHeader(FrontendConstants.API_USER_CODE);
				log.info("从请求头中获取当前用户:"+userCode);
			}
			String url = req.getServletPath();
			if(url.length()>1){
				String urlPrefix = url.substring(1,url.length());
				if(urlPrefix.indexOf("/")>-1){
					urlPrefix = "/"+urlPrefix.substring(0,urlPrefix.indexOf("/"));
				}
				if(StringUtils.isNotEmpty(userCode) && !notUrlprefixCachetKey.containsKey(urlPrefix)){
					log.info("设置当前用户信息开始:"+userCode);
					IUser user = CacheManager.getInstance().getCache(FrontendConstants.USER_CACHE_UUID, userCode);
					log.info("设置当前用户信息中...:"+userCode);
					UserContext.setCurrentUser(user);
					log.info("设置当前用户信息结束:"+userCode);
				}
			}else{
				if(StringUtils.isNotEmpty(userCode)){
					log.info("设置当前用户信息开始:"+userCode);
					IUser user = CacheManager.getInstance().getCache(FrontendConstants.USER_CACHE_UUID, userCode);
					log.info("设置当前用户信息中...:"+userCode);
					UserContext.setCurrentUser(user);
					log.info("设置当前用户信息结束:"+userCode);
				}
			}
		
			String contentType = req.getHeader(FrontendConstants.CONTENT_TYPE);
			if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType)) {
				req = new BodyReaderHttpServletRequestWrapper(req);
			}
			if(token!=null && expire>0){
				CookieUtil.setCookie(res, AppConst.SESSION_COOKIE_KYE, token.getUserCode(),expire);
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
