package org.hbhk.aili.esb.server.security.exception;

import org.hbhk.aili.esb.common.exception.ESBRunTimeException;

public class AuthorizeException extends  ESBRunTimeException{
	private static final long serialVersionUID = -5130270740050412822L;

	public AuthorizeException(){
		
	}
	public AuthorizeException(String msg){
		super(msg);
	}
	
	public AuthorizeException(String msg,String type,String code){
		super(msg,type,code);
	}
	
	public AuthorizeException(Throwable ex){
		super(ex);
	}
	
	public AuthorizeException(String msg,Throwable ex){
		super(msg,ex);
	}

}
