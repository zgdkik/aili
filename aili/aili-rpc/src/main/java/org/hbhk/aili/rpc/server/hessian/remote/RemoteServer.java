package org.hbhk.aili.rpc.server.hessian.remote;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.rpc.server.hessian.IHessianRemoting;
import org.hbhk.aili.rpc.server.hessian.client.HttpClientHessianProxyFactory;
import org.hbhk.aili.rpc.server.hessian.handler.IRemoteExceptionHandler;
import org.hbhk.aili.rpc.server.hessian.transport.INetworkStatusListener;
import org.hbhk.aili.rpc.server.hessian.transport.ITransport;
import org.hbhk.aili.rpc.server.hessian.transport.Transport;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * 杩滅▼搴旂敤鐨勭粺涓�皟鐢ㄥ疄鐜�
 * 
 * @author Steven Cheng
 * 
 */
public class RemoteServer implements IRemoteServer {

	/**
	 * 缂撳瓨閫氳繃HessianProxyFactory鍒涘缓鐨勮繙绋嬫湇鍔℃帴鍙ｄ唬鐞嗗疄渚�
	 */
	private static Map<Class<?>, IHessianRemoting> servicesCache = new HashMap<Class<?>, IHessianRemoting>();
	/**
	 * HessianProxy宸ュ巶 宸ュ巶鐨勮繛鎺ュ垱寤哄伐鍘傜敱鏈湴瀹炵幇
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
	public <T extends IHessianRemoting> T getService(Class<T> serviceCls, T executor) {
		
		return getService(serviceCls,
				computeDefaultServiceName(serviceCls.getSimpleName(),getModuleName(serviceCls)), executor);
	}

	@Override
	public <T extends IHessianRemoting> T getService(Class<T> serviceCls,
			String aliasName) {
		return getService(serviceCls, aliasName, null);
	}

	@Override
	public <T extends IHessianRemoting> T getService(Class<T> serviceCls) {
		return getService(serviceCls,
				computeDefaultServiceName(serviceCls.getSimpleName(),getModuleName(serviceCls)), null);
	}
	
	@SuppressWarnings("rawtypes")
	private String getModuleName(Class cls){
		String pkgName = cls.getPackage().getName();
   		String preFix = "";
   		try{
   			if(pkgName.indexOf(".server")>-1){
   				String tmpStr = pkgName.substring(0,pkgName.indexOf(".server"));
   	       		preFix = tmpStr.substring(tmpStr.lastIndexOf(".")+1,tmpStr.length());
   			}else{
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
	 * 瀹為檯鑾峰彇杩滅▼鏈嶅姟鐨勫疄鐜�杩斿洖鐨凷ervice鏄袱灞備唬鐞嗗寘瑁�1. hessian 杩涜浜嗙涓�鐨勪唬鐞嗗寘瑁�2.
	 * 閫氳繃ServiceProxy杩涜浜嗙浜屾鍖呰锛屼富瑕佽В鍐崇绾垮拰鍦ㄧ嚎鐨勮皟鐢ㄩ棶棰�
	 */
	public <T extends IHessianRemoting> T getService(Class<T> serviceCls,
			String serviceName, T executor) {
		IHessianRemoting targetService = null;
		// 鍒ゆ柇鏄惁鍦ㄦ湇鍔＄紦瀛橀噷闈㈠瓨鍦�
		if (servicesCache.containsKey(serviceCls)) {
			targetService = servicesCache.get(serviceCls);
		} else {
			// 閫氳繃Hessian宸ュ巶鐢熸垚
			serviceName = computeServiceName(serviceName);
			try {
				targetService = (IHessianRemoting) this.factory.create(serviceCls,
						serviceName);
				// 鏀惧叆缂撳瓨淇濈暀
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
		// 瀛樺叆褰撳墠浠ｇ悊瀹為檯瑕佽皟鐢ㄧ殑瀵硅薄鍜岀绾挎墽琛宑allBack
		proxy.setExceptionHandler(exceptionHandler);
		proxy.setExecutor(executor);
		proxy.setTarget(targetService);
		return proxy.create(serviceCls);

	}

	/**
	 * 鏈寚瀹氳繙绋嬫湇鍔″悕鐢ㄧ被鍚�IUserService -> userService
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
		//涓轰簡鏉冮檺闇�鍔犱竴涓ā鍧楀悕
		
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

