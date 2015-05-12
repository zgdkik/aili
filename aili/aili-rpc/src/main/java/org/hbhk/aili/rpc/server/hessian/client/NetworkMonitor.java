package org.hbhk.aili.rpc.server.hessian.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.impl.conn.DefaultClientConnectionOperator;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.hbhk.aili.rpc.server.hessian.transport.INetworkStatusListener;

public class NetworkMonitor {

	private URL monitorTarget;
	private volatile NetworkStatus status;
	private List<INetworkStatusListener> listeners = new ArrayList<INetworkStatusListener>();
	private static final int DEFAULT_MONITOR_INTERVAL = 180 * 1000;
	private static final int DEFAULT_MONOTOR_INTERVAL_OFFLINE = 10000; 
	private final Log logger = LogFactory.getLog(this.getClass());
	private int monitorInterval;
	private int monitorIntervalBak;
	private MonitorThread monitorThread;
	private boolean monitorThreadStarted;

	public NetworkMonitor(URL monitorTarget) {
		this.monitorTarget = computeDefaultTestURL(monitorTarget);
	}

	/**
	 * 
	 * @Classname:MonitorThread
	 * @Description:鍐呴儴绫伙紝鐩戞帶鍣ㄧ嚎绋嬶紝鐩戞帶缃戠粶鐘舵�
	 * @date 2011-6-11 涓婂崍11:20:07
	 * 
	 */
	private class MonitorThread extends Thread {
		private volatile boolean abort;

		private final byte[] lock = new byte[0];

		public MonitorThread() {
			super("NetWorkStatus-Monitor-Thread");
			this.setDaemon(true);
		}

		@Override
		public void run() {
			while (!this.abort) {
				final NetworkStatus newStatus = NetworkMonitor.this.tryGetStatus();
				if (newStatus != NetworkMonitor.this.status) {
					NetworkMonitor.this.status = newStatus;
					for (INetworkStatusListener listener : NetworkMonitor.this.listeners) {
						listener.onStatusChange(newStatus);
					}
				}

				if (NetworkStatus.ONLINE == newStatus) {
					synchronized (lock) {
						NetworkMonitor.this.monitorIntervalBak = NetworkMonitor.this.monitorInterval;
						NetworkMonitor.this.monitorInterval = NetworkMonitor.DEFAULT_MONITOR_INTERVAL;
					}
				}
				else {
					NetworkMonitor.this.monitorInterval = NetworkMonitor.DEFAULT_MONOTOR_INTERVAL_OFFLINE;
				}
				
				
				try {
					Thread.sleep(NetworkMonitor.this.monitorInterval);
				} catch (final InterruptedException e) {
				}
			}
		}
	}

	public NetworkStatus  getStatus() {
		if (null == this.status) {
			this.status = tryGetStatus();
		}
		return this.status;
	}

	
	public NetworkStatus  getRefreshStatus() {
		this.status = tryGetStatus(false);
		
		return this.status;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opple.framework.client.java.net.INetworkStatusListener#addStatusListener
	 * (com.opple.framework.client.java.net.NetworkStatusListener)
	 */

	public synchronized void addStatusListener(final INetworkStatusListener listener) {
		this.listeners.add(listener);
	}

	public NetworkStatus tryGetStatus(){
		return tryGetStatus(true);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opple.framework.client.java.net.INetworkStatusListener#getStatus()
	 */
	public NetworkStatus tryGetStatus(boolean needLog) {
		HttpConnection connection = null;
		try {
			final SchemeRegistry schemeRegistry = new SchemeRegistry();
			final SchemeSocketFactory sf = PlainSocketFactory.getSocketFactory();
			schemeRegistry.register(new Scheme("http", 80, sf));
			final HttpHost target = new HttpHost(monitorTarget.getHost(), monitorTarget.getPort(), monitorTarget.getProtocol());
			final ClientConnectionOperator scop = new DefaultClientConnectionOperator(schemeRegistry);
			final HttpRequest req = new BasicHttpRequest("HEAD", this.monitorTarget.toString(), HttpVersion.HTTP_1_1);
			req.addHeader("Host", target.getHostName());
			final HttpContext ctx = new BasicHttpContext();
			final OperatedClientConnection conn = scop.createConnection();
			final HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setUseExpectContinue(params, false);
			HttpConnectionParams.setSoTimeout(params, 5000);
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			scop.openConnection(conn, target, null, ctx, params);
			connection = conn;
			conn.sendRequestHeader(req);
			conn.flush();
			final HttpResponse hr = conn.receiveResponseHeader();
			final StatusLine sl = hr.getStatusLine();
			if (sl.getStatusCode() == 200) {
				return NetworkStatus.ONLINE;
			} else {
				return NetworkStatus.OFFLINE;
			}
		} catch (final Exception ex) {
			if(needLog){
				logger.error(ex.getMessage(), ex);
			}
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (final IOException e) {
				}
			}
		}
		return NetworkStatus.OFFLINE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.opple.framework.client.java.net.INetworkStatusListener#
	 * removeStatusListener
	 * (com.opple.framework.client.java.net.NetworkStatusListener)
	 */
	public synchronized void removeStatusListener(final INetworkStatusListener listener) {
		this.listeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opple.framework.client.java.net.INetworkStatusListener#start()
	 */
	public synchronized void start() {
		if (this.monitorInterval == NetworkMonitor.DEFAULT_MONITOR_INTERVAL) {
			this.monitorInterval = (0 == this.monitorIntervalBak) ? 5000 : this.monitorIntervalBak;
		}
		if (!this.monitorThreadStarted) {
			this.monitorThread = new MonitorThread();
			this.monitorThread.start();
			this.monitorThreadStarted = true;
		} else {
			this.monitorThread.interrupt();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opple.framework.client.java.net.INetworkStatusListener#stop()
	 */
	public synchronized void stop() {
		if (this.monitorThread != null) {
			this.monitorThread.abort = true;
			this.monitorThread.interrupt();
			this.monitorThreadStarted = false;
			this.monitorThread = null;

		}
	}

	URL computeDefaultTestURL(URL url) {
		String urlStr = url.getPath();
		String fixx = "monitor";
		try {
			return new URL(url.getProtocol(), url.getHost(), url.getPort(), "/" + fixx.concat("/index.html"));
		} catch (MalformedURLException e) {
		}
		return null;

	}
}
