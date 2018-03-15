package org.hbhk.aili.mybatis.share.util;

import java.lang.reflect.Proxy;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Plugin;
import org.springside.modules.utils.Reflections;

public class InterceptorUtils {
	public static boolean isRoutingStatementHandlerProxy(Object target) {
		return ((target instanceof RoutingStatementHandler))
				|| ((Proxy.isProxyClass(target.getClass())) && ((Reflections
						.getFieldValue(Proxy.getInvocationHandler(target),
								"target") instanceof RoutingStatementHandler)));
	}

	public static boolean isExecutorHandlerProxy(Object target) {
		return ((target instanceof Executor))
				|| ((Proxy.isProxyClass(target.getClass())) && ((Reflections
						.getFieldValue(Proxy.getInvocationHandler(target),
								"target") instanceof Executor)));
	}

	public static boolean isPreparedStatementHandler(Object target) {
		return ((target instanceof PreparedStatementHandler))
				|| ((Proxy.isProxyClass(target.getClass())) && ((Reflections
						.getFieldValue(Proxy.getInvocationHandler(target),
								"target") instanceof PreparedStatementHandler)));
	}

	public static boolean isCachingExecutor(Object target) {
		return ((target instanceof CachingExecutor))
				|| ((Proxy.isProxyClass(target.getClass())) && ((Reflections
						.getFieldValue(Proxy.getInvocationHandler(target),
								"target") instanceof CachingExecutor)));
	}

	public static RoutingStatementHandler getRoutingStatementHandler(
			Object target) {
		if ((target instanceof RoutingStatementHandler)) {
			return (RoutingStatementHandler) target;
		}
		if (((target instanceof StatementHandler))
				&& (Proxy.isProxyClass(target.getClass()))) {
			Plugin localPlugin = (Plugin) Reflections
					.getFieldValue(target, "h");
			return (RoutingStatementHandler) Reflections.getFieldValue(
					localPlugin, "target");
		}
		return null;
	}
}
