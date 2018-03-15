package com.deppon.esb.management.common.exception;

/**
 * 一期代码，直接移植过来，用于标注一期的异常类型
 * @author HuangHua
 * @date 2012-12-27 上午9:05:23
 */
public class ExceptionType {
	/**
	 * 不可恢复异常；
	 */
	public static final Integer IRRECOVERABLE = Integer.valueOf(1);
	
	/**
	 * 可恢复异常；
	 */
	public static final Integer RECOVERABLE = Integer.valueOf(2);
}
