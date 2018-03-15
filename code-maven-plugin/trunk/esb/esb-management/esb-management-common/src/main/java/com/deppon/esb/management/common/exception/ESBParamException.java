package com.deppon.esb.management.common.exception;

/**
 * 
 * 参数校验异常
 * @author qiancheng
 * @date 2013-4-22 上午9:32:24
 */
public class ESBParamException extends RuntimeException{
	private static final long serialVersionUID = -5639397239139455693L;
	public ESBParamException(){}
	public ESBParamException(String msg){
		super(msg);
	}
	public ESBParamException(String msg,Throwable ex){
		super(msg,ex);
	}
	public ESBParamException(Throwable ex){
		super(ex);
	}
}
