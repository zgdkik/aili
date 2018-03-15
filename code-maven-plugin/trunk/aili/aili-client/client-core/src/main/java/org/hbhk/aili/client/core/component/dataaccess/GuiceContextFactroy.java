package org.hbhk.aili.client.core.component.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.hbhk.aili.client.core.core.context.ApplicationContext;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;

/**
 * 
 * Google-Guice上下文环境,通过Injector获取gui注入的类。
 */
public final class GuiceContextFactroy {
	private static Injector injector = null;
	
	private static Properties properties = null;

	private static List<Module> modules = new ArrayList<Module>();

	private GuiceContextFactroy(){
		
	}
	
	public static void addModule(Module m){
		modules.add(m);
	}
	
	public static Injector getInjector() {
		if (injector == null) {
			createInJector();
		}
		
		return injector;
	}
	
	private static synchronized Properties createProperties() {
		File file = new File(ApplicationContext.getAppConfigHome() + File.separator + "hsqldb.properties");
		properties = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(is!=null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return properties;
	}
	
	private static synchronized void createInJector() {
		if(injector==null){
			modules.add(new MyBatisModule() {
				@Override
				protected void initialize() {
					 bindDataSourceProviderType(PooledDataSourceProvider.class);
		             bindTransactionFactoryType(JdbcTransactionFactory.class);
		             //addMapperClasses("com.deppon.foss.module.waybill.client.dao");
				}
			});
			
//			modules.add(new XMLMyBatisModule() {
//
//	            @Override
//	            protected void initialize() {
//	                setEnvironmentId("test");
//	                setClassPathResource("/conf/mybatis.xml");
//	            }
//
//	        });
			if(properties==null){
				modules.add(new Module() {
					public void configure(Binder binder) {
						Names.bindProperties(binder, createProperties());
					}
				});
			}
			injector = Guice.createInjector(modules);
		}
	}

}
