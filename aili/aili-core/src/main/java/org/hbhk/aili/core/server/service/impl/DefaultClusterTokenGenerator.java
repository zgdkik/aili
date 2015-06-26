package org.hbhk.aili.core.server.service.impl;

import org.hbhk.aili.core.server.service.IClusterTokenGenerator;
import org.hbhk.aili.core.share.entity.Token;
import org.hbhk.aili.core.share.util.BASE64Util;
import org.hbhk.aili.core.share.util.JsonUtil;

public class DefaultClusterTokenGenerator implements IClusterTokenGenerator {

	@Override
	public String encodeToken() {
		Token token = new Token();
		String str = JsonUtil.toJson(token);
		return BASE64Util.encode(str);
	}

	@Override
	public Object decodeToken(String token) {
		String str = BASE64Util.decode(token);
		return JsonUtil.parseJson(str, Token.class);
	}

}
