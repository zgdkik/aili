package com.deppon.esb.management.common.ws;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import com.deppon.esb.management.common.util.DateUtil;

/**
 * 一期移植
 * @author HuangHua
 * @date 2012-12-27 上午9:55:44
 */
public class DynamicWSClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicWSClient.class);
	
	public static Object[] callWS(final String address, String operationNamespace, 
			String operation, Object[] objects) throws Exception {
		JaxWsDynamicClientFactory dynamicClient = JaxWsDynamicClientFactory.newInstance();
		
		String wsdlAddress = address;
		if ( !StringUtils.endsWith(address, "wsdl")) {
			wsdlAddress += "?wsdl";
		}
		Client client = dynamicClient.createClient(wsdlAddress);
				
        ClientImpl clientImpl = (ClientImpl) client;
        //通过拦截器，添加属性到soap消息header中；
        clientImpl.getOutInterceptors().add(new AddAttributeInterceptor());
        Endpoint endpoint = clientImpl.getEndpoint();

        ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
        
        QName opName = new QName(operationNamespace, operation);
        
        BindingOperationInfo boi = getOperation(serviceInfo, opName);
        if ( boi == null )
        	return null;
        
        BindingMessageInfo inputMessageInfo = boi.getUnwrappedOperation().getInput();

        List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();
        Object[] requests = new Object[parts.size()];
        for(int i=0; i<parts.size(); i++) {
        	MessagePartInfo partInfo = parts.get(i);
        	Class<?> partClass = partInfo.getTypeClass();
        	LOGGER.info("Request class: " + partClass.getCanonicalName());
        	requests[i] = partClass.newInstance();

        	// 2012/06/11 zhengwl
        	// 动态客户端生成的dateTime的类型是javax.xml.datatype.XMLGregorianCalendar，
        	// 无法copy到java.util.Date的类型；这里暂时把时间类型的字段排序掉；
        	// 之前尝试使用binding文件控制生成的dateTime的java类为java.util.Date，但未成功；
        	// 所以暂时把javax.xml.datatype.XMLGregorianCalendar排除掉；
        	List<String> excludedProperties = new ArrayList<String>();
        	PropertyDescriptor[] descs = PropertyUtils.getPropertyDescriptors(requests[i]);
        	for(PropertyDescriptor desc: descs) {
        		Class propertyClz = desc.getPropertyType();
        		if ( propertyClz.isAssignableFrom(javax.xml.datatype.XMLGregorianCalendar.class) ) {
        			excludedProperties.add(desc.getName());
        		}
        	}
        	LOGGER.info("Executing dynamic ws client, exclude XMLGregorianCalendar properties: " + 
        			StringUtils.join((String[])excludedProperties.toArray(new String[0])));
        	
        	try {
        		BeanUtils.copyProperties(objects[i], requests[i],
        			(String[])excludedProperties.toArray(new String[0]));
        	} catch ( BeansException t) {
        		LOGGER.error(t.getMessage(), t);
        		throw t;
        	}
        	
        	// 手工设置类型为javax.xml.datatype.XMLGregorianCalendar的值；
        	for(String prop: excludedProperties) {
        		Object value = PropertyUtils.getProperty(objects[i], prop);
        		if ( value instanceof Date) {
	        		Date dateValue = (Date)value;
//	        		if ( dateValue != null )
	        			PropertyUtils.setProperty(requests[i], prop, 
	        				DateUtil.convertToXMLGregorianCalendar(dateValue));
        		}
        	}
        	
        }
        
        Object[] result = client.invoke(opName, requests);
        
		return result;
	}
	private static BindingOperationInfo getOperation(ServiceInfo serviceInfo, QName opName) {
		if ( serviceInfo == null || opName == null )
			return null;
		
		Collection<BindingInfo> bindings = serviceInfo.getBindings();
		BindingOperationInfo operation = null;
		if ( bindings != null ) {
			for(BindingInfo binding: bindings) {
				operation = binding.getOperation(opName);
				if ( operation != null )
					return operation;
			}
		}
		return operation;
	}
}
