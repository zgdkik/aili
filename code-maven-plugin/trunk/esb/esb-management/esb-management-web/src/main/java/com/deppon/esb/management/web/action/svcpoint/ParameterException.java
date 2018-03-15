package com.deppon.esb.management.web.action.svcpoint;

public class ParameterException extends RuntimeException{

	private static final long serialVersionUID = 242104306622403923L;

   public ParameterException() {
	super();
   }

   public ParameterException(String message) {
	super(message);
   }


   public ParameterException(String message, Throwable cause) {
       super(message, cause);
   }


   public ParameterException(Throwable cause) {
       super(cause);
   }
}
