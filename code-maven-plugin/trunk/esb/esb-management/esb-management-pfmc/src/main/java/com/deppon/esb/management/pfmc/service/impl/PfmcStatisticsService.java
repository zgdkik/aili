package com.deppon.esb.management.pfmc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.pfmc.dao.IPfmcStatisticsDao;
import com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo;
import com.deppon.esb.management.pfmc.service.IPfmcStatisticsService;
import com.deppon.esb.management.svccfg.dao.ISvcPointDao;

/**
 * @Description 性能统计Service实现
 * @author HuangHua
 * @date 2012-5-7下午10:02:55
 */
@Service
@Transactional
public class PfmcStatisticsService implements IPfmcStatisticsService {

	private IPfmcStatisticsDao pfmcStatisticsDao;

	private ISvcPointDao svcPointDao;

	@Override
	@Deprecated
	public void collectData() {
		pfmcStatisticsDao.collectData();
	}

	@Override
	public void updatePfmcStatistics(List<String> ids) {
		pfmcStatisticsDao.updatePfmcStatistics(ids);
	}
	
	@Override
	public List<PfmcStatisticsInfo> queryPfmcStatistics2Notice() {
		return pfmcStatisticsDao.queryPfmcStatistics2Notice();
	}

	
	public IPfmcStatisticsDao getPfmcStatisticsDao() {
		return pfmcStatisticsDao;
	}

	public void setPfmcStatisticsDao(IPfmcStatisticsDao pfmcStatisticsDao) {
		this.pfmcStatisticsDao = pfmcStatisticsDao;
	}

	public ISvcPointDao getSvcPointDao() {
		return svcPointDao;
	}

	public void setSvcPointDao(ISvcPointDao svcPointDao) {
		this.svcPointDao = svcPointDao;
	}

}
