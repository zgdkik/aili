package org.hbhk.test;

import java.util.List;

public class OAResultMessageEntity {
	
	 //消息代码｛成功为“10000”，失败为“00000”｝
	private String message_code;
	
	//消息代码说明
	private String message_detail;
	
	//返回记录的条数
	private int count;
	
	//处理明细
	private List<RewardFineDetailEntity> detail;

	public String getMessage_code() {
		return message_code;
	}

	public void setMessage_code(String message_code) {
		this.message_code = message_code;
	}

	public String getMessage_detail() {
		return message_detail;
	}

	public void setMessage_detail(String message_detail) {
		this.message_detail = message_detail;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<RewardFineDetailEntity> getDetail() {
		return detail;
	}

	public void setDetail(List<RewardFineDetailEntity> detail) {
		this.detail = detail;
	}

}
