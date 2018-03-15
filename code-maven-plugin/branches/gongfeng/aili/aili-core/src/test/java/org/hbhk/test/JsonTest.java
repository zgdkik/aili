package org.hbhk.test;

import java.util.ArrayList;
import java.util.List;

import org.hbhk.aili.core.share.util.FileAsStringUtil;
import org.hbhk.aili.core.share.util.JsonUtil;

public class JsonTest {

	
	public static void main(String[] args) throws Exception {
		String str = FileAsStringUtil.readFileToStr("F:/java-dev/workspaces/hbhkws00/aili/aili-core/src/test/resources/foss/json.txt");
		JsonUtil.parseJson(str, OAResultMessageEntity.class);
		List<OAResultMessageEntity> List = new ArrayList<OAResultMessageEntity>();
		for (int i = 0; i <10000; i++) {
			OAResultMessageEntity o = new OAResultMessageEntity();
			o.setCount(1);
			o.setMessage_code("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			o.setMessage_detail("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaadsfsdfsdfsdfsf");
			List.add(o);
		}
		String sss = JsonUtil.toJson(List);
		System.out.println(sss);
		System.out.println(sss.getBytes().length/1024);
	}
}
