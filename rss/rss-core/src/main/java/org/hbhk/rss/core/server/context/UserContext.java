package org.hbhk.rss.core.server.context;

import org.hbhk.rss.weixinapi.server.msg.Msg4Head;

public class UserContext {

	public static final String master = "hbhk520";

	private static ThreadLocal<UserContext> context = new ThreadLocal<UserContext>() {
		@Override
		protected UserContext initialValue() {
			return new UserContext();
		}
	};
	private Msg4Head msg4Head;

	private String currentUserName;

	public static void setCurrentUserName(String currentUserName) {
		UserContext userContext = getCurrentContext();
		userContext.currentUserName = currentUserName;
	}

	public static void setCurrentMsg4Head(Msg4Head msg4Head) {
		UserContext userContext = getCurrentContext();
		userContext.msg4Head = msg4Head;
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

	public Msg4Head getMsg4Head() {
		return msg4Head;
	}

	public void setMsg4Head(Msg4Head msg4Head) {
		this.msg4Head = msg4Head;
	}

}
