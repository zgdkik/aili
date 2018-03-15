package com.deppon.esb.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo;
import org.hbhk.aili.esb.server.common.exception.ESBConvertException;
import org.hbhk.aili.esb.server.common.transformer.CommonExceptionInfoTrans;
import org.junit.Assert;
import org.junit.Test;


/**
 * 公共异常转换测试.
 * 
 * @author HuangHua
 * @date 2012-12-25 下午5:04:42
 */
public class CommonExceptionInfoTransTest {
	
	/** The log. */
	private static Log log = LogFactory.getLog(CommonExceptionInfoTransTest.class);
	
	/**
	 * 测试.
	 * 
	 * @throws ESBConvertException
	 *             the eSB convert exception
	 * @author HuangHua
	 * @date 2012-12-25 下午5:05:00
	 */
	@Test
	public void test() throws ESBConvertException{
		CommonExceptionInfoTrans trans = new CommonExceptionInfoTrans();
		CommonExceptionInfo info = new CommonExceptionInfo();
		info.setCreatedTime(new Date());
		info.setDetailedInfo("value");
		info.setExceptioncode("value");
		info.setExceptiontype("value");
		info.setMessage("value");
		String result = trans.fromMessage(info);
		log.info(result);
		Assert.assertNotNull(result);
		
		Object exceptionInfo = trans.toMessage(result);
		assertNotNull(exceptionInfo);
		
	}

}
