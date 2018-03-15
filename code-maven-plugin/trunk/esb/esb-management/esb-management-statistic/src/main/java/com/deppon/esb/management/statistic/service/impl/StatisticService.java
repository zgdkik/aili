package com.deppon.esb.management.statistic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.statistic.dao.IStatisticDao;
import com.deppon.esb.management.statistic.service.IStatisticService;
import com.deppon.esb.management.statistic.view.StatisticQueryBean;
import com.deppon.esb.management.statistic.view.StatisticView;
@Transactional
@Service
public class StatisticService implements IStatisticService{
	@Resource
	private IStatisticDao statisticDao;
	
	@Override
	public List<StatisticView> queryStatisticView(StatisticQueryBean bean) {
		
		return statisticDao.queryStatisticView(bean);
	}

	@Override
	public Integer queryStatisticViewCount(StatisticQueryBean bean) {
		return statisticDao.queryStatisticViewCount(bean);
	}

	public IStatisticDao getStatisticDao() {
		return statisticDao;
	}

	public void setStatisticDao(IStatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}
}
