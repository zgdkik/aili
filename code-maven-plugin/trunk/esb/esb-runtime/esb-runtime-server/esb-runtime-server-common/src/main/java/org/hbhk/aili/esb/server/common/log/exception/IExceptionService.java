package org.hbhk.aili.esb.server.common.log.exception;

import java.util.Map;

import org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo;

/**
 * 异常处理接口.
 * 
 * @author HuangHua
 * @date 2012-12-21 上午10:48:16
 */
public interface IExceptionService {

	/**
	 * Save exception.
	 * 
	 * @param header
	 *            <p>
	 *            ESBServiceConstant.ESB_SERVICE_CODE, esbServiceCode/p>
	 *            <p>
	 *            ESBServiceConstant.BACK_SERVICE_CODE, backServiceCode/p>
	 *            <p>
	 *            ESBServiceConstant.BUSINESSID, businessId/p>
	 *            <p>
	 *            ESBServiceConstant.BUSINESSDESC1, businessDesc1/p>
	 *            <p>
	 *            ESBServiceConstant.BUSINESSDESC2, businessDesc2/p>
	 *            <p>
	 *            ESBServiceConstant.BUSINESSDESC3, businessDesc3/p>
	 *            <p>
	 *            ESBServiceConstant.REQUEST_ID, requestId/p>
	 *            <p>
	 *            ESBServiceConstant.RESPONSE_ID, responseId/p>
	 *            <p>
	 *            ESBServiceConstant.HOST_IP, ip
	 *            </p>
	 * @param commonException
	 *            CommonExceptionInfo
	 */
	public void saveException(Map<String, Object> header, CommonExceptionInfo commonException);

}
