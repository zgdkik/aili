package org.hbhk.aili.esb.server.foss.csair;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfPayload;
import org.apache.cxf.binding.soap.SoapHeader;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;


/**
 *  路由过程中添加Csair返回信息消息头
 *  @author songshishuai
 *  @date 2013-2-20 下午4:39:00
 */
public class CsairHeaderProcessor implements Processor {
	/**
	 *  从上下文中获取消息头，完成处理转化后设置到SOAP消息中
	 *  @author songshishuai
	 *  @date 2013-2-20 下午4:51:00
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		ESBHeader esbHeader= (ESBHeader) exchange.getIn().getHeader(ESBServiceConstant.ESB_HEADER);
		SoapHeader soapHeader =WSHeaderHelper.createClientOutHeader(esbHeader);
		List<SoapHeader> headersList = new ArrayList<SoapHeader>();
		headersList.add(soapHeader);
		@SuppressWarnings("unchecked")
		CxfPayload<SoapHeader> payload= (CxfPayload<SoapHeader>) exchange.getIn().getBody();
		payload.getHeaders().clear();
		payload.getHeaders().addAll(headersList);
	}
}
