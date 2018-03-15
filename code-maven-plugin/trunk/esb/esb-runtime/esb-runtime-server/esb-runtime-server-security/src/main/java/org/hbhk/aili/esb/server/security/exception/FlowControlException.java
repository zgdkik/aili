package org.hbhk.aili.esb.server.security.exception;

import org.hbhk.aili.esb.common.exception.ESBRunTimeException;

public class FlowControlException extends  ESBRunTimeException{
	private static final long serialVersionUID = -5130270740050412822L;

	public FlowControlException(){
		
	}
	
	public FlowControlException(String msg){
		super(msg);
	}
	
	public FlowControlException(String msg,String type,String code){
		super(msg,type,code);
	}
	
	public FlowControlException(Throwable ex){
		super(ex);
	}
	
	public FlowControlException(String msg,Throwable ex){
		super(msg,ex);
	}
	public String getType() {
		return type;
	}
	public String getCode() {
		return code;
	}
	
}
