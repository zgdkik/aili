package org.hbhk.aili.rpc.server.hessian.remote;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.share.consts.Protocol;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.rpc.server.hessian.IHessianRemoting;
import org.hbhk.aili.rpc.server.hessian.context.ApplicationContext;
import org.hbhk.aili.rpc.server.hessian.context.IApplication;
import org.hbhk.aili.rpc.server.hessian.context.SessionContext;
import org.hbhk.aili.rpc.server.hessian.handler.IRemoteExceptionHandler;
import org.hbhk.aili.rpc.server.hessian.transport.Transport;

public class RemoteServiceProxy implements InvocationHandler {

	private IHessianRemoting target;
	private IHessianRemoting executor;
	private IRemoteExceptionHandler exceptionhander;
	
	private Log log = LogFactory.getLog(RemoteServiceProxy.class);

	public void setExceptionHandler(IRemoteExceptionHandler exceptionHandler) {
		this.exceptionhander = exceptionHandler;
	}

	public void setTarget(IHessianRemoting target) {
		this.target = target;
	}

	public void setExecutor(IHessianRemoting executor) {
		this.executor = executor;
	}

	@SuppressWarnings("unchecked")
	public <T> T create(Class<T> serviceCls) {
		return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[] { serviceCls }, this);
	}

	/**
	 * 
	 * <p>
	 * Title:invoke
	 * <p>
	 * Description:閫氳繃浠ｇ悊鏂瑰紡璋冪敤鏂规硶
	 * </p>
	 * 
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 *      java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, final Object[] args) throws Throwable {
		Object result = null;
		try {
			try {
				try {
					Transport.setSendHeader(Protocol.SECURITY_HEADER, method.getName());
					/**
					 * 鍦ㄨ姹傛槸锛屽湪璇锋眰澶村姞鍏ュ浗闄呭寲璁剧疆
					 */
					if(SessionContext.get(SessionContext.ACCEPT_LANGUAGE)!=null){
					Transport.setSendHeader("Accept-Language",  (String) SessionContext.get(SessionContext.ACCEPT_LANGUAGE));
					}
					result = method.invoke(target, args);
				} catch (InvocationTargetException e) {
					
					//TODO -----
					if(e.getTargetException() instanceof BusinessException){
						BusinessException be = (BusinessException)
								e.getTargetException();
						if(be.getMessage()!=null &&"userNotLogin".equals(be.getMessage()) ){
							log.error("用户未登录",be);
						}
					}
					
					throw e.getTargetException();
				}
			} catch (UndeclaredThrowableException e) {
				throw e.getUndeclaredThrowable();
			}
		} catch (Exception e1) {
			if (null == this.executor) {
				throw e1;
			}
			return invokeLocalExecutor(method, this.executor, args);
		}
		return result;
	}

	/**
	 * 
	 * @param t
	 */
	void handleException(Throwable t) throws Throwable {

		if (null != this.exceptionhander) {
			exceptionhander.handle(t);
		}
		throw t;
	}

	/**
	 * 
	 * @param method
	 * @param target
	 * @param args
	 * @return
	 */
	Object invokeLocalExecutor(Method method, Object target, Object[] args) throws Throwable {
		try {
			try {
				try {
					return method.invoke(target, args);
				} catch (InvocationTargetException e) {
					throw e.getTargetException();
				} 
			} catch (UndeclaredThrowableException t) {
				throw t.getUndeclaredThrowable();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return null;
	}
}

