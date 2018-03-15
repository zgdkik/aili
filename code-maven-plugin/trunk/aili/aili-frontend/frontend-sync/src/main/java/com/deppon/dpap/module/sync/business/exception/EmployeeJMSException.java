package com.deppon.dpap.module.sync.business.exception;

import com.deppon.foss.framework.exception.BusinessException;


public class EmployeeJMSException extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	public static final String DATA_RULE_REASON_OBJECTISNOTNULL_ERROR = "新增的员工已经存在";
	public static final String DATA_RULE_REASON_OBJECTISNULL_ERROR = "修改或删除的员工不存在";
	
	public EmployeeJMSException(String errCode) {
		super();
		super.errCode = errCode;
	}

	public EmployeeJMSException(String code, String msg) {
		super(code, msg);
	}

	public EmployeeJMSException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

	
	

}
