package com.deppon.esb.management.alert.dao;

import java.util.List;

import com.deppon.esb.management.alert.domain.QueueThresholdInfo;
import com.deppon.esb.management.alert.domain.view.QueueBean;
import com.deppon.esb.management.alert.domain.view.QueueQueryBean;

public interface IQueueThresholdDao {
	/**
	 * 新增
	 * 
	 * @param info
	 */
	public int addQueueThreshold(QueueThresholdInfo info);

	/**
	 * 更新
	 * 
	 * @param info
	 */
	public int updateQueueThreshold(QueueThresholdInfo info);

	/**
	 * 根据id删除
	 * 
	 * @param ids
	 */
	public int deleteQueueThreshold(List<String> ids);

	/**
	 * 查询QueueThresholdInfo
	 * 
	 * @param bean
	 * @return
	 */
	public List<QueueThresholdInfo> queryQueueThreshold(QueueQueryBean bean);

	/**
	 * 查询QueueBean
	 * 
	 * @param bean
	 * @return
	 */
	public List<QueueBean> queryQueueBean(QueueQueryBean bean);
	
	/**
	 * 根据查询条件查询queueBean的总数
	 */
	public Integer queryQueueBeanCount(QueueQueryBean bean);
	
	/**
	 * 2012/06/20  zhengwl
	 * 去掉QMGR的条件！
	 * 因为通过PCF读取队列深度时，需要指定IP/端口号/通道名称；
	 * 这组配置是通过spring配置文件进行注入的（不同环境有不同的配置信息），
	 * 管理平台进行队列监控设置时不需要指定队列管理器名称，仅针对队列名称设置即可；
	 * 
	 * @Description 根据条件查询阀值信息
	 * @param qmgr
	 * @param queue
	 * @param depth
	 * @return
	 */
	public List<QueueThresholdInfo> findQuThrsldByQueueAndCrntDepth(String queue, int depth);

	/**
	 * 查询所有队列管理器连接异常时需要告警的人员
	 * @author HuangHua
	 * @date 2013-5-10 下午3:30:55
	 */
	List<QueueThresholdInfo> findQmgrConn();
	
	/** 
	 *  查询所有需要告警的队列
	 */
	
	List<QueueThresholdInfo> findAll();

}
