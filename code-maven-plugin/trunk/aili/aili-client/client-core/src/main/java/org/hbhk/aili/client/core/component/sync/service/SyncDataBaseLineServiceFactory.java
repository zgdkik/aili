package org.hbhk.aili.client.core.component.sync.service;

import org.hbhk.aili.client.core.component.dataaccess.GuiceContextFactroy;
import org.hbhk.aili.client.core.component.sync.service.imp.SyncDataBaseLineService;

import com.google.inject.Injector;

/**
 * 
 * 
 * <b style="font-family:微软雅黑"><small>Description:SyncDataBaseLineServiceFactory
 */
public final class SyncDataBaseLineServiceFactory {
	
	private SyncDataBaseLineServiceFactory(){
		
	} 
	
	/**
	 * 
	 * 功能：getSyncDateBaseLineService
	 * 
	 * @param:
	 * @return ISyncDataBaseLineService
	 * @since:1.6
	 */
	public static ISyncDataBaseLineService getSyncDateBaseLineService() {
		Injector injector = GuiceContextFactroy.getInjector();
		return injector.getInstance(SyncDataBaseLineService.class);
	}

}
