package org.hbhk.aili.esb.server.common.exception;

import org.hbhk.aili.esb.common.exception.ESBRunTimeException;
/**
 * 
 * 路由找不到目标endpoint 异常
 * @author qiancheng
 * @date 2013-4-20 下午3:51:25
 */
public class ESBDestinationNotFoundException extends ESBRunTimeException{
	private static final long serialVersionUID = -3785862456861337602L;
	public ESBDestinationNotFoundException(){}
	public ESBDestinationNotFoundException(String msg){
		super(msg);
	}
	public ESBDestinationNotFoundException(String msg,Throwable ex){
		super(msg,ex);
	}
	public ESBDestinationNotFoundException(Throwable ex){
		super(ex);
	}
}
