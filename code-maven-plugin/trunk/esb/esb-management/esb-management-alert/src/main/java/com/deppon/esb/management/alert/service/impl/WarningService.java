package com.deppon.esb.management.alert.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.esb.management.alert.dao.IinterfaceThresholdDao;
import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean;
import com.deppon.esb.management.alert.service.IWarningService;
import com.deppon.esb.management.user.dao.INoticUserDao;
import com.deppon.esb.management.user.domain.NoticUserInfo;

public class WarningService implements IWarningService {
	private static final Log log = LogFactory.getLog(WarningService.class);
	
	private IinterfaceThresholdDao interfaceThresholdDao;
	
	private INoticUserDao noticeUserDao;
	
	public IinterfaceThresholdDao getInterfaceThresholdDao() {
		return interfaceThresholdDao;
	}

	public void setInterfaceThresholdDao(
			IinterfaceThresholdDao interfaceThresholdDao) {
		this.interfaceThresholdDao = interfaceThresholdDao;
	}

	public INoticUserDao getNoticeUserDao() {
		return noticeUserDao;
	}

	public void setNoticeUserDao(INoticUserDao noticeUserDao) {
		this.noticeUserDao = noticeUserDao;
	}

	/**
	 * 查询到待发送监控信息的列表；
	 * key为对应服务的已设置的监控配置的阀值；
	 * value为待发送的渠道地址列表，比如邮件地址；
	 * 
	 * @param serviceCode 服务编码
	 * @param channel 渠道，参考InterfaceThresholdInfo中的常量，目前有三类：短信，飞信以及邮件地址；
	 * @return
	 */
	public Set<String> getExceptionWarningTargets(String serviceCode, String channel) {
		InterfaceThresholdQueryBean condition = new InterfaceThresholdQueryBean();
		condition.setSvcCode(serviceCode);
		condition.setChannel(channel);
		
		// 查询到所有监控的设置
		List<InterfaceThresholdInfo> thresholds = interfaceThresholdDao.getThresholdForException(condition);
		
		Set<String> targets = new HashSet<String>();
		if ( CollectionUtils.isNotEmpty(thresholds)) {
			for(InterfaceThresholdInfo threshold: thresholds) {
				String strPersonId = threshold.getPersonId();
				
				// 拆分personid字段；
				String[] personIds = StringUtils.split(strPersonId, InterfaceThresholdInfo.PERSON_SEPARATOR);
				if ( personIds != null ) {
					CollectionUtils.addAll(targets, personIds);
				}
			}
		}
		
		Set<String> results = new HashSet<String>();
		if ( CollectionUtils.isNotEmpty(targets) ) {
			for(String id: targets) {
				String channelAddress = getChannelAddress(id, channel);
				if ( StringUtils.isNotBlank(channelAddress) )
					results.add(channelAddress);
			}
		}
		return results;
	}
	
	/**
	 * 查询到待发送性能监控信息的列表；
	 * key为对应服务的已设置的监控配置的阀值；
	 * value为待发送的渠道地址列表，比如邮件地址；
	 * 
	 * @param serviceCode 服务编码
	 * @param channel 渠道，参考InterfaceThresholdInfo中的常量，目前有三类：短信，飞信以及邮件地址；
	 * @param timecost 对应服务在某段时间内的平均响应时间，用于和对应监控设置的threshold进行对比；
	 * @return
	 */
	public Map<Integer, Set<String>> getPerformanceWarningTargets(String serviceCode, String channel, Long timecost) {
		InterfaceThresholdQueryBean condition = new InterfaceThresholdQueryBean();
		condition.setSvcCode(serviceCode);
		condition.setChannel(channel);
		condition.setThreshold(timecost.intValue());
		
		// 查询到所有监控的设置
		List<InterfaceThresholdInfo> thresholds = interfaceThresholdDao.getThresholdForPerformance(condition);

		// key为监控人员的id，value为阀值；
		// 对于同样一组监控（服务编码+渠道），如果对于同一个人配置了不同的阀值，那么需要取最小的阀值;
		// 避免对于同一个人发送多个告警信息；
		Map<String, Integer> persons = new HashMap<String, Integer>();
		if ( CollectionUtils.isNotEmpty(thresholds) ) {
			// 遍历每个符合条件的监控设置；
			for(InterfaceThresholdInfo info: thresholds) {
				String strPersonId = info.getPersonId();
				
				// 拆分personid字段；
				String[] personIds = StringUtils.split(strPersonId, InterfaceThresholdInfo.PERSON_SEPARATOR);
				
				// 把每个person对应的最小的监控阀值放入map中；
				if ( personIds != null ) {
					for(int i=0; i<personIds.length; i++) {
						Integer previousThreshold = (Integer)persons.get(personIds[i]);
						if ( previousThreshold != null ) {
							if ( info.getThreshold() < previousThreshold ) {
								// 当前的阀值小于map里的值，替换之；
								persons.put(personIds[i], info.getThreshold());
							}
						} else {
							persons.put(personIds[i], info.getThreshold());
						}
					}
				}
			}
		}
		
		// 把查询到的结果转化成另外的map，key为阀值，value为监控人员的对应渠道的接入地址，比如邮件地址；
		Map<Integer, Set<String>> results = new HashMap<Integer, Set<String>>();
		
		Set<Entry<String, Integer>> entries = persons.entrySet();
		for(Entry<String, Integer> entry: entries) {
			String personId = entry.getKey();
			Integer threshold = entry.getValue();
			
			Set<String> p = results.get(threshold);
			NoticUserInfo user = noticeUserDao.queryNoticeUser(personId);
			if ( user == null )
				continue;
			
			String channelAddress = getChannelAddress(user.getId(), channel);
			if ( CollectionUtils.isNotEmpty(p) ) {
				if ( StringUtils.isNotBlank(channelAddress) ) {
					p.add(channelAddress);
				}
				
				results.put(threshold, p);
			} else {
				Set<String> s = new HashSet<String>();
				
				if ( StringUtils.isNotBlank(channelAddress) ) {
					s.add(channelAddress);
				}
				
				results.put(threshold, s);
			}
		}
		
		return results;
	}
	private String getChannelAddress(String userId, String channel) {
		NoticUserInfo user = noticeUserDao.queryNoticeUser(userId);
		if ( user != null ) {
			if ( InterfaceThresholdInfo.CHANNEL_EMAIL.equals(channel) ) {
				return user.getEmail();
			}
			
			if ( InterfaceThresholdInfo.CHANNEL_FETION.equals(channel)) {
				// 
			}
			
			if ( InterfaceThresholdInfo.CHANNEL_SMS.equals(channel) ) {
				return user.getMobilePhone();
			}
		} else {
			log.error("Could not find user by id: " + userId);
		}
		return null;
	}

}
