package org.hbhk.test.proxy;

import java.io.IOException;

import org.hbhk.aili.core.share.util.FileAsStringUtil;

import com.alibaba.fastjson.JSONArray;

public class JsonArrayTest {

	public static void main(String[] args) throws IOException {
		String json = FileAsStringUtil
				.readFileToStr("E:/workspace/hbhk00/aili/aili-core/src/test/resources/file/data.txt");
		JSONArray jsonArray = JSONArray.parseArray(json);
		String keys = jsonArray.getString(0);
		String values = jsonArray.getString(1);
		JSONArray keyArray = JSONArray.parseArray(keys);
		JSONArray valueArray = JSONArray.parseArray(values);
		for (int i = 0; i < keyArray.size(); i++) {
			System.out.println(keyArray.getString(i) +" = "+valueArray.getString(i));
		}
		
	}

}
