package org.hbhk.aili.client.core.component.exception;

public interface IException{
	  
    String getErrorCode();
    
    String getNativeMessage();
    
    void setErrorArguments(Object... objects);
    
    Object[] getErrorArguments();
}
