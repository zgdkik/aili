package com.deppon.esb.management.web.action.express.agent;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.agentuser.domain.AgentUserInfo;
import com.deppon.esb.management.agentuser.service.IAgentUserService;
import com.deppon.esb.management.agentuser.view.AgentUserQueryBean;
import com.deppon.esb.management.web.MD5Util;
import com.deppon.esb.management.web.action.svcpoint.ParameterException;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;
import com.deppon.esb.security.domain.UserInfo;

@Controller("agentUserAction")
@Scope("prototype") 
public class AgentUserAction extends ESBActionSupport{
	private static  Logger LOG = Logger.getLogger(AgentUserAction.class); 
	@Resource
	private IAgentUserService agetUserService;
	public AgentUserInfo info;
	public AgentUserQueryBean bean;
	private String  ids;
	private List<AgentUserInfo> list;

	//新增用户
	public String addAgentUser(){
		try {
			//输入参数检查
			checkInput(info);
			//判断是否有重复代理编码
			//加密密码
			info.setPasswd(agentMD5(info.getPasswd()));
			//保存代理用户
			//TODO agentUserInfo2UserInfo
			UserInfo userInfo = agentUserInfo2UserInfo(info);
			int i = agetUserService.addAgentUser(userInfo);
			 if(i != 1){
				 message = "add new AgentUser fail";
				 return ERROR; 
			 }
			 else{
				 success=true;
				 return SUCCESS; 
			 }
		} catch (Exception e) {
			message = "add new AgentUser fail";
			LOG.error(e.getMessage(), e);
			return ERROR;
		}
	}
	
	private UserInfo agentUserInfo2UserInfo(AgentUserInfo agentUserInfo){
		UserInfo userInfo = new UserInfo();
		userInfo.setUser(agentUserInfo.getUserName());
		userInfo.setPasswd(agentUserInfo.getPasswd());
		userInfo.setDesc(agentUserInfo.getAgentName());
		return userInfo;
	}
	
	//检查输入表单
	private void checkInput(AgentUserInfo agent){
		if(agent == null||agent.getAgentName()==null|| agent.getAgentName().length()==0
				||agent.getUserName()==null||agent.getUserName().length()==0
				||agent.getPasswd()==null||agent.getPasswd().length()==0){
			throw new  ParameterException("输入参数不合法，请检查代理名称、代理编码以及密码是否正确传递");
		}
	}
	//更新用户
	public String update(){
		try {
			//TODO userInfo
			UserInfo userInfo = agentUserInfo2UserInfo(info);
			//检查输入ll
			int updatCount = agetUserService.updateAgentUser(userInfo);
			if(updatCount==1){
				success=true;
				message="更新代理成功";
				return SUCCESS;
			}
			return ERROR;
		} catch (Exception e) {
			message="更新代理失败";
			LOG.error(message,e );
		}
		return ERROR;
	}
	
	//作废用户
	public String cancelAgentUser(){
		List<String> list = null;//没写
		int updateCount = agetUserService.canelAgenUser(list);
		return SUCCESS;
	}
	//激活用户
	public String activeAgentUser(){
		List<String> list = null;//没写
		agetUserService.activeAgenUser(list);
		return SUCCESS;
	}

	//查询用户
	public String query(){
		// 检查输入参数
		try {
			checkAgentUserQueryBean(bean);
			List<UserInfo> userInfolist= agetUserService.queryAgenUser(bean);
			list = userInfo2AgentUseInfo(userInfolist);
			success=true;
			return SUCCESS;
		} catch (Exception e) {
			message = "query agentuser fail";
			LOG.error(e.getMessage(), e);
			return ERROR;
		}
	}
	//TODO UserInfo ->AgentUserInfo
	public List<AgentUserInfo> userInfo2AgentUseInfo(List<UserInfo> userInfoList){
		List<AgentUserInfo> agentUserInfoList = new ArrayList<AgentUserInfo>();
		for(UserInfo userInfo:userInfoList){
			AgentUserInfo agentUserInfo = new AgentUserInfo();
			agentUserInfo.setId(userInfo.getFid());
			agentUserInfo.setUserName(userInfo.getUser());
			agentUserInfo.setPasswd(userInfo.getPasswd());
			agentUserInfo.setAgentName(userInfo.getDesc());
			agentUserInfo.setCreateTime(userInfo.getCreateTime());
			agentUserInfo.setStatus(userInfo.getActive());
			agentUserInfoList.add(agentUserInfo);
		}
		return agentUserInfoList;
	}
	
	public List<String> converToStringList(String str) {
		String[] array = str.split(",");
		return Arrays.asList(array);
	}
	//删除用户
	public String delete(){
		// 检查输入参数
		if(ids ==null ||ids.length()==0){
			message = "删除数据时传入的参数不正确";
			return ERROR;
		}
		try {
			List<String> list = converToStringList(ids);
			int deleteCount = agetUserService.deleteAgentUser(list);
			message="实际删除记录"+deleteCount +"条";
			success=true;
			return SUCCESS;
		} catch (Exception e) {
			message = "删除记录失败";
			LOG.error(e.getMessage(), e);
			return ERROR;
		}
	}
	
	public void checkAgentUserQueryBean(AgentUserQueryBean bean){
		if(bean ==null ||limit==0){
			throw new  ParameterException("请至少输入分页条件，作为查询条件");
		}
	}
	
	
	public AgentUserInfo getInfo() {
		return info;
	}

	public void setInfo(AgentUserInfo info) {
		this.info = info;
	}

	public AgentUserQueryBean getBean() {
		return bean;
	}
	public void setBean(AgentUserQueryBean bean) {
		this.bean = bean;
	}
	public List<AgentUserInfo> getList() {
		return list;
	}
	public void setList(List<AgentUserInfo> list) {
		this.list = list;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	protected String agentMD5(String s){ 
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}
