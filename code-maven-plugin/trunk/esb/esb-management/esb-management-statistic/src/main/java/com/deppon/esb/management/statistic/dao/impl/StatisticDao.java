package com.deppon.esb.management.statistic.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.statistic.dao.IStatisticDao;
import com.deppon.esb.management.statistic.view.StatisticQueryBean;
import com.deppon.esb.management.statistic.view.StatisticView;

@Repository
public class StatisticDao extends IBatis3DaoImpl implements IStatisticDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticView> queryStatisticView(StatisticQueryBean bean) {
		RowBounds rowBounds = new RowBounds(bean.getStart(),bean.getLimit());
		return getSqlSession().selectList("com.deppon.esb.management.statistic.view.StatisticView.queryStatisticView", bean, rowBounds);
	}

	@Override
	public Integer queryStatisticViewCount(StatisticQueryBean bean) {
		return (Integer)getSqlSession().selectOne("com.deppon.esb.management.statistic.view.StatisticView.queryStatisticViewCount", bean);
	}

}
