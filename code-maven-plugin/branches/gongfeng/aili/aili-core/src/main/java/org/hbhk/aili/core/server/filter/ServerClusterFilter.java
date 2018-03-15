package org.hbhk.aili.core.server.filter;

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
import org.hbhk.aili.core.server.service.IClusterTokenGenerator;
import org.hbhk.aili.core.server.service.impl.DefaultClusterTokenGenerator;
import org.hbhk.aili.core.share.consts.AppConst;
import org.hbhk.aili.core.share.entity.Token;
import org.hbhk.aili.core.share.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * @author 089115
 *
 */
public class ServerClusterFilter implements Filter {

	private Logger log = LoggerFactory.getLogger(getClass());
	private IClusterTokenGenerator clusterTokenGenerator;
	public static  int  expire = 0;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		if (clusterTokenGenerator == null) {
			if (context.containsBean("clusterTokenGenerator")) {
				log.info("使用自定义token处理集群");
				clusterTokenGenerator = context.getBean("clusterTokenGenerator", IClusterTokenGenerator.class);
			} else {
				log.info("使用自带token处理集群");
				clusterTokenGenerator = new DefaultClusterTokenGenerator();
			}
		}
		String exp = filterConfig.getInitParameter("expire");
		if(StringUtils.isNotEmpty(exp)){
			expire = Integer.parseInt(exp);
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		if(expire >0){
			Token tokenObj = (Token) session.getAttribute(AppConst.SEESION_USER_KEY);
			if(tokenObj == null){
				String sessionToken = CookieUtil.getCookieValue(request,AppConst.SESSION_COOKIE_KYE);
				if(StringUtils.isNotEmpty(sessionToken)){
					Token sessionTokenObj = clusterTokenGenerator.decodeToken(sessionToken);
					long timestamp = Long.parseLong(sessionTokenObj.getTimestamp()) ;
					long currentTime = System.currentTimeMillis();
					if(currentTime >(timestamp+(expire*60*1000))){
						chain.doFilter(request, response);
						return;
					}
					String token = CookieUtil.getCookieValue(request,AppConst.SEESION_CLUSTER_TOKEN);
					log.debug("集群token:" + token);
					if (StringUtils.isNotEmpty(token)) {
						Token obj = clusterTokenGenerator.decodeToken(token);
						session.setAttribute(AppConst.SEESION_USER_KEY, obj);
						token = clusterTokenGenerator.encodeToken(request,obj);
						log.debug("重新生成的集群token:" + token);
						CookieUtil.setCookie(response, AppConst.SEESION_CLUSTER_TOKEN, token,expire);
					}
					Token sessionKeyObj = clusterTokenGenerator.decodeToken(sessionToken);
					sessionToken = clusterTokenGenerator.encodeToken(request, sessionKeyObj);
					CookieUtil.setCookie(response, AppConst.SESSION_COOKIE_KYE, sessionToken);
				}
			}else{
				String sessionToken = clusterTokenGenerator.encodeToken(request, tokenObj);
				CookieUtil.setCookie(response, AppConst.SESSION_COOKIE_KYE, sessionToken);
			}
		
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
