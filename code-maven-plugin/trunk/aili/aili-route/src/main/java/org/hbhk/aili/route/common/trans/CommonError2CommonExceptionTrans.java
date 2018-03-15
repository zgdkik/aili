package org.hbhk.aili.route.common.trans;

import org.hbhk.aili.route.http.bean.CommonError;
import org.hbhk.aili.route.jms.server.common.CommonExceptionInfo;
/** 
 * @className: CommonError2CommonExceptionTrans 
 * @description: 转化工具类，CommonExceptionInfo 与CommonError进行转换
 * @author 054839 
 * @date 2014年10月29日 下午3:28:12  
 */ 
public class CommonError2CommonExceptionTrans {
	public static  CommonExceptionInfo trans(
			CommonError error) {
		//引用CommonExceptionInfo，将CommonError JAVA类转换成json对象
		CommonExceptionInfo commonExceptionInfo = new CommonExceptionInfo();
		commonExceptionInfo.setExceptioncode(error.getExceptionCode());
		commonExceptionInfo.setExceptiontype(error.getExceptionType());
		commonExceptionInfo.setDetailedInfo(error.getDetailedInfo());
		commonExceptionInfo.setCreatedTime(error.getCreatedTime());
		commonExceptionInfo.setMessage(error.getMessage());
		return commonExceptionInfo;
	}
}
