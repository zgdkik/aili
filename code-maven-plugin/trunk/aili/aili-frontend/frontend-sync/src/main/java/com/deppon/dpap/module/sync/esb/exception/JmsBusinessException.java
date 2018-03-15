package com.deppon.dpap.module.sync.esb.exception;

import com.deppon.foss.framework.exception.BusinessException;

public class JmsBusinessException extends BusinessException {

	private static final long serialVersionUID = 691797036656592039L;

	/**
	 * Instantiates a new dispatchException.
	 * 
	 * @param errCode the errCode
	 */
	public JmsBusinessException(String errCode) {
		super();
		super.errCode = errCode;
	}
	
	/**
	 * Instantiates a new dispatchException.
	 * 
	 * @param errCode the errCode
	 * @param msg the msg
	 */
	public JmsBusinessException(String errCode, Throwable cause){
		super(errCode, cause);
		super.errCode = errCode;
	}
}
