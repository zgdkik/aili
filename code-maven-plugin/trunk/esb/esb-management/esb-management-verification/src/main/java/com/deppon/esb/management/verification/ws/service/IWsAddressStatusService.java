package com.deppon.esb.management.verification.ws.service;


/**
 * WebService地址状态服务
 * 
 * @author HuangHua
 * 
 */
public interface IWsAddressStatusService {

	/**获取状态。
	 * key--value:url--Server HTTP version, Response code
	 * 如果获取状态时发出异常，则返回的Server HTTP version Response code有可能是null。
	 * @param urls
	 *            待验证的URL地址列表
	 * @return Map<String, String>：key--value:url--Server HTTP version, Response code
	 */
	public String[] getStatus(String... urls);
	public String getStatus(String url);
}
