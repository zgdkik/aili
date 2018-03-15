package org.hbhk.aili.route.jms.server.route.impl;

import java.util.Map;

import org.hbhk.aili.route.jms.server.common.utils.ValidateHeaderUtil;
import org.hbhk.aili.route.jms.server.route.IDynamicRouter;

/**
 * 对消息头进行规范验证，通过：路由到正常的endpoint；失败：路由到异常endpoint.
 */
public class ValidateDynamicRouter implements  IDynamicRouter{

	/** 头信息验证工具类. */
	private ValidateHeaderUtil validateHeaderUtil;
	
	@Override
	public String[] route(String previous, String interfaceCode,
			Map<String, Object> headers, Map<String, Object> properties) {
		return new String[]{ whereToGo(headers, previous)};
	}


	/**
	 * 计算下一个节点
	 * 
	 * @param header
	 *            头信息
	 * @param previous
	 *            camel执行标志
	 * @return the string
	 */
	private String whereToGo(Map<String, Object> header, String previous) {
		// 如果为空,直接返回null
		if (previous == null) {
			// 验证接口是否已经注册
			boolean isSuccess = validateHeaderUtil.validateExistService(header);
			// 如果不存在，走异常路由
			if (!isSuccess) {
				return "direct:exception";
			}
				// 请求校验
			isSuccess = isSuccess&& validateHeaderUtil.validateRequest(header);
			// 校验失败,走异常路由
			if (!isSuccess) {
				return "direct:exception";
			} else {// 检验成功,走正常路由
				return "direct:normal";
			}
		}
		// no more, so return null to indicate end of dynamic router
		return null;

	}

	public ValidateHeaderUtil getValidateHeaderUtil() {
		return validateHeaderUtil;
	}

	public void setValidateHeaderUtil(ValidateHeaderUtil validateHeaderUtil) {
		this.validateHeaderUtil = validateHeaderUtil;
	}

	
}
