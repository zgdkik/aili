package com.yimidida.ows.base.server.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.yimidida.ymdp.core.server.web.BaseController;
import com.yimidida.ymdp.core.share.ex.BusinessException;

public class AbstractController extends BaseController {
	public final static String REDIRECT = "redirect:";
	
	private Log log = LogFactory.getLog(getClass());
	public String redirect(String url) {
		return REDIRECT + url;
	}

	public void validator(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error(bindingResult);
			List<ObjectError> objectErrors = bindingResult.getAllErrors();
			StringBuilder error = new StringBuilder();
			for (ObjectError oe : objectErrors) {
				error.append(oe.getDefaultMessage() + "\r");
			}
			throw new BusinessException(error.toString());
		}
	}
	public void validator(BindingResult bindingResult,String... fields) {
		if (bindingResult.hasErrors()) {
			log.error(bindingResult);
			List<FieldError> objectErrors = bindingResult.getFieldErrors();
			StringBuilder error = new StringBuilder();
			if(fields==null){
				fields = new String[]{};
			}
			List<String> fieldList = Arrays.asList(fields);
			for (FieldError oe : objectErrors) {
				String name = oe.getField();
				if(!fieldList.contains(name)){
					error.append(oe.getDefaultMessage() + "\r");
				}
			}
			if(StringUtils.isNotBlank(error.toString())){
				throw new BusinessException(error.toString());
			}
		}
	}

}
