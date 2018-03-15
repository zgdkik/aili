package com.deppon.esb.management.mq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.mq.dao.IMqManagerDao;
import com.deppon.esb.management.mq.domain.QueueManagerInfo;

@Repository
public class MqManagerDao extends IBatis3DaoImpl implements IMqManagerDao {

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<QueueManagerInfo> findAlert() {
		return getSqlSession().selectList(
				"com.deppon.esb.management.mq.domain.QueueManagerInfo.findAlert");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QueueManagerInfo> queryManagerAdder(QueueManagerInfo managerInfo) {
		return getSqlSession().selectList("com.deppon.esb.management.mq.domain.QueueManagerInfo.queryManagerAdder",managerInfo);
	}

	@Override
	public int addManagerAdder(QueueManagerInfo managerInfo) {
		return getSqlSession().insert("com.deppon.esb.management.mq.domain.QueueManagerInfo.addManagerAdder", managerInfo);
	}

	@Override
	public int updateManagerAdder(QueueManagerInfo managerInfo) {
		return getSqlSession().update("com.deppon.esb.management.mq.domain.QueueManagerInfo.updateManagerAdder", managerInfo);
	}

	@Override
	public int deleteManagerAdder(List<String> list) {
		return getSqlSession().delete("com.deppon.esb.management.mq.domain.QueueManagerInfo.deleteManagerAdder", list);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QueueManagerInfo> findAll() {
		return getSqlSession().selectList(
				"com.deppon.esb.management.mq.domain.QueueManagerInfo.findAll");
	}
	
}
