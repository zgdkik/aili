package com.deppon.esb.management.web.action.sysuser;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.user.domain.SysUserInfo;
import com.deppon.esb.management.user.service.ISysUserService;
import com.deppon.esb.management.web.MD5Util;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;
import com.deppon.esb.management.web.deploy.struts.interceptor.LoginInterceptor;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ESBActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware,CookiesAware {

	private static final long serialVersionUID = -8848590532137519362L;

	@Resource
	private ISysUserService sysUserService;
	private String loginName;
	private String password;
	private boolean rememberMe;
	private HttpServletResponse response;
	private HttpServletRequest request;
	@SuppressWarnings("rawtypes")
	private Map session;
	@SuppressWarnings("rawtypes")
	private Map cookies;
	private String goingToURL;

	@SuppressWarnings("unchecked")
	public String login() throws Exception {
		if(loginName==null || password == null){
			addActionMessage("user name or password can not be null.");
			return INPUT;
		}
		SysUserInfo sysUserInfo = sysUserService.attemptLogin(loginName, MD5Util.md5(password));
		if (sysUserInfo != null) {
			if (rememberMe) {
				Cookie cookie = new Cookie(LoginInterceptor.COOKIE_REMEMBERME_KEY, sysUserInfo.getSysUserName() + "==" + sysUserInfo.getPassword());
				cookie.setMaxAge(60 * 60 * 24 * 14);
				response.addCookie(cookie);
			}
			session.put(LoginInterceptor.USER_SESSION_KEY, sysUserInfo);
			String goingToURL = (String) session.get(LoginInterceptor.GOING_TO_URL_KEY);
			if (StringUtils.isNotBlank(goingToURL)) {
				setGoingToURL(goingToURL);
				session.remove(LoginInterceptor.GOING_TO_URL_KEY);
			} else {
				setGoingToURL("index.action");
			}
			request.setAttribute("currentUserName", sysUserInfo.getUserName());
			return SUCCESS;
		} else {
			addActionMessage("user name or password is not corrected.");
			return INPUT;
		}
	}

	public String logout() throws Exception {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(LoginInterceptor.USER_SESSION_KEY);
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (LoginInterceptor.COOKIE_REMEMBERME_KEY.equals(cookie.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					return "login";
				}
			}
		}
		return "login";
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}

	@SuppressWarnings("rawtypes")
	public Map getCookies() {
		return cookies;
	}

	@SuppressWarnings("rawtypes")
	public void setCookies(Map cookies) {
		this.cookies = cookies;
	}

	public String getGoingToURL() {
		return goingToURL;
	}

	public void setGoingToURL(String goingToURL) {
		this.goingToURL = goingToURL;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setCookiesMap(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
