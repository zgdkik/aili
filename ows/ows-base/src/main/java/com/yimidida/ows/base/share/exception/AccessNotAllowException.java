package com.yimidida.ows.base.share.exception;

import com.yimidida.ymdp.core.share.ex.BusinessException;

public class AccessNotAllowException extends BusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public AccessNotAllowException(String accessURL) {
		super("无权访问:"+accessURL);
	}

	public AccessNotAllowException() {
		super();
	}

}
