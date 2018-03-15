package org.hbhk.aili.client.core.core.application;

import java.io.File;

import org.hbhk.aili.client.core.core.context.ApplicationContext;
import org.java.plugin.boot.DefaultApplicationInitializer;
import org.java.plugin.util.ExtendedProperties;

/**
 * 
 *	应用初始化类，当JPF启动Application之前他会调用此类ApplicationInitializer类的configure及initApplication方法
 */
public class ApplicationInitializer extends DefaultApplicationInitializer {
	public static final String PARAM_APPLICATION_PLUGIN = "org.java.plugin.boot.applicationPlugin";
	
	@Override
	public void configure(ExtendedProperties configuration) throws Exception {
		
		String fossHome = (String) configuration.get("applicationRoot");
		String fossPluginsHome = System.getenv("foss_plugins_home");
		if (fossPluginsHome == null) {
			fossPluginsHome = fossHome + System.getProperty("file.separator") + "plugins";
		}
		
		String fossLauncherHome = System.getenv("foss_launcher_home");
		if (fossLauncherHome != null) {
			System.setProperty("foss_launcher_home", fossLauncherHome);
		}
		
		System.setProperty("foss_home", fossHome);
		System.setProperty("foss_plugins_home", fossPluginsHome);
		System.setProperty(PARAM_APPLICATION_PLUGIN, configuration.getProperty(PARAM_APPLICATION_PLUGIN));
		
		///appHome
		ApplicationContext.setApplicationHome(fossHome);
		
		//dbHome
		String dbDir = fossHome + File.separator + "database" + File.separator + "hsqldb" ;
		File dbFile = new File(dbDir);
		if(!dbFile.exists()){
			dbFile.mkdirs();
		}
		System.setProperty("foss_db_home", dbFile.getAbsolutePath());
		
		//configHome
		String appConfigHome = fossHome + File.separator + "conf";
		File conf = new File(appConfigHome);
		if(!conf.exists()){
			conf.mkdirs();
		}
		System.setProperty("foss_config_home", conf.getAbsolutePath());
		
		super.configure(configuration);
	}
}
