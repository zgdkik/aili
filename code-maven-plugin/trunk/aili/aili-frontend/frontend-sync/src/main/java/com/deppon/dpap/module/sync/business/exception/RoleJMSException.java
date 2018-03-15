package com.deppon.dpap.module.sync.business.exception;

import com.deppon.foss.framework.exception.BusinessException;

public class RoleJMSException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public static final String ROLE_ADD_EXIT = "新增的用户已经存在";
	public static final String ROLE_ADD_NOT_EXIT = "修改或删除的用户不存在";
	public static final String ROLE_EMPCODE_NOT_EXIT = "用户信息的员工编码不存在";
	public RoleJMSException(String errCode) {
		super();
		super.errCode = errCode;
	}

	public RoleJMSException(String code, String msg) {
		super(code, msg);
	}

	public RoleJMSException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
