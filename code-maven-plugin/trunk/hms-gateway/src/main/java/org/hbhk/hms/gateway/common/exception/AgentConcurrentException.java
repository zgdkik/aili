package org.hbhk.hms.gateway.common.exception;

public class AgentConcurrentException extends RuntimeException {
	private String type;
	private String code;
	private static final long serialVersionUID = 2532706331761811809L;

	public AgentConcurrentException(){
		
	}
	public AgentConcurrentException(String msg,String type,String code){
		super(msg);
		this.type=type;
		this.code=code;
	}
	
	public AgentConcurrentException(Throwable ex){
		super(ex);
	}
	
	public AgentConcurrentException(String msg,Throwable ex){
		super(msg,ex);
	}
	public String getType() {
		return type;
	}
	public String getCode() {
		return code;
	}
}
