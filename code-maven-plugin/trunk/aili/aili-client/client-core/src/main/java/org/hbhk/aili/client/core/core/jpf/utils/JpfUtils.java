package org.hbhk.aili.client.core.core.jpf.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.hbhk.aili.client.core.core.application.IApplication;
import org.hbhk.aili.client.core.core.application.IApplicationAware;
import org.hbhk.aili.client.core.core.context.ApplicationContext;
import org.hbhk.aili.client.core.core.jpf.IPluginIdAware;
import org.hbhk.aili.client.core.core.workbench.IPluginAware;
import org.hbhk.aili.client.core.core.workbench.IWorkbenchAware;
import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;

/**
 * 
 *一个工具类，可以通过指定的插件对象及类名创建对象
 */
public final class JpfUtils {
	
	private JpfUtils(){
		
	}
	
	/**
	 * 通过指定的插件及类名创建对象，如有需要可以向创建的对象注入一些全局对象
	 * createInstance
	 * @param <T>
	 * @param plugin 插件
	 * @param className 类名
	 * @return
	 * @return T
	 * @since:0.6
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createInstance(Plugin plugin, String className) {
		if (plugin == null || className == null) {
			return null;
		}
		T result = null;
		
		if (!plugin.isActive()) {
			try {
				plugin.getManager().activatePlugin(plugin.getDescriptor().getId());
			} catch (PluginLifecycleException e) {
				e.printStackTrace();
			}
		}
		ClassLoader classLoader = plugin.getManager().getPluginClassLoader(plugin.getDescriptor());
		try {
			Class<T> clazz = (Class<T>)classLoader.loadClass(className);
			result = (T)clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	
		if (result != null) {
			injectObjects(plugin,result);
			return result;
		}
		
		throw new RuntimeException("can not load class "+className);
	}
	
	
	
	public static Class loadClass(Plugin plugin, String className){
		if (plugin == null || className == null) {
			return null;
		}
		
		if (!plugin.isActive()) {
			try {
				plugin.getManager().activatePlugin(plugin.getDescriptor().getId());
			} catch (PluginLifecycleException e) {
				e.printStackTrace();
			}
		}
		ClassLoader classLoader = plugin.getManager().getPluginClassLoader(plugin.getDescriptor());
		try {
			Class clazz = (Class)classLoader.loadClass(className);
			return clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static <T> T createInstance(Plugin plugin, String className, 
			Object[] param, Class[] constructs) {
		if (plugin == null || className == null) {
			return null;
		}
		T result = null;
		
		if (!plugin.isActive()) {
			try {
				plugin.getManager().activatePlugin(plugin.getDescriptor().getId());
			} catch (PluginLifecycleException e) {
				e.printStackTrace();
			}
		}
		ClassLoader classLoader = plugin.getManager().getPluginClassLoader(plugin.getDescriptor());
		try {
			Class[] cc = constructs;
			Class<T> clazz = (Class<T>)classLoader.loadClass(className);
			Constructor ct = clazz.getConstructor(cc);
			result = (T)ct.newInstance(param );
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if (result != null) {
			injectObjects(plugin,result);
			return result;
		}
		
		throw new RuntimeException("can not load class "+className);
	}
	
	public static <T> T createInstance(Plugin plugin, String className, Object[] param) {
		if (plugin == null || className == null) {
			return null;
		}
		T result = null;
		
		if (!plugin.isActive()) {
			try {
				plugin.getManager().activatePlugin(plugin.getDescriptor().getId());
			} catch (PluginLifecycleException e) {
				e.printStackTrace();
			}
		}
		ClassLoader classLoader = plugin.getManager().getPluginClassLoader(plugin.getDescriptor());
		try {
			Class[] cc = { Object.class };
			Class<T> clazz = (Class<T>)classLoader.loadClass(className);
			Constructor ct = clazz.getConstructor(cc);
			result = (T)ct.newInstance(param );
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if (result != null) {
			injectObjects(plugin,result);
			return result;
		}
		
		throw new RuntimeException("can not load class "+className);
	}
	
	/**
	 * 给指定的对象注入IApplication、IWorkbench、PluginId、Plugin对象。
	 * 当然，前提是指定的对象需要实现相应对象的注入接口。
	 * injectObjects
	 * @param plugin 插件
	 * @param object 指定的对象
	 * @return void
	 * @since:0.6
	 */
	public static void injectObjects(Plugin plugin, Object object) {
		if(plugin == null || object == null) {
			return;
		}
		
		IApplication application = ApplicationContext.getApplication();
		
		if((object instanceof IWorkbenchAware) && (application != null)) {
			((IWorkbenchAware)object).setWorkbench(application.getWorkbench());
		}
		
		if((object instanceof IApplicationAware) && (application != null)) {
			((IApplicationAware)object).setApplication(application);
		}
		
		String pluginId = plugin.getDescriptor().getId();
		if((object instanceof IPluginIdAware)) {
			((IPluginIdAware)object).setPluginId(pluginId);
		}
			
		if((object instanceof IPluginAware)) {
			((IPluginAware)object).setPlugin(plugin);
		}
	}
}