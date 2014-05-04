package org.hbhk.module.framework.shared.exception;

import org.hbhk.module.framework.server.exception.GeneralException;


/**
 * Cache没找到异常
 * 
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:ningyu,date:2013-5-8 下午4:48:06,content:TODO </p>
 * @date 2013-5-8 下午4:48:06
 * @since
 * @version
 */
public class CacheNotFoundException extends GeneralException {
	
	private static final long serialVersionUID = -8573419783281346196L;

	public CacheNotFoundException(String msg) {
        super(msg);
    }
	
	public CacheNotFoundException(Throwable e) {
	    super(e);
	}
}
