package org.hbhk.aili.support.server;

import org.hbhk.aili.support.server.httpclient.HttpClientUtil;
import org.hbhk.aili.support.server.httpclient.exception.ClientException;


public class AppTest{
	public static void main(String[] args) throws ClientException {
		HttpClientUtil.post("").send();;
	}
}