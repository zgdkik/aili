package org.hbhk.aili.client.main.client.action;

import org.hbhk.aili.client.main.client.vo.TestBindVo;

public class ValidationData {

	//成功标识
	public final static String SUCCESS = "success";
	
	public String validateName1(String name1, TestBindVo bean) {
		if("hbhk".equals(name1)){
			return "你错了";
		}
		return SUCCESS;
		
	}
	
	public String validateTfv(String name1, TestBindVo bean) {
		if("hbhk".equals(name1)){
			return "你错了";
		}
		return SUCCESS;
		
	}
}
