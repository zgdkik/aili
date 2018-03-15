package com.deppon.esb.management.alert.service;

import java.util.List;

import com.deppon.esb.management.alert.domain.QueueThresholdInfo;
import com.deppon.esb.management.alert.domain.view.QueueBean;
import com.deppon.esb.management.alert.domain.view.QueueQueryBean;

public interface IQueueThresholdService {
	
	public int addQueueThreshold(QueueThresholdInfo info);
	public int updateQueueThreshold(QueueThresholdInfo info) ;
	public int deleteQueueThreshold(List<String> ids) ;
	public List<QueueThresholdInfo> getQueueThresholdInfo(QueueQueryBean bean) ;
	public List<QueueBean> getQueueBean(QueueQueryBean bean);
	public Integer getQueueBeanCount(QueueQueryBean bean);
}
