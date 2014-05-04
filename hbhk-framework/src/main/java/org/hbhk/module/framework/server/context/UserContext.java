package org.hbhk.module.framework.server.context;

import org.hbhk.module.framework.shared.domain.security.UsersEntity;


public class UserContext {

    private static ThreadLocal<UserContext> context = new ThreadLocal<UserContext>(){
        @Override
        protected UserContext initialValue(){
            return new UserContext();
        }
    };
    private  String currentUserName ;
    
    private UsersEntity currentUser;
    
    public static void setCurrentUserName(String currentUserName) {
    	UserContext userContext = getCurrentContext();
    	userContext.currentUserName = currentUserName;
	}
    
	public UsersEntity getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(UsersEntity currentUser) {
		UserContext userContext = getCurrentContext();
		userContext.currentUser = currentUser;
	}

	public static UserContext getCurrentContext() {
		return context.get();
	}
	/**
	 * 清楚ThreadLocal
	 * remove
	 * @return void
	 * @since:
	 */
	public static void remove(){	
	    context.remove();
	}
	public String getCurrentUserName() {
		return currentUserName;
	}
	
	

}
