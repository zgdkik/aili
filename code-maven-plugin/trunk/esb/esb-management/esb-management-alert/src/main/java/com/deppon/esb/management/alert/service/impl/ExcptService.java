/*
 * PROJECT NAME: esb-management-alert
 * PACKAGE NAME: com.deppon.esb.management.alert.service.impl
 * FILE    NAME: ExcptService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.alert.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.esb.management.alert.dao.INoticeDao;
import com.deppon.esb.management.alert.dao.IinterfaceThresholdDao;
import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.domain.NoticeInfo;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean;
import com.deppon.esb.management.alert.service.IExcptService;
import com.deppon.esb.management.common.adapter.mail.VelocityMailSupport;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;
import com.deppon.esb.management.excptn.domain.EsbExceptionInfo;
import com.deppon.esb.management.excptn.service.IEsbExceptionService;
import com.deppon.esb.management.exptn.generate.CommonExceptionInfo;
import com.deppon.esb.management.user.dao.INoticUserDao;

/**
 * 异常日志Service
 * 
 * @author HuangHua
 * @date 2013-5-18 上午9:33:33
 */
public class ExcptService implements IExcptService {

	/**
	 * 异常Service
	 */
	private IEsbExceptionService esbExceptionService;

	/**
	 * 告警信息Dao,用于保存告警信息
	 */
	private INoticeDao noticeDao;

	private INoticUserDao noticUserDao;

	private IinterfaceThresholdDao interfaceThresholdDao;

	private VelocityMailSupport excptn2MailSupport;

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-5-18 上午9:34:04
	 * @see com.deppon.esb.management.alert.service.IExcptService#sendExcptnWarning()
	 */
	@Override
	public void sendExcptnWarning() {
		List<NoticeInfo> noticeInfos = new ArrayList<NoticeInfo>();
		List<String> ids = new ArrayList<String>();
		List<String> notSendIds = new ArrayList<String>();

		// 查询所有未处理的系统异常信息
		List<EsbExceptionInfo> exceptionInfos = esbExceptionService
				.querySysExcption();
		// 发送告警信息
		for (EsbExceptionInfo esbExceptionInfo : exceptionInfos) {
			List<NoticeInfo> infos = sendExcptnWarningOne(esbExceptionInfo);
			if (infos != null) {
				noticeInfos.addAll(infos);
				ids.add(esbExceptionInfo.getId());
			}
		}

		// 保存告警信息
		if (noticeInfos != null) {
			if (noticeInfos.size() > 0) {
				noticeDao.insertNotices(noticeInfos);
			}
		}
		// 根据id更新异常信息,已发送告警的标记为1
		esbExceptionService.updateExceptionFlagByIds(ids, 1);
		
		for (EsbExceptionInfo esbExceptionInfo : exceptionInfos) {
			if(!ids.contains(esbExceptionInfo.getId())){
				notSendIds.add(esbExceptionInfo.getId());
			}
		}
		// 根据id更新异常信息,未发送告警的标记为2
		esbExceptionService.updateExceptionFlagByIds(notSendIds, 2);
	}

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-5-18 上午11:38:43
	 */
	@Override
	public List<NoticeInfo> sendExcptnWarningOne(
			EsbExceptionInfo esbExceptionInfo) {
		Map<String, Object> model = new HashMap<String, Object>();
		StringBuffer content = new StringBuffer();
		NoticeInfo noticeInfo = new NoticeInfo();

		// 整理出所有的需要发送的信息
		EsbHeader header = esbExceptionInfo.getEsbHeader();
		String bizId = header.getBusinessId();
		String requestId = header.getRequestId();
		String esbServiceCode = header.getEsbServiceCode();
		String backServiceCode = header.getBackServiceCode();
		CommonExceptionInfo commonExceptionInfo = esbExceptionInfo
				.getCommonExceptionInfo();
		String excptnCode = commonExceptionInfo.getExceptioncode();
		String excptnType = commonExceptionInfo.getExceptiontype();
		String excptnMsg = commonExceptionInfo.getMessage();
		Date excptnTime = commonExceptionInfo.getCreatedTime();

		// 模板赋值
		model.put("bizId", bizId);
		content.append("bizId:");
		content.append(bizId);

		model.put("requestId", requestId);
		content.append("requestId:");
		content.append(requestId);

		model.put("esbServiceCode", esbServiceCode);
		content.append("esbServiceCode:");
		content.append(esbServiceCode);

		model.put("backServiceCode", backServiceCode);
		content.append("backServiceCode:");
		content.append(backServiceCode);

		model.put("excptnCode", excptnCode);
		content.append("excptnCode:");
		content.append(excptnCode);

		model.put("excptnType", excptnType);
		content.append("excptnType:");
		content.append(excptnType);

		model.put("excptnMsg", excptnMsg);
		content.append("excptnMsg:");
		content.append(excptnMsg);

		model.put("excptnTime", excptnTime);
		content.append("excptnTime:");
		content.append(excptnTime);

		// 发送邮件
		String[] emails = getExcptn2WarningTargets(esbServiceCode,
				InterfaceThresholdInfo.CHANNEL_EMAIL);
		if (emails == null) {
			return null;
		}
		excptn2MailSupport.sendMime(model, emails);
		noticeInfo.setSendTime(new Date());
		noticeInfo.setSvcCode(esbServiceCode);
		noticeInfo.setContent(content.toString());
		noticeInfo.setStatisticsTime(new Date());
		noticeInfo.setType(NoticeInfo.NOTICE_TYPE_EXCEPTION);
		List<NoticeInfo> list = new ArrayList<NoticeInfo>();
		list.add(noticeInfo);

		return list;
	}

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-2-2 下午3:12:30
	 * @see com.deppon.esb.management.alert.service.IWarningService#getStatusNoCompleteWarningTargets(java.lang.String)
	 */
	@Override
	public String[] getExcptn2WarningTargets(String serviceCode,
			String channelEmail) {

		InterfaceThresholdQueryBean condition = new InterfaceThresholdQueryBean();
		condition.setChannel(channelEmail);
		condition.setSvcCode(serviceCode);
		condition.setType(InterfaceThresholdInfo.THRESHOLD_TYPE_EXCEPTION);

		// 查询到所有监控的设置
		InterfaceThresholdInfo thresholds = interfaceThresholdDao
				.getThresholdForStatusNoComplete(condition);
		if (thresholds == null) {
			return null;
		}

		String[] personIds = StringUtils.split(thresholds.getPersonId(),
				InterfaceThresholdInfo.PERSON_SEPARATOR);

		String[] emails = noticUserDao.queryEmailById(personIds);

		return emails;
	}

	public IEsbExceptionService getEsbExceptionService() {
		return esbExceptionService;
	}

	public void setEsbExceptionService(IEsbExceptionService esbExceptionService) {
		this.esbExceptionService = esbExceptionService;
	}

	public INoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public INoticUserDao getNoticUserDao() {
		return noticUserDao;
	}

	public void setNoticUserDao(INoticUserDao noticUserDao) {
		this.noticUserDao = noticUserDao;
	}

	public IinterfaceThresholdDao getInterfaceThresholdDao() {
		return interfaceThresholdDao;
	}

	public void setInterfaceThresholdDao(
			IinterfaceThresholdDao interfaceThresholdDao) {
		this.interfaceThresholdDao = interfaceThresholdDao;
	}

	public VelocityMailSupport getExcptn2MailSupport() {
		return excptn2MailSupport;
	}

	public void setExcptn2MailSupport(VelocityMailSupport excptn2MailSupport) {
		this.excptn2MailSupport = excptn2MailSupport;
	}

}
