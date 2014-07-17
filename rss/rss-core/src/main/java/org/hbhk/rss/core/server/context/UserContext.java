package org.hbhk.rss.core.server.context;

public class UserContext {
	
	public static final String master="hbhk520";

	private static ThreadLocal<UserContext> context = new ThreadLocal<UserContext>() {
		@Override
		protected UserContext initialValue() {
			return new UserContext();
		}
	};
	private String currentUserName;

	public static void setCurrentUserName(String currentUserName) {
		UserContext userContext = getCurrentContext();
		userContext.currentUserName = currentUserName;
	}

	public static UserContext getCurrentContext() {
		return context.get();
	}

	public static void remove() {
		context.remove();
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

}
