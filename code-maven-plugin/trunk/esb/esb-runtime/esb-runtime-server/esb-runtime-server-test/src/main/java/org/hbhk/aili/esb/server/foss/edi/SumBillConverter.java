package org.hbhk.aili.esb.server.foss.edi;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import com.deppon.esb.inteface.domain.air.ObjectFactory;
import com.deppon.esb.inteface.domain.air.SumBillRequest;

/**
 * xml SumBillRequest 转换器.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午4:39:00
 */
public class SumBillConverter {
	
	/** logger. */
	private static Logger log = Logger.getLogger(SumBillConverter.class);
	
	/**
	 * SumBillRequest转换为 xml文档.
	 * 
	 * @param message
	 *            the message
	 * @return the string
	 * @throws Exception
	 *             the exception
	 * @author qiancheng
	 * @date 2012-12-20 下午4:40:51
	 */
	public static  String objectToXml(SumBillRequest message) throws Exception {	
		ObjectFactory objectfactory = new ObjectFactory();
		JAXBElement<SumBillRequest> jaxbRetieveResponse = objectfactory.createCollectAirBillRequest(message);
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(SumBillRequest.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.marshal(jaxbRetieveResponse, stringWriter);
		} catch (PropertyException e) {
			log.error("", e);
		} catch (FactoryConfigurationError e) {
			log.error("", e);
		} catch (JAXBException e) {
			log.error("", e);
		}
		return stringWriter.getBuffer().toString();
	}
	
	/**
	 * xml 文档转换为SumBillRequest对象.
	 * 
	 * @param source
	 *            the source
	 * @return the sum bill request
	 * @throws Exception
	 *             the exception
	 * @author qiancheng
	 * @date 2012-12-20 下午4:41:22
	 */
	public static SumBillRequest xmlToObject(String source)throws Exception{
		JAXBContext context = JAXBContext.newInstance(SumBillRequest.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		StringReader stringReader = new StringReader(source);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader xmlStreamReader =factory.createXMLStreamReader(stringReader);
		JAXBElement<SumBillRequest> jaxbElement = unmarshaller.unmarshal(xmlStreamReader, SumBillRequest.class);
		return jaxbElement.getValue();
	}
}
