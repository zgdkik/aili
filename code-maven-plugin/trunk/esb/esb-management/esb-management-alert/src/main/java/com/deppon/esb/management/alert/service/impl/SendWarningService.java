package com.deppon.esb.management.alert.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.alert.dao.INoticeDao;
import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.service.ISendWarningService;
import com.deppon.esb.management.alert.service.IWarningService;
import com.deppon.esb.management.common.adapter.mail.VelocityMailSupport;
import com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo;
import com.deppon.esb.management.excptn.service.IExcptnStatisticsService;
import com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo;
import com.deppon.esb.management.pfmc.service.IPfmcStatisticsService;
import com.deppon.esb.management.svccfg.domain.SvcPointInfo;
import com.deppon.esb.management.svccfg.service.ISvcpointService;

public class SendWarningService implements ISendWarningService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SendWarningService.class);
	private ISvcpointService svcpointService;
	private IWarningService warningService;
	private IPfmcStatisticsService pfmcStatisticsService;
	private IExcptnStatisticsService excptnStatisticsService;
	private INoticeDao noticeDao;
	private VelocityMailSupport pfmncWrnMailSupport;
	private VelocityMailSupport excptnWrnMailSupport;
	private VelocityMailSupport statusNoCompleteMailSupport;
	
	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-31 下午5:29:00
	 * @see com.deppon.esb.management.alert.service.ISendWarningService#sendExcptnStatistics()
	 */
	@Override
	public void sendExcptnStatistics() {
		LOGGER.info("send Warning starting...");
		List<ExcptnStatisticsInfo> excptnStatisticsInfos = excptnStatisticsService.queryExcptnStatistics();// 查出所有需要发送预警的记录
		if ( CollectionUtils.isNotEmpty(excptnStatisticsInfos)) {
			for (ExcptnStatisticsInfo excptnStatisticsInfo : excptnStatisticsInfos) {
				LOGGER.info("Start processing exception statistics information: " + excptnStatisticsInfo.getId());
				sendExcptnStatisticsOne(excptnStatisticsInfo);
				LOGGER.info("End processing exception statistics information: " + excptnStatisticsInfo.getId());
			}
		}
		
		LOGGER.info("send Warning ended...");
	}
	
	
	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-31 下午5:29:00
	 * @see com.deppon.esb.management.alert.service.ISendWarningService#sendExcptnStatisticsOne(com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo)
	 */
	@Override
	public void sendExcptnStatisticsOne(ExcptnStatisticsInfo excptnStatisticsInfo) {
		String serviceCode = excptnStatisticsInfo.getSvcCode();
		SvcPointInfo svcPointInfo = svcpointService.loadConfigBySvcCode(serviceCode);
		if ( svcPointInfo == null ) {
			LOGGER.error("Could not find service configuration: " + serviceCode);
			return ;
		}
		
		String svcName = svcPointInfo.getSvcName();// 获取服务名
		
		Set<String> emailTargets = warningService.getExceptionWarningTargets(
				excptnStatisticsInfo.getSvcCode(), 
				InterfaceThresholdInfo.CHANNEL_EMAIL
				);
		
		List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		
		if ( CollectionUtils.isNotEmpty(emailTargets) ) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("startTime", dateFormat.format(excptnStatisticsInfo.getStartTime()));
			model.put("endTime", dateFormat.format(excptnStatisticsInfo.getEndTime()));
			model.put("svcName", svcName);
			model.put("svcCode", excptnStatisticsInfo.getSvcCode());
			model.put("excptnCount", excptnStatisticsInfo.getExcptnCount());
			
			excptnWrnMailSupport.sendMime(model, emailTargets.toArray(new String[0]));// 发送邮件
			
			LOGGER.info("Successfully send exception warning email to: " + emailTargets);
			
			models.add(model);
		} else {
			LOGGER.info("There is no monitor for: " + excptnStatisticsInfo.getSvcCode() + ", " + InterfaceThresholdInfo.CHANNEL_EMAIL);
		}
		
		Set<String> fetionTargets = warningService.getExceptionWarningTargets(
				excptnStatisticsInfo.getSvcCode(), 
				InterfaceThresholdInfo.CHANNEL_FETION
				);
		if ( CollectionUtils.isNotEmpty(fetionTargets) ) {
			// send fetion warning message
		}
		
		Set<String> smsTargets = warningService.getExceptionWarningTargets(
				excptnStatisticsInfo.getSvcCode(), 
				InterfaceThresholdInfo.CHANNEL_SMS
				);
		if ( CollectionUtils.isNotEmpty(smsTargets) ) {
			// send SMS warning message
		}
		
		List<String> ids = new ArrayList<String>();
		ids.add(excptnStatisticsInfo.getId());
		
		excptnStatisticsService.markProcessed(ids);
		LOGGER.info("Successfully process exception statistics status for: " + ids);
		
		if ( CollectionUtils.isNotEmpty(models)) {
			List<ExcptnStatisticsInfo> statisticsInfos = new ArrayList<ExcptnStatisticsInfo>();
			statisticsInfos.add(excptnStatisticsInfo);
			
			noticeDao.insertExcptnStatistics(statisticsInfos);
			LOGGER.info("Successfully insert notices data for: " + excptnStatisticsInfo.getId());
		}
	}
	
	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-31 下午5:29:00
	 * @see com.deppon.esb.management.alert.service.ISendWarningService#sendPerformanceWarning()
	 */
	@Override
	public boolean sendPerformanceWarning() {
		List<PfmcStatisticsInfo> statisticsInfos = pfmcStatisticsService.queryPfmcStatistics2Notice();
		
		for (PfmcStatisticsInfo pfmcStatisticsInfo : statisticsInfos) {
			LOGGER.info("Start sending warning for: " + pfmcStatisticsInfo.getId());
			sendPerformanceWarningOne(pfmcStatisticsInfo);
			LOGGER.info("End sending warning for: " + pfmcStatisticsInfo.getId());
		}
		
		return true;
	}
	
	
	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-31 下午5:29:00
	 * @see com.deppon.esb.management.alert.service.ISendWarningService#sendPerformanceWarningOne(com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo)
	 */
	@Override
	public void sendPerformanceWarningOne(PfmcStatisticsInfo pfmcStatisticsInfo){
		String serviceCode = pfmcStatisticsInfo.getSvcCode();
		String svcName = svcpointService.getSvcpointBySvcCode(serviceCode).getSvcName();// 获取服务名
		
		LOGGER.info("Query monitors for: " + serviceCode + ", " + 
				InterfaceThresholdInfo.CHANNEL_EMAIL + ", " +
				pfmcStatisticsInfo.getAvgRspTime());
		
		Map<Integer, Set<String>> emailTargets = warningService.getPerformanceWarningTargets(
				serviceCode, 
				InterfaceThresholdInfo.CHANNEL_EMAIL, 
				pfmcStatisticsInfo.getAvgRspTime());
		
		List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		
		if ( MapUtils.isNotEmpty(emailTargets) ) {
			Set<Entry<Integer, Set<String>>> entrys = emailTargets.entrySet();
			for(Entry<Integer, Set<String>> entry: entrys) {
				Integer threshold = entry.getKey();
				LOGGER.info("Thredholds: " + threshold);
				
				Set<String> emails = entry.getValue();
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("svcName", svcName);
				model.put("svcCode", pfmcStatisticsInfo.getSvcCode());
				model.put("startTime", dateFormat.format(pfmcStatisticsInfo.getStartTime()));
				model.put("endTime", dateFormat.format(pfmcStatisticsInfo.getEndTime()));
				model.put("avgTime", pfmcStatisticsInfo.getAvgRspTime());
				model.put("threshold", threshold);// 查询阀值信息，并放入map中
				model.put("maxrespTime", pfmcStatisticsInfo.getMaxRspTime());
				
				pfmncWrnMailSupport.sendMime(model, emails.toArray(new String[0]));// 发送邮件
				
				LOGGER.info("Successfully send email to: " + emails);
				
				models.add(model);
			}
		} else {
			LOGGER.info("There is no monitor!");
		}
		
		Map<Integer, Set<String>> fetionTargets = warningService.getPerformanceWarningTargets(
				pfmcStatisticsInfo.getSvcCode(), 
				InterfaceThresholdInfo.CHANNEL_FETION, 
				pfmcStatisticsInfo.getAvgRspTime());
		if ( MapUtils.isNotEmpty(fetionTargets) ) {
			// send fetion warning message
		}
		
		Map<Integer, Set<String>> smsTargets = warningService.getPerformanceWarningTargets(
				pfmcStatisticsInfo.getSvcCode(), 
				InterfaceThresholdInfo.CHANNEL_SMS, 
				pfmcStatisticsInfo.getAvgRspTime());
		if ( MapUtils.isNotEmpty(smsTargets) ) {
			// send SMS warning message
		}
		
		List<String> ids = new ArrayList<String>();
		ids.add(pfmcStatisticsInfo.getId());
		
		pfmcStatisticsService.updatePfmcStatistics(ids);
		LOGGER.info("Successfully update performance statistics status for: " + ids);
		
		if ( CollectionUtils.isNotEmpty(models)) {
			List<PfmcStatisticsInfo> statisticsInfos = new ArrayList<PfmcStatisticsInfo>();
			statisticsInfos.add(pfmcStatisticsInfo);
			
			noticeDao.insertPfmcStatistics(statisticsInfos, models);
			LOGGER.info("Successfully insert notices data for: " + pfmcStatisticsInfo.getId());
		}
	}
	
	public IWarningService getWarningService() {
		return warningService;
	}

	public void setWarningService(IWarningService warningService) {
		this.warningService = warningService;
	}


	public IPfmcStatisticsService getPfmcStatisticsService() {
		return pfmcStatisticsService;
	}


	public void setPfmcStatisticsService(
			IPfmcStatisticsService pfmcStatisticsService) {
		this.pfmcStatisticsService = pfmcStatisticsService;
	}


	public IExcptnStatisticsService getExcptnStatisticsService() {
		return excptnStatisticsService;
	}


	public void setExcptnStatisticsService(
			IExcptnStatisticsService excptnStatisticsService) {
		this.excptnStatisticsService = excptnStatisticsService;
	}


	public INoticeDao getNoticeDao() {
		return noticeDao;
	}


	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}


	public VelocityMailSupport getPfmncWrnMailSupport() {
		return pfmncWrnMailSupport;
	}


	public void setPfmncWrnMailSupport(VelocityMailSupport pfmncWrnMailSupport) {
		this.pfmncWrnMailSupport = pfmncWrnMailSupport;
	}


	public VelocityMailSupport getExcptnWrnMailSupport() {
		return excptnWrnMailSupport;
	}


	public void setExcptnWrnMailSupport(VelocityMailSupport excptnWrnMailSupport) {
		this.excptnWrnMailSupport = excptnWrnMailSupport;
	}


	public VelocityMailSupport getStatusNoCompleteMailSupport() {
		return statusNoCompleteMailSupport;
	}


	public void setStatusNoCompleteMailSupport(
			VelocityMailSupport statusNoCompleteMailSupport) {
		this.statusNoCompleteMailSupport = statusNoCompleteMailSupport;
	}

	public ISvcpointService getSvcpointService() {
		return svcpointService;
	}

	public void setSvcpointService(ISvcpointService svcpointService) {
		this.svcpointService = svcpointService;
	}
}
