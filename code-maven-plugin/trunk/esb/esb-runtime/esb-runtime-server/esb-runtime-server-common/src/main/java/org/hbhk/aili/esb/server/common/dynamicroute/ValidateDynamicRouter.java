package org.hbhk.aili.esb.server.common.dynamicroute;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.utils.ValidateHeaderUtil;

/**
 * 对消息头进行规范验证，通过：路由到正常的endpoint；失败：路由到异常endpoint.
 * 
 * @author HuangHua
 */
public class ValidateDynamicRouter {

	/** 头信息验证工具类. */
	private ValidateHeaderUtil validateHeaderUtil;

	/**
	 * 路由.
	 * 
	 * @param header
	 *            the header
	 * @param previous
	 *            the previous
	 * @return the string
	 */
	public String route(@Headers Map<String, Object> header,
			@Header(Exchange.SLIP_ENDPOINT) String previous) {
		return whereToGo(header, previous);
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
			// 前端是不能传输后端服务编码的，所以如果存在后端服务编码，则表示回调
			String backServiceCode = (String) header
					.get(ESBServiceConstant.BACK_SERVICE_CODE);
			if (backServiceCode != null && !"".equals(backServiceCode)) {
				// 回调校验
				isSuccess = isSuccess
						&& validateHeaderUtil.validateCallBack(header);
			} else {
				// 请求校验
				isSuccess = isSuccess
						&& validateHeaderUtil.validateRequest(header);
			}
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

	/**
	 * getValidateHeaderUtil.
	 * 
	 * @return the 头信息验证工具类
	 */
	public ValidateHeaderUtil getValidateHeaderUtil() {
		return validateHeaderUtil;
	}

	/**
	 * setValidateHeaderUtil.
	 * 
	 * @param validateHeaderUtil
	 *            the new 头信息验证工具类
	 */
	public void setValidateHeaderUtil(ValidateHeaderUtil validateHeaderUtil) {
		this.validateHeaderUtil = validateHeaderUtil;
	}
}
