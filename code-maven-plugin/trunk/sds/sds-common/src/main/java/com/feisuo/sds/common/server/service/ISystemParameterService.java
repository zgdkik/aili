package com.feisuo.sds.common.server.service;

import com.feisuo.sds.base.server.service.IBaseService;
import com.feisuo.sds.common.share.entity.SystemParameterEntity;

/**
 * 
 * ClassName: ISystemParameterService 
 * Description: TODO 
 * Author: fanhoutao Date:
 * 2015年12月5日
 */

public interface ISystemParameterService extends
		IBaseService <SystemParameterEntity, String> {

	boolean checkSysKey(String sysKey);

}
