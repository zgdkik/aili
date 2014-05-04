package org.hbhk.util.security;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Logger;
import org.apache.ws.security.WSPasswordCallback;

public class ServerPasswordCallback implements CallbackHandler {

	Logger log = Logger.getLogger(ServerPasswordCallback.class);

	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

		int usage = pc.getUsage();

		if (usage == WSPasswordCallback.USERNAME_TOKEN) {
			pc.setPassword("135246");

		} else if (usage == WSPasswordCallback.SIGNATURE) {
			pc.setPassword("135246");
		}

		String username = pc.getIdentifier();
		String pw = pc.getPassword();

		System.out.println("identifier:" + username);
		System.out.println("password:" + pw);

		if (username.equals("hbhk")) {
			// 验证通过
		} else {
			throw new SecurityException("验证失败");
		}

	}

}
