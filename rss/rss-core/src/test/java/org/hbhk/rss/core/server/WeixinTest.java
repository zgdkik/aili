package org.hbhk.rss.core.server;

import org.hbhk.aili.support.server.httpclient.HttpClientUtil;
import org.hbhk.aili.support.server.httpclient.ResponseContent;
import org.hbhk.aili.support.server.httpclient.exception.ClientException;
import org.junit.Test;

public class WeixinTest {

	@Test
	public void post() throws ClientException{
		
		//http://hbhkrss.jd-app.com /weixin/auth.htm?
		//http://localhost:8080/rss/weixin/auth.htm
		String url ="http://localhost:8080/rss/weixin/auth.htm";
		ResponseContent<String>  responseContent= HttpClientUtil.post(url).param("name","hbhk").send();
	}
}
