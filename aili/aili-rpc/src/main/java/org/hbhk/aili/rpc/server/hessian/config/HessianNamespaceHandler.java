package org.hbhk.aili.rpc.server.hessian.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class HessianNamespaceHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("hessian-exporter", new HessianBeanDefinitionParser());	
	}

}
