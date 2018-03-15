package org.hbhk.aili.route.http.context;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.component.servlet.ServletComponent;
import org.apache.camel.component.servlet.ServletEndpoint;
import org.apache.camel.http.common.HttpConsumer;
import org.apache.camel.http.common.ServletResolveConsumerStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicCamelHttpTransportServlet extends CamelHttpTransportServlet {

	private static final long serialVersionUID = -6278403822151007593L;
	private static Logger LOG = LoggerFactory.getLogger(DynamicCamelHttpTransportServlet.class);
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		initHttpConsumer(config);
	}

	@Override
	public ServletResolveConsumerStrategy getServletResolveConsumerStrategy() {
		return new DynHttpRestServletResolveConsumerStrategy();
	}

	@Override
	public void connect(HttpConsumer consumer) {
		super.connect(consumer);
	}

	@Override
	public void disconnect(HttpConsumer consumer) {
		super.disconnect(consumer);
	}

	@Override
	public Map<String, HttpConsumer> getConsumers() {
		return super.getConsumers();
	}

	public void initHttpConsumer(ServletConfig config) {
		Map<String, HttpConsumer> hcMap = this.getConsumers();
		HttpConsumer hcCom= null;
		if (hcMap != null) {
			Collection<HttpConsumer> hcs = hcMap.values();
			for (HttpConsumer hc : hcs) {
				hcCom = hc;
				break;
			}
		}
		try {
			String servletName = config.getServletName();
			URI httpURI = new URI("https://www.baidu.com");
			ServletEndpoint endpoint = new ServletEndpoint("servlet:///hbhk",(ServletComponent)hcCom.getEndpoint().getComponent(),
					httpURI);
			endpoint.setServletName(servletName);
			HttpConsumer consumer = new HttpConsumer(endpoint, hcCom.getProcessor());
			connect(consumer);
			LOG.info(""+this.getConsumers().keySet());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
