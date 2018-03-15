package com.deppon.esb.management.mq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.mq.dao.IMqManagerDao;
import com.deppon.esb.management.mq.domain.QueueManagerInfo;
import com.deppon.esb.management.mq.service.IMqManagerService;

@Service
@Transactional
public class MqManagerService implements IMqManagerService{
	
	@Resource
	private IMqManagerDao mqManagerDao;
	
	@Override
	public List<QueueManagerInfo> findAlert() {
		return mqManagerDao.findAlert();
	}

	@Override
	public List<QueueManagerInfo> queryManagerAdder(QueueManagerInfo managerInfo) {
		return mqManagerDao.queryManagerAdder(managerInfo);
	}

	@Override
	public int addManagerAdder(QueueManagerInfo managerInfo) {
		return mqManagerDao.addManagerAdder(managerInfo);
	}

	@Override
	public int updateManagerAdder(QueueManagerInfo managerInfo) {
		return mqManagerDao.updateManagerAdder(managerInfo);
	}

	@Override
	public int deleteManagerAdder(List<String> list) {
		return mqManagerDao.deleteManagerAdder(list);
	}

	@Override
	public List<QueueManagerInfo> findAll() {
		return mqManagerDao.findAll();
	}
	
}
