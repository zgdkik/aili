/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.utils
 * FILE    NAME: ValidateHeaderUtilTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.server.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.utils.ValidateHeaderUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.esb.server.common.mock.ServiceConfigLoader;


/**
 * ValidateHeaderUtil测试类.
 * 
 * @author HuangHua
 * @date 2012-12-25 下午2:07:32
 */
public class ValidateHeaderUtilTest {

	/** 待验证的工具类. */
	private ValidateHeaderUtil validateHeaderUtil;

	/**
	 * Test method for.
	 * 
	 * {@link org.hbhk.aili.esb.server.common.utils.ValidateHeaderUtil#validateExistService(java.util.Map)}
	 * .
	 */
	@Test
	public void testValidateExistService() {
		// 后端编码
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(ESBServiceConstant.BACK_SERVICE_CODE, "esbServiceCode");
		boolean isExistBack = validateHeaderUtil.validateExistService(headers);
		Assert.assertTrue(isExistBack);
		// ESB编码
		headers.clear();
		headers.put(ESBServiceConstant.ESB_SERVICE_CODE, "esbServiceCode");
		boolean isExistEsb = validateHeaderUtil.validateExistService(headers);
		Assert.assertTrue(isExistEsb);
		// 不存在的编码
		headers.clear();
		headers.put(ESBServiceConstant.ESB_SERVICE_CODE, "111");
		boolean isExistErr = validateHeaderUtil.validateExistService(headers);
		Assert.assertFalse(isExistErr);

	}

	/**
	 * Test method for.
	 * 
	 * {@link org.hbhk.aili.esb.server.common.utils.ValidateHeaderUtil#validateRequest(java.util.Map)}
	 * .
	 */
	@Test
	public void testValidateRequest() {
		// 正确
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(ESBServiceConstant.VERSION, "111");
		headers.put(ESBServiceConstant.BUSINESSID, "222");
		headers.put(ESBServiceConstant.REQUEST_ID, "113331");
		headers.put(ESBServiceConstant.SOURCE_SYSTEM, "asdf");
		headers.put(ESBServiceConstant.ESB_SERVICE_CODE, "err");
		headers.put(ESBServiceConstant.MESSAGE_FORMATE, "er");
		headers.put(ESBServiceConstant.EXCHANGE_PATTERN, "cv");
		boolean resultRight = validateHeaderUtil.validateRequest(headers);
		Assert.assertTrue(resultRight);
		// 错误
		Map<String, Object> headers1 = new HashMap<String, Object>();
		headers1.put(ESBServiceConstant.VERSION, "334");
		headers1.put(ESBServiceConstant.BUSINESSID, "fds");
		headers1.put(ESBServiceConstant.REQUEST_ID, "erew");
		headers1.put(ESBServiceConstant.SOURCE_SYSTEM, "hgh");
		headers1.put(ESBServiceConstant.ESB_SERVICE_CODE, "ghg");
		headers1.put(ESBServiceConstant.MESSAGE_FORMATE, "tr");
		// headers1.put(ESBServiceConstant.EXCHANGE_PATTERN,"hg");
		boolean resultError = validateHeaderUtil.validateRequest(headers1);
		Assert.assertFalse(resultError);
	}

	/**
	 * Test method for.
	 * 
	 * {@link org.hbhk.aili.esb.server.common.utils.ValidateHeaderUtil#validateCallBack(java.util.Map)}
	 * .
	 */
	@Test
	public void testValidateCallBack() {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(ESBServiceConstant.VERSION,"22");
		headers.put(ESBServiceConstant.BUSINESSID,"657");
		headers.put(ESBServiceConstant.REQUEST_ID,"678");
		headers.put(ESBServiceConstant.RESPONSE_ID,"ghf");
		headers.put(ESBServiceConstant.SOURCE_SYSTEM,"gh");
		headers.put(ESBServiceConstant.TARGET_SYSTEM,"cv");
		headers.put(ESBServiceConstant.ESB_SERVICE_CODE,"sd");
		headers.put(ESBServiceConstant.BACK_SERVICE_CODE,"erter");
		headers.put(ESBServiceConstant.MESSAGE_FORMATE,"erter");
		headers.put(ESBServiceConstant.EXCHANGE_PATTERN,"bngh");
		headers.put(ESBServiceConstant.RESULT_CODE,"wre");
		Assert.assertTrue(validateHeaderUtil.validateCallBack(headers));
	}

	/**
	 * 测试前准备.
	 * 
	 * @author HuangHua
	 * @date 2012-12-25 下午4:41:21
	 */
	@Before
	public void setUp() {
		validateHeaderUtil = new ValidateHeaderUtil();
		validateHeaderUtil
				.setConfigLoader(new ServiceConfigLoader());
	}

}
