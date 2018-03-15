package com.feisuo.sds.base.server.interceptor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;

import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.share.constants.FrontendConstants;
import com.feisuo.sds.base.share.entity.IPrivilege;
import com.feisuo.sds.base.share.entity.IUser;
import com.feisuo.sds.base.share.exception.AccessNotAllowException;
import com.feisuo.sds.base.share.exception.UserNotLoginException;

/**
 * 
 * 权限访问控制器
 * 
 */
public final class SecurityAccessor {

	private SecurityAccessor() {
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
		IPrivilege function = getFunction(accessURL);
		if (function == null) {
			if (ignoreUnstoredFunction) {
				return;
			}

			throw new AccessNotAllowException(accessURL);
		}

		Set<String> accessUris = user.queryAccessUris();

		// 是否拥有访问权限
		if (accessUris == null || !accessUris.contains(function.getUrl())) {
			throw new AccessNotAllowException(accessURL);
		}
	}

	/**
	 * 通过URL走缓存获取权限对象
	 */
	public static IPrivilege getFunction(String accessURL) {
		ICache<String,  Map<String, IPrivilege>> functionCache = CacheManager.getInstance()
				.getCache(FrontendConstants.FUNCTION_ALL_ENTITY_CACHE_UUID);
		Map<String, IPrivilege> function = functionCache.get(FrontendConstants.FUNCTION_ALL_ENTITY_CACHE_UUID);
		return function.get(accessURL);
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
