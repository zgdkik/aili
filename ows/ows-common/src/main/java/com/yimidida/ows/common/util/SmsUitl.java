package com.yimidida.ows.common.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.yimidida.ows.base.share.util.RestfulClient;
import com.yimidida.ows.base.share.util.SystemParameterUtil;
import com.yimidida.ows.common.server.dao.ISendLogDao;
import com.yimidida.ows.common.share.entity.SendLogEntity;
import com.yimidida.ows.common.share.response.ServiceResult;
import com.yimidida.ymdp.core.server.web.WebApplicationContextHolder;
import com.yimidida.ymdp.core.share.ex.BusinessException;
import com.yimidida.ymdp.core.share.util.JsonUtil;

public class SmsUitl {

	private static Logger log = LoggerFactory.getLogger(SmsUitl.class);

	private static ISendLogDao sendLogDao;

	public static ServiceResult sendSms(Object smsContent,String bizType) {
		if (smsContent == null) {
			throw new BusinessException("短信对象不能为空");
		}
		// 保存日志
		if (sendLogDao == null) {
			sendLogDao = WebApplicationContextHolder.getApplicationContext().getBean(ISendLogDao.class);
		}
		SendLogEntity smsLog = new SendLogEntity();
		smsLog.setId(UuidUtil.getUuid());
//		smsLog.setBizType(bizType);
//		smsLog.setBody(smsContent.getBody());
//		smsLog.setCreateTime(new Date());
//		smsLog.setBizNo(smsContent.getPhoneNumber());
		sendLogDao.insert(smsLog);
		String smsUrl = SystemParameterUtil.getValue("sms.url");
		log.info("短息详情:" + JsonUtil.toJson(smsContent));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> request = new HttpEntity<Object>(smsContent,
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
