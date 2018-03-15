package com.deppon.esb.management.status.util;

import com.deppon.esb.management.common.constant.ESBServiceConstant;
import com.deppon.esb.management.status.domain.EsbStatusInfo;

/**
 * 
 * @author MoYi
 * @date 2013-8-15 状态日志字段截取，保证能正常插入数据库。包括（busindessId,requestId,responseId）
 */
public class EsbStatusInfoUtil {
	public static void truncEsbStatusInfoAttribute(EsbStatusInfo esbStatusInfo) {
		String businessId = esbStatusInfo.getBusinessId();
		String requestId = esbStatusInfo.getRequestId();
		String responseId = esbStatusInfo.getResponseId();
		if(businessId!=null){
			esbStatusInfo
					.setBusinessId(businessId.length() > ESBServiceConstant.BUSINESSID_MAX_LENGTH ?
							businessId.substring(0,ESBServiceConstant.BUSINESSID_MAX_LENGTH)
							: businessId);
		}
		if (requestId != null) {
			esbStatusInfo
					.setRequestId(requestId.length() > ESBServiceConstant.REQUEST_ID_MAX_LENGTH ? 
							requestId.substring(0,ESBServiceConstant.REQUEST_ID_MAX_LENGTH)
							: requestId);
		}
		if (responseId != null) {
			esbStatusInfo
					.setResponseId(responseId.length() > ESBServiceConstant.RESPONSE_ID_MAX_LENGTH ? responseId
							.substring(0,
									ESBServiceConstant.RESPONSE_ID_MAX_LENGTH)
							: responseId);
		}
		esbStatusInfo.setBusinessId(businessId);
		esbStatusInfo.setRequestId(requestId);
		esbStatusInfo.setResponseId(responseId);
	}

}
