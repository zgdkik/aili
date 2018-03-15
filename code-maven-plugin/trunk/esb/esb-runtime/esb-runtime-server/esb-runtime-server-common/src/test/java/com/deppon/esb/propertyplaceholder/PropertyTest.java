package com.deppon.esb.propertyplaceholder;

import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;



/**
 * 使用自定义propertiesResolver:
 *   创建自定义propertiesResolver的bean 。
 *   在spring中配置org.apache.camel.component.properties.PropertiesComponent,
 *   id必须为"properties",如果id写错，将会报错,并且设置propertiesResolver属性 :
 * 		<property name="propertiesResolver">
 *			<bean class="com.deppon.esb.propertyplaceholder.ESBPropertiesResolver"></bean>
 *		</property>
 * 	
 *   注：1、所有的camelContext都使用这个PropertiesComponent。
 * 	    2、不能再camelContext中添加propertyPlaceholder tag，否则报错。
 * 	    3、camel 的占位符“{{}}”只在camelContext内才生效。
 * @author qiancheng
 *
 */
@ContextConfiguration({
	"classpath:com/deppon/esb/propertyplaceholder/propertyplaceholder-camel.xml"
	})
@Ignore
public class PropertyTest extends AbstractJUnit4SpringContextTests{
	
	/**
	 * Test.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test()throws Exception{
		CamelContext camelContext = (CamelContext)applicationContext.getBean("context1");
		Endpoint a =camelContext.getEndpoint("direct:start");
		ProducerTemplate pro = camelContext.createProducerTemplate();
		pro.sendBody(a, "test");
	
		CamelContext camelContext2 = (CamelContext)applicationContext.getBean("context2");
		Endpoint start2 =camelContext2.getEndpoint("direct:start2");
		ProducerTemplate p2 = camelContext2.createProducerTemplate();
		p2.sendBody(start2,"test");	
	}

	/**
	 * Test2.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test2()throws Exception{
		CamelContext camelContext2 = (CamelContext)applicationContext.getBean("context2");
		ProducerTemplate template = camelContext2.createProducerTemplate();
		Endpoint endpoint =camelContext2.getEndpoint("direct:start3");
		template.sendBody(endpoint,"test");
	}
	
	/**
	 * Test3.
	 */
	@Test
	public void test3(){
		Properties p =  new Properties();
		p.setProperty("123", null);
	}
}
