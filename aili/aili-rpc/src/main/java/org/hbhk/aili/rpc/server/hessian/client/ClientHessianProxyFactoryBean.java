package org.hbhk.aili.rpc.server.hessian.client;

import java.net.MalformedURLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.caucho.hessian.client.HessianProxyFactory;

public class ClientHessianProxyFactoryBean extends HessianProxyFactoryBean {
	private final Log logger = LogFactory.getLog(this.getClass());
	@Override
	protected Object createHessianProxy(HessianProxyFactory proxyFactory)
			throws MalformedURLException {
		//设置当前用户
		logger.debug("设置当前用户");
		proxyFactory.setUser("hbhk");
		proxyFactory.setPassword("135246");
		return super.createHessianProxy(proxyFactory);
	}

	
}
