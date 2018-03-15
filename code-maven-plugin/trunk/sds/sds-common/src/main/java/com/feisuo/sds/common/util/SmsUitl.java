package com.feisuo.sds.common.util;

import java.util.Date;

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
import com.feisuo.sds.common.share.request.sms.SmsContent;
import com.feisuo.sds.common.share.response.ServiceResult;

public class SmsUitl {

	private static Logger log = LoggerFactory.getLogger(SmsUitl.class);

	private static ISendLogDao sendLogDao;

	public static ServiceResult sendSms(SmsContent smsContent,String bizType) {
		if (smsContent == null) {
			throw new BusinessException("短信对象不能为空");
		}
		// 保存日志
		if (sendLogDao == null) {
			sendLogDao = WebApplicationContextHolder.getApplicationContext().getBean(ISendLogDao.class);
		}
		SendLogEntity smsLog = new SendLogEntity();
		smsLog.setId(UuidUtil.getUuid());
		smsLog.setBizType(bizType);
		smsLog.setBody(smsContent.getBody());
		smsLog.setCreateTime(new Date());
		smsLog.setBizNo(smsContent.getPhoneNumber());
		sendLogDao.insert(smsLog);
		String smsUrl = SystemParameterUtil.getValue("sms.url");
		log.info("短息详情:" + JsonUtil.toJson(smsContent));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SmsContent> request = new HttpEntity<SmsContent>(smsContent,
				headers);
		ResponseEntity<ServiceResult> response = RestfulClient.getClient()
				.postForEntity(smsUrl, request, ServiceResult.class);
		ServiceResult result = response.getBody();
		log.info("返回结果:"+JsonUtil.toJson(result));
		if(result!=null && "0".equals(result.getErrCode())){
			//成功
			smsLog.setStatus(2);
			sendLogDao.update(smsLog);
		}else{
			String errMsg ="发送失败";
			if(result!=null){
				errMsg =result.getErrMsg();
			}
			smsLog.setDescp(errMsg);
			sendLogDao.update(smsLog);
			throw new BusinessException("发送失败:"+errMsg);
		}
		return result;
	}

}
