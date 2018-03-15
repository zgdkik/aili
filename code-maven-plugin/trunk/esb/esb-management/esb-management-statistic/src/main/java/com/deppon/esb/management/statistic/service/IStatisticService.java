package com.deppon.esb.management.statistic.service;

import java.util.List;

import com.deppon.esb.management.statistic.view.StatisticQueryBean;
import com.deppon.esb.management.statistic.view.StatisticView;

public interface IStatisticService {

	public List<StatisticView> queryStatisticView(StatisticQueryBean bean);
	public Integer queryStatisticViewCount(StatisticQueryBean bean);
}
