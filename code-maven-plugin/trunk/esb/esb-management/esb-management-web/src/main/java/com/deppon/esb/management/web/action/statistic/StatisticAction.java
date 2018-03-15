package com.deppon.esb.management.web.action.statistic;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.statistic.service.IStatisticService;
import com.deppon.esb.management.statistic.view.StatisticQueryBean;
import com.deppon.esb.management.statistic.view.StatisticView;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;

@Controller("statistic2Action")
@Scope("prototype")
public class StatisticAction extends ESBActionSupport{
	private static final long serialVersionUID = 1L;
	private Logger LOG = Logger.getLogger(StatisticAction.class);

	@Resource
	private IStatisticService statisticService;
	
	//传入参数
	private StatisticQueryBean queryBean;
	//传出数据
	private List<StatisticView> list;
	public String queryStatisticView(){
		try {
			//TODO检查参数合法性
			queryBean.setStart(start);
			queryBean.setLimit(limit);
			list = statisticService.queryStatisticView(queryBean);
			resultCount = statisticService.queryStatisticViewCount(queryBean);
			message="查询成功";
			success=true;
		} catch (Exception e) {
			message=e.getMessage();
			success=false;
			LOG.error(e.getMessage(),e);
		}
		return SUCCESS;
	}
	public IStatisticService getStatisticService() {
		return statisticService;
	}
	public void setStatisticService(IStatisticService statisticService) {
		this.statisticService = statisticService;
	}
	public StatisticQueryBean getQueryBean() {
		return queryBean;
	}
	public void setQueryBean(StatisticQueryBean queryBean) {
		this.queryBean = queryBean;
	}
	public List<StatisticView> getList() {
		return list;
	}
	public void setList(List<StatisticView> list) {
		this.list = list;
	}
	
}
