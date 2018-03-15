package com.deppon.esb.management.web.action.exceptionlog;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.excptn.domain.view.ExceptionBean;
import com.deppon.esb.management.excptn.domain.view.ExceptionQueryBean;
import com.deppon.esb.management.excptn.service.IExceptionService;
import com.opensymphony.xwork2.ActionContext;

@Controller("exceptionLogAction")
@Scope("prototype")
public class ExceptionLogAction {
	private String message;
	private String success;
	private Integer start;
	private Integer limit;
	private ExceptionQueryBean bean;
	private List<ExceptionBean> list;
	private String stackTrace;
	private Integer totalCount;
	@Resource(name = "exceptionService")
	private IExceptionService exceptionService;

	public String queryExceptionBean() {
		if (bean == null) {
			bean = new ExceptionQueryBean();
		}
		bean.setLimit(limit);
		bean.setStart(start);
		list = exceptionService.queryExceptionBean(bean);
		totalCount = exceptionService.queryExceptioBeanCount(bean);
		message = "操作成功";
		success = "true";
		return "success";
	}

	/**
	 * 查询异常堆栈信息
	 * @return
	 */
	public String queryExceptionStrace(){
		if(bean == null ||bean.getId()== null || "".equals(bean.getId())){
			message ="参数解析异常";
			success="fail";
		}else{
			stackTrace= exceptionService.quereyExceptionStackTrace(bean.getId());
			ActionContext ctx = ActionContext.getContext();
			ctx.put("stackTrace", stackTrace);
			success="true";
		}

		
		return "success";
	}
	
	/**
	 * 检查传入参数
	 * 
	 * @return
	 */
	public boolean check() {
		return bean == null ? false : true;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public ExceptionQueryBean getBean() {
		return bean;
	}

	public void setBean(ExceptionQueryBean bean) {
		this.bean = bean;
	}

	public List<ExceptionBean> getList() {
		return list;
	}

	public void setList(List<ExceptionBean> list) {
		this.list = list;
	}

	public IExceptionService getExceptionService() {
		return exceptionService;
	}

	public void setExceptionService(IExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

}
