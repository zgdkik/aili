package com.deppon.esb.management.alert.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.deppon.esb.management.alert.config.impl.ServiceConfigLoader;
import com.deppon.esb.management.alert.dao.INoticeDao;
import com.deppon.esb.management.alert.dao.IQueueThresholdDao;
import com.deppon.esb.management.alert.domain.NoticeInfo;
import com.deppon.esb.management.alert.domain.QueueThresholdInfo;
import com.deppon.esb.management.alert.service.IQueueService;
import com.deppon.esb.management.common.adapter.mail.VelocityMailSupport;
import com.deppon.esb.management.mq.domain.LocalQueueBean;
import com.deppon.esb.management.mq.domain.QueueManagerBean;
import com.deppon.esb.management.mq.service.IMqQueueService;
import com.deppon.esb.management.user.dao.INoticUserDao;

/**
 * 修改队列告警模块----将简单的队列深度告警标记为过时方法
 * 			  ----队列连接告警中数据来源从数据库中重新获取
 * 
 * @author bruce
 * @Date 2014-03-10
 */
public class QueueService implements IQueueService, InitializingBean {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QueueService.class);

	// ip、port、channel三个数组的大小必须一致
	@Deprecated
	private String[] ip;
	@Deprecated
	private int[] port;
	@Deprecated
	private String[] channel;

	private VelocityMailSupport quWrnMailSupport;
	private VelocityMailSupport quConnWrnMailSupport;

	private IQueueThresholdDao queueThresholdDao;

	private INoticUserDao noticUserDao;

	private INoticeDao noticeDao;

	private IMqQueueService mqQueueService;
	
	/**
	 * 数据库连接配置
	 */
	private ServiceConfigLoader configLoader;

	/**
	 * 队列深度告警
	 * 
	 * @author HuangHua
	 * @date 2013-5-4 下午8:59:44
	 * @see com.deppon.esb.management.alert.service.IQueueService#sendDepthWarning()
	 */
	@Override
	@Deprecated
	public void sendDepthWarning() throws Exception {
		String[] regQueueName = new String[ip.length];
		for (int i = 0; i < ip.length; i++) {
			regQueueName[i] = "QU*";
		}
		Map<String, List<LocalQueueBean>> queues = mqQueueService
				.listLocalQueues(ip, port, channel, regQueueName);
		// 通过查询阀值信息，确认哪些记录将发送通知
		List<Map<String, Object>> maps = filterOverThreshold(queues);
		if (!maps.isEmpty()) {// maps非空时执行
			for (Map<String, Object> map : maps) {
				if (map.get("email") instanceof String[]) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("send email！");
					}
					quWrnMailSupport.sendMime(map, (String[]) map.get("email"));
				} else {
					LOGGER.error("can not find email address which is must be String[]!");
				}
			}
		}
	}

	// 【DP_ESB队列预警通知】
	// 队列管理器${qmgr}中的队列${queue}的队列在${time}当前深度为${currentDepth}，
	// 已超过阀值${threshold},
	// 请注意查看ESB日志！
	/**
	 * 
	 * key--qmgrName value--待发送的信息
	 * 
	 * @author HuangHua
	 * @date 2013-5-4 下午5:47:31
	 */
	@Override
	@Deprecated
	public List<Map<String, Object>> filterOverThreshold(
			Map<String, List<LocalQueueBean>> maps) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		// 通过查询阀值信息，确认哪些记录将发送通知
		Set<Entry<String, List<LocalQueueBean>>> qmgrs = maps.entrySet();

		for (Entry<String, List<LocalQueueBean>> entry : qmgrs) {
			List<LocalQueueBean> queueBeans = entry.getValue();
			for (LocalQueueBean localQueueBean : queueBeans) {
				String queueName = localQueueBean.getName();
				String qmgrName = localQueueBean.getQmgrName();
				int currentDepth = localQueueBean.getCurrentDepth();
				Date time = localQueueBean.getCreateTime();
				List<QueueThresholdInfo> queueThresholdInfos = queueThresholdDao
						.findQuThrsldByQueueAndCrntDepth(queueName,
								localQueueBean.getCurrentDepth());
				if (queueThresholdInfos == null) {
					continue;
				}
				for (QueueThresholdInfo queueThresholdInfo : queueThresholdInfos) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("qmgr", qmgrName);
					map.put("queue", queueName);
					map.put("time", time);
					map.put("currentDepth", currentDepth);
					map.put("threshold", queueThresholdInfo.getThreshold());
					map.put("email", noticUserDao
							.queryEmailById(queueThresholdInfo.getPersonId()
									.split(",")));
					results.add(map);
				}
			}
		}
		return results;
	}

	/**
	 * 查询队列管理器连接异常时的告警人员
	 * 
	 * @author HuangHua
	 * @date 2013-5-10 下午3:33:37
	 */
	public List<Map<String, Object>> queryMqConnWarn() {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		List<QueueThresholdInfo> queueThresholdInfos = queueThresholdDao
				.findQmgrConn();
		for (QueueThresholdInfo queueThresholdInfo : queueThresholdInfos) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qmgr", queueThresholdInfo.getQmgr());
			map.put("time", new Date());
			map.put("email", noticUserDao.queryEmailById(queueThresholdInfo
					.getPersonId().split(",")));
			results.add(map);
		}
		return results;
	}

	/**
	 * ip、port、channel三个数组的大小必须一致
	 * 
	 * @author HuangHua
	 * @date 2013-5-4 下午5:08:37
	 */
	@Deprecated
	private void validate() {
		if (ip.length != port.length && port.length != channel.length) {
			throw new IllegalArgumentException(
					"QueueManager's ip,port,channel is a array,please check they length is same or not.");
		}
	}

	/**
	 * 连接告警
	 * 
	 * @author HuangHua
	 * @date 2013-5-4 下午5:57:33
	 * @see com.deppon.esb.management.alert.service.IQueueService#sendConnWarning()
	 */
	@Override
	public void sendConnWarning() throws Exception {
		if(configLoader == null){
			configLoader.refresh();
			return;
		}
		QueueManagerBean managerBean = configLoader.getManagerAll().get(0);
		// 查看所有的MQ连接情况
		Map<String, Boolean> maps = mqQueueService.checkConnection(managerBean.getIp(), managerBean.getPort(),
				managerBean.getChannel());
		List<Map<String, Object>> warnList = queryMqConnWarn();

		int count = warnList.size();
		for (int i = 0; i < count; i++) {
			if (i >= warnList.size()) {
				break;
			}
			if (maps.containsKey(warnList.get(i).get("qmgr"))) {
				warnList.remove(i);
				i--;
			}
		}
		List<Map<String, Object>> noticeList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> warn : warnList) {
			String qmgrName = (String) warn.get("qmgr");
			if (warn.get("email") instanceof String[]
					&& !maps.containsKey(qmgrName)) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("send email！");
				}
				quConnWrnMailSupport.sendMime(warn,
						(String[]) warn.get("email"));
				noticeList.add(warn);
			} else {
				LOGGER.error("can not find email address which is must be String[]!");
			}
		}
		saveNotice(noticeList);
	}

	/**
	 * 保存告警信息到数据库
	 * @author HuangHua
	 * @date 2013-5-13 上午10:01:32
	 */
	public void saveNotice(List<Map<String, Object>> noticeList) {
		List<NoticeInfo> noticeInfos = new ArrayList<NoticeInfo>();
		for (Map<String, Object> map : noticeList) {
			NoticeInfo noticeInfo = new NoticeInfo();
			noticeInfo.setSendTime(new Date());
			noticeInfo.setContent(map2String(map));
			noticeInfo.setStatisticsTime(null);
			noticeInfo.setSvcCode(null);
			noticeInfo.setType(NoticeInfo.NOTIC_TYPE_QUEUE);// 统计类型：0：性能，1：异常，2：队列
			noticeInfos.add(noticeInfo);
		}
		noticeDao.insertNotices(noticeInfos);
	}
	
	@Deprecated
	public String[] getIp() {
		return ip;
	}
	
	@Deprecated
	public void setIp(String[] ip) {
		this.ip = ip;
	}

	@Deprecated
	public int[] getPort() {
		return port;
	}

	@Deprecated
	public void setPort(int[] port) {
		this.port = port;
	}

	@Deprecated
	public String[] getChannel() {
		return channel;
	}

	@Deprecated
	public void setChannel(String[] channel) {
		this.channel = channel;
	}

	public VelocityMailSupport getQuWrnMailSupport() {
		return quWrnMailSupport;
	}

	public void setQuWrnMailSupport(VelocityMailSupport quWrnMailSupport) {
		this.quWrnMailSupport = quWrnMailSupport;
	}

	public IQueueThresholdDao getQueueThresholdDao() {
		return queueThresholdDao;
	}

	public void setQueueThresholdDao(IQueueThresholdDao queueThresholdDao) {
		this.queueThresholdDao = queueThresholdDao;
	}

	public INoticUserDao getNoticUserDao() {
		return noticUserDao;
	}

	public void setNoticUserDao(INoticUserDao noticUserDao) {
		this.noticUserDao = noticUserDao;
	}

	public IMqQueueService getMqQueueService() {
		return mqQueueService;
	}

	public void setMqQueueService(IMqQueueService mqQueueService) {
		this.mqQueueService = mqQueueService;
	}

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-5-4 下午9:16:52
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
//		validate();
	}

	public VelocityMailSupport getQuConnWrnMailSupport() {
		return quConnWrnMailSupport;
	}

	public void setQuConnWrnMailSupport(VelocityMailSupport quConnWrnMailSupport) {
		this.quConnWrnMailSupport = quConnWrnMailSupport;
	}

	/**
	 * 把map转换成字符串,方便在保存数据库的时候识别
	 * @author HuangHua
	 * @date 2013-5-13 上午9:55:52
	 */
	protected String map2String(Map<String, Object> map) {
		Set<Entry<String, Object>> entries = map.entrySet();
		StringBuffer buffer = new StringBuffer();
		for (Entry<String, Object> entry : entries) {
			buffer.append(entry.getKey());
			buffer.append(":");
			buffer.append(entry.getValue());
			buffer.append(", ");
		}
		if (buffer.length() > 0) {
			buffer.substring(0, buffer.length() - 2);
		}
		return buffer.toString();
	}

	public INoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public ServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	public void setConfigLoader(ServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

}
