package org.hbhk.aili.esb.server.foss.csair;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 *  
 *   处理csair头信息
 *   foss使用2.4.6版本的cxf通过esb(cxf为2.6.3)访问南航返回400错误。
 *   解决方法：
 *   1 exchange.getIn().getHeaders().put("user-agent", "Apache CXF 2.6.3");
 *   2 exchange.getIn().getHeaders().remove("user-agent");
 *   @author songshishuai
 *   @date 2013-2-20 下午5:39:00
 */
public class CsairProcessor implements Processor {
	@Override
	public void process(Exchange exchange) throws Exception {
		/*
		 * @说明  去除HTTP请求头 user-agent ，防止出现404问题
		 * @auth songshishuai
		 * @date 2013-03-05
		 */
		exchange.getIn().getHeaders().remove("user-agent");
	}
}