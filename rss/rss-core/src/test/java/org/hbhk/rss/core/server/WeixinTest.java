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
		String xml = "<xml><ToUserName><![CDATA[hbhk520]]></ToUserName><FromUserName><![CDATA[1024784402]]></FromUserName> <CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
		ResponseContent<String>  responseContent= HttpClientUtil.post(url).param(xml).sendXml();
		System.out.println(responseContent.getResult());
	}
}
