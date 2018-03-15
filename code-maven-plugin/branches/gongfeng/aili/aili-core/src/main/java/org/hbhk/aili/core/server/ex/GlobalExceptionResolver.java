package org.hbhk.aili.core.server.ex;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.web.WebApplicationContextHolder;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @Description: aili框架核心处理和基本支持
 * @author 何波
 * @date 2015年3月11日 上午10:05:24
 * 
 */
@ControllerAdvice
public class GlobalExceptionResolver {

	private Log log = LogFactory.getLog(getClass());

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResultEntity> handleCustomException(
			BusinessException ex) {
		log.error(ex.getMessage(), ex);
		ResultEntity entity = new ResultEntity();
		entity.setSuccess(true);
		String errCode = ex.getErrCode();
		if (errCode != null && errCode != "") {
			WebApplicationContext context = WebApplicationContextHolder
					.getWebApplicationContext();
			String msg = context.getMessage(errCode, null,
					LocaleContextHolder.getLocale());
			entity.setMsg(msg);
		} else {
			if(ex.getErrMsg()!=null){
				entity.setMsg(ex.getErrMsg());
			}else{
				entity.setMsg(ex.getMessage());
			}
		}
		ResponseEntity<ResultEntity> httpEntity = new ResponseEntity<ResultEntity>(
				entity, HttpStatus.OK);

		return httpEntity;

	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ResultEntity> handleAllException(Throwable ex) {
		log.error(ex.getMessage(), ex);
		ResultEntity entity = new ResultEntity();
		entity.setSuccess(false);
		String stackTrace  = ExceptionUtils.getStackTrace(ex);
		String msg = "";
		if(stackTrace !=null && stackTrace.indexOf(":")>-1){
			msg = stackTrace.substring(0, stackTrace.indexOf(":"));
		}
		entity.setMsg(msg);
		entity.setStackTrace(stackTrace);
		ResponseEntity<ResultEntity> httpEntity = new ResponseEntity<ResultEntity>(
				entity, HttpStatus.INTERNAL_SERVER_ERROR);
		return httpEntity;

	}
}
