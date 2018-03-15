package com.deppon.esb.management.pfmc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.pfmc.dao.IPfmcStatisticsDao;
import com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo;

/**
 * @Description 性能统计DAO实现
 * @author HuangHua
 * @date 2012-5-7下午8:15:41
 */
@Repository
public class PfmcStatisticsDao extends IBatis3DaoImpl implements IPfmcStatisticsDao {

	@Override
	@Deprecated
	public void collectData(){
//		CallableStatement proc;
//		try {
//			proc = getSqlSession().getConnection().prepareCall(" { call pro_collectPfmData } ");
//			proc.execute();
//		} catch (SQLException e) {
//			throw new ESBInternalExeption("call procedure occured exception!");
//		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PfmcStatisticsInfo> queryPfmcStatistics2Notice() {
		return getSqlSession().selectList("com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo.queryPfmcStatistics2Notice");
	}
	
	@Override
	public int updatePfmcStatistics(List<String> ids){
		return getSqlSession().update("com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo.updatePfmcStatistics", ids);
	}

	public PfmcStatisticsInfo getPfmcStatistic(String id) {
		return (PfmcStatisticsInfo)getSqlSession().selectOne(
				"com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo.getPfmcStatistic", 
				id);
	}
}
