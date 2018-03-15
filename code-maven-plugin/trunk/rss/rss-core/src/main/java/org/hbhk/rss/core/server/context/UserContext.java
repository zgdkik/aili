package org.hbhk.rss.core.server.context;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.rss.weixinapi.server.msg.Msg4Head;

public class UserContext {

	
	private static Log log = LogFactory.getLog(UserContext.class);
	private static ThreadLocal<UserContext> context = new ThreadLocal<UserContext>() {
		@Override
		protected UserContext initialValue() {
			return new UserContext();
		}
	};
	
	public  String master;
	
	private Msg4Head msg4Head;

	private String currentUserName;

	private ByteArrayOutputStream byteArrayOutputStream = null;

	public static void setMaster(String master) {
		UserContext userContext = getCurrentContext();
		userContext.master = master;
	}
	public static void setInputStream(InputStream inputStream) {
		UserContext userContext = getCurrentContext();
		if (inputStream == null) {
			return;
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buffer)) > -1) {
				byteArrayOutputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		userContext.byteArrayOutputStream = byteArrayOutputStream;
	}

	public InputStream getInputStream() {
		if (byteArrayOutputStream == null) {
			return null;
		}
		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}

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
	public String getMaster() {
		return master;
	}

}
