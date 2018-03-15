package com.deppon.esb.test;

import java.io.StringWriter;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo;
import org.hbhk.aili.esb.server.common.exception.ESBConvertException;
import org.hbhk.aili.esb.server.common.transformer.OxmTrans;
import org.junit.Test;


/**
 * 公共转换类测试.
 * 
 * @author HuangHua
 * @date 2012-12-25 下午5:05:10
 */
public class OxmTransTest {
	
	/** The log. */
	private static Log log = LogFactory.getLog(OxmTransTest.class);

	/**
	 * test.
	 * 
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 * @author HuangHua
	 * @date 2012-12-25 下午5:05:25
	 */
	@Test
	public void test() throws ESBConvertException{
		CommonExceptionInfo info = new CommonExceptionInfo();
		info.setCreatedTime(new Date());
		info.setDetailedInfo("value");
		info.setExceptioncode("value");
		info.setExceptiontype("value");
		info.setMessage("value");
		StringWriter stringWriter = new StringWriter();
		OxmTrans.fromMessage(info, stringWriter);
		log.info(stringWriter);
		CommonExceptionInfo result = OxmTrans.toMessage(stringWriter.toString(), CommonExceptionInfo.class);

		log.info(result.getCreatedTime());

	}

}
