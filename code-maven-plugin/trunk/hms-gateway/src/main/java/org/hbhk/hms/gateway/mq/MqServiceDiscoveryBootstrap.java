package org.hbhk.hms.gateway.mq;

import org.hbhk.hms.mq.rabbit.EventTemplate;
import org.hbhk.hms.mq.rabbit.IMessageAdapterHandler;
import org.hbhk.hms.mq.rabbit.MqControlConfig;
import org.hbhk.hms.mq.rabbit.impl.MqBootstrap;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MqServiceDiscoveryBootstrap  implements  InitializingBean , DisposableBean{

	private  ServiceDiscoveryController controller;

	
	private  MqControlConfig mqControlConfig;
	
	private  String registryAddress;
	
	private MqServiceDiscovery mqServiceDiscovery;

	@Override
	public void afterPropertiesSet() throws Exception {
		IMessageAdapterHandler  messageRouteAdapterHandler = new MessageRouteAdapterHandler();
		controller = ServiceDiscoveryController.getInstance(mqControlConfig,messageRouteAdapterHandler);
		MqBootstrap.eventTemplate = controller.getEopEventTemplate();
		mqServiceDiscovery = new MqServiceDiscovery(registryAddress,controller);
	}

	@Override
	public void destroy() throws Exception {
		controller.destroy();
		
	}

	public ServiceDiscoveryController getController() {
		return controller;
	}

	public void setController(ServiceDiscoveryController controller) {
		this.controller = controller;
	}

	public EventTemplate getEventTemplate() {
		return MqBootstrap.eventTemplate;
	}


	public void setEventTemplate(EventTemplate eventTemplate) {
		MqBootstrap.eventTemplate = eventTemplate;
	}


	public MqControlConfig getMqControlConfig() {
		return mqControlConfig;
	}


	public void setMqControlConfig(MqControlConfig mqControlConfig) {
		this.mqControlConfig = mqControlConfig;
	}

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public MqServiceDiscovery getMqServiceDiscovery() {
		return mqServiceDiscovery;
	}

	public void setMqServiceDiscovery(MqServiceDiscovery mqServiceDiscovery) {
		this.mqServiceDiscovery = mqServiceDiscovery;
	}

	
	
}
