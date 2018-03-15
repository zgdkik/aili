package org.hbhk.aili.esb.server.common.config;


/**
 * 运行时加载配置信息.
 */
public class ServiceConfigLoaderRuntimeService {
	
	/** The config loader. */
	private IServiceConfigLoader configLoader;
	
	/**
	 * 刷新配置信息.
	 */
	public void refreshServiceConfig(){
		configLoader.refresh();
	}

	/**
	 * Gets the config loader.
	 * 
	 * @return the config loader
	 */
	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	/**
	 * Sets the config loader.
	 * 
	 * @param configLoader
	 *            the new config loader
	 */
	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}


}
