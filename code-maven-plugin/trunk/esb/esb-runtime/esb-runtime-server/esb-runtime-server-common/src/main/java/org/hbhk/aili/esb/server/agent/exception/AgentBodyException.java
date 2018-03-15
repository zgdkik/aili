package org.hbhk.aili.esb.server.agent.exception;

import org.hbhk.aili.esb.common.exception.ESBRunTimeException;

public class AgentBodyException extends ESBRunTimeException{
	private static final long serialVersionUID = 2532706331761811809L;

	public AgentBodyException(){
		
	}
	public AgentBodyException(String msg,String type,String code){
		super(msg);
		this.type=type;
		this.code=code;
	}
	
	public AgentBodyException(Throwable ex){
		super(ex);
	}
	
	public AgentBodyException(String msg,Throwable ex){
		super(msg,ex);
	}
	public String getType() {
		return type;
	}
	public String getCode() {
		return code;
	}
	
}
