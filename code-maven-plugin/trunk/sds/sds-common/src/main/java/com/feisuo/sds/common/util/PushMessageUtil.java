package com.feisuo.sds.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hbhk.aili.core.server.web.WebApplicationContextHolder;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.feisuo.sds.base.share.util.RestfulClient;
import com.feisuo.sds.base.share.util.SystemParameterUtil;
import com.feisuo.sds.common.server.dao.ISendLogDao;
import com.feisuo.sds.common.share.entity.SendLogEntity;
import com.feisuo.sds.common.share.request.push.PushMessage;
import com.feisuo.sds.common.share.request.push.PushTargetGroup;
import com.feisuo.sds.common.share.request.push.UserTarget;
import com.feisuo.sds.common.share.response.ServiceResult;

public class PushMessageUtil {

	private static Logger log = LoggerFactory.getLogger(PushMessageUtil.class);
	
	private static  ISendLogDao sendLogDao;
	
	private static ExecutorService executor = Executors.newCachedThreadPool();
	public static ServiceResult pushMessage(final PushMessage pushMessage ,final String bizType) {
		String pushMsg =  SystemParameterUtil.getValue("push.msg");
		if(!"Y".equals(pushMsg)){
			log.info("消息推送未开启");
			return new ServiceResult();
		}
		if(pushMessage==null){
			throw  new BusinessException("消息对象不能为空");
		}
		//保存日志
		if(sendLogDao == null){
			sendLogDao = WebApplicationContextHolder.getApplicationContext().getBean(ISendLogDao.class);
		}
		executor.execute(new Runnable() {
			@Override
			public void run() {
				SendLogEntity smsLog = new SendLogEntity();
				smsLog.setId(UuidUtil.getUuid());
				smsLog.setBizType(bizType);
				smsLog.setBody(JsonUtil.toJsonInclusion(pushMessage));
				smsLog.setCreateTime(new Date());
				smsLog.setBizNo(pushMessage.getContentId());
				sendLogDao.insert(smsLog);
				//http://121.40.213.248:8099/Push.Send.json
				String pushMessageUrl =  SystemParameterUtil.getValue("push.message.url");
				log.info("推送详情:"+JsonUtil.toJson(pushMessage));
				HttpHeaders headers = new HttpHeaders(); 
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<PushMessage> request = new HttpEntity<PushMessage>(pushMessage,
						headers);
				ResponseEntity<ServiceResult> response = RestfulClient.getClient()
						.postForEntity(pushMessageUrl, request, ServiceResult.class);
				ServiceResult result = response.getBody();
				log.info("返回结果:"+JsonUtil.toJson(result));
				if(result!=null && "0".equals(result.getErrCode())){
					//成功
					smsLog.setStatus(2);
					sendLogDao.update(smsLog);
				}else{
					String errMsg ="推送失败";
					if(result!=null){
						errMsg =result.getErrMsg();
					}
					smsLog.setDescp(errMsg);
					sendLogDao.update(smsLog);
				}
			}
		});
		return new ServiceResult() ;
	}
	
	public static ServiceResult pushMessageNotification(String content,String contentId,String bizType,String code,String... userIds) {
		PushMessage push = new PushMessage();
		push.setUserType(2);
		push.setPriority(10);
		push.setTitle("智送-消息通知");
		push.setContentId(contentId);
		push.setContent(content);
		push.setContentType(0);
		Map<String, String> extensions = new HashMap<String, String>();
		extensions.put("key2", code);
		extensions.put("key1", contentId);
		push.setExtensions(extensions);
		List<UserTarget> userTargets = new ArrayList<UserTarget>();
		for (String userId : userIds) {
			UserTarget user = new UserTarget();
			user.setUserId(userId);
			userTargets.add(user);
		}
		PushTargetGroup pushTargetGroup = new PushTargetGroup();
		pushTargetGroup.setInterval(5);
		pushTargetGroup.setPriority(10);
		pushTargetGroup.setTargets(userTargets);
		List<PushTargetGroup> list = new ArrayList<PushTargetGroup>();
		list.add(pushTargetGroup);
		push.setGroups(list);
		ServiceResult result = pushMessage(push, bizType);;
		return result;
	}
	
	public static ServiceResult pushMessageNotification(String content,String contentId,String bizType,String code,Map<String, String> extensions,String... userIds) {
		PushMessage push = new PushMessage();
		push.setUserType(2);
		push.setPriority(10);
		push.setTitle("智送-消息通知");
		push.setContentId(contentId);
		push.setContent(content);
		push.setContentType(0);
		if(extensions==null){
			extensions = new HashMap<String, String>();
		}
		extensions.put("key2", code);
		extensions.put("key1", contentId);
		push.setExtensions(extensions);
		List<UserTarget> userTargets = new ArrayList<UserTarget>();
		for (String userId : userIds) {
			UserTarget user = new UserTarget();
			user.setUserId(userId);
			userTargets.add(user);
		}
		PushTargetGroup pushTargetGroup = new PushTargetGroup();
		pushTargetGroup.setInterval(5);
		pushTargetGroup.setPriority(10);
		pushTargetGroup.setTargets(userTargets);
		List<PushTargetGroup> list = new ArrayList<PushTargetGroup>();
		list.add(pushTargetGroup);
		push.setGroups(list);
		ServiceResult result = pushMessage(push, bizType);;
		return result;
	}


}
