package com.deppon.esb.management.excptn.dao.impl;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.common.exception.ESBRunTimeException;
import com.deppon.esb.management.excptn.dao.IExcptnStatisticsDao;
import com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo;

@Repository
public class ExcptnStatisticsDao extends IBatis3DaoImpl implements IExcptnStatisticsDao {

	@Override
	@Deprecated
	public void collectData() {
		CallableStatement proc;
		try {
			proc = getSqlSession().getConnection().prepareCall(" { call pro_collectExpData } ");
			proc.execute();
		} catch (SQLException e) {
			throw new ESBRunTimeException("call procedure occured exception!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExcptnStatisticsInfo> queryExcptnStatistics() {
		return getSqlSession().selectList("com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo.queryExcptnStatistics");
	}
	
	@Override
	public int markProcessed(List<String> ids){
		return getSqlSession().update("com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo.markProcessed", ids);
	}
	
	public ExcptnStatisticsInfo getExceptionStatistic(String id) {
		return (ExcptnStatisticsInfo)getSqlSession().selectOne(
				"com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo.getExceptionStatistic", 
				id);
	}
}
