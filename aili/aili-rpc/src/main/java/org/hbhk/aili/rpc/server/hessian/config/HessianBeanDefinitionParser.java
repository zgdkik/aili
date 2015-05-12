package org.hbhk.aili.rpc.server.hessian.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class HessianBeanDefinitionParser implements BeanDefinitionParser {
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		RootBeanDefinition bd = new RootBeanDefinition(HessianExporter.class);
		bd.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		bd.setSource(parserContext.extractSource(element));
		parserContext.registerBeanComponent(new BeanComponentDefinition(bd,
				HessianExporter.class.getName()));
		return null;
	}
}
