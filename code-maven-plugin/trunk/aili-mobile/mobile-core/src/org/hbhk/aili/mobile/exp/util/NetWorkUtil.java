package org.hbhk.aili.mobile.exp.util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class NetWorkUtil {

	private static NetWorkUtil INSTANCE = new NetWorkUtil();

	private ExecutorService exec = Executors.newFixedThreadPool(5);

	private NetWorkUtil() {
	}

	public static NetWorkUtil newInstance() {
		return INSTANCE;
	}

	public String sendPostRequest(String url, List<NameValuePair> params)
			throws Exception {
		String result = "";
		if (url == null || url.equals("")) {
			throw new NullPointerException("url is null");
		}
		if (params == null || params.size() == 0) {
			throw new NullPointerException("params is null");
		}
		Future<String> fu = this.exec.submit(new DealSynPost(url, params));

		try {
			result = fu.get();
		} catch (InterruptedException e) {
			throw new InterruptedException();
		} catch (ExecutionException e) {
			throw new Exception(e);
		}

		return result;
	}

	public String sendGetRequest(String url) throws Exception {
		String result = "";
		if (url == null || url.equals("")) {
			throw new NullPointerException("url is null");
		}
		Future<String> fu = this.exec.submit(new DealSynGet(url));

		try {
			result = fu.get();
		} catch (InterruptedException e) {
			throw new InterruptedException();
		} catch (ExecutionException e) {
			throw new Exception(e);
		}

		return result;

	}

	static class DealSynPost implements Callable<String> {

		private String url;

		private List<NameValuePair> params;

		public DealSynPost(String url, List<NameValuePair> params) {
			this.url = url;
			this.params = params;
		}

		@Override
		public String call() throws Exception {
			StringBuffer result = new StringBuffer();
			// ����HttpPost����
			HttpPost httpPost = new HttpPost(url);
			HttpResponse httpResponse = null;
			try {
				// ����httpPost�������
				httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				httpResponse = new DefaultHttpClient().execute(httpPost);
				// System.out.println(httpResponse.getStatusLine().getStatusCode());
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					// ʹ��getEntity������÷��ؽ��
					// ��ʽʱ�����ظ���ȡ httpResponse.getEntity()
					String responseStr = EntityUtils.toString(httpResponse
							.getEntity());
					result.append(responseStr);
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result.toString();
		}

	}

	static class DealSynGet implements Callable<String> {

		private String url;

		public DealSynGet(String url) {
			this.url = url;
		}

		@Override
		public String call() throws Exception {
			StringBuffer result = new StringBuffer();
			// ����HttpGet����
			HttpGet httpGet = new HttpGet(url);
			// ʹ��execute��������HTTP GET���󣬲�����HttpResponse����
			HttpResponse httpResponse = null;
			try {
				httpResponse = new DefaultHttpClient().execute(httpGet);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				try {
					// ʹ��getEntity������÷��ؽ��
					// ��ʽʱ�����ظ���ȡ httpResponse.getEntity()
					result.append(EntityUtils.toString(httpResponse.getEntity()));
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return result.toString();
		}

	}

}
