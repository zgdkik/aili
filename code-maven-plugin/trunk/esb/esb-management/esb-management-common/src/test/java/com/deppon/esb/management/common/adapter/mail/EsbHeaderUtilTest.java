package com.deppon.esb.management.common.adapter.mail;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.deppon.esb.management.common.constant.ESBServiceConstant;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;
import com.deppon.esb.management.common.util.EsbHeaderUtil;

public class EsbHeaderUtilTest {
	@Test
	public void test(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(ESBServiceConstant.RESULT_CODE,1+"");
		EsbHeader header = EsbHeaderUtil.map2ESBHeader(map);
		Assert.assertNotNull(header);
		Assert.assertTrue(1==header.getResultCode());
	}
	//null 可以强转
	@Test
	public void test2(){
		String str = (String)(null);
		System.out.println(str);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(ESBServiceConstant.RESULT_CODE,1);
		Integer b = (Integer)map.get(ESBServiceConstant.RESULT_CODE);
		
		
	}
	
	@Test
	public void testString(){
	  String responseId = StringUtils.substring("68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d68185e0a-fb96-453a-abaf-477027d", 0, 78);
	  System.out.println(responseId);
	}
	
	
}
