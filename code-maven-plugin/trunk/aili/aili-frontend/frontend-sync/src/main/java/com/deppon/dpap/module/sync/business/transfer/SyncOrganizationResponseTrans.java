package com.deppon.dpap.module.sync.business.transfer;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.dpap.module.sync.business.jms.ObjectFactory;
import com.deppon.dpap.module.sync.business.jms.SyncAdminOrgResponse;
import com.deppon.dpap.module.sync.esb.exception.ConvertException;
import com.deppon.dpap.module.sync.esb.transfer.IMessageTransform;
import com.deppon.dpap.module.sync.esb.util.JAXBContextUtil;

public class SyncOrganizationResponseTrans implements IMessageTransform<SyncAdminOrgResponse>{

	/** The Constant CLZZ. */
	private static final Class<SyncAdminOrgResponse> CLZZ =  SyncAdminOrgResponse.class;

	/** The log. */
	private static Log log = LogFactory.getLog(SyncOrganizationResponseTrans.class);
	
	/** The context. */
	private static JAXBContext context = JAXBContextUtil.initContext(CLZZ);;
	
	@Override
	public SyncAdminOrgResponse toMessage(String string) throws ConvertException, UnsupportedEncodingException {
		if(context == null){
			JAXBContextUtil.initContext(CLZZ);//再次尝试一次
			if (context == null) {
				throw new ConvertException("初始化JAXBContext失败！");
			}
		}
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(string.getBytes("UTF-8"));
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<SyncAdminOrgResponse> element = unmarshaller.unmarshal(new StreamSource(stream), CLZZ);
			return element.getValue();
		} catch (JAXBException e) {
			throw new ConvertException("反序列化" + CLZZ.getName() + "时失败！", e);
		}
	}

	@Override
	public String fromMessage(SyncAdminOrgResponse value) throws ConvertException {
		if(context == null){
			JAXBContextUtil.initContext(CLZZ);//再次尝试一次
			if (context == null) {
				throw new ConvertException("初始化JAXBContext失败！");
			}
		}
		if (value == null) {
			return null;
		}
		try {
			StringWriter stringWriter = new StringWriter();
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
			JAXBElement<SyncAdminOrgResponse> element = new ObjectFactory().createSyncAdminOrgResponse(value);
			marshaller.marshal(element, stringWriter);
			stringWriter.flush();
			return stringWriter.toString();
		} catch (PropertyException e) {
			throw new ConvertException("序列化" + value.getClass().getName() + "时失败！", e);
		} catch (FactoryConfigurationError e) {
			throw new ConvertException("序列化" + value.getClass().getName() + "时失败！", e);
		} catch (JAXBException e) {
			throw new ConvertException("序列化" + value.getClass().getName() + "时失败！", e);
		}
	}

}
