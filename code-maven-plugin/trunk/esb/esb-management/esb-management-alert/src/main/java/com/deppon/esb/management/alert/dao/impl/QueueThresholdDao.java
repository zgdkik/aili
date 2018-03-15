package com.deppon.esb.management.alert.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.alert.dao.IQueueThresholdDao;
import com.deppon.esb.management.alert.domain.QueueThresholdInfo;
import com.deppon.esb.management.alert.domain.view.QueueBean;
import com.deppon.esb.management.alert.domain.view.QueueQueryBean;
import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;

@Repository
public class QueueThresholdDao extends IBatis3DaoImpl implements IQueueThresholdDao {

	@Override
	public int addQueueThreshold(QueueThresholdInfo info) {
		return this.getSqlSession().insert("com.deppon.esb.management.alert.domain.QueueThresholdInfo.insert", info);

	}

	@Override
	public int updateQueueThreshold(QueueThresholdInfo info) {
		return this.getSqlSession().update("com.deppon.esb.management.alert.domain.QueueThresholdInfo.update", info);
	}

	@Override
	public int deleteQueueThreshold(List<String> ids) {
		return this.getSqlSession().delete("com.deppon.esb.management.alert.domain.QueueThresholdInfo.deleteById", ids);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QueueThresholdInfo> queryQueueThreshold(QueueQueryBean bean) {
		return this.getSqlSession().selectList("com.deppon.esb.management.alert.domain.QueueThresholdInfo.select", bean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QueueBean> queryQueueBean(QueueQueryBean bean) {
		RowBounds rowBounds = new RowBounds(bean.getStart(), bean.getLimit());
		return this.getSqlSession().selectList("com.deppon.esb.management.alert.domain.QueueThresholdInfo.selectQueueBean", bean, rowBounds);
	}
	
	@Override
	public Integer queryQueueBeanCount(QueueQueryBean bean) {
		return (Integer)this.getSqlSession().selectOne("com.deppon.esb.management.alert.domain.QueueThresholdInfo.selectQueueBeanCount", bean);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QueueThresholdInfo> findQuThrsldByQueueAndCrntDepth(String queue, int depth) {
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("qmgr", qmgr);
		map.put("queue", queue);
		map.put("depth", depth);
		return getSqlSession().selectList(
				"com.deppon.esb.management.alert.domain.QueueThresholdInfo.findQuThrsldByQueueAndCrntDepth", map);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QueueThresholdInfo> findQmgrConn(){
		return getSqlSession().selectList("com.deppon.esb.management.alert.domain.QueueThresholdInfo.findQmgrConn");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QueueThresholdInfo> findAll() {
		return getSqlSession().selectList("com.deppon.esb.management.alert.domain.QueueThresholdInfo.findAll");
	}
	
	
	
}
