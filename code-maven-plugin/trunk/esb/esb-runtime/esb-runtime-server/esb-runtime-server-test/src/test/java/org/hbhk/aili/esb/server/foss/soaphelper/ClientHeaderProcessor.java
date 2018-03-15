package org.hbhk.aili.esb.server.foss.soaphelper;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

/**
 * add customer soap header with cxf framework.
 * 
 * @author qiancheng
 */
public  class ClientHeaderProcessor extends AbstractSoapInterceptor {	
	
	/**
	 * Instantiates a new client header processor.
	 */
	public ClientHeaderProcessor() {
		super(Phase.WRITE);
	}
	
	/**
	 * 消息处理
	 * 
	 * @param message
	 *            the message
	 * @throws Fault
	 *             the fault
	 * @author HuangHua
	 * @date 2012-12-25 下午5:39:38
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	 */
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		// 添加SoapHeader内容
		List<Header> headers = message.getHeaders();
		// 使用EsbHeaderHelper创建自定义soapHeader信息
		Header  header = WSHeaderHelper.createClientOutHeader();
		headers.add(header);
	}	
}
