package org.hbhk.aili.core.server.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hbhk.aili.core.server.service.IClusterTokenGenerator;
import org.hbhk.aili.core.share.consts.AppConst;
import org.hbhk.aili.core.share.entity.Token;
import org.hbhk.aili.core.share.util.BASE64Util;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultClusterTokenGenerator implements IClusterTokenGenerator {

	private Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public String encodeToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Token token = (Token) session.getAttribute(AppConst.AILI_SEESION_KEY);
		if(token == null){
			token  = new Token();
		}
		token.setSessionId(session.getId());
		token.setTimestamp(String.valueOf(System.currentTimeMillis()));
		String str = JsonUtil.toJson(token);
		log.debug("集群josn->token:"+str);
		return BASE64Util.encode(str);
	}

	@Override
	public Object decodeToken(String token) {
		String str = BASE64Util.decode(token);
		return JsonUtil.parseJson(str, Token.class);
	}

}
