package com.deppon.esb.management.web.action.svcpoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.svccfg.domain.SvcPoint2Info;
import com.deppon.esb.management.svccfg.domain.view.SvcPoint2QueryBean;
import com.deppon.esb.management.svccfg.service.ISvcpoint2Service;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;

@Controller("svcpoint2Action")
@Scope("prototype")
public class Svcpoint2Action extends ESBActionSupport{

	private static final long serialVersionUID = 1L;
	private static  Logger LOG = Logger.getLogger(Svcpoint2Action.class); 
	@Resource
	private ISvcpoint2Service svcpoint2Service;
	
	//=========请求信息=========
	//服务配置信息
	private SvcPoint2Info  info;
	//服务编码列表
	private String  codes;
	//查询条件表单
	private SvcPoint2QueryBean queryBean;
	
	//============响应信息============
	//配置信息
	private List<SvcPoint2Info> list ;
	//系统编码
	private List<JsonBean> sysIds;
	//接入协议
	private List<JsonBean> agrmts;
	//查询服务配置
	public String query(){
		try {
			//校验查询表单
			checkQueryParams();
			//查询总记录数据
			queryBean.setStart(start);
			queryBean.setLimit(limit);
			resultCount = svcpoint2Service.queryCount(queryBean);
			//查询服务配置列表
			list = svcpoint2Service.querySvcpoint2(queryBean);
			success=true;
		} catch (Exception e) {
			message=ActionMesssage.UNEXPECTED_EXCEPTION+":"+e.getMessage();
			LOG.error(e.getMessage(),e);
			return ERROR;
		}
		return SUCCESS;
	}
	//新增服务配置
	public String add(){
		try {
			//检查表单
			checkaddNewParams();
			//新增服务配置
			int i = svcpoint2Service.addSvcpoint2(info);
			if(i != 1){
				message =ActionMesssage.ADDNEW_FAIL;
			}
			message=ActionMesssage.ADDNEW_SUCCESS;
			success=true;
		} catch (Exception e) {
			message=ActionMesssage.UNEXPECTED_EXCEPTION+":"+e.getMessage();
			LOG.error(e.getMessage(),e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//更新服务配置
	public String update(){
		try {
			checkUpdateParams();
			int i =  svcpoint2Service.updateSvcpoint2(info);
			if(i !=0){
				message =ActionMesssage.UPDATE_SUCCESS;
			}else{
				message =ActionMesssage.UPDATE_FAIL+":没有找到相应的服务编码";
			}
			success=true;
		} catch (Exception e) {
			message=ActionMesssage.UNEXPECTED_EXCEPTION+":"+e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}
	//删除服务配置
	public String delete(){
		try {
			//校验参数
			checkDeleteParams();
			//删除记录
			List<String> list = converToStringList(codes);
			int i = svcpoint2Service.deleteSvcpoint2(list);
			message =ActionMesssage.DELETE_SUCCESS+":delete "+i+" record";
			success=true;
		} catch (Exception e) {
			message =ActionMesssage.UNEXPECTED_EXCEPTION+":"+e.getMessage();
			LOG.error(e.getMessage(),e);
			return ERROR;
		}
		return SUCCESS;
	}
	//查询系统编码
	public String querySysIds(){
		try {
			List<String> list = svcpoint2Service.querySysIds();
			sysIds= converToJsonBean(list);
			success=true;
		} catch (Exception e) {
			message = ActionMesssage.QUERY_SYSID_FAIL;
			LOG.error(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}
	//查询接入协议
	public String queryAgrmts(){
		try {
			List<String> list = svcpoint2Service.queryAgrmt();
			agrmts= converToJsonBean(list);
			success=true;
		} catch (Exception e) {
			message = ActionMesssage.QUERY_AGRMT_FAIL;
			LOG.error(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//TODO增加服务关联信息
	//TODO删除服务关联信息
	
	//查询服务表单验证
	public void checkQueryParams(){

	}
	//新增服务配置表单验证
	public void checkaddNewParams(){
		if(info == null){
			throw new ParameterException("parameter svpoint2Info is null");
		}else if(StringUtils.isBlank(info.getName())){
			throw new ParameterException("parameter name is blank");
		}else if(StringUtils.isBlank(info.getFrontOrBack())){
			throw new ParameterException("parameter frontOrBack is blank");
		}else if(StringUtils.isBlank(info.getMessageFormat())){
			throw new ParameterException("parameter messageFormat is blank");
		}else if(StringUtils.isBlank(info.getSysid())){
			throw new ParameterException("parameter sysid is blank");
		}else if(StringUtils.isBlank(info.getCode())){
			throw new ParameterException("parameter code is blank");
		}else if(info.getExpattern()==0){
			throw new ParameterException("parameter expattern is blank");
		}
		//检查是否有重复的编码
		if(svcpoint2Service.existCode(info.getCode())){
			throw new ParameterException("the code ["+info.getCode()+"] has existed");
		}
	}
	//更新服务表单验证
	public void checkUpdateParams(){
		if(info == null){
			throw new ParameterException("parameter svpoint2Info is null");
		}else if(StringUtils.isBlank(info.getName())){
			throw new ParameterException("parameter name is blank");
		}else if(StringUtils.isBlank(info.getFrontOrBack())){
			throw new ParameterException("parameter frontOrBack is blank");
		}else if(StringUtils.isBlank(info.getMessageFormat())){
			throw new ParameterException("parameter messageFormat is blank");
		}else if(StringUtils.isBlank(info.getSysid())){
			throw new ParameterException("parameter sysid is blank");
		}else if(StringUtils.isBlank(info.getCode())){
			throw new ParameterException("parameter code is blank");
		}else if(info.getExpattern()==0){
			throw new ParameterException("parameter expattern is blank");
		}
	}
	//删除服务参数校验
	public void checkDeleteParams(){
		if(StringUtils.isBlank(codes)){
			throw new ParameterException(" 服务编码 未传递");
		}
	}
	public SvcPoint2Info getInfo() {
		return info;
	}
	public void setInfo(SvcPoint2Info info) {
		this.info = info;
	}

	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	public SvcPoint2QueryBean getQueryBean() {
		return queryBean;
	}
	public void setQueryBean(SvcPoint2QueryBean queryBean) {
		this.queryBean = queryBean;
	}
	public List<SvcPoint2Info> getList() {
		return list;
	}
	public void setList(List<SvcPoint2Info> list) {
		this.list = list;
	}
	public ISvcpoint2Service getSvcpoint2Service() {
		return svcpoint2Service;
	}
	public void setSvcpoint2Service(ISvcpoint2Service svcpoint2Service) {
		this.svcpoint2Service = svcpoint2Service;
	}

	/**
	 * 字符串列表转换成pojo数组
	 */
	public List<JsonBean> converToJsonBean(List<String> list) {
		List<JsonBean> jsonList = new ArrayList<JsonBean>();
		for (String str : list) {
			JsonBean bean = new JsonBean();
			bean.setValue(str);
			jsonList.add(bean);
		}
		return jsonList;
	}

	public List<String> converToStringList(String codes) {
		String[] array = codes.split(",");
		return Arrays.asList(array);
	}
	
	public List<JsonBean> getSysIds() {
		return sysIds;
	}
	public void setSysIds(List<JsonBean> sysIds) {
		this.sysIds = sysIds;
	}
	public List<JsonBean> getAgrmts() {
		return agrmts;
	}
	public void setAgrmts(List<JsonBean> agrmts) {
		this.agrmts = agrmts;
	}
	
	class JsonBean{
		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
