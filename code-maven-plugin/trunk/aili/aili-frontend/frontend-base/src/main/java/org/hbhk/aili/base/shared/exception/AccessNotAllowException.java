package org.hbhk.aili.base.shared.exception;

import org.hbhk.aili.core.share.ex.BusinessException;

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
