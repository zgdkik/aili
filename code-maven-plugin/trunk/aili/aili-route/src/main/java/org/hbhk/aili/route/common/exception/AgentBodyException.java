package org.hbhk.aili.route.common.exception;


public class AgentBodyException extends RuntimeException{
	private static final long serialVersionUID = 2532706331761811809L;

	public AgentBodyException(){
		
	}
	public AgentBodyException(String msg,String type,String code){
		super(msg);
	}
	
	public AgentBodyException(Throwable ex){
		super(ex);
	}
	
	public AgentBodyException(String msg,Throwable ex){
		super(msg,ex);
	}
	
}
