package com.deppon.esb.management.mq.dao;

import java.util.List;

import com.deppon.esb.management.mq.domain.QueueManagerInfo;

public interface IMqManagerDao {
	
	List<QueueManagerInfo> findAlert();
	
	List<QueueManagerInfo> queryManagerAdder(QueueManagerInfo managerInfo);
	
	int addManagerAdder(QueueManagerInfo managerInfo);
	
	int updateManagerAdder(QueueManagerInfo managerInfo);
	
	int deleteManagerAdder(List<String> list);
	
	List<QueueManagerInfo> findAll();
}
