package org.hbhk.aili.cache.server.aop.advice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hbhk.aili.cache.server.aop.annotation.CacheKey;
import org.hbhk.aili.cache.server.aop.annotation.InvaliCache;
import org.hbhk.aili.cache.server.aop.annotation.ReadCache;
import org.hbhk.aili.cache.server.templet.ICacheTemplet;

/**
 * 拦截缓存
 */
@Aspect
public class CacheAdvice {
	ICacheTemplet<String, Object> cacheTemplet;

	private final String pointcut = "execution(* org.hbhk.*.*.server.dao.impl.*.*(..))";

	// 定义切面
	@Pointcut(pointcut)
	public void cachedPointcut() {
	}

	@Around("cachedPointcut()")
	public Object doAround(ProceedingJoinPoint call) {
		Object result = null;
		Method[] methods = call.getTarget().getClass().getDeclaredMethods();
		Signature signature = call.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		for (Method m : methods) {
			// 循环方法，找匹配的方法进行执
			if (m.getName().equals(method.getName())) {
				if (m.isAnnotationPresent(ReadCache.class)) {
					ReadCache cache = m.getAnnotation(ReadCache.class);
					if (cache != null) {
						String prefix = cache.namespace();
						String key = (prefix + "_" + getKey(m, call.getArgs()))
								.trim();
						result = cacheTemplet.get(key);
						if (null == result) {
							try {
								result = call.proceed();
								int expire = cache.expire();
								if (expire > 0) {
									cacheTemplet.set(key, result, expire);
								} else {
									cacheTemplet.set(key, result);
								}
							} catch (Throwable e) {
								throw new RuntimeException(e);
							}
						}
					}
				} else if (method.isAnnotationPresent(InvaliCache.class)) {
					InvaliCache flush = method.getAnnotation(InvaliCache.class);
					if (flush != null) {
						String prefix = flush.namespace();
						String key = (prefix + "_" + getKey(m, call.getArgs()))
								.trim();
						// 删除缓存
						cacheTemplet.invalid(key);
					}
				}
			}
		}
		return result;
	}

	/**
	 * 组装 key 值
	 */
	private String getKey(Method method, Object[] args) {
		Annotation[][] ans = method.getParameterAnnotations();
		int index = -1;
		for (int i = 0; i < ans.length; i++) {
			int flag = getAnnotation(CacheKey.class, ans[i]);
			++index;
			if (flag == 1) {
				break;
			}
		}
		if (args != null && args.length >= index && index > -1) {
			if (args[index] == null) {
				return null;
			}
			return args[index].toString();
		}
		return null;
	}

	public int getAnnotation(Class<?> annotationClass, Annotation[] annotations) {
		if (annotations != null && annotations.length > 0) {
			for (final Annotation annotation : annotations) {
				if (annotationClass.equals(annotation.annotationType())) {
					return 1;
				}
			}
		}
		return -1;
	}

	public ICacheTemplet<String, Object> getCacheTemplet() {
		return cacheTemplet;
	}

	public void setCacheTemplet(ICacheTemplet<String, Object> cacheTemplet) {
		this.cacheTemplet = cacheTemplet;
	}

}
