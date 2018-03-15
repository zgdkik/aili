package com.deppon.esb.management.statistic.dao;

import java.util.List;

import com.deppon.esb.management.statistic.view.StatisticQueryBean;
import com.deppon.esb.management.statistic.view.StatisticView;

public interface IStatisticDao {
	public List<StatisticView> queryStatisticView(StatisticQueryBean bean);
	public Integer queryStatisticViewCount(StatisticQueryBean bean);
}
