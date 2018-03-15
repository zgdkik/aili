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
import org.hbhk.aili.core.share.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * @author 089115
 *
 */
public class ClusterFilter implements Filter {

	private Logger log = LoggerFactory.getLogger(getClass());
	private IClusterTokenGenerator clusterTokenGenerator;

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
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String token = CookieUtil.getCookieValue(request,AppConst.AILI_CLUSTER_TOKEN);
		log.debug("集群token:" + token);
		if (StringUtils.isNotEmpty(token)) {
			HttpSession session = request.getSession();
			Object obj = clusterTokenGenerator.decodeToken(token);
			session.setAttribute(AppConst.AILI_SEESION_KEY, obj);
		}
		token = clusterTokenGenerator.encodeToken(request);
		log.debug("重新生成的集群token:" + token);
		CookieUtil.setCookie(response, AppConst.AILI_CLUSTER_TOKEN, token);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
