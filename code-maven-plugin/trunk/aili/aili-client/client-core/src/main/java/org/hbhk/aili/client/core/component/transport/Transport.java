package org.hbhk.aili.client.core.component.transport;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.hbhk.aili.client.core.component.networkstatus.NetworkMonitor;
import org.hbhk.aili.client.core.component.remote.IRemoteInfo;

/**
 * 本地与远程服务的通讯传输管理组件 本组件完成以下功能 1. 负责管理与多个不同的远程主机的通讯 1. 负责保持本地与远程应用的会话 3.
 * 负责处理数据传输，并管理系统级别的异常 4. 负责根据配置处理数据传输过程中的加解密和解压缩
 * 
 */
public final class Transport implements ITransport, ITransportInfo {
	private boolean useEncypt;
	private boolean useZIP;
	private int connectionTimeout;
	private int waitTimeout;
	private int maxConnections;
	private String UUID;
	
	private NetworkMonitor netMonitor;
	private HttpClient httpClient;
	private HttpParams httpParams;
	private static ClientConnectionManager connMgr;
	private static Map<String, Transport> multilator = new HashMap<String, Transport>();
	private static ThreadLocal<Map<String, String>> sendHeader = new ThreadLocal<Map<String, String>>() {
		
		@Override
		protected Map<String, String> initialValue() {
			return new HashMap<String, String>();
		}
		
	};
	
	private Transport(IRemoteInfo configInfo) {
		this.useEncypt = configInfo.isUseEncypt();
		this.useZIP = configInfo.isUseZIP();
		this.UUID = configInfo.getName();
		this.connectionTimeout = configInfo.getConnectionTimeout();
		this.waitTimeout = configInfo.getWaitTimeout();
		this.maxConnections = configInfo.getMaxConnections();
		this.init();
		this.httpClient = this.createHttpClient();
		this.netMonitor = new NetworkMonitor(configInfo.getRemoteURL());
		this.netMonitor.start();
	}
	
	// 添加获取HTTPCLIENT 接口
	public HttpClient getHttpClient() {
		return this.httpClient;
	}
	
	// 添加获取HTTPCLIENT 接口
	@Override
	public HttpClient getTransportor() {
		return this.httpClient;
	}
	
	@Override
	public Map<String, String> getTransportProtocol() {
		return sendHeader.get();
	}
	/**
	 * 
	 * <p>Title:init
	 * <p>Description:传输组件初始化</p>
	 * @see org.hbhk.aili.client.core.component.transport.ITransport#init()
	 */
	@Override
	public void init() {
		this.httpParams = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(httpParams, this.waitTimeout);
		HttpConnectionParams.setConnectionTimeout(httpParams, this.connectionTimeout);
		HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
		HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
		if (null == connMgr) {
			final SchemeRegistry schemeRegistry = new SchemeRegistry();
			final SchemeSocketFactory sf = PlainSocketFactory.getSocketFactory();
			schemeRegistry.register(new Scheme("http", 80, sf));
			connMgr = new ThreadSafeClientConnManager(schemeRegistry);
			((ThreadSafeClientConnManager) connMgr).setMaxTotal(maxConnections);
		}
	}
	
	@Override
	public void destroy() {
		for (Map.Entry<String, Transport> entry : multilator.entrySet()) {
			Transport transport = entry.getValue();
			transport.netMonitor.stop();
		}
		connMgr.shutdown();
	}
	
	/**
	 * 
	 * @return
	 */
	private HttpClient createHttpClient() {
		HttpClient client = new DefaultHttpClient(connMgr, httpParams);
		((DefaultHttpClient) client).setHttpRequestRetryHandler(new FossDefaultHttpRequestRetryHandler());
		return client;
		
	}
	
	
	class FossDefaultHttpRequestRetryHandler extends DefaultHttpRequestRetryHandler{
		
		 public boolean retryRequest(
		            final IOException exception,
		            int executionCount,
		            final HttpContext context) {
		        if (exception == null) {
		            throw new IllegalArgumentException("Exception parameter may not be null");
		        }
		        if (context == null) {
		            throw new IllegalArgumentException("HTTP context may not be null");
		        }
		        if (executionCount > super.getRetryCount()) {
		            // Do not retry if over max retry count
		            return false;
		        }
		        if (exception instanceof InterruptedIOException) {
		            // Timeout
		            return false;
		        }
		        if (exception instanceof UnknownHostException) {
		            // Unknown host
		            return false;
		        }
		      
		        if (exception instanceof SSLException) {
		            // SSL handshake exception
		            return false;
		        }

		        if(exception instanceof java.net.SocketException){
		        	return true;
		        }
		        
		        if (exception instanceof ConnectException) {
		            // Connection refused
		            return false;
		        }
		        
		        HttpRequest request = (HttpRequest)
		            context.getAttribute(ExecutionContext.HTTP_REQUEST);
		        if (handleAsIdempotent(request)) {
		            // Retry if the request is considered idempotent
		            return true;
		        }

		        Boolean b = (Boolean)
		            context.getAttribute(ExecutionContext.HTTP_REQ_SENT);
		        boolean sent = (b != null && b.booleanValue());

		        if (!sent || isRequestSentRetryEnabled()) {
		            // Retry if the request has not been sent fully or
		            // if it's OK to retry methods that have been sent
		            return true;
		        }
		        // otherwise do not retry
		        return false;
		    }
		 
		 	private boolean handleAsIdempotent(final HttpRequest request) {
		        return !(request instanceof HttpEntityEnclosingRequest);
		    }

	
	}
	
	
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public static void setSendHeader(String key, String value) {
		Map<String, String> target = sendHeader.get();
		target.put(key, value);
	}
	
	/**
	 * 
	 * @param remote
	 * @return
	 */
	public static ITransport create(IRemoteInfo remote) {
		Transport transport = null;
		if (multilator.containsKey(remote.getName())) {
			return multilator.get(remote.getName());
		}
		transport = new Transport(remote);
		multilator.put(remote.getName(), transport);
		return transport;
	}
	
	@Override
	public boolean isUseZIP() {
		return this.useZIP;
	}
	
	@Override
	public void setUseZIP(boolean useZIP) {
		this.useZIP = useZIP;
	}
	
	@Override
	public boolean isUseEncypt() {
		return this.useEncypt;
	}
	
	@Override
	public void setUseEncypt(boolean useEncypt) {
		this.useEncypt = useEncypt;
	}
	
	@Override
	public String getUUID() {
		return this.UUID;
	}
	
	@Override
	public void setUUID(String name) {
		this.UUID = name;
	}
	
	@Override
	public int getConnectionTimeout() {
		return this.connectionTimeout;
	}
	
	@Override
	public void setConnectionTimeout(int idleTimeout) {
		this.connectionTimeout = idleTimeout;
	}
	
	@Override
	public int getWaitTimeout() {
		return this.waitTimeout;
	}
	
	@Override
	public void setWaitTimeout(int waitTimeout) {
		this.waitTimeout = waitTimeout;
	}
	
	@Override
	public int getMaxConnections() {
		return this.maxConnections;
	}
	
	@Override
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.framework.client.component.transport.ITransport#
	 * getNetworkMonitor()
	 */
	@Override
	public NetworkMonitor getNetworkMonitor() {
		return this.netMonitor;
	}
}
