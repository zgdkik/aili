package org.hbhk.aili.esb.server.foss4sms;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.exception.ESBSmsSendException;

/**
 * 短信发送服务.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午4:43:43
 */
public class SmsSendService {

	private static HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
	
	private static Logger LOG = Logger.getLogger(SmsSendService.class);
	
	/**
	 * Send.
	 * 
	 * @param user
	 *            the user
	 * @param passwd
	 *            the passwd
	 * @param jsonMsg
	 *            the json msg
	 * @param url
	 *            the url
	 * @return the string
	 * @throws ESBSmsSendException
	 *             the eSB sms send exception
	 */
	public String send(String user,String passwd,String jsonMsg,String url)throws ESBSmsSendException {
		
		HttpClient httpClient = new HttpClient();
		
		httpConnectionManager.getParams().setConnectionTimeout(30*1000);
		httpConnectionManager.getParams().setSoTimeout(60*1000);
		
		httpClient.setHttpConnectionManager(httpConnectionManager);
		
		PostMethod postMethod = new PostMethod(url);
		String encryption = MD5Encrypt.encrypt(user+passwd);
		postMethod.setRequestBody(new NameValuePair[]{new NameValuePair("data_digest", encryption), new NameValuePair("smsInfo", jsonMsg)});
		postMethod.getParams().setContentCharset("UTF-8");
		try {
			 LOG.debug(" start to send Info to SMS"+":"+jsonMsg);
			 LOG.debug("msg:"+jsonMsg);
			 httpClient.executeMethod(postMethod);
			 LOG.debug("send Info to SMS successfully ");
			 return  postMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			LOG.error(e.getMessage(), e);
			throw new ESBSmsSendException(e.getMessage(),e); 
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new ESBSmsSendException(e.getMessage(),e); 
		} finally {
			postMethod.releaseConnection();
		}
	}
	
}
