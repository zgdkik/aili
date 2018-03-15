/**
属性代码			属性名			适用范围		说明
Version			版本号			同步+异步	用于服务的版本控制，有可能存在不同的版本。
BusinessId		业务唯一ID		同步+异步	用于两端应用跟踪服务，属于同一个businessId的消息即表示在业务含义上相同。
businessDesc1	业务辅助字段		同步+异步	业务保留字段1，用于描述业务的辅助信息
businessDesc2	业务辅助字段		同步+异步	业务保留字段2，用于描述业务的辅助信息
businessDesc3	业务辅助字段		同步+异步	业务保留字段3，用于描述业务的辅助信息
RequestId		请求唯一ID		同步+异步	用于请求消息的唯一性标识
ResponseId		响应唯一ID		同步+异步	用于响应消息的唯一性标识
SourceSystem	客户端接入系统	同步+异步	记录客户端的前端接入系统标识，ESB为每个接入的系统设定固定的常量，如ERP， CRM， OA。。。
TargetSystem	请求的目标系统	同步+异步	记录客户端的后端接入系统标识
ESBServiceCode	ESB服务编码		同步+异步	ESB提供给服务消费端的服务编码
BackServiceCode	后端服务编码		同步+异步	服务提供端提供给ESB的服务编码
MessageFormat	消息格式			同步+异步	如XML，JSON，二进制对象等
ExchangePattern	交互模式			异步			请求/响应，请求/回调，单向（只有请求没有结果返回）
SentSequence	发送计数			异步			用来标识是否是重发的消息，第一次发送为1，后续每次重发加1
ResultCode		响应结果状态		同步+异步	处理结果，成功：SUCCESS， 失败：FAILURE
UserName		用户名			同步+异步	用于认证和授权
Password		密码		
<<Service Status List>>	服务状态列表	异步		用于记录服务从请求到相应的整个周期中每个状态的时间戳具体参见服务状态管理

 */

package org.hbhk.aili.esb.server.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 验证头信息是否符合ESB规范.
 * 
 * @author HuangHua
 * @see 《Deppon_ESB接入规范说明书_V0.1.doc》
 */
public class ValidateHeaderUtil {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateHeaderUtil.class);
	
	/** The config loader. */
	private IServiceConfigLoader configLoader;

	/**
	 * 验证服务是否存在.
	 * 
	 * @param headers
	 *            消息头信息
	 * @return true, if successful
	 */
	public boolean validateExistService(Map<String, Object> headers) {
		String esbServiceCode = (String) headers
				.get(ESBServiceConstant.ESB_SERVICE_CODE);
		String backServiceCode = (String) headers
				.get(ESBServiceConstant.BACK_SERVICE_CODE);
		// 前端是不能传输后端服务编码的，所以如果存在后端服务编码，则表示回调
		if (backServiceCode != null && !"".equals(backServiceCode)) {
			return configLoader.getAll().containsKey(backServiceCode);
		} else {
			return configLoader.getAll().containsKey(esbServiceCode);
		}
	}

	/**
	 * 请求时验证.
	 * 
	 * @param headers
	 *            the headers
	 * @return true, if successful
	 */
	public boolean validateRequest(Map<String, Object> headers) {
		// 只要head里没有包含如下的任何一个字段，则校验失败。
		List<String> codes = new ArrayList<String>();
		codes.add(ESBServiceConstant.VERSION);
		codes.add(ESBServiceConstant.BUSINESSID);
		codes.add(ESBServiceConstant.REQUEST_ID);
		codes.add(ESBServiceConstant.SOURCE_SYSTEM);
		codes.add(ESBServiceConstant.ESB_SERVICE_CODE);
		codes.add(ESBServiceConstant.MESSAGE_FORMATE);
		codes.add(ESBServiceConstant.EXCHANGE_PATTERN);
		// codes.add(ESBServiceConstant.USER_NAME);
		// codes.add(ESBServiceConstant.PASSWORD);
		return isContain(headers, codes);
	}

	/**
	 * 回调时验证.
	 * 
	 * @param headers
	 *            the headers
	 * @return true, if successful
	 */
	public boolean validateCallBack(Map<String, Object> headers) {
		// 只要head里没有包含如下的任何一个字段，则校验失败。
		List<String> codes = new ArrayList<String>();
		codes.add(ESBServiceConstant.VERSION);
		codes.add(ESBServiceConstant.BUSINESSID);
		codes.add(ESBServiceConstant.REQUEST_ID);
		codes.add(ESBServiceConstant.RESPONSE_ID);
		codes.add(ESBServiceConstant.SOURCE_SYSTEM);
		codes.add(ESBServiceConstant.TARGET_SYSTEM);
		codes.add(ESBServiceConstant.ESB_SERVICE_CODE);
		codes.add(ESBServiceConstant.BACK_SERVICE_CODE);
		codes.add(ESBServiceConstant.MESSAGE_FORMATE);
		codes.add(ESBServiceConstant.EXCHANGE_PATTERN);
		codes.add(ESBServiceConstant.RESULT_CODE);
		// codes.add(ESBServiceConstant.USER_NAME);
		// codes.add(ESBServiceConstant.PASSWORD);
		return isContain(headers, codes);
	}

	/**
	 * List中的所有值是否都是map中的key.
	 * 
	 * @param map
	 *            the map
	 * @param keys
	 *            the keys
	 * @return true, if is contain
	 */
	private static boolean isContain(Map<String, Object> map, List<String> keys) {
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if (!map.containsKey(string)) {
				LOGGER.debug("validate failed while validating " + string + ".");
				return false;
			}
		}
		LOGGER.debug("validate passed.");
		return true;
	}

	/**
	 * Gets the config loader.
	 * 
	 * @return the config loader
	 */
	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	/**
	 * Sets the config loader.
	 * 
	 * @param configLoader
	 *            the new config loader
	 */
	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

}
