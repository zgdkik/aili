package org.hbhk.module.framework.server.mysecurity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.module.framework.server.context.UserConstants;
import org.hbhk.module.framework.server.service.IUserService;
import org.hbhk.module.framework.shared.domain.security.ResourcesEntity;
import org.hbhk.module.framework.shared.domain.security.RolesEntity;
import org.hbhk.module.framework.shared.domain.security.UsersEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.UrlPathHelper;

public class MySecurityInterceptor implements Filter {

	private Log log = LogFactory.getLog(getClass());
	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	private Properties groupMappings;
	private IUserService userService;

	private String lookupGroup(String url) {
		groupMappings = new Properties();
		// none
		groupMappings.put("/framework/userLogin", "none");
		groupMappings.put("/framework/login", "none");
		groupMappings.put("/framework/validateCode", "none");
		groupMappings.put("/framework/authorizeError", "none");
		groupMappings.put("/framework/logout", "none");
		groupMappings.put("/getInfoService", "none");
		String group = groupMappings.getProperty(url);
		return group;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(filterConfig.getServletContext());
		userService = (IUserService) ctx.getBean("userService");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String urlcontext = httpServletRequest.getRequestURI();
		String contextName = httpServletRequest.getContextPath();
		urlcontext = urlcontext.substring(urlcontext.indexOf(contextName)
				+ contextName.length(), urlcontext.length());
		// 用户请求URL
		String url = urlPathHelper.getLookupPathForRequest(httpServletRequest);
		if (url.startsWith("/resource") || urlcontext.startsWith("/service")) {
			chain.doFilter(request, response);
			return;
		}

		// 使用req对象获取RequestDispatcher对象
		RequestDispatcher dispatcher = httpServletRequest
				.getRequestDispatcher("/framework/login");
		String username = (String) httpServletRequest.getSession()
				.getAttribute(UserConstants.CURRENT_USER_NAME);
		String loginUser = null;
		if (StringUtils.isEmpty(username)) {
			loginUser = (String) httpServletRequest.getParameter("username");
		} else {
			loginUser = username;
		}

		if (StringUtils.isEmpty(username)) {

			ServletContext application = httpServletRequest.getSession()
					.getServletContext();
			List<String> countStr = (List<String>) application
					.getAttribute(UserConstants.USER_INFO_COUNT);
			List<String> userCount = new ArrayList<String>();
			if (!CollectionUtils.isEmpty(countStr)
					&& !StringUtils.isEmpty(loginUser)) {
				for (String user : countStr) {
					if (user.equals(loginUser)) {
						log.info("username:" + user);
						userCount.add(user);

					}
				}
			}
			Integer count = (Integer) application
					.getAttribute(UserConstants.USER_LOGIN_COUNT);

			if (userCount.size() > 0 && count != null
					&& userCount.size() >= count) {
				dispatcher = httpServletRequest
						.getRequestDispatcher("/framework/authorizeError");
				request.setAttribute("errorMsg", "你(" + loginUser
						+ ")已经在别处登录,服务器只允许" + count + "次登录!");
				dispatcher.forward(request, response);
				return;
			}

		}

		String group = lookupGroup(url);
		boolean ok = true;
		if (StringUtils.isEmpty(group)) {
			if (StringUtils.isEmpty(username)) {
				try {
					request.setAttribute("errorMsg", "你还没有登录");
					dispatcher.forward(request, response);
					return;
				} catch (Exception e) {
					log.warn("forward", e);
				}
			}
			UsersEntity users = userService.queryUsers(loginUser);
			Set<RolesEntity> roles = users.getRoles();
			for (Iterator<RolesEntity> iterator = roles.iterator(); iterator
					.hasNext() && ok;) {
				RolesEntity rolesEntity = iterator.next();
				Set<ResourcesEntity> resources = rolesEntity.getResources();
				for (Iterator<ResourcesEntity> iterator2 = resources.iterator(); iterator2
						.hasNext() && ok;) {
					ResourcesEntity resourcesEntity = (ResourcesEntity) iterator2
							.next();
					String surl = resourcesEntity.getUrl();
					log.info("surl:" + surl);
					if (surl.equals(url)) {
						ok = false;
					}

				}
			}
			if (ok) {
				dispatcher = httpServletRequest
						.getRequestDispatcher("/framework/authorizeError");
				request.setAttribute("errorMsg", "请求的URL不存在或没有权限访问!");
				dispatcher.forward(request, response);
				return;
			}
		}

		// 找出资源所需要的权限, 即组名
		if (!StringUtils.isEmpty(group) && group.equals("none")) {
			// 所请求的资源不需要保护.
			chain.doFilter(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
