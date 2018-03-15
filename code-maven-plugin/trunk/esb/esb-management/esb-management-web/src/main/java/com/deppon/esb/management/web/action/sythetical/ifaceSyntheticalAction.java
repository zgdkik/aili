package com.deppon.esb.management.web.action.sythetical;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.audit.service.IEsbAuditService;
import com.deppon.esb.management.audit.view.EsbAuditInfoQueryBean;
import com.deppon.esb.management.audit.view.EsbAuditInfoView;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;
import com.deppon.esb.management.statistic.service.IStatisticService;
import com.deppon.esb.management.statistic.view.StatisticQueryBean;
import com.deppon.esb.management.statistic.view.StatisticView;
import com.deppon.esb.management.web.action.audit.AuditLogAction;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;

@Controller("ifaceSyntheticalAction")
@Scope("prototype") 
public class ifaceSyntheticalAction extends ESBActionSupport{

	private static final long serialVersionUID = -5758034560891573793L;
	@Resource
	private IStatisticService statisticService;
	
	private Logger LOGGER = Logger.getLogger(AuditLogAction.class);
	//后端服务编码List
	private List<String> codeList;
	//展示数据List
	private List<StatisticView> viewList;
	//查询条件
	private StatisticQueryBean statisticQueryBean;
	//提示信息
	private String tips;
	//错误信息
	private String errorMsg;
	private int page;
	private int limit;
	private int start;
	
	public String execute(){
		return SUCCESS;
	}
	public String svcCodeList(){
		codeList = new ArrayList<String>();
		codeList.add("esbSvcCode':'ESB_FOOS");
		codeList.add("esbSvcCode':'ESB_FOOS0");
		codeList.add("esbSvcCode':'ESB_FOOS8");
		codeList.add("esbSvcCode':'ESB_FOOS1");
		codeList.add("esbSvcCode':'ESB_FOOS2");
		codeList.add("esbSvcCode':'ESB_FOOS3");
		codeList.add("esbSvcCode':'ESB_FOOS4");
		codeList.add("esbSvcCode':'ESB_FOOS5");
		codeList.add("esbSvcCode':'ESB_FOOS6");
		codeList.add("esbSvcCode':'ESB_FOOS7");
//		statisticQueryBean = new StatisticQueryBean();
//		statisticQueryBean.setStart(0);
//		statisticQueryBean.setLimit(0);
//		resultCount = statisticService.queryStatisticViewCount(statisticQueryBean);
//		statisticQueryBean.setLimit(resultCount);
//		viewList = statisticService.queryStatisticView(statisticQueryBean);
//		for (StatisticView statisticView : viewList) {
//			codeList.add(statisticView.getEsbSvcCode());
//		}
		return SUCCESS;
	}
	
	public String viewList(){
		try{
			statisticQueryBean.setStart(start);
			statisticQueryBean.setLimit(limit);
			resultCount = statisticService.queryStatisticViewCount(statisticQueryBean);
			viewList = statisticService.queryStatisticView(statisticQueryBean);
			success=true;
		}catch(Exception e){
			success=false;
			tips="查询出现未知错误："+e.getMessage();
			LOGGER.error(e.getMessage(), e);
		}
		return SUCCESS;
	}
	
	public List<String> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

	public IStatisticService getStatisticService() {
		return statisticService;
	}
	public void setStatisticService(IStatisticService statisticService) {
		this.statisticService = statisticService;
	}
	public StatisticQueryBean getStatisticQueryBean() {
		return statisticQueryBean;
	}
	public void setStatisticQueryBean(StatisticQueryBean statisticQueryBean) {
		this.statisticQueryBean = statisticQueryBean;
	}
	public List<StatisticView> getViewList() {
		return viewList;
	}
	public void setViewList(List<StatisticView> viewList) {
		this.viewList = viewList;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
