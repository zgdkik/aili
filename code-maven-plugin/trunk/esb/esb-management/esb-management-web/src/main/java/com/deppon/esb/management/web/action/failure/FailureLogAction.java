package com.deppon.esb.management.web.action.failure;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.failure.service.IEsbFailureService;
import com.deppon.esb.management.failure.view.EsbFailureInfoQueryBean;
import com.deppon.esb.management.failure.view.EsbFailureInfoView;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;
/**
 * 失败日志 action
 */
@Controller("failureLogAction")
@Scope("prototype") 
public class FailureLogAction extends  ESBActionSupport{
	private static final long serialVersionUID = -1449104831483836479L;
	@Resource
	private IEsbFailureService esbFailureService;
	//查询条件
	private EsbFailureInfoQueryBean queryBean;
	//失败信息DTO
	private List<EsbFailureInfoView> list ;
	//审计日志详细信息
	private String failureLogBody;
	//错误信息
	private String errorMsg;
	//根据条件过滤的记录总条数
	private int count; 
	//提示信息
	private String tips;
	
	private Logger LOGGER = Logger.getLogger(FailureLogAction.class);

	/**
	 * 查询失败信息列表
	 */
	
	public String queryFailureLogList(){
		try {
			queryBean.setStart(start);
			queryBean.setLimit(limit);
			resultCount = esbFailureService.queryFailureLogCount(queryBean);	
			list = esbFailureService.queryEsbFailureLogList(queryBean);
			success=true;
		} catch (Exception e) {
			success=false;
			tips="查询出现未知错误："+e.getMessage();
			LOGGER.error(e.getMessage(), e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 查询 详细失败信息
	 */
	public String queryFailureLogBody(){
		if (queryBean == null || queryBean.getFid() == null
				|| queryBean.getFid().length() == 0) {
			tips = "查询参数为空";
			return ERROR;
		}
		failureLogBody = esbFailureService.queryFailureLogBody(queryBean.getFid());
		return SUCCESS;
	}

	public IEsbFailureService getEsbFailureService() {
		return esbFailureService;
	}

	public void setEsbFailureService(IEsbFailureService esbFailureService) {
		this.esbFailureService = esbFailureService;
	}

	public EsbFailureInfoQueryBean getQueryBean() {
		return queryBean;
	}

	public void setQueryBean(EsbFailureInfoQueryBean queryBean) {
		this.queryBean = queryBean;
	}

	public List<EsbFailureInfoView> getList() {
		return list;
	}

	public void setList(List<EsbFailureInfoView> list) {
		this.list = list;
	}

	public String getFailureLogBody() {
		return failureLogBody;
	}

	public void setFailureLogBody(String failureLogBody) {
		this.failureLogBody = failureLogBody;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
 
}
