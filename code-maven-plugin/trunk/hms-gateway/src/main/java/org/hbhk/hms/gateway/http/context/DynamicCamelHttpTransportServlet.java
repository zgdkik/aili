package org.hbhk.hms.gateway.http.context;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.http.common.HttpConsumer;
import org.apache.camel.http.common.ServletResolveConsumerStrategy;

public class DynamicCamelHttpTransportServlet extends CamelHttpTransportServlet {

	private static final long serialVersionUID = -6278403822151007593L;
	public static  DynamicCamelHttpTransportServlet dchts;
	
	public static ServletConfig config ;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DynamicCamelHttpTransportServlet.dchts = this;
		DynamicCamelHttpTransportServlet.config = config;
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

	
}
