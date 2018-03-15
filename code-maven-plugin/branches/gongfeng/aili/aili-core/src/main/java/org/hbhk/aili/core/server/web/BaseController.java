package org.hbhk.aili.core.server.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.core.server.i18n.IMessageBundle;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @Description: aili框架核心处理和基本支持
 * @author 何波
 * @date 2015年3月11日 上午10:05:24
 * 
 */
public abstract class BaseController {

	@Autowired(required = false)
	private IMessageBundle messageBundle;
	
	
	public WebApplicationContext getWebApplicationContext() {
		return WebApplicationContextHolder.getWebApplicationContext();
	}

	public ResultEntity returnSuccess() {
		return new ResultEntity();
	}

	public ResultEntity returnSuccess(String msg, Object result) {
		ResultEntity response = new ResultEntity();
		response.setMsg(getI18nMsg(msg));
		response.setData(result);
		return response;
	}

	public ResultEntity returnSuccess(Object result) {
		ResultEntity response = new ResultEntity();
		response.setData(result);
		return response;
	}

	public ResultEntity returnSuccess(String msg) {
		ResultEntity response = new ResultEntity();
		response.setMsg(getI18nMsg(msg));
		return response;
	}

	public ResultEntity returnException() {
		ResultEntity response = new ResultEntity();
		response.setSuccess(false);
		return response;
	}

	public ResultEntity returnException(String msg) {
		ResultEntity response = new ResultEntity();
		response.setSuccess(false);
		response.setMsg(getI18nMsg(msg));
		return response;
	}
	
	public ResultEntity returnException(Object result) {
		ResultEntity response = new ResultEntity();
		response.setSuccess(false);
		response.setData(result);
		return response;
	}
	
	public ResultEntity returnException(String msg,Object result) {
		ResultEntity response = new ResultEntity();
		response.setSuccess(false);
		response.setData(result);
		response.setMsg(getI18nMsg(msg));
		return response;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	public String  getI18nMsg(String key , Object... args){
		if(messageBundle != null){
			String msg = messageBundle.getMessage(key, args);
			if(StringUtils.isEmpty(msg)){
				return key;
			}
			return msg;
		}
		return key;
	}

}
