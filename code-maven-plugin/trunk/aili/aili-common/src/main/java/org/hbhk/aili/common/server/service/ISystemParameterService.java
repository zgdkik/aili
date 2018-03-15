package org.hbhk.aili.common.server.service;

import org.hbhk.aili.base.server.service.IBaseService;
import org.hbhk.aili.common.share.entity.SystemParameterEntity;


public interface ISystemParameterService extends
		IBaseService <SystemParameterEntity, String> {

	boolean checkSysKey(String sysKey);

}
