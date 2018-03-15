package org.hbhk.aili.base.server.interceptor;

import java.util.List;
import java.util.Set;

import org.hbhk.aili.base.server.context.RequestContext;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.shared.define.FrontendConstants;
import org.hbhk.aili.base.shared.domain.IFunction;
import org.hbhk.aili.base.shared.domain.IUser;
import org.hbhk.aili.base.shared.exception.AccessNotAllowException;
import org.hbhk.aili.base.shared.exception.UserNotLoginException;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;

/**
 * 
 * 权限访问控制器
 * 
 */
public final class SecurityAccessor {

	private SecurityAccessor() {
	}

	/**
	 * 读取request头里的method，并判断与实际调用method是否一致，不一致抛出异常 checkMethodAccessSecurity
	 * 
	 * @param methodName
	 * @return void
	 * @since:0.6
	 */
	public static void checkMethodAccessSecurity(String methodName) {
		final String protocolHeader = RequestContext.getCurrentContext()
				.getRemoteRequestMethod();
		if (null == protocolHeader) {
			return;
		}
		if (!protocolHeader.equalsIgnoreCase(methodName)) {
			// Client forge method invoke url
			throw new AccessNotAllowException();
		}
	}

	/**
	 * 校验权限
	 */
	public static void checkURLAccessSecurity(String accessURL) {
		checkURLAccessSecurity(accessURL, true);
	}

	/**
	 * 校验权限 checkURLAccessSecurity
	 * 
	 * @param accessURL
	 * @return void
	 * @since:0.6
	 */
	public static void checkURLAccessSecurity(String accessURL,
			boolean ignoreUnstoredFunction) {

		IUser user = UserContext.getCurrentUser();
		// 用户未登录
		if (user == null) {
			throw new UserNotLoginException();
		}

		// 去掉多余的'/'
		if (accessURL != null) {
			accessURL = accessURL.replaceAll("[/]{2,}", "/");
		}
		IFunction function = getFunction(accessURL);
		if (function == null) {
			if (ignoreUnstoredFunction) {
				return;
			}

			throw new AccessNotAllowException(accessURL);
		}

		if (!function.getValidFlag()) {
			return;
		}
		Set<String> accessUris = user.queryAccessUris();

		// 是否拥有访问权限
		if (accessUris == null || !accessUris.contains(function.getUri())) {
			throw new AccessNotAllowException(accessURL);
		}
	}

	/**
	 * 通过URL走缓存获取权限对象
	 */
	public static IFunction getFunction(String accessURL) {
		ICache<String, IFunction> functionCache = CacheManager.getInstance()
				.getCache(FrontendConstants.FUNCTION_ENTITY_CACHE_UUID);
		IFunction function = functionCache.get(accessURL);
		return function;
	}

	public static boolean checkUrlFunction(String accessURL) {
		ICache<String, List<String>> functionCache = CacheManager.getInstance()
				.getCache(FrontendConstants.FUNCTION_ALL_CACHE_UUID);
		List<String> function = functionCache
				.get(FrontendConstants.FUNCTION_ALL_CACHE_UUID);
		if(function==null){
			return false;
		}
		return function.contains(accessURL);
	}

	/**
	 * 判断请求是否被允许 hasAccessSecurity
	 */
	public static boolean hasAccessSecurity(String accessURL) {
		try {
			checkURLAccessSecurity(accessURL);
			return true;
		} catch (Exception t) {
			return false;
		}
	}

}
