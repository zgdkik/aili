package org.hbhk.aili.esb.common.exception;

/**
 * 短信语音消息发送异常.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午4:44:25
 */
public class ESBSmsSendException extends Exception{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8478629905714776041L;
	
	/** 错误码. */
	private String errorCode;
	
	/**
	 * Instantiates a new eSB sms send exception.
	 * 
	 * @param errorCode
	 *            the error code
	 * @param msg
	 *            the msg
	 */
	public  ESBSmsSendException(String errorCode,String msg){
		super(msg);
		this.errorCode=errorCode;
	}
	
	/**
	 * Instantiates a new eSB sms send exception.
	 * 
	 * @param msg
	 *            the msg
	 */
	public  ESBSmsSendException(String msg){
		super(msg);
	}
	
	/**
	 * Instantiates a new eSB sms send exception.
	 * 
	 * @param msg
	 *            the msg
	 * @param ex
	 *            the ex
	 */
	public  ESBSmsSendException(String msg,Throwable ex){
		super(msg,ex);
	}
	
	/**
	 * Instantiates a new eSB sms send exception.
	 * 
	 * @param ex
	 *            the ex
	 */
	public  ESBSmsSendException(Throwable ex){
		super(ex);
	}
	
	/**
	 * Instantiates a new eSB sms send exception.
	 * 
	 * @param errorCode
	 *            the error code
	 * @param msg
	 *            the msg
	 * @param ex
	 *            the ex
	 */
	public  ESBSmsSendException(String errorCode,String msg,Throwable ex){
		super(msg,ex);
		this.errorCode=errorCode;
	}

	/**
	 * Gets the 错误码.
	 * 
	 * @return the 错误码
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the 错误码.
	 * 
	 * @param errorCode
	 *            the new 错误码
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
