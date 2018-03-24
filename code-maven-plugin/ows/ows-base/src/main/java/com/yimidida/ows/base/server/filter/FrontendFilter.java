package com.yimidida.ows.base.server.filter;

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
import org.springframework.http.MediaType;

import com.yimidida.ows.base.server.context.AppContext;
import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.base.share.entity.IUser;
import com.yimidida.ows.base.share.util.Base64Util;
import com.yimidida.ymdp.cache.server.CacheManager;
import com.yimidida.ymdp.core.share.consts.AppConst;
import com.yimidida.ymdp.core.share.entity.Token;
import com.yimidida.ymdp.core.share.ex.BusinessException;
import com.yimidida.ymdp.core.share.util.CookieUtil;
import com.yimidida.ymdp.core.share.util.JsonUtil;

public class FrontendFilter implements Filter {


	private static Log log =LogFactory.getLog(FrontendFilter.class);
	
	private String env = "dev";
	
	public static  int  expire = 0;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		env = filterConfig.getServletContext().getInitParameter("env");
		if("${env}".equals(env)){
			env = "dev";
		}
		log.info("env:"+env);
		AppContext.initAppContext("metertick-web", "", filterConfig.getServletContext().getContextPath(),env);
		String exp = filterConfig.getInitParameter("expire");
		if(StringUtils.isNotEmpty(exp)){
			expire = Integer.parseInt(exp);
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
			if(StringUtils.isEmpty(userCode)){
				String accessToken =  req.getHeader(FrontendConstants.ACCESS_TOKEN);
				if(StringUtils.isEmpty(accessToken)){
					accessToken =  req.getParameter(FrontendConstants.ACCESS_TOKEN);
				}
				if(StringUtils.isNotEmpty(accessToken)){
					try {
						accessToken = Base64Util.decode(accessToken);
						String[] arr = accessToken.split(",");
						token = new Token();
						userCode = arr[1];
						token.setCompCode(arr[0]);
						token.setUserCode(userCode);
						session.setAttribute(FrontendConstants.USER_SESSION_KEY, token);
					} catch (Exception e) {
						throw new BusinessException("access_token:"+accessToken+"请求非法");
					}
				}
			}
			if(StringUtils.isNotEmpty(userCode)){
				log.info("设置当前用户信息开始:"+userCode);
				String compCode = token.getCompCode();
				IUser user = CacheManager.getInstance().getCache(FrontendConstants.USER_CACHE_UUID, compCode+","+userCode);
				log.info("设置当前用户信息中...:"+userCode);
				UserContext.setCurrentUser(user);
				log.info("设置当前用户信息结束:"+userCode);
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
