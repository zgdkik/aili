package com.yimidida.ows.common.server.service;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.common.share.entity.SystemParameterEntity;

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
