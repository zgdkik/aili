package org.hbhk.aili.support.server.httpclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.hbhk.aili.support.server.httpclient.exception.ClientException;

public class HttpClientUtil extends Client {

	public HttpClientUtil(CloseableHttpClient client, String url) {
		super(client);
		this.request = new HttpPost(url);
		this.params = new HashMap<String, String>();
	}

	@Override
	protected void addParams() throws ClientException {
		if (this.params.size() > 0) {
			HttpPost post = (HttpPost) this.request;
			List<NameValuePair> nvps = new ArrayList<NameValuePair>(this.params.size());
			for (Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		}
	}

}
