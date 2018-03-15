package org.hbhk.aili.client.core.component.remote;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.component.networkstatus.INetworkStatusListener;
import org.hbhk.aili.client.core.component.transport.ITransport;
import org.hbhk.aili.client.core.component.transport.Transport;
import org.hbhk.aili.client.core.component.transport.hessian.HttpClientHessianProxyFactory;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * 远程应用的统一调用实现
 */
public class RemoteServer implements IRemoteServer {

	/**
	 * 缓存通过HessianProxyFactory创建的远程服务接口代理实例
	 */
	private static Map<Class<?>, IService> servicesCache = new HashMap<Class<?>, IService>();
	/**
	 * HessianProxy工厂 工厂的连接创建工厂由本地实现
	 */
	private HessianProxyFactory factory;
	private Log logger = LogFactory.getLog(getClass());
	private IRemoteInfo remoteInfo;
	private IRemoteExceptionHandler exceptionHandler;
	private ITransport transport;

	public RemoteServer(IRemoteInfo remoteInfo,
			IRemoteExceptionHandler exceptionHandler) {
		this.remoteInfo = remoteInfo;
		this.exceptionHandler = exceptionHandler;
		transport = Transport.create(remoteInfo);
		factory = new HttpClientHessianProxyFactory((Transport)transport);
		factory.setHessian2Reply(true);
		factory.setHessian2Request(true);
	}

	@Override
	public <T extends IService> T getService(Class<T> serviceCls, T executor) {
		
		return getService(serviceCls,
				computeDefaultServiceName(serviceCls.getSimpleName(),getModuleName(serviceCls)), executor);
	}

	@Override
	public <T extends IService> T getService(Class<T> serviceCls,
			String aliasName) {
		return getService(serviceCls, aliasName, null);
	}

	@Override
	public <T extends IService> T getService(Class<T> serviceCls) {
		return getService(serviceCls,
				computeDefaultServiceName(serviceCls.getSimpleName(),getModuleName(serviceCls)), null);
	}
	
	@SuppressWarnings("rawtypes")
	private String getModuleName(Class cls){
		String pkgName = cls.getPackage().getName();
   		String preFix = "";
   		try{
       		String tmpStr = pkgName.substring(("com.deppon.foss.module.").length());
       		preFix = tmpStr.substring(0,tmpStr.indexOf("."));
       		if("module".equals(preFix) || "framework".equals(preFix)){
       			preFix = "";		       		
       		}
   		}catch(Exception e){
   			if(logger.isDebugEnabled()){
   				logger.debug("error pkg name");
   			}
   				
   		}
   		return preFix;
	}
	/**
	 * 实际获取远程服务的实现 返回的Service是两层代理包装 1. hessian 进行了第一次的代理包装 2.
	 * 通过ServiceProxy进行了第二次包装，主要解决离线和在线的调用问题
	 */
	public <T extends IService> T getService(Class<T> serviceCls,
			String serviceName, T executor) {
		IService targetService = null;
		// 判断是否在服务缓存里面存在
		if (servicesCache.containsKey(serviceCls)) {
			targetService = servicesCache.get(serviceCls);
		} else {
			// 通过Hessian工厂生成
			serviceName = computeServiceName(serviceName);
			try {
				targetService = (IService) this.factory.create(serviceCls,
						serviceName);
				// 放入缓存保留
				servicesCache.put(serviceCls, targetService);
			} catch (MalformedURLException e) {
				logger.error(String.format(
						"Not found Serivce interface with classpaht [%s]",
						serviceCls.getName()), e);
			}
		}
		if (null == targetService) {
			throw new RemoteConfigurationException();
		}

		RemoteServiceProxy proxy = new RemoteServiceProxy();
		// 存入当前代理实际要调用的对象和离线执行callBack
		proxy.setExceptionHandler(exceptionHandler);
		proxy.setExecutor(executor);
		proxy.setTarget(targetService);
		return proxy.create(serviceCls);

	}

	/**
	 * 未指定远程服务名用类名 IUserService -> userService
	 * 
	 * @param clsName
	 * @return
	 */
	private String computeDefaultServiceName(String clsName,String moduleName) {
		final String returnName = clsName.substring(1);
		return moduleName+"/"+(returnName.substring(0, 1).toLowerCase()
				.concat(returnName.substring(1)));
	}

	private String computeServiceName(String serviceName) {
		URL defURL = this.remoteInfo.getRemoteURL();
		String urlString = defURL.toString();
		//为了权限需要加一个模块名
		
		return (urlString.endsWith("/") ? urlString.concat(serviceName)
				: urlString.concat("/").concat(serviceName));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.foss.framework.client.component.remote.IRemoteServer#shutdown()
	 */
	@Override
	public void shutdown() {
		this.transport.destroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.framework.client.component.remote.IRemoteServer#
	 * addRemoteServerStatusListener
	 * (com.deppon.foss.framework.client.component.networkstatus
	 * .INetworkStatusListener)
	 */
	@Override
	public void addRemoteServerStatusListener(INetworkStatusListener listener) {
		this.transport.getNetworkMonitor().addStatusListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.framework.client.component.remote.IRemoteServer#
	 * removeRemoteServerStatusListener
	 * (com.deppon.foss.framework.client.component
	 * .networkstatus.INetworkStatusListener)
	 */
	@Override
	public void removeRemoteServerStatusListener(INetworkStatusListener listener) {
		this.transport.getNetworkMonitor().removeStatusListener(listener);

	}

	@Override
	public ITransport getTransport() {
		return this.transport;
	}

}
