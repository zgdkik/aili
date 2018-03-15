package org.hbhk.aili.esb.server.agent.trans;

import org.hbhk.aili.esb.server.agent.bean.CommonError;
import org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo;

public class CommonError2CommonExceptionTrans {
	public static  CommonExceptionInfo trans(
			CommonError error) {
		CommonExceptionInfo commonExceptionInfo = new CommonExceptionInfo();
		commonExceptionInfo.setExceptioncode(error.getExceptionCode());
		commonExceptionInfo.setExceptiontype(error.getExceptionType());
		commonExceptionInfo.setDetailedInfo(error.getDetailedInfo());
		commonExceptionInfo.setCreatedTime(error.getCreatedTime());
		commonExceptionInfo.setMessage(error.getMessage());
		return commonExceptionInfo;
	}
}
