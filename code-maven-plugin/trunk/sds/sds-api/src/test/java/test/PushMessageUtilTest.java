package test;

import com.feisuo.sds.common.share.request.push.PushMessage;


public class PushMessageUtilTest {

	
	public static void main(String[] args) {
		PushMessage pushMessage = new PushMessage();
		pushMessage.setTitle("招募司机");
		pushMessage.setContent("线路上海到北京-招募司机");
		pushMessage.setContentId("sds");
		//PushMessageUtil.pushMessage(pushMessage);
	}
}
