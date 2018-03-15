package org.hbhk.aili.esb.server.agent.processor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.common.constant.EsbRouteConstant;
import org.hbhk.aili.esb.common.constant.ExpressConstant;
import org.hbhk.aili.esb.server.agent.exception.AgentEsbHeaderException;
import org.hbhk.aili.esb.server.agent.exception.AgentExceptionConstant;
import org.hbhk.aili.esb.server.agent.util.URLResolution;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;
import org.hbhk.aili.esb.server.security.authorize.IAuthorize;
import org.hbhk.aili.esb.server.security.exception.AuthorizeException;
import org.hbhk.aili.esb.server.security.exception.FlowControlException;
import org.hbhk.aili.esb.server.security.flowcontrol.container.ICountContainer;
import org.hbhk.aili.esb.server.security.flowcontrol.counter.ICounter;

public class ExpressHeaderProcessor {
	private static Logger LOG = Logger.getLogger(ExpressHeaderProcessor.class);
	// 服务配置
	private IServiceConfigLoader configLoader;
	// 权限认证
	private IAuthorize authorize;
	// 流量计数
	private ICountContainer esbInterfaceCountContainer;
	private ICountContainer esbUserInterfaceCountContainer;

	/**
	 * 检查esbHeader属性是否完整
	 */
	public void esbHeadercheck(@Header(ExpressConstant.EXPRESS_USERNAME) String username,
			@Header(ExpressConstant.EXPRESS_PASSWORD) String password,
			@Header(ExpressConstant.EXPRESS_ESB_SERVICE_CODE) String esbServiceCode,
			@Header(ExpressConstant.EXPRESS_VERSION) String version, @Headers Map<String, Object> headers) {
		if (username == null || username.length() == 0 || password == null || password.length() == 0
				|| esbServiceCode == null || esbServiceCode.length() == 0 || version == null || version.length() == 0) {
			throw new AgentEsbHeaderException("esbheader信息不完整，ESB-UserName:[" + username + "],ESB-Password:["
					+ password + "],ESB-ESBServiceCode:[" + esbServiceCode + "],ESB-Version[" + version + "]",
					AgentExceptionConstant.TYPE_SYS, AgentExceptionConstant.INCOMPLETE_SPECIFICATION);
		}
		if (configLoader.get(esbServiceCode) == null) {
			String msg = "ESB-ESBServiceCode消息头中所定义的服务编码" + esbServiceCode + "不存在";
			throw new AgentEsbHeaderException(msg, AgentExceptionConstant.TYPE_SYS,
					AgentExceptionConstant.AUTH_ESBSERVICE_NOT_EXIST);
		}
		LOG.debug("esbHeadercheck method called");
	}

	/**
	 * 
	 */
	public void authCheck(@Header(ExpressConstant.EXPRESS_USERNAME) String username,
			@Header(ExpressConstant.EXPRESS_PASSWORD) String password,
			@Header(ExpressConstant.EXPRESS_ESB_SERVICE_CODE) String esbServiceCode,
			@Headers Map<String, Object> headers) {
		LOG.debug("authcheck method called");
		if (!authorize.userCheck(username, password)) {
			String msg = "用户名:" + username + " 密码:" + password + "验证不通过";
			throw new AuthorizeException(msg, AgentExceptionConstant.TYPE_SYS,
					AgentExceptionConstant.AUTH_USER_PASSWORD_ERROR);
		}
		if (!authorize.interfaceCheck(username, esbServiceCode)) {
			String msg = "用户没有权限访问接口，用户名：" + username + "服务编码：" + esbServiceCode;
			throw new AuthorizeException(msg, AgentExceptionConstant.TYPE_SYS,
					AgentExceptionConstant.AUTH_UESR_INTERFACE_ERROR);
		}
		// 密码清除掉,不然后端能从http头里获取获ESB密码
		headers.remove(ExpressConstant.EXPRESS_PASSWORD);

	}

	/**
	 * 接口流量控制
	 * 
	 * @return
	 */
	public void interfaceCountCheck(@Header(ExpressConstant.EXPRESS_USERNAME) String user,
			@Header(ExpressConstant.EXPRESS_ESB_SERVICE_CODE) String esbServiceCode) {
		// 接口用户调用次数计数
		ICounter userInterfaceCounter = esbUserInterfaceCountContainer.get(user + esbServiceCode);
		if (userInterfaceCounter.decrementandGet() < 0) {
			String msg = "该接口,此用户调用次数到达上限，接口服务编码：" + esbServiceCode + " 用户：" + user + "上限值："
					+ userInterfaceCounter.getInitValue();
			throw new FlowControlException(msg, AgentExceptionConstant.TYPE_SYS,
					AgentExceptionConstant.FLOWCONTROL_USER_INTERFACE_LIMIT);
		}
		// 接口调用次数计数
		ICounter interfaceCounter = esbInterfaceCountContainer.get(esbServiceCode);
		if (interfaceCounter.decrementandGet() < 0) {
			String msg = "接口调用次数达到上限,接口服务编码：" + esbServiceCode + " 上限值：" + interfaceCounter.getInitValue();
			throw new FlowControlException(msg, AgentExceptionConstant.TYPE_SYS,
					AgentExceptionConstant.FLOWCONTROL_INTERFACE_LIMIT);
		}
	}

	/**
	 * 完善esbHeader属性
	 * 
	 * @param headers
	 */
	public void completeEsbHeader(@Headers Map<String, Object> headers) {		
		Map<String, Object> map = URLResolution.parsing(headers);
		headers.put(ExpressConstant.EXPRESS_ESB_SERVICE_CODE, map.get(EsbRouteConstant.SERVICE_CODE));
		headers.put(ExpressConstant.EXPRESS_VERSION, "1.0");
		if (EsbRouteConstant.HTTP_GET_METHOD.equalsIgnoreCase((String) headers.get(Exchange.HTTP_METHOD))) {
			headers.put(ESBServiceConstant.EXCHANGE_BODY, headers.get(EsbRouteConstant.HTTP_URL));
		}
		HttpServletRequest servletRequest = (HttpServletRequest) headers.get(Exchange.HTTP_SERVLET_REQUEST);
		String parameter = (String) headers.get(Exchange.HTTP_QUERY);
		String http = "http://" + servletRequest.getRemoteHost() + ":" + servletRequest.getRemotePort()
				+ headers.get(Exchange.HTTP_PATH) + (parameter == null ? "" : ("?" + parameter));
		headers.put(EsbRouteConstant.HTTP_REMOTE_URL, http);
		headers.put(EsbRouteConstant.MESSAGE_BOLB, map.get(EsbRouteConstant.MESSAGE_BOLB));
		LOG.debug("completeEsbHeader method called");
	}
	

	/** 
	 * @author :054839
	 * @Description:  添加是否记录审计日志、状态日志、异常日志
	 * @param headers    
	 * @return void     
	 * @create 2014-5-26上午10:00:18
	 */
	public void completeEsbHeaderForExpress(@Headers Map<String, Object> headers) {
		String code = (String)headers.get(ExpressConstant.EXPRESS_ESB_SERVICE_CODE);
		ESBRuntimeConfiguration config = configLoader.get(code).get(0);
		headers.put(EsbRouteConstant.IS_RECORD_AUDIT, config.isNeedAudit());
		headers.put(EsbRouteConstant.IS_RECORD_EXCEPTION, config.isNeedExpn());
		headers.put(EsbRouteConstant.IS_RECORD_STATUS, config.isNeedStatus());
	}
	
	/** 
	 * @author :054839
	 * @Description: 在header信息中添加 ESB-ResultCode=1
	 * @param headers    
	 * @return void     
	 * @create 2014-7-1下午3:50:33
	 */
	public void addResultCodeHeader(@Headers Map<String, Object> headers){
		headers.put(ExpressConstant.EXPRESS_RESUTLCODE, "1");
	}

	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

	public IAuthorize getAuthorize() {
		return authorize;
	}

	public void setAuthorize(IAuthorize authorize) {
		this.authorize = authorize;
	}

	public ICountContainer getEsbInterfaceCountContainer() {
		return esbInterfaceCountContainer;
	}

	public void setEsbInterfaceCountContainer(ICountContainer esbInterfaceCountContainer) {
		this.esbInterfaceCountContainer = esbInterfaceCountContainer;
	}

	public ICountContainer getEsbUserInterfaceCountContainer() {
		return esbUserInterfaceCountContainer;
	}

	public void setEsbUserInterfaceCountContainer(ICountContainer esbUserInterfaceCountContainer) {
		this.esbUserInterfaceCountContainer = esbUserInterfaceCountContainer;
	}

}
