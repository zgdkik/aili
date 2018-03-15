package org.hbhk.aili.client.core.component.dataaccess;

/**
* <b style="font-family:微软雅黑"><small>Description:服务工厂类</small></b>   </br>
 */
public final class ServiceFactory {
	
	private ServiceFactory(){
		
	}
	
	public static <T> T getService(Class<T> type){
		T obj = null;
		type.isPrimitive();
		try {
			obj = type.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
