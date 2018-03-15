package org.hbhk.aili.esb.server.foss.edi;


import org.hbhk.aili.esb.server.common.utils.DateConverterUtil;
import org.hbhk.aili.esb.server.foss.edi.SumBillService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.deppon.esb.inteface.domain.air.SumBillRequest;

/**
 * 测试调用EDI存储过程,添加合票清单信息.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午7:14:28
 */
@ContextConfiguration({
	"classpath:com/deppon/esb/server/foss/edi/ds-spring.xml"
})
@Ignore
public class ProcedureTest extends AbstractJUnit4SpringContextTests{
	
	/**
	 * 测试调用上传合票清单存储过程。.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test()throws Exception{
		SumBillService service	=	applicationContext.getBean(SumBillService.class);
		SumBillRequest rq = createSubillInfo();
		service.addSumbill(rq);
	}
	//exec add_edi_mail 'KY22','113432','2013-3-11','C','清单夹',1,'',1,1,50,'ceshi','ceshi'
	/**
	 * 生成测试数据.
	 * 
	 * @return the sum bill request
	 * @author qiancheng
	 * @date 2012-12-20 下午7:15:08
	 */
	public SumBillRequest createSubillInfo(){
		SumBillRequest rq = new SumBillRequest();
		rq.setCreatorNumber("KYSH001");
		rq.setSenderName("11342432");
		rq.setSendDate(DateConverterUtil.convertToXMLGregorianCalendar(new java.util.Date()));
		rq.setSubject("C");
		rq.setMailFolderName("清单夹");
		rq.setNoticeFlag("1");
		rq.setReadFlag("1");
		rq.setMailFlag("1");
		rq.setPriorityLevel("");
		rq.setMailSize(50);
		rq.setAttachmentName("ceshi");
		rq.setAttachmentLink("ceshi");
		return rq;
	}
}
