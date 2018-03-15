package com.deppon.esb.management.alert.config.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.alert.config.IServiceConfigLoader;
import com.deppon.esb.management.alert.dao.IQueueThresholdDao;
import com.deppon.esb.management.alert.domain.QueueAlertInfo;
import com.deppon.esb.management.alert.domain.QueueThresholdInfo;
import com.deppon.esb.management.mq.dao.IMqManagerDao;
import com.deppon.esb.management.mq.domain.QueueManagerBean;
import com.deppon.esb.management.mq.domain.QueueManagerInfo;
import com.deppon.esb.management.user.dao.INoticUserDao;
import com.deppon.esb.management.user.domain.NoticUserInfo;

/**
 * 通过Spring的InitializingBean加载配置信息
 * 
 * @author bruce_x
 */

public class ServiceConfigLoader implements IServiceConfigLoader {

	/** LOGGER. */
	public static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceConfigLoader.class);

	/** 告警队列信息缓存. */
	private static Map<String, List<QueueAlertInfo>> queueConfig;

	/** 连接队列管理器信息缓存。 */
	private static List<QueueManagerBean> queueManagerBeans;

	public List<QueueManagerBean> getManagerAll() {
		return queueManagerBeans;
	}

	@Resource
	private IQueueThresholdDao queueThresholdDao;

	@Resource
	private INoticUserDao noticUserDao;

	@Resource
	private IMqManagerDao managerDao;

	/**
	 * 默认构造方法.
	 * 
	 * @author bruce_x
	 * @date 2013-10-22 上午09:00:23
	 */
	private ServiceConfigLoader() {
	}

	/**
	 * 调用这个方法会触发静态代码块，完成初始化.
	 * 
	 * @author bruce_x
	 * @date 2013-10-22 上午09:00:27
	 */
	public void start() {
		LOGGER.info("Refresh the queue configuration successfully");
	}

	/**
	 * 通过队列名称获取待告警信息，如果对应的配置信息只有一个（P2P）,可以调用之后用get(0)来拿到配置信息.
	 * 
	 * @param queueName
	 *            队列名称
	 * @return the list
	 */
	public List<QueueAlertInfo> get(String queueName) {
		return queueConfig.get(queueName);
	}

	/**
	 * 返回当前配置.
	 * 
	 * @return the all
	 */
	public Map<String, List<QueueAlertInfo>> getAlertAll() {
		return queueConfig;
	}

	/**
	 * 初始化实现.
	 * 
	 * @author bruce_x
	 * @date 2013-10-22 上午09:05:36
	 */
	private synchronized void init() {
		// 告警队列信息缓存
		Map<String, List<QueueAlertInfo>> configMap = new HashMap<String, List<QueueAlertInfo>>();
		// 获取告警队列队列信息配置
		List<QueueThresholdInfo> queueThresholdInfos = queueThresholdDao
				.findAll();
		// 获取需要告警的用户信息
		List<NoticUserInfo> userInfos = noticUserDao.queryAlertUser();
		// 组装信息配置
		for (QueueThresholdInfo alertInfo : queueThresholdInfos) {
			// 首先判断该队列是否在告警队列信息缓存中---------存在
			// 判断告警的阀值是否大于原有的告警阀值 ----小于 不进行覆盖。
			// -------不存在或是大于
			// 进行告警阀值装配和覆盖
			if (!configMap.containsKey(alertInfo.getQueue())
					|| alertInfo.getThreshold() > configMap
							.get(alertInfo.getQueue()).get(0)
							.getThresholdInfo().getThreshold()) {
				// key---队列信息
				// value---封装信息(告警信息，告警地址[邮件地址])
				configMap.put(alertInfo.getQueue(),
						configListAdd(alertInfo, userInfos));
			}
		}
		queueConfig = configMap;
		LOGGER.info("load Configuration successed!");
		// 装配连接队列管理器信息
		queueManagerBeans = transform(managerDao.findAlert());
	}

	/**
	 * 
	 * @param alertInfo
	 *            需要告警队列配置
	 * @param userInfos
	 *            告警人员名单
	 * @return 告警信息集合---里面包括告警内容，告警地址
	 */
	protected List<QueueAlertInfo> configListAdd(QueueThresholdInfo alertInfo,
			List<NoticUserInfo> userInfos) {
		List<QueueAlertInfo> queueAlertInfos = new ArrayList<QueueAlertInfo>();
		// 通过约定将关联的用户信息分离出来
		String[] users = alertInfo.getPersonId().split(",");
		// 当没有配置关联的用户信息时
		if (users.length == 0) {
			LOGGER.error("未给告警队列添加告警人员，请联系管理员将队列的告警人员添加上。(提示：添加后请刷新配置方可起效～)");
			return null;
		}
		// 创建邮件地址信息，由于不能完全的确认是否为该用户配置了邮件地址和移动电话
		List<String> emailAdder = new ArrayList<String>();
		// 创建告警bean
		QueueAlertInfo queueAlertInfo = new QueueAlertInfo();
		// 首先将告警队列信息添加到告警bean中
		queueAlertInfo.setThresholdInfo(alertInfo);
		for (int i = 0; i < users.length; i++) {
			for (NoticUserInfo userInfo : userInfos) {
				if (users[i].equals(userInfo.getId())) {
					// 首先以139邮件告警为主，如果不是以移动电话[必须是移动电话号码]改为139邮箱为辅，最后以任意邮件为主，都不存在则是没有告警地址。
					if (userInfo.getEmail() == null) {
						if (userInfo.getMobilePhone() != null) {
							if (userInfo
									.getMobilePhone()
									.matches(
											"^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")) {
								emailAdder.add(userInfo.getMobilePhone()
										+ "@139.com");
							}
						}
					} else {
						if (userInfo.getEmail().endsWith("@139.com")) {
							emailAdder.add(userInfo.getEmail());
							continue;
						} else if (userInfo.getMobilePhone() != null) {
							if (userInfo
									.getMobilePhone()
									.matches(
											"^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")) {
								emailAdder.add(userInfo.getMobilePhone()
										+ "@139.com");
								continue;
							}
						}
						emailAdder.add(userInfo.getEmail());
					}
				}
			}
		}
		// 如果没有告警地址则返回为null
		if (emailAdder.size() == 0) {
			LOGGER.error("告警人员未配置任何邮箱地址和电话号码，请管理员进行添加。(提示：添加后请刷新配置方可起效～)");
			return null;
		}
		queueAlertInfo.setEmailAdder(parseString(emailAdder));
		queueAlertInfos.add(queueAlertInfo);
		return queueAlertInfos;
	}

	/**
	 * 
	 * @param managerInfos
	 *            连接队列管理器的相应配置
	 * @return 组合队列管理器
	 */
	private List<QueueManagerBean> transform(List<QueueManagerInfo> managerInfos) {
		// 创建管理器信息
		QueueManagerBean managerBean = new QueueManagerBean();
		// 管理器集合
		List<QueueManagerBean> beans = new ArrayList<QueueManagerBean>();
		// ip集合
		List<String> ip = new ArrayList<String>();
		// port集合
		List<Integer> port = new ArrayList<Integer>();
		// qmgr集合
		List<String> qmgr = new ArrayList<String>();
		// 通道集合
		List<String> channel = new ArrayList<String>();
		// 队列通配符集合
		List<String> queueNameReg = new ArrayList<String>();
		// 组装一套连接队列管理器信息配置
		for (QueueManagerInfo queueManagerInfo : managerInfos) {
			// 获取队列通配符
			String queueName = queueManagerInfo.getQueueNameReg();
			// 通配符为null则不进行记录
			if (queueName == null) {
				continue;
			}
			// 查看统配符是否都相同
			if (queueNameReg != null) {
				for (int i = 0; i < ip.size(); i++) {
					//当ip地址和队列管理器名称都相同时
					if (queueManagerInfo.getIp() == ip.get(i)
							&& queueManagerInfo.getQmgr() == qmgr.get(i)) {
						//队列管理统配符已经是"*"或则已经存在相同的通配符则不进行覆盖
						if (queueNameReg.get(i).equals("*")
								|| queueNameReg.get(i).equals(queueName)) {
							continue;
						} else if (queueName.equals("*")) {
							ip.remove(i);
							port.remove(i);
							qmgr.remove(i);
							channel.remove(i);
							queueNameReg.remove(i);
						}
					}
				}
			}
			ip.add(queueManagerInfo.getIp());
			port.add(queueManagerInfo.getPort());
			channel.add(queueManagerInfo.getChannel());
			qmgr.add(queueManagerInfo.getQmgr());
			queueNameReg.add(queueName);
		}
		//要保持ip=port=qmgr=channel=queueNameReg的个数要保持一致，如果不一致则告诉管理员需要重新配置
		if(!(ip.size() == port.size() && ip.size() == qmgr.size()
				&& ip.size() == channel.size()
				&& ip.size() == queueNameReg.size())) {
			throw new RuntimeException("填写的连接MQ的配置信息不一致，主要原因在数据库的查询或者是数据配置有问题，请及时检查。");
		}
		managerBean.setIp(parseString(ip));
		managerBean.setPort(parseInt(port));
		managerBean.setChannel(parseString(channel));
		managerBean.setQueueNameReg(parseString(queueNameReg));
		beans.add(managerBean);
		return beans;
	}

	// 将list<Intger>转化成int[]
	private int[] parseInt(List<Integer> list) {
		int[] inr = new int[list.size()];
		for (int i = 0; i < inr.length; i++) {
			inr[i] = list.get(i);
		}
		return inr;
	}

	// 将List<String>转化成String[]
	private String[] parseString(List<String> list) {
		String[] str = new String[list.size()];
		for (int i = 0; i < str.length; i++) {
			str[i] = list.get(i);
		}
		return str;
	}

	/**
	 * 刷新配置.
	 * 
	 * @author bruce_x
	 * @date 2013-10-22 上午10:40:47
	 */
	public synchronized void refresh() {
		init();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOGGER.info("QueueConfigLoader load queueAlert configuration starting ...");
		init();
	}

}
