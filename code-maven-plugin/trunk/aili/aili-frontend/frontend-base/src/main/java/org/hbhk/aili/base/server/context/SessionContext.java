package org.hbhk.aili.base.server.context;

import java.util.Locale;

import org.hbhk.aili.base.shared.define.FrontendConstants;

public final class SessionContext {
    /**
     * 初始化session上下文
     */
    private static final ThreadLocal<ISession<Object>> SAFE_SESSION = new ThreadLocal<ISession<Object>>() {
        @Override
        protected ISession<Object> initialValue() {
            return new Session<Object>();
        }
        
    };
    
    private SessionContext() {
        
    }
    
    public static ISession<Object> getSession() {
        return SAFE_SESSION.get();
    }
    
    /**
     * 设置真是session
     */
    public static void setSession(javax.servlet.http.HttpSession session) {
        ISession<Object> sessionHold = SAFE_SESSION.get();
        sessionHold.init(session);   
    }
    
    /**
     * 清除ThreadLocal
     */
    public static void remove() {
    	SAFE_SESSION.get().init(null);
    }
    
    /**
     * 设置User
     */
    public static void setCurrentUser(String user){
    	SAFE_SESSION.get().setObject(FrontendConstants.USER_SESSION_KEY, user);
    }
    
    /**
     * 设置Locale
     */
    public static void setUserLocale(Locale userLocale){
    	SAFE_SESSION.get().setObject(FrontendConstants.USER_SESSION_KEY, userLocale);
    }
    
    /**
     * 使session失效
     */
    public static void invalidateSession() {
    	SAFE_SESSION.get().invalidate();
    }
    
}
