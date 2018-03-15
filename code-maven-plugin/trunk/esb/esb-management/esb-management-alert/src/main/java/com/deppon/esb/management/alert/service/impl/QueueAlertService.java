package com.deppon.esb.management.alert.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.alert.config.IServiceConfigLoader;
import com.deppon.esb.management.alert.domain.QueueAlertInfo;
import com.deppon.esb.management.alert.service.IQueueAlertService;
import com.deppon.esb.management.common.adapter.mail.VelocityMailSupport;
import com.deppon.esb.management.common.util.DataTypeAdapter;
import com.deppon.esb.management.mq.domain.LocalQueueBean;
import com.deppon.esb.management.mq.domain.QueueManagerBean;
import com.deppon.esb.management.mq.service.IMqQueueService;

/**
 * 队列告警
 * 
 * @author bruce_x
 * 
 */
@Repository
public class QueueAlertService implements IQueueAlertService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QueueAlertService.class);

	private static Map<String, List<Integer>> compares = new HashMap<String, List<Integer>>();

	private VelocityMailSupport queueAlertMailSupport;

	private IMqQueueService mqQueueService;

	private IServiceConfigLoader configLoader;

	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

	public void setQueueAlertMailSupport(
			VelocityMailSupport queueAlertMailSupport) {
		this.queueAlertMailSupport = queueAlertMailSupport;
	}

	public void setMqQueueService(IMqQueueService mqQueueService) {
		this.mqQueueService = mqQueueService;
	}

	@Override
	public void earlyQueueAlert() {
		if(configLoader == null){
			configLoader.refresh();
			return;
		}
		QueueManagerBean managerBean = configLoader.getManagerAll().get(0); // 重配置信息里面获取连接队列管理器信息
		Map<String, List<LocalQueueBean>> queues = mqQueueService
				.listLocalQueues(managerBean.getIp(), managerBean.getPort(),
						managerBean.getChannel(), managerBean.getQueueNameReg()); // 获取监控的队列当前数据信息
		List<Map<String, Object>> maps = filterOverThreshold(queues); // 需要告警的信息集合
		if (!maps.isEmpty()) {// maps非空时执行
			for (Map<String, Object> map : maps) {
				if (map.get("email") instanceof String[]) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("send email！");
					}
					queueAlertMailSupport.sendMime(map,
							(String[]) map.get("email"));
				} else {
					LOGGER.error("can not find email address which is must be String[]!");
				}
			}
		}
	}

	/**
	 * 根据告警阀值和每次监听的波动值去获取需要告警的队列信息和告警地址
	 * 
	 * @param queues
	 * @return
	 */
	private List<Map<String, Object>> filterOverThreshold(
			Map<String, List<LocalQueueBean>> queues) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		Set<Entry<String, List<LocalQueueBean>>> qmgrs = queues.entrySet();
		for (Entry<String, List<LocalQueueBean>> entry : qmgrs) {
			List<LocalQueueBean> queueBeans = entry.getValue();
			for (LocalQueueBean localQueueBean : queueBeans) {
				//获取当前队列管理的ip地址
				String ip = localQueueBean.getId();
				//获取当前队列的队列名称
				String queueName = localQueueBean.getName();
				//获取当前队列的队列管理器名称
				String qmgrName = localQueueBean.getQmgrName();
				//组装唯一的key为下次信息做判断(ip+queueName+qmgrName)
				String key = ip + queueName + qmgrName;
				//获取当前队列深度
				int currentDepth = localQueueBean.getCurrentDepth();
				//获取当前队列的 更新时间
				Date time = localQueueBean.getCreateTime();
				//通过队列名名称去服务配置里面加载需要告警的队列
				List<QueueAlertInfo> alertInfos = configLoader.get(queueName);
				//当为null时，进行下一轮的判断
				if (alertInfos == null) {
					continue;
				}
				//不为null时，获取告警数据源源
				QueueAlertInfo queueThresholdInfos = alertInfos.get(0);
				//当前的队列深度还未超多阀值不进行告警
				if (!(currentDepth > queueThresholdInfos.getThresholdInfo()
						.getThreshold())) {
					continue;
				}
				//当队列深度逐渐递增到某段值的时候就会出现报警
				if(currentDepth >= queueThresholdInfos.getThresholdInfo().getMaxDepth()){
					results.add(groupEmail(qmgrName, queueName, DataTypeAdapter.printFormatTime(time), currentDepth, queueThresholdInfos.getThresholdInfo().getMaxDepth(), queueThresholdInfos.getThresholdInfo().getVolatility(), queueThresholdInfos.getEmailAdder()));
				}
				//超多阀值的时候,对信息进行一次记录;查看是否已经存在配置
				if (compares.containsKey(key)) {
					//当不存在的时候,对队列深度进行在一次记录
					compares.get(key).add(currentDepth);
					//获取波动值范围[2~30]
					int size = queueThresholdInfos.getThresholdInfo()
							.getComparetime();
					//比较当前记录的队列深度次数超过波动值时
					if (compares.get(key).size() >= size) {
						//对每次记录的队列深度进行判断
						if (compareDepth(key, queueThresholdInfos
								.getThresholdInfo().getVolatility())) {
							//封装在返回信息里面
							results.add(groupEmail(qmgrName, queueName, DataTypeAdapter.printFormatTime(time), currentDepth, queueThresholdInfos.getThresholdInfo().getThreshold(), queueThresholdInfos.getThresholdInfo().getVolatility(), queueThresholdInfos.getEmailAdder()));
						}
					}
				} else {
					//创建新的队列深度集合
					List<Integer> list = new ArrayList<Integer>();
					//将本次的队列深度添加到集合里面
					list.add(currentDepth);
					//将集合放在key为(ip+queueName+qmgrName)里面,下次如何发现已存在key的时候直接在list里面添加元素
					compares.put(key, list);
				}
			}
		}
		return results;
	}

	/**
	 * 比较在波动值内的数据值的趋势
	 * 
	 * @param key
	 *            通过key找到相应的vaule
	 * @param volatility
	 *            趋势幅度
	 * @return
	 */
	private boolean compareDepth(String key, int volatility) {
		// 构造一个标识符(默认为:false)
		boolean isWarn = false;
		// 获取记录的队列深度集合
		List<Integer> integers = compares.get(key);
		// 对集合里面的每个值进行逐一比较
		for (int i = 1; i < integers.size(); i++) {
			// 使用前1个值与后1个值作差比较>趋势幅度则返回true,反之false
			isWarn = (int) integers.get(i) - (int) integers.get(i - 1) >= volatility ? true
					: false;
			// 当第一次比较已经是false的时候,说明队列深度还未能达到持续递增状态
			if (!isWarn) {
				// 移除记录的第一个元素
				integers.remove(0);
				// 返回false告知本次还未达到告警通知
				return false;
			}
		}
		// 当差值达到了趋势幅度,首先将第一个元素移除
		integers.remove(0);
		// 返回true告知本次需要发送告知管理员进行处理.
		return isWarn;
	}

	private Map<String, Object> groupEmail(String qmgr, String queue,
			String time, int currentDepth, int threshold, int volatility,
			String[] email) {
		// 当发现需要告警的时候,封装告警信息
		Map<String, Object> map = new HashMap<String, Object>();
		// 告警的队列管理器
		map.put("qmgr", qmgr);
		// 告警的队列
		map.put("queue", queue);
		// 告警最后一次队列上涨的时间
		map.put("time", time);
		// 告警队列当前的深度值
		map.put("currentDepth", currentDepth);
		// 告警设置的队列阀值
		map.put("threshold", threshold);
		// 告警设置的幅度
		map.put("volatility", volatility);
		// 告警的地址数据组
		map.put("email", email);
		return map;
	}

}
