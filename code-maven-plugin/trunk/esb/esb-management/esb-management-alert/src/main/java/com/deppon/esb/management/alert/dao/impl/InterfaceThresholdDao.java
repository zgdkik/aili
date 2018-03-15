package com.deppon.esb.management.alert.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.alert.dao.IinterfaceThresholdDao;
import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdBean;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean;
import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.common.exception.ESBIllegalArgumentException;

@Repository
public class InterfaceThresholdDao extends IBatis3DaoImpl implements IinterfaceThresholdDao {

	@Override
	public void addInterfacethreshold(InterfaceThresholdInfo info) {
		this.getSqlSession().insert("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.insert", info);

	}

	@Override
	public void updateInterfaceThreshold(InterfaceThresholdInfo info) {
		this.getSqlSession().update("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.update", info);

	}

	@Override
	public void deleteInterfaceThresholdById(List<String> idList) {
		this.getSqlSession().delete("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.deleteThresholdById", idList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InterfaceThresholdInfo> getInterfaceThreshold(InterfaceThresholdQueryBean bean) {
		return this.getSqlSession().selectList("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.selectThresholdInfo", bean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InterfaceThresholdBean> getThresholdResultBean(InterfaceThresholdQueryBean bean) {
		RowBounds rowbound = new RowBounds(bean.getStart(), bean.getLimit());
		return this.getSqlSession().selectList("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.selectThresholdResultBean", bean, rowbound);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InterfaceThresholdInfo> getThresholdForPerformance(InterfaceThresholdQueryBean bean) {
		return this.getSqlSession().selectList(
				"com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.selectThresholdForPerformance", 
				bean
				);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InterfaceThresholdInfo> getThresholdForException(InterfaceThresholdQueryBean bean) {
		return this.getSqlSession().selectList(
				"com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.selectThresholdForException", 
				bean
				);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer getThresholdResultBeanCount(InterfaceThresholdQueryBean bean){
		List<InterfaceThresholdBean> list = this.getSqlSession().selectList("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.selectThresholdResultBean", bean);
		return Integer.valueOf(list.size());
	}
	
	
	@Override
	public double getThresholdBySvcCode(String svcCode) {
		if(svcCode != null && !"".equals(svcCode)){
			return (Double) getSqlSession().selectOne("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.getThresholdBySvcCode", svcCode);
		}else{
			throw new ESBIllegalArgumentException("svcCode can not be null!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] queryExcptnEmailBySvcCodeAndType(String svcCode, int type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("svcCode", svcCode);
		map.put("type", type);
		map.put("channelId", InterfaceThresholdInfo.CHANNEL_EMAIL);//邮件的channelid为0。统一为一个静态常量(2012-7-11)
		String personIds = (String) getSqlSession().selectOne("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.queryExcptnEmailBySvcCodeAndType1",
				map);
		if(personIds == null){//如果没查询到数据，则返回一个空的String[]
			return new String[0];
		}
		String[] ids = personIds.split(",");
		List<String> list = getSqlSession().selectList("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.queryExcptnEmailBySvcCodeAndType2",
				ids);
		return list.toArray(new String[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InterfaceThresholdBean> getExceptionSet(
			InterfaceThresholdQueryBean bean) {
		RowBounds rowBounds = new RowBounds(bean.getStart(), bean.getLimit());
		return getSqlSession().selectList("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.selectExceptionSet", bean, rowBounds);
	}

	@Override
	public Integer getExceptionSetCount(InterfaceThresholdQueryBean bean) {
		return (Integer)getSqlSession().selectOne("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.selectExceptionSetCount", bean);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-14 下午4:36:01
	 * @see com.deppon.esb.management.alert.dao.IinterfaceThresholdDao#getThresholdForStatusNoComplete(com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean)
	 */
	@Override
	public InterfaceThresholdInfo getThresholdForStatusNoComplete(
			InterfaceThresholdQueryBean condition) {
		return (InterfaceThresholdInfo) getSqlSession().selectOne("com.deppon.esb.management.alert.domain.InterfaceThresholdInfo.getThresholdForStatusNoComplete", condition);
	}

}
