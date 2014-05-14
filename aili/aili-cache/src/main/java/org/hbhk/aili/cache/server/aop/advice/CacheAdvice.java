package org.hbhk.aili.cache.server.aop.advice;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hbhk.aili.cache.server.aop.annotation.InvaliCache;
import org.hbhk.aili.cache.server.aop.annotation.ReadCache;
import org.hbhk.aili.cache.server.templet.ICacheTemplet;
import org.hbhk.aili.cache.server.templet.impl.MemoryCacheTemplet;
import org.springframework.stereotype.Component;

/**
 * 拦截缓存
 */
@Component
@Aspect
public class CacheAdvice {
	ICacheTemplet<String, Object> cacheTemplet = new MemoryCacheTemplet<Object>();

	// 定义切面
	@Pointcut("execution(* org.hbhk.*.*.server.dao.impl.*.*(..))")
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
						String tempKey = this.getKey(call.getArgs());
						String prefix = cache.namespace();
						String key = prefix + "_" + tempKey;
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
						String key = getKey(call.getArgs());
						// 删除缓存
						cacheTemplet.invalid(prefix + key);

					}
				}
			}
		}
		return result;
	}

	/**
	 * 组装 key 值
	 */
	private String getKey(Object[] args) {
		StringBuffer sb = new StringBuffer();
		if (args != null && args.length > 0) {
			for (Object arg : args) {
				sb.append(arg);
			}
		}
		return sb.toString();

	}

}
