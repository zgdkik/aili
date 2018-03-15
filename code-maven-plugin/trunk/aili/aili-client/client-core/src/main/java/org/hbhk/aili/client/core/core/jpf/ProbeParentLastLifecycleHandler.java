package org.hbhk.aili.client.core.core.jpf;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.java.plugin.PluginClassLoader;
import org.java.plugin.registry.PluginDescriptor;
import org.java.plugin.standard.StandardPluginLifecycleHandler;

/**
 * 
 *重写插件生命周期管理类的createPluginClassLoader类。
 *主要是为了完成在加载指定名称资源的时候先加载当前插件的资源，
 *最后再加载父ClassLoader中相同名称的资源
 */
public class ProbeParentLastLifecycleHandler extends StandardPluginLifecycleHandler {
	/**
	 * 
	 * @see org.java.plugin.standard.StandardPluginLifecycleHandler#createPluginClassLoader(org.java.plugin.registry.PluginDescriptor)
	 * createPluginClassLoader
	 * @param descr
	 * @return
	 * @since:
	 */
	@Override
	protected PluginClassLoader createPluginClassLoader(final PluginDescriptor descr) {
		ProbeParentLastClassLoader result = AccessController.doPrivileged(
			new PrivilegedAction<ProbeParentLastClassLoader>() {
				public ProbeParentLastClassLoader run() {
					return new ProbeParentLastClassLoader(getPluginManager(), descr,
							ProbeParentLastLifecycleHandler.this.getClass()
							.getClassLoader());
				}
			}
		);
		
		setPrivateFieldValue(result, "probeParentLoaderLast",
			(Boolean)getPrivateFieldValue( this, this.getClass().getSuperclass(),"probeParentLoaderLast"));
		setPrivateFieldValue(result, "stickySynchronizing",
				(Boolean)getPrivateFieldValue(this, this.getClass().getSuperclass(), "stickySynchronizing"));
		setPrivateFieldValue(result, "localClassLoadingOptimization",
				(Boolean)getPrivateFieldValue(this, this.getClass().getSuperclass(), "localClassLoadingOptimization"));
		setPrivateFieldValue(result, "foreignClassLoadingOptimization",
				(Boolean)getPrivateFieldValue(this, this.getClass().getSuperclass(), "foreignClassLoadingOptimization"));
		
		return result;
	}
	
	private Object getPrivateFieldValue(Object object, Class<?> type, String fieldName) {
		Field field = null;
		Boolean oldAccessible = null;
		try {
			field = type.getDeclaredField(fieldName);
			oldAccessible = field.isAccessible();
			field.setAccessible(true);
			
			return field.get(object);
		} catch (Exception e) {
			return null;
		} finally {
			if (field != null && oldAccessible != null) {
				field.setAccessible(oldAccessible);
			}
		}
	}
	
	private void setPrivateFieldValue(Object object, String fieldName, Object value) {
		Field field = null;
		Boolean oldAccessible = null;
		try {
			field = object.getClass().getDeclaredField(fieldName);
			oldAccessible = field.isAccessible();
			field.setAccessible(true);
			field.set(object, value);
			
		} catch (Exception e) {
			// ignore
		} finally {
			if (field != null && oldAccessible != null) {
				field.setAccessible(oldAccessible);
			}
		}
		
	}
}
