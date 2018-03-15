package com.feisuo.sds.common.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feisuo.sds.common.share.request.push.PushMessage;
import com.feisuo.sds.common.share.request.push.PushTargetGroup;
import com.feisuo.sds.common.share.request.push.UserTarget;
import com.feisuo.sds.common.share.response.ServiceResult;
import com.feisuo.sds.common.util.PushMessageUtil;

public class PushMessageUtilTest {

	public static void main(String[] args) {
		PushMessage pushMessage = new PushMessage();
		pushMessage.setTitle("招募司机(至少5个字符)");
		pushMessage.setContent("线路上海到北京-招募司机");
		pushMessage.setContentId("a12345678622(至少10个字符)");
		pushMessage.setContentType(2);
		pushMessage.setUserType(1);
		pushMessage.setPriority(4);
		List<PushTargetGroup> pushTargetGroups = new ArrayList<>();
		PushTargetGroup pushTargetGroup = null;
		List<UserTarget> targets = null;
		UserTarget user = null;
		for (int i = 0; i < 1; i++) {
			pushTargetGroup = new PushTargetGroup();
			pushTargetGroup.setPriority(3 + i);
			pushTargetGroup.setInterval(30 + i);
			targets = new ArrayList<>();
			for (int j = 0; j < 2; j++) {
				user = new UserTarget();
				//推送的司机对象id
				user.setUserId("5a70d00b-f0fe-4bf7-94f4-bb0e85768423");
				targets.add(user);
			}
			pushTargetGroup.setTargets(targets);
			pushTargetGroups.add(pushTargetGroup);
		}
		pushMessage.setGroups(pushTargetGroups);
		Map<String, String> extensions = new HashMap<String, String>();
		extensions.put("key2", "401");
		extensions.put("key1", "559b97a1d8754c1a99d3a2683950ab91");
		pushMessage.setExtensions(extensions);
		ServiceResult result = PushMessageUtil.pushMessage(pushMessage,"recruit");
		System.out.println(result);
	}
}
