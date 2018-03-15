package com.deppon.dpap.module.sync.business.exception;

import com.deppon.foss.framework.exception.BusinessException;

public class DeptJMSException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public static final String USER_ADD_EXIT = "新增的用户已经存在";
	public static final String USER_ADD_NOT_EXIT = "修改或删除的用户不存在";
	public static final String USER_EMPCODE_NOT_EXIT = "用户信息的员工编码不存在";
	public DeptJMSException(String errCode) {
		super();
		super.errCode = errCode;
	}

	public DeptJMSException(String code, String msg) {
		super(code, msg);
	}

	public DeptJMSException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
