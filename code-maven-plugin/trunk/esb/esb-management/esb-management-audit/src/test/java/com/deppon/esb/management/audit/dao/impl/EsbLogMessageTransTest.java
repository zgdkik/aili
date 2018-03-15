package com.deppon.esb.management.audit.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.cxf.helpers.FileUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;

import com.deppon.esb.management.common.constant.ESBServiceConstant;
import com.deppon.esb.management.common.entity.EsbLogMessage;

/**
 * EsbLogMessage json转换测试
 * TODO（描述类的职责）
 * @author Administrator
 * @date 2013-4-25 上午10:09:49
 */
public class EsbLogMessageTransTest {
	private static Logger LOG = Logger.getLogger(EsbLogMessageTransTest.class);
	private ObjectMapper mapper ;
	private String json ;
	@Before
	public void init(){
		json =FileUtils.getStringFromFile(new File ("src/test/resources/com/deppon/esb/management/audit/META-INF/dao/data/esbLogMessage_json.txt"));
		mapper = new ObjectMapper();
	}
	
	@Test
	public void test()throws Exception{
		List<EsbLogMessage> list = null;
		list = mapper.readValue(json, new TypeReference<List<EsbLogMessage>>(){});
		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
	}
	/**
	 * map 经过jackson 转换成json数组后， map中的Date对象变成了 
	 * fuanction（方法详细描述说明、方法参数的具体涵义）
	 * @author qiancheng
	 * @param 
	 * @date 2013-4-26 下午3:52:12
	 * @return
	 */
	@Test
	public void test2()throws Exception{
		List<EsbLogMessage> list = new ArrayList<EsbLogMessage>();
		EsbLogMessage msg = new EsbLogMessage();
		msg.setBody("abc");
		Map<String,Object> map = new HashMap<String,Object>();
		Date d = new Date();
		map.put(ESBServiceConstant.ESB_LOGMSG_CREATETIME, d);
		msg.setHeader(map);
		list.add(msg);
		String json = mapper.writeValueAsString(list);
		LOG.info(json);
		List<EsbLogMessage> ll = mapper.readValue(json, new TypeReference<List<EsbLogMessage>>() {
		});
		Assert.assertNotNull(ll);
		Assert.assertTrue(ll.size()>0);
		EsbLogMessage log = ll.get(0);
		Assert.assertNotNull(log.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME));
		LOG.info(log.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME).getClass().toString());
		//Assert.assertTrue(log.getHeader().get(ESBServiceConstant.ESG_LOGMSG_CREATETIME) instanceof Date);
		LOG.info(log.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME));
	}
}
