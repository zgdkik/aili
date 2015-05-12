package org.hbhk.aili.rpc.server.hessian.remote;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.share.util.FileLoadUtil;
import org.hbhk.aili.core.share.util.IOUtils;
import org.hbhk.aili.rpc.server.hessian.IHessianRemoting;
import org.hbhk.aili.rpc.share.model.RemoteInfo;

public final class DefaultRemoteServiceFactory extends AbstractRemoteServerFactory {
	private static final Log LOG = LogFactory.getLog(DefaultRemoteServiceFactory.class);

	private static final DefaultRemoteServiceFactory instance = new DefaultRemoteServiceFactory();

	// 配置文件位置
	private static final String HESSIAN_CONFIG = "config/config.properties";
	
	public static Properties properties = new Properties();

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
	 * 读取配置文件
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
			in =  FileLoadUtil.getInputStreamForClasspath(HESSIAN_CONFIG);
			properties = new Properties();
			properties.load(in);
			
			reader = new BufferedReader(new InputStreamReader(in,Charset.defaultCharset().name()));
			String info = null;
			RemoteInfo rInfo = new RemoteInfo();
			rInfo.setName(properties.getProperty("remote-name", ""));
			rInfo.setPort(Integer.valueOf(properties.getProperty("server-port", "8080")));
			rInfo.setHessianPrefix(properties.getProperty("service-path", "/"));
			rInfo.setHostName(properties.getProperty("server-host", "localhost"));
			rInfo.setWaitTimeout(Integer.valueOf(properties.getProperty("service-waittimeout", "3")) * 1000);
			rInfo.setConnectionTimeout(Integer.valueOf(properties.getProperty("connection-waittimeout", "3")) * 1000);
			this.addRemoteInfo(rInfo);
			LOG.debug("hessian配置文件信息:"+ToStringBuilder.reflectionToString(info));
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
