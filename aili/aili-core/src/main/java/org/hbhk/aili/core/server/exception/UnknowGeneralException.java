package org.hbhk.aili.core.server.exception;

public final class UnknowGeneralException extends GeneralException {
    
    private static final long serialVersionUID = -7547624267762545457L;
    public static final String ERROR_CODE = "errror.common.unknow";
    
    public UnknowGeneralException(String msg) {
        super(msg);
        this.errCode = ERROR_CODE;
    }
    public UnknowGeneralException(String msg, Throwable t) {
        super(msg,t);
        this.errCode = ERROR_CODE;
        this.setStackTrace(t.getStackTrace());
    }
   
}
