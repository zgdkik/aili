package org.hbhk.aili.esb.server.foss.edi;

import org.hbhk.aili.esb.server.common.utils.DateConverterUtil;
import org.hbhk.aili.esb.server.foss.edi.SumBillConverter;
import org.junit.Assert;
import org.junit.Test;

import com.deppon.esb.inteface.domain.air.SumBillRequest;

/**
 * Jaxb转换测试.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午7:12:42
 */
public class JaxbTest {
	
	/**
	 * 测试使用Jaxb，进行SumBillRequest对象 转换为xml文档以及 反转换；.
	 * 
	 * @throws Exception
	 *             the exception
	 * @author qiancheng
	 * @date 2012-12-20 下午7:13:02
	 */
	@Test
	public void  test() throws Exception {
		  SumBillRequest sr =  new SumBillRequest();
		  sr.setAttachmentLink("link");
		  sr.setAttachmentName("aName");
		  sr.setCreatorNumber("an");
		  sr.setMailFlag("mailflag");
		  sr.setMailFolderName("folderName");
		  sr.setMailSize(1000);
		  sr.setNoticeFlag("noticeFlag");
		  sr.setPriorityLevel("高");
		  sr.setReadFlag("readFlag");
		  sr.setSendDate(DateConverterUtil.convertToXMLGregorianCalendar(new java.util.Date()));
		  sr.setSenderName("senderName");
		  sr.setSubject("subject");
		  String msg = SumBillConverter.objectToXml(sr);
		  System.out.println(msg);
		  Assert.assertNotNull(msg);
		  SumBillRequest rq = SumBillConverter.xmlToObject(msg);
		  System.out.println(rq);
		  Assert.assertNotNull(rq);
	}
}
