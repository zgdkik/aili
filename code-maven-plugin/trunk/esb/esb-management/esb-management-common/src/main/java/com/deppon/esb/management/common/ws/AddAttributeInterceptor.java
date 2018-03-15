package com.deppon.esb.management.common.ws;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.common.constant.ESBConstants;
/**
 * 一期移植
 * 
 * 在soap消息头中添加属性
 * @author qiancheng
 *
 */
public class AddAttributeInterceptor extends AbstractSoapInterceptor {
	/**
	 * 日志
	 */
	protected static final Logger LOGGER = LoggerFactory.getLogger(AddAttributeInterceptor.class);
	public AddAttributeInterceptor(){
		super(Phase.WRITE);
	}
	
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		try {
			Header redoHeader = new Header(
					new QName(null, ESBConstants.REDO_SYSTEMSEND),
					ESBConstants.REDO_SYSTEMSEND,
                    new JAXBDataBinding(String.class));
			message.getHeaders().add(redoHeader);
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
