package org.hbhk.aili.rpc.server.hessian.remote;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.share.util.IOUtils;
import org.hbhk.aili.rpc.server.hessian.IHessianRemoting;
import org.hbhk.aili.rpc.share.model.RemoteInfo;
import org.springframework.context.ApplicationContext;

/**
 * Filename: DefaultRSFactory.java Description: Company: deppon
 * 
 * @author: davidcun
 * @version: 1.0 Create at: 2011-4-7 涓嬪崍05:06:26
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2011-4-7 davidcun 1.0 1.0 Version
 */
public final class DefaultRemoteServiceFactory extends AbstractRemoteServerFactory {
	// 鏃ュ織瀵硅薄
	private static final Log LOG = LogFactory.getLog(DefaultRemoteServiceFactory.class);

	private static final DefaultRemoteServiceFactory instance = new DefaultRemoteServiceFactory();

	// 杩滅▼杩炴帴閰嶇疆鏂囦欢鍚嶇О
	private static final String HESSIAN_CONFIG = "hessianConfig.ini";

	public static IRemoteServerFactory getInstance() {
		return instance;
	}
	
	/**
	 * 
	 * <p>Title: DefaultRemoteServiceFactory</p>
	 * <p>Description: 鏋勯�骞跺垵濮嬪寲</p>
	 */
	private DefaultRemoteServiceFactory() {
		init();
	}

	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 鑾峰彇杩滅▼鏈嶅姟瀵硅薄</p>
	 * @param <T>
	 * @param cls
	 * @param executor
	 * @return
	 */
	public static <T extends IHessianRemoting> T getService(Class<T> cls, T executor) {
		return instance.getRemoteServer().getService(cls, executor);
	}

	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 鑾峰彇杩滅▼鏈嶅姟瀵硅薄</p>
	 * @param <T>
	 * @param cls
	 * @return
	 */
	public static <T extends IHessianRemoting> T getService(Class<T> cls) {
		return instance.getRemoteServer().getService(cls);
	}

	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 鑾峰彇杩滅▼鏈嶅姟瀵硅薄</p>
	 * @param <T>
	 * @param serviceCls
	 * @param aliasName
	 * @return
	 */
	public static <T extends IHessianRemoting> T getService(Class<T> serviceCls, String aliasName) {
		return instance.getRemoteServer().getService(serviceCls, aliasName);
	}

	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 鑾峰彇杩滅▼鏈嶅姟瀵硅薄</p>
	 * @param <T>
	 * @param serviceCls
	 * @param aliasName
	 * @param executor
	 * @return
	 */
	public static <T extends IHessianRemoting> T getService(Class<T> serviceCls, String aliasName, T executor) {
		return instance.getRemoteServer().getService(serviceCls, aliasName, executor);
	}
	
	/**
	 * 
	 * <p>Title:parseTransportConfigurations
	 * <p>Description:瑙ｆ瀽杩滅▼閫氳娉ㄥ唽灞炴�</p>
	 * @see com.deppon.foss.framework.client.component.remote.AbstractRemoteServerFactory#parseTransportConfigurations()
	 */
	@Override
	protected void parseTransportConfigurations() {
		BufferedReader reader = null;
		InputStream in = null;
		try {
			/*
			File file = new File(ApplicationContext.getAppConfigHome() + File.separator + HESSIAN_CONFIG);
			if (!file.exists()) {
				LOG.error("can not find hessianConfig file in appConfigHome");
			}*/
			in = ApplicationContext.class.getClassLoader().getResourceAsStream(HESSIAN_CONFIG);
			reader = new BufferedReader(new InputStreamReader(in,Charset.defaultCharset().name()));
			String info = null;
			RemoteInfo rInfo = null;
			while ((info = reader.readLine()) != null) {
				info = info.trim();
				if (info.length() < 1){
					continue;
				}
				if (info.startsWith("[") && info.endsWith("]")) {
					rInfo = new RemoteInfo();
					rInfo.setName(info);
					this.addRemoteInfo(rInfo);
				}
				if (info.startsWith("server-port")) {
					info = info.replaceAll(".*\\:", "");
					rInfo.setPort(Integer.valueOf(info));
				}
				if (info.startsWith("service-path")) {
					info = info.replaceAll(".*\\:", "");
					rInfo.setHessianPrefix(info);
				}
				if (info.startsWith("server-host")) {
					info = info.replaceAll(".*\\:", "");
					rInfo.setHostName(info);
				}
				if (info.startsWith("service-waittimeout")) {
					info = info.replaceAll(".*\\:", "");
					rInfo.setWaitTimeout(Integer.valueOf(info) * 1000);
				}
				if (info.startsWith("connection-waittimeout")) {
					info = info.replaceAll(".*\\:", "");
					rInfo.setConnectionTimeout(Integer.valueOf(info) * 1000);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			IOUtils.close(reader);
			IOUtils.close(in);
		}
	}

	/**
	 * 閿�瘉
	 */
	public static void destroy() {
		instance.shutdown();
	}
}
