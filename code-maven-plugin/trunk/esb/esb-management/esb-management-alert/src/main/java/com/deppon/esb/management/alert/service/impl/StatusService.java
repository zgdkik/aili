/*
 * PROJECT NAME: esb-management-alert
 * PACKAGE NAME: com.deppon.esb.management.alert.service.impl
 * FILE    NAME: StatusService.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.alert.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.deppon.esb.management.alert.dao.INoticeDao;
import com.deppon.esb.management.alert.dao.IinterfaceThresholdDao;
import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.domain.NoticeInfo;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean;
import com.deppon.esb.management.alert.service.IStatusService;
import com.deppon.esb.management.common.adapter.mail.VelocityMailSupport;
import com.deppon.esb.management.status.domain.EsbRouteInfo;
import com.deppon.esb.management.status.domain.EsbStatusInfo;
import com.deppon.esb.management.status.service.IEsbStatusService;
import com.deppon.esb.management.user.dao.INoticUserDao;

/**
 * 状态日志Service
 * 
 * @author HuangHua
 * @date 2013-5-11 下午4:34:25
 */
public class StatusService implements IStatusService {
	
	private static final String ST999 = "ST_999";

	/**
	 * 邮件模板
	 */
	private VelocityMailSupport statusNoCompleteMailSupport;

	/**
	 * 告警信息Dao,用于保存告警信息
	 */
	private INoticeDao noticeDao;
	
	private INoticUserDao noticUserDao ;

	private IEsbStatusService esbStatusService;
	
	private IinterfaceThresholdDao interfaceThresholdDao;

	/**
	 * 单个发送状态不完整告警(不包括分发的服务)
	 * @author HuangHua
	 * @date 2013-5-11 下午4:34:33
	 * @see com.deppon.esb.management.alert.service.IStatusService#sendStatusNoCompleteWarnOne(com.deppon.esb.management.common.entity.jms.header.EsbHeader,
	 *      java.lang.String, long)
	 */
	@Override
	public List<NoticeInfo> sendStatusNoCompleteWarnOne(EsbStatusInfo statusInfo) {
		Map<String, Object> model = new HashMap<String, Object>();
		StringBuffer content = new StringBuffer();
		NoticeInfo noticeInfo = new NoticeInfo();

		String esbServiceCode = statusInfo.getEsbServiceCode();
		String backServiceCode = statusInfo.getBackServiceCode();
		String requestId = statusInfo.getRequestId();
		String responseId = statusInfo.getResponseId();
		String statusId = statusInfo.getStatusInfo().getStatusId();
		long time = statusInfo.getStatusInfo().getTimeStamp();
		
		model.put("esbServiceCode", esbServiceCode);
		content.append("esbServiceCode:");
		content.append(esbServiceCode);
		model.put("backServiceCode", backServiceCode);
		content.append(",backServiceCode:");
		content.append(backServiceCode);
		model.put("requestId", requestId);
		content.append(",requestId:");
		content.append(requestId);
		model.put("responseId", responseId);
		content.append(",requestId:");
		content.append(requestId);
		model.put("statusId", statusId);
		content.append(",statusId:");
		content.append(statusId);
		Date date = new Date(time);
		model.put("statusTimeStamp", date);
		content.append(",statusTimeStamp:");
		content.append(date);
		String[] emails = getStatusNoCompleteWarningTargets(esbServiceCode, InterfaceThresholdInfo.CHANNEL_EMAIL);
		if(emails == null){
			return null;
		}
		statusNoCompleteMailSupport.sendMime(model, emails);
		noticeInfo.setSendTime(new Date());
		noticeInfo.setSvcCode(esbServiceCode);
		noticeInfo.setContent(content.toString());
		noticeInfo.setStatisticsTime(new Date());
		noticeInfo.setType(NoticeInfo.NOTIC_TYPE_STATUS);
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
	public String[] getStatusNoCompleteWarningTargets(String serviceCode, String channelEmail) {

		InterfaceThresholdQueryBean condition = new InterfaceThresholdQueryBean();
		condition.setChannel(channelEmail);
		condition.setSvcCode(serviceCode);
		condition.setType(InterfaceThresholdInfo.THRESHOLD_TYPE_STATUS);
		
		// 查询到所有监控的设置
		InterfaceThresholdInfo thresholds = interfaceThresholdDao.getThresholdForStatusNoComplete(condition);
		if(thresholds == null){
			return null;
		}

		String[] personIds = StringUtils.split(thresholds.getPersonId(), InterfaceThresholdInfo.PERSON_SEPARATOR);
		
		String [] emails = noticUserDao.queryEmailById(personIds);
		
		return emails;
	}

	/**
	 * 发送状态不完整告警(没有999的情况)
	 * @author HuangHua
	 * @date 2013-5-13 上午10:22:38
	 */
	@Override
	public void sendStatusNoCompleteWarn() {
		List<EsbStatusInfo> esbStatusInfos = esbStatusService
				.getNotCompleteRecords(1);
		List<EsbStatusInfo> esbStatusInfosToSend = filterProcess(esbStatusInfos);
		
		List<NoticeInfo> noticeInfos = new ArrayList<NoticeInfo>();
		List<String> requestIds = new ArrayList<String>();
		//发送告警信息
		for (EsbStatusInfo statusInfo : esbStatusInfosToSend) {
			List<NoticeInfo> infos = sendStatusNoCompleteWarnOne(statusInfo);
			if(infos != null){
				noticeInfos.addAll(infos);
			}
			requestIds.add(statusInfo.getRequestId());
		}
		//保存告警信息
		if (noticeInfos != null) {
			if (noticeInfos.size() > 0) {
				noticeDao.insertNotices(noticeInfos);
			}
		}
		
		//拿出所有id
		List<String> ids = new ArrayList<String>();
		for (EsbStatusInfo esbStatusInfo : esbStatusInfos) {
			if(requestIds.contains(esbStatusInfo.getRequestId())){
				ids.add(esbStatusInfo.getId());
			}
		}
		esbStatusService.updateStatusByIds(ids,2);
	}
	
	
	/**
	 * 发送状态不完整告警(分发中有部分999的情况)
	 
	 * @author HuangHua
	 * @date 2013-5-15 上午10:07:11
	 */
	@Override
	public void sendMultiStatusNoCompleteWarn(){
		List<EsbStatusInfo> esbStatusInfos = esbStatusService
				.getMultiNotCompleteRecords(1);
		List<EsbStatusInfo> esbStatusInfosToSend = filterMultiProcess(esbStatusInfos);
		
		//发送告警信息
		List<NoticeInfo> noticeInfos = new ArrayList<NoticeInfo>();
		List<String> requestIds = new ArrayList<String>();
		for (EsbStatusInfo statusInfo : esbStatusInfosToSend) {
			statusInfo.getStatusInfo().setStatusId("");//因为获取的不一定是最新的状态,所以直接置空
			List<NoticeInfo> infos = sendStatusNoCompleteWarnOne(statusInfo);
			if(infos != null){
				noticeInfos.addAll(infos);
			}
			requestIds.add(statusInfo.getRequestId());
		}
		//保存告警信息
		if (noticeInfos != null) {
			if (noticeInfos.size() > 0) {
				noticeDao.insertNotices(noticeInfos);
			}
		}
		
		//拿出所有id
		List<String> ids = new ArrayList<String>();
		for (EsbStatusInfo esbStatusInfo : esbStatusInfos) {
			if(requestIds.contains(esbStatusInfo.getRequestId())){
				ids.add(esbStatusInfo.getId());
			}
		}
		esbStatusService.updateStatusByIds(ids, 2);
	}

	/**
	 * 选出最新的状态,并去掉999状态
	 * 
	 * @author HuangHua
	 * @date 2013-5-14 上午10:57:24
	 */
	protected List<EsbStatusInfo> filterProcess(List<EsbStatusInfo> esbStatusInfos) {
		List<EsbStatusInfo> infos = new ArrayList<EsbStatusInfo>();
		
		Map<String, EsbStatusInfo> swapMap = new HashMap<String, EsbStatusInfo>();
		
		for (EsbStatusInfo esbStatusInfo1 : esbStatusInfos) {
			for (EsbStatusInfo esbStatusInfo2 : esbStatusInfos) {
				int statusId1 = Integer.parseInt(esbStatusInfo1.getStatusInfo().getStatusId().substring(3));
				int statusId2 = Integer.parseInt(esbStatusInfo2.getStatusInfo().getStatusId().substring(3));
				if(esbStatusInfo1.getRequestId().equals(esbStatusInfo2.getRequestId())){
					if(swapMap.containsKey(esbStatusInfo1.getRequestId())){
						String swapStatusId = swapMap.get(esbStatusInfo1.getRequestId()).getStatusInfo().getStatusId();
						int swapSid = Integer.parseInt(swapStatusId.substring(3));
						if(swapSid < statusId2){
							swapMap.put(esbStatusInfo1.getRequestId(),esbStatusInfo2);
						}
					} else {
						swapMap.put(esbStatusInfo1.getRequestId(),statusId1 > statusId2?esbStatusInfo1:esbStatusInfo2);
					}
				}
			}
		}
		
		Set<Entry<String, EsbStatusInfo>> entries = swapMap.entrySet();
		
		for (Entry<String, EsbStatusInfo> entry : entries) {
			if(!"ST_999".equals(entry.getValue().getStatusInfo().getStatusId())){
				infos.add(entry.getValue());
			}
		}
		
		return infos;
	}

	/**
	 * 找出999状态不完全的请求,返回的状态不一定是最新的状态
	 * 
	 * @author HuangHua
	 * @date 2013-5-15 上午11:49:02
	 */
	protected List<EsbStatusInfo> filterMultiProcess(
			List<EsbStatusInfo> esbStatusInfos) {
		List<EsbRouteInfo> esbRouteInfos = esbStatusService.queryMultiRoute();
		
		// key-esbServiceCode,value-targetCode(s)
		Map<String, List<String>> routeMap = new HashMap<String, List<String>>();
		for (EsbRouteInfo esbRouteInfo : esbRouteInfos) {
			String serviceCode = esbRouteInfo.getEsbServiceCode();
			if (routeMap.containsKey(serviceCode)) {
				routeMap.get(serviceCode).add(
						esbRouteInfo.getTargetServiceCode());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(esbRouteInfo.getTargetServiceCode());
				routeMap.put(serviceCode, list);
			}
		}
		
		//key-requestId,value-statusInfo(s)
		Map<String, List<EsbStatusInfo>> statusMap = new HashMap<String, List<EsbStatusInfo>>();
		for (EsbStatusInfo esbStatusInfo : esbStatusInfos) {
			String requestId = esbStatusInfo.getRequestId();
			if(statusMap.containsKey(requestId)){
				statusMap.get(requestId).add(esbStatusInfo);
			} else {
				List<EsbStatusInfo> list = new ArrayList<EsbStatusInfo>();
				list.add(esbStatusInfo);
				statusMap.put(requestId, list);
			}
		}
		
		List<EsbStatusInfo> resultList = new ArrayList<EsbStatusInfo>();
		//找出999状态不完全的请求
		Set<Entry<String, List<EsbStatusInfo>>> statEntries = statusMap.entrySet();
		for (Entry<String, List<EsbStatusInfo>> entry : statEntries) {
			List<EsbStatusInfo> statusInfos = entry.getValue();
			if(statusInfos.size()>=0){
				break;
			}
			String serviceCode = statusInfos.get(0).getEsbServiceCode();
			List<String> routes = routeMap.get(serviceCode);
			for (String tarCode : routes) {
				if(!check999(tarCode, statusInfos)){
					resultList.add(statusInfos.get(0));
				}
			}
		}

		return resultList;
	}
	
	/**
	 * 查看该目标服务编码是否有999状态
	 * @author HuangHua
	 * @date 2013-5-15 下午4:18:13
	 */
	protected boolean check999(String tarCode, List<EsbStatusInfo> list) {
		for (EsbStatusInfo esbStatusInfo : list) {
			if(tarCode.equals(esbStatusInfo.getBackServiceCode()) && ST999.equals(esbStatusInfo.getStatusInfo().getStatusId())){
				return true;
			}
		}
		return false;
	}
	
	public VelocityMailSupport getStatusNoCompleteMailSupport() {
		return statusNoCompleteMailSupport;
	}

	public void setStatusNoCompleteMailSupport(
			VelocityMailSupport statusNoCompleteMailSupport) {
		this.statusNoCompleteMailSupport = statusNoCompleteMailSupport;
	}

	public INoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public IEsbStatusService getEsbStatusService() {
		return esbStatusService;
	}

	public void setEsbStatusService(IEsbStatusService esbStatusService) {
		this.esbStatusService = esbStatusService;
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
	
}
