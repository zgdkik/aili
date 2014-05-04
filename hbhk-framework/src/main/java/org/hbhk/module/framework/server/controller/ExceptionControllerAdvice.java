package org.hbhk.module.framework.server.controller;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.module.framework.server.exception.BusinessException;
import org.hbhk.module.framework.server.exception.GeneralException;
import org.hbhk.module.framework.server.exception.IExceptionConvert;
import org.hbhk.module.framework.shared.util.WebErrorUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice implements InitializingBean {

	public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	
	private static final Log LOG = LogFactory.getLog(ExceptionControllerAdvice.class);
	
	private static final String CONTENT_TYPE = "Content-Type";
	
//	@Resource
//	protected IMessageBundle messageBundle;
	@Resource
	private IExceptionConvert exceptionConvert;
	
	private String jsperrorPage = "framework/error/error";
	
	private List<MediaType> supportedMediaTypes = Collections.emptyList();

	public void setJsperrorPage(String jsperrorPage) {
		this.jsperrorPage = jsperrorPage;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		supportedMediaTypes = Arrays.asList(
				new MediaType[] {
					new MediaType("application", "json", UTF8_CHARSET),
					new MediaType("application", "*+json", UTF8_CHARSET)
				});
	}
	
	/**
	 * 捕获业务异常
	 * @author 李光辉
	 * @date 2013年7月31日 下午4:53:22
	 * @param ex
	 * @param locale
	 * @param request
	 * @return 将异常信息放入响应消息体
	 * @since 2.0
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		HttpHeaders headers = new HttpHeaders();
		MediaType contentType = new MediaType("application", "json", Charset.forName("UTF-8"));
		headers.setContentType(contentType);
		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("success", false);
		map.put("isException", false);
		//map.put("message", messageBundle.getMessage(ex.getErrorCode()));
		map.put("message","msg info");
		return new ResponseEntity<Object>(map, headers, HttpStatus.OK);
	}
	
//	@ExceptionHandler(UserNotLoginException.class)
//	public Object handleUserNotLoginException(UserNotLoginException ex, Locale locale, HttpServletRequest request) {
//		if (LOG.isDebugEnabled()) {
//			LOG.debug(ex.getMessage(), ex);
//		}
//		
//		return this.handleGeneralException(ex, locale, request, "redirect:/login/index.action");
//	}
	
	/**
	 * 捕获认证不通过的异常，并将异常堆栈等信息反馈回客户端
	 * @author 李光辉
	 * @date 2013年7月31日 下午4:53:22
	 * @param ex
	 * @param locale
	 * @param request
	 * @return 根据请求的Content-type决定返回到错误页面，还是将异常信息放入响应消息体
	 * @since 2.0
	 */
//	@ExceptionHandler(AccessNotAllowException.class)
//	public Object handleAccessNotAllowException(AccessNotAllowException ex, Locale locale, HttpServletRequest request) {
//		if (LOG.isDebugEnabled()) {
//			LOG.debug(ex.getMessage(), ex);
//		}
//		
//		return handleGeneralException(ex, locale, request);
//	}
//	
	/**
	 * 捕获所有的未知异常，并将异常堆栈等信息反馈回客户端
	 * @author 李光辉
	 * @date 2013年7月31日 下午4:53:22
	 * @param ex
	 * @param locale
	 * @param request
	 * @return 根据请求的Content-type决定返回到错误页面，还是将异常信息放入响应消息体
	 * @since 2.0
	 */
	@ExceptionHandler(Throwable.class)
	public Object handleUnknowExceptions(Throwable ex, Locale locale, HttpServletRequest request) {
		if (LOG.isErrorEnabled()) {
			LOG.error(ex.getMessage(), ex);
		}
		
		GeneralException iex = exceptionConvert.convert(ex);
		return handleGeneralException(iex, locale, request);
	}

	/**
	 * 判断此请求是否是json请求
	 * @author 李光辉
	 * @date 2013年7月31日 下午4:08:34
	 * @param mediaType
	 * @return true是JSON请求
	 * @since 2.0
	 */
	private boolean isJsonContentType(MediaType mediaType) {
		if (mediaType == null) {
			return false;
		}
		
		for (MediaType supportedMediaType :supportedMediaTypes) {
			if (supportedMediaType.includes(mediaType)) {
				return true;
			}
		}
		
		return false;
	}
	
	private Object handleGeneralException(GeneralException iex, Locale locale, 
			HttpServletRequest request) {
		return this.handleGeneralException(iex, locale, request, jsperrorPage);
	}
	
	private Object handleGeneralException(GeneralException iex, Locale locale, 
			HttpServletRequest request, String viewName) {
		String stackTrace = getExceptionStackTrace(iex);
		request.setAttribute(WebErrorUtils.STACKTRACE, stackTrace);
		
		if (isJsonContentType(this.getClientContentType(request))) {
			return new ResponseEntity<Map<String, Object>>(
							getJsonResultErrorMessage(locale, iex, stackTrace, request), HttpStatus.OK);
		}
		
		setJspResultErrorMessage(locale, iex, request);
		return viewName;
	}
	
	private String getErrorCode(GeneralException iex) {
		return iex.getErrorCode() != null ? iex.getErrorCode() : "framework.server.exception.general";
	}
	
	private String getExceptionStackTrace(Throwable e) {
		return WebErrorUtils.getStackTrace(e);
	}
	
	/*
	 * 设置错误信息
	 */
	private Map<String, Object> getJsonResultErrorMessage(
			Locale locale, GeneralException iex, String stackTrace, HttpServletRequest request) {
		String errorCode = getErrorCode(iex);
		//String errMsg = messageBundle.getMessage(locale, errorCode, iex.getErrorArguments());
		String errMsg = "errMsg";
		
		Map<String, Object> errorMap = new HashMap<String, Object>(5);
		errorMap.put("success", false);
		errorMap.put("isException", true);
		errorMap.put("message", errMsg);
		errorMap.put("errorCode", errorCode);
		errorMap.put("stackTrace", stackTrace);
		return errorMap;
	}
	
	/** 
	 * 设置错误信息，以页面转向的形式，把页面返回到之前的页面，
	 * 并把之前的页面路径（referer，ERROR）放到request中
	 */
	private void setJspResultErrorMessage(Locale locale, GeneralException iex, HttpServletRequest request){
		String errorCode = getErrorCode(iex);
		String errMsg = "errMsg";//messageBundle.getMessage(locale, errorCode, iex.getErrorArguments());
		request.setAttribute(WebErrorUtils.ERROR, errMsg);
		request.setAttribute("errorCode", errorCode);
		
//		request.setAttribute("errorPage", 
//				messageBundle.getMessage(locale, "errror.JSPErrorResult.errorPage"));
//		request.setAttribute("errorMessage", 
//				messageBundle.getMessage(locale, "errror.JSPErrorResult.errorMessage"));
//		request.setAttribute("errorInfo", 
//				messageBundle.getMessage(locale, "errror.JSPErrorResult.errorInfo"));
		
		
		request.setAttribute("errorPage", 
				"errorPage");
		request.setAttribute("errorMessage", 
				"errorMessage");
		request.setAttribute("errorInfo", 
				"errorInfo");
		
		request.setAttribute("referer", request.getHeader("Referer"));
	}
	
	/**
	 * 获取请求头的Content-Type
	 * @author 李光辉
	 * @date 2013年7月31日 下午4:09:50
	 * @param request
	 * @return 没有Content-Type将返回null
	 * @since
	 */
	private MediaType getClientContentType(HttpServletRequest request) {
		String clientContentType = request.getHeader(CONTENT_TYPE);
		if (StringUtils.isBlank(clientContentType)) {
			return null;
		}
		
		try {
			return MediaType.parseMediaType(clientContentType);
		} catch (InvalidMediaTypeException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
		
}
