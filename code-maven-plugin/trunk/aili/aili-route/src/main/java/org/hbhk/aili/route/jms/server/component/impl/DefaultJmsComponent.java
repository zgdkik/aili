package org.hbhk.aili.route.jms.server.component.impl;

import javax.jms.ConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;
import org.hbhk.aili.route.jms.server.component.IComponent;
import org.hbhk.aili.route.jms.server.listener.JmsRoutelistener;
import org.hbhk.aili.route.jms.share.ComponentInfo;

public class DefaultJmsComponent  implements IComponent{

	@Override
	public ComponentInfo getComponent() {
		ConnectionFactory connectionFactory = JmsRoutelistener.springContext.getBean("jmsConnectionFactory",ConnectionFactory.class);
		JmsComponent component = JmsComponent.jmsComponentAutoAcknowledge(connectionFactory);
		ComponentInfo  componentInfo = new ComponentInfo();
		componentInfo.setComponent(component);
		componentInfo.setName("jms");
		return componentInfo;
	}

}
