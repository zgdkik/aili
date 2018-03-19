package org.hbhk.hms.gateway.common.exception;

public class AgentEsbHeaderException extends RuntimeException{
	private String type;
	private String code;
	private static final long serialVersionUID = 2532706331761811809L;

	public AgentEsbHeaderException(){
		
	}
	public AgentEsbHeaderException(String msg,String type,String code){
		super(msg);
		this.type=type;
		this.code=code;
	}
	
	public AgentEsbHeaderException(Throwable ex){
		super(ex);
	}
	
	public AgentEsbHeaderException(String msg,Throwable ex){
		super(msg,ex);
	}
	public String getType() {
		return type;
	}
	public String getCode() {
		return code;
	}
	
}
