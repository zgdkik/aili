[#ftl]
package com.deppon.${project}.module.${module}.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;
/**
 * ${table.name}
 * 
 * @author DPAP ${.now}
 * 
 */
public class ${table.typeName}Exception extends BusinessException{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 异常的构造方法
	 * 
	 * @param errCode
	 * @since
	 */
	public ${table.typeName}Exception(String errCode) {
		super();
		super.errCode = errCode;
	}

	/**
	 * 异常的构造方法
	 * 
	 * @param errCode
	 * @since
	 */
	public ${table.typeName}Exception(String code, String msg) {
		super(code, msg);
	}

	/**
	 * 异常的构造方法
	 * 
	 * @param errCode
	 * @since
	 */
	public ${table.typeName}Exception(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
	
}