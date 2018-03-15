package com.deppon.esb.management.excptn.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.excptn.dao.IExcptnStatisticsDao;
import com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo;
import com.deppon.esb.management.excptn.service.IExcptnStatisticsService;
import com.deppon.esb.management.svccfg.dao.ISvcPointDao;

/**
 * @Description 异常统计Service实现
 * @author HuangHua
 * @date 2012-5-7下午10:02:36
 */
@Service
@Transactional
public class ExcptnStatisticsService implements IExcptnStatisticsService {

	private IExcptnStatisticsDao excptnStatisticsDao;

	private ISvcPointDao svcPointDao;

	@Override
	@Deprecated
	public void collectData() {
		excptnStatisticsDao.collectData();
	}

	@Override
	public void markProcessed(List<String> ids) {
		excptnStatisticsDao.markProcessed(ids);
	}

	@Override
	public List<ExcptnStatisticsInfo> queryExcptnStatistics() {
		return excptnStatisticsDao.queryExcptnStatistics();
	}

	public IExcptnStatisticsDao getExcptnStatisticsDao() {
		return excptnStatisticsDao;
	}

	public void setExcptnStatisticsDao(IExcptnStatisticsDao excptnStatisticsDao) {
		this.excptnStatisticsDao = excptnStatisticsDao;
	}

	public ISvcPointDao getSvcPointDao() {
		return svcPointDao;
	}

	public void setSvcPointDao(ISvcPointDao svcPointDao) {
		this.svcPointDao = svcPointDao;
	}

}
