package com.deppon.esb.springplaceholder;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


/**
 * The Class SpringPlaceHolderTest.
 */
@ContextConfiguration({
	"classpath:com/deppon/esb/propertyplaceholder/propertyplaceholder-spring.xml"
	})
//@Ignore
public class SpringPlaceHolderTest extends AbstractJUnit4SpringContextTests {
	
	/**
	 * Test.
	 */
	@Test
	public void test(){
		Bean bean = (Bean)applicationContext.getBean("bean");
		Assert.assertEquals("JIM", bean.getName());
		Assert.assertEquals("myPasswd", bean.getPasswd());
	}
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().length());
	}
}
