package org.hbhk.aili.client.core.component.remote;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

import org.hbhk.aili.client.core.component.transport.Transport;
import org.hbhk.aili.client.core.component.transport.exception.NetworkNotAllowException;
import org.hbhk.aili.client.core.component.transport.exception.RemoteServiceNotFoundException;
import org.hbhk.aili.client.core.core.application.IApplication;
import org.hbhk.aili.client.core.core.context.ApplicationContext;
import org.hbhk.aili.client.core.core.context.SessionContext;
import org.hbhk.aili.client.core.widget.msgbox.MsgBox;

/**
 * <b style="font-family:微软雅黑"><small>Description:远程服务器代理类</small></b> </br> <b
 */
public class RemoteServiceProxy implements InvocationHandler {

	private IService target;
	private IService executor;
	private IRemoteExceptionHandler exceptionhander;

	public void setExceptionHandler(IRemoteExceptionHandler exceptionHandler) {
		this.exceptionhander = exceptionHandler;
	}

	public void setTarget(IService target) {
		this.target = target;
	}

	public void setExecutor(IService executor) {
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
	 * Description:通过代理方式调用方法
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
					 * 在请求是，在请求头加入国际化设置
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
							
							MsgBox.showError("用户登录信息丢失，需要重新登录");
							IApplication application = ApplicationContext.getApplication();
							
							application.restart();
						}
					}
					
					throw e.getTargetException();
				}
			} catch (UndeclaredThrowableException e) {
				throw e.getUndeclaredThrowable();
			}
		} catch (RemoteServiceNotFoundException e1) {
			if (null == this.executor) {
				throw e1;
			}
			return invokeLocalExecutor(method, this.executor, args);
		} catch (NetworkNotAllowException e1) {
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
