package org.hbhk.aili.core.server.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hbhk.aili.core.server.service.IClusterTokenGenerator;
import org.hbhk.aili.core.share.entity.Token;
import org.hbhk.aili.core.share.util.BASE64Util;
import org.hbhk.aili.core.share.util.DesUtil;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultClusterTokenGenerator implements IClusterTokenGenerator {

	private Logger log = LoggerFactory.getLogger(getClass());

	private String seckey = "aili";
	private DesUtil desUtil;

	@Override
	public String encodeToken(HttpServletRequest request, Token token) {
		HttpSession session = request.getSession();
		if (token == null) {
			token = new Token();
		}
		token.setSessionId(session.getId());
		token.setTimestamp(String.valueOf(System.currentTimeMillis()));
		String str = JsonUtil.toJson(token);
		log.debug("集群josn->token:" + str);
		if (desUtil == null) {
			desUtil = new DesUtil(seckey);
		}
		str = desUtil.encypt(str);
		str = BASE64Util.encode(str);
		return str;
	}

	@Override
	public Token decodeToken(String token) {
		token = BASE64Util.decode(token);
		if (desUtil == null) {
			desUtil = new DesUtil(seckey);
		}
		String str = desUtil.decypt(token);
		return JsonUtil.parseJson(str, Token.class);
	}

	public String getSeckey() {
		return seckey;
	}

	public void setSeckey(String seckey) {
		this.seckey = seckey;
	}

}
