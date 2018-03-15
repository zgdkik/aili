package com.yimidida.ymdp.dao.server.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MybatisNamespaceHandler extends NamespaceHandlerSupport {

	public void init() {
		registerBeanDefinitionParser("mybatis-config", new MybatisConfigBeanDefinitionParser());
	}

}
