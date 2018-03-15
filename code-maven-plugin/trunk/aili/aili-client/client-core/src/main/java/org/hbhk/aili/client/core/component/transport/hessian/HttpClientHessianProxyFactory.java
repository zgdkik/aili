package org.hbhk.aili.client.core.component.transport.hessian;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.hbhk.aili.client.core.component.networkstatus.NetworkStatus;
import org.hbhk.aili.client.core.component.remote.BusinessException;
import org.hbhk.aili.client.core.component.transport.Transport;
import org.hbhk.aili.client.core.component.transport.exception.NetworkNotAllowException;
import org.hbhk.aili.client.core.component.transport.exception.RemoteServiceNotFoundException;
import org.hbhk.aili.client.core.core.context.SessionContext;

import com.caucho.hessian.client.AbstractHessianConnection;
import com.caucho.hessian.client.AbstractHessianConnectionFactory;
import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianConnectionFactory;
import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.io.HessianRemoteObject;

/**
 * <b style="font-family:微软雅黑"><small>Description:客户端hessian代理工厂</small></b>   </br>
 */
public class HttpClientHessianProxyFactory extends HessianProxyFactory {
	private static final Log log = LogFactory.getLog(HttpClientHessianProxyFactory.class);
	private Transport transport;

	/**
	 * 
	 * <p>Title: HttpClientHessianProxyFactory</p>
	 * <p>Description: 构造函数</p>
	 * @param transport 传输对象
	 */
	public HttpClientHessianProxyFactory(Transport transport) {
		this.transport = transport;
		System.setProperty(HessianConnectionFactory.class.getName(),
				ThreadLocalHesssianConnectionFactory.class.getName());
	}
	
	/**
	*******************************************
	* <b style="font-family:微软雅黑"><small>Description:客户端hessian连接类</small></b>   </br>
	* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
	* <b style="font-family:微软雅黑"><small>
	*  ID      DATE    PERSON     REASON</small></b><br>
	********************************************
	* <div style="font-family:微软雅黑,font-size:70%"> 
	* 1   2011-6-11   谢传国       新增
	* </div>  
	********************************************
	 */
	public static class HttpClientHessianConnection extends
			AbstractHessianConnection {
		private HttpResponse response;
		private HttpPost post;

		public void addHeader(String key, String value) {
			this.post.addHeader(key, value);
		}

		public void setResponse(HttpResponse response) {
			this.response = response;
		}

		private OutputStream out;

		public void setOutputStream(OutputStream out) {
			this.out = out;
		}

		public HttpClientHessianConnection(HttpPost post) {
			this.post = post;
		}

		@Override
		public OutputStream getOutputStream() throws IOException {
			return out;
		}

		@Override
		public void sendRequest() throws IOException {
			out.flush();
		}

		@Override
		public int getStatusCode() {
			return response.getStatusLine().getStatusCode();
		}

		@Override
		public String getStatusMessage() {
			return response.getStatusLine().getReasonPhrase();
		}

		@Override
		public InputStream getInputStream() throws IOException {
			if (200 != this.getStatusCode()) {

				String error = parseServiceException();
				
				if(404== this.getStatusCode()){
					throw new BusinessException("userNotLogin");
				}else{
					throw new RuntimeException(error);
				}
				
			}
			return response.getEntity().getContent();
		}

		/**
		 * 
		 * <p>Title: parseServiceException</p>
		 * <p>Description: 解析服务异常</p>
		 * @return
		 * @throws IOException
		 */
		String parseServiceException() throws IOException {
			byte[] abyte = new byte[512000];
			int len = 0;
			
			InputStream in = null;
			try {
				in = response.getEntity().getContent();
				len = in.read(abyte);
			} finally {
				in.close();
			}
			if(len<0){
				len=0;
			}
			return new String(abyte, 0, len);
		}

		@Override
		public void destroy() throws IOException {
			if (response != null) {
				getInputStream().close();
			} else {
				post.abort();
			}
		}

	}
	/**
	*******************************************
	* <b style="font-family:微软雅黑"><small>Description:hessian连接工厂类，把连接缓存在本地缓存中</small></b>   </br>
	* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
	* <b style="font-family:微软雅黑"><small>
	*  ID      DATE    PERSON     REASON</small></b><br>
	********************************************
	* <div style="font-family:微软雅黑,font-size:70%"> 
	* 1   2011-6-11   谢传国       新增
	* </div>  
	********************************************
	 */
	public static class ThreadLocalHesssianConnectionFactory extends
			AbstractHessianConnectionFactory {

		ThreadLocal<HttpClientHessianConnection> pool = new ThreadLocal<HttpClientHessianProxyFactory.HttpClientHessianConnection>();

		@Override
		public HessianConnection open(URL url) throws IOException {
			return pool.get();
		}

		/**
		 * 
		 * <p>Title: setConnection</p>
		 * <p>Description: 设置连接</p>
		 * @param conn
		 */
		public void setConnection(HttpClientHessianConnection conn) {
			pool.set(conn);
		}
	}

	@Override
	public Object create(Class<?> api, URL url, ClassLoader loader) {
		if (api == null){
			throw new NullPointerException(
					"clazz does not allow null");
		}
			
		InvocationHandler handler = null;

		handler = new HttpClientHessianProxy(url, this, api);

		return Proxy.newProxyInstance(loader, new Class[] { api,
				HessianRemoteObject.class }, handler);
	}
	
	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	@SuppressWarnings("serial")
	public class HttpClientHessianProxy extends HessianProxy {
		protected HttpClientHessianProxy(URL url, HessianProxyFactory factory,
				Class<?> type) {
			super(url, factory, type);
		}

		@Override
		protected HessianConnection sendRequest(final String methodName,
				final Object[] args) throws IOException {
			URI url = null;
			try {
				url = this.getURL().toURI();
			} catch (URISyntaxException e) {
				throw new IOException(e);
			}
			if (transport.getNetworkMonitor().getStatus() != NetworkStatus.ONLINE){
				throw new NetworkNotAllowException();
			}
				

			HttpPost post = new HttpPost(url);
		
			final HttpClientHessianConnection conn = new HttpClientHessianConnection(
					post);
			ThreadLocalHesssianConnectionFactory connFactory = (ThreadLocalHesssianConnectionFactory) this._factory
					.getConnectionFactory();
			connFactory.setConnection(conn);

			ContentProducer content = new ContentProducer() {

				@Override
				public void writeTo(OutputStream outstream) throws IOException {
					conn.setOutputStream(new BufferedOutputStream(outstream,
							1024));
					
					
					sendRequest2(methodName, args);
				}

			};
			/**
			 * 放入头部协议信息
			 */
			for (Map.Entry<String, String> entry : transport
					.getTransportProtocol().entrySet()) {
				post.setHeader(entry.getKey(), entry.getValue());
				
			}
			String userId = (String)SessionContext.get("FRAMEWORK__KEY_USER__");
			post.setHeader("FRAMEWORK__KEY_USER__",userId);
			
			String empCode = (String)SessionContext.get("FRAMEWORK_KEY_EMPCODE");
			post.setHeader("FRAMEWORK_KEY_EMPCODE",empCode);
						
			String deptCode = (String)SessionContext.get("FOSS_KEY_CURRENT_DEPTCODE");
			post.setHeader("FOSS_KEY_CURRENT_DEPTCODE", deptCode);
			
			String token = (String)SessionContext.get("_TOKEN");
			post.setHeader("_TOKEN", token);
			
			String tokenUuid = (String)SessionContext.get("_TOKEN_UUID");
			post.setHeader("_TOKEN_UUID", tokenUuid);
			
			
			
			
			post.setHeader("CLIENT", "gui");
						
			post.setEntity(new EntityTemplate(content));
			try {
				HttpResponse response = transport.getTransportor().execute(post);
				
				
				Header[] headers = response.getHeaders("set-cookie");
				if (SessionContext.get("JSESSIONID")==null) {
					for (Header header : headers) {
						if (SessionContext.get("JSESSIONID")!=null) {
							break;
						}
						HeaderElement[] elements = header.getElements();
						for (HeaderElement headerElement : elements) {
							String headerName = headerElement.getName();
							if ("JSESSIONID".equals(headerName)) {
								SessionContext.set("JSESSIONID", headerElement.getValue());
								break;
							}
						}
					}
				}
				
				conn.setResponse(response);
				
			} catch (IOException e) {
				log.error("ioException", e);
				throw new RemoteServiceNotFoundException(e);
			}
			connFactory.setConnection(null);
			return conn;
		}

		/**
		 * 
		 * <p>Title: sendRequest2</p>
		 * <p>Description: 发送请求</p>
		 * @param methodName
		 * @param args
		 * @throws IOException
		 */
		protected void sendRequest2(String methodName, Object[] args)
				throws IOException {
			super.sendRequest(methodName, args);
		}
		
		
	}
}