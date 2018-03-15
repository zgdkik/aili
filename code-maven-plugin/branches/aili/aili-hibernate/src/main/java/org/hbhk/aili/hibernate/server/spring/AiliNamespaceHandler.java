package org.hbhk.aili.hibernate.server.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class AiliNamespaceHandler extends NamespaceHandlerSupport {

	public void init() {
		registerBeanDefinitionParser("dao", new GenericDaoBeanDefinitionParser());
		registerBeanDefinitionParser("dao-config", new GenericDaoConfigBeanDefinitionParser());
	}

}
