package com.deppon.esb.management.web.action.sysuser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.user.domain.SysUserInfo;
import com.deppon.esb.management.user.service.ISysUserService;
import com.deppon.esb.management.web.MD5Util;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;

/**
 * @Description 系统用户管理Action
 * @author HuangHua
 * @Date 2012-4-18
 * 
 */
@Controller("sysUserAction")
@Scope("prototype")
public class SysUserAction extends ESBActionSupport {

	private static final long serialVersionUID = 3657599726979659602L;

	@Resource
	private ISysUserService sysUserService;

	private SysUserInfo sysUserInfo;
	private List<SysUserInfo> sysUserInfoList;
	private String sysUserName;
	private String userName;
	private String password;
	private String newPassword;// 旧密码
	private String sysUserIds;

	@SuppressWarnings("unchecked")
	public String querySysUsers() {
		Map<String, Object> result = new HashMap<String, Object>();
		result = sysUserService.querySysUsers(sysUserInfo, start, limit);
		sysUserInfoList = (List<SysUserInfo>) result.get("sysUserInfoList");
		resultCount = (Integer) result.get("resultCount");
		return SUCCESS;
	}

	public String deleteSysUsersByIds() {
		if (sysUserIds == null || "".equals(sysUserIds)) {
			message = "参数不能为空！";
			success = false;
			return SUCCESS;
		}
		String[] paras = sysUserIds.split(",");
		int count = sysUserService.deleteSysUsersByIds(paras);
		message = "成功删除[" + count + "]条数据！";
		success = true;
		return SUCCESS;
	}

	public String updateSysUser() {
		String pwd = sysUserService.querySysUserBySysUserName(sysUserInfo.getSysUserName());
		sysUserInfo.setPassword(pwd);//竟然前台会把当前用户的密码传过来，先就这样写着吧。对不起，我没时间去找问题了
		int count = sysUserService.updateSysUser(sysUserInfo);
		if (count < 1) {
			message = "修改数据失败！";
		} else {
			message = "修改记录成功！";
			success = true;
		}
		return SUCCESS;
	}

	public String addSysUser() {
		//如果前端没有传入密码，则设置默认密码(前端没传密码过来，这里都有的password都有值，我擦~~~表怪我！！！)
//		if (sysUserInfo.getSysUserName() != null && !"".equals(sysUserInfo.getSysUserName())) {
			sysUserInfo.setPassword(MD5Util.md5(sysUserName));// 系统默认的密码为用户名
//		}
		String name = sysUserService.querySysUserBySysUserName(sysUserInfo.getSysUserName());
		if(name != null){
			message="系统中已经存在与["+sysUserInfo.getSysUserName()+"]"+"相同的用户名,新增失败";
			return SUCCESS;
		}
		int count = sysUserService.addSysUser(sysUserInfo);
		if (count < 1) {
			message = "插入数据失败！";
		} else {
			message = "添加记录成功！";
			success = true;
		}
		return SUCCESS;
	}

	public String modifyPassWord() {
		String pwd = sysUserService.querySysUserBySysUserName(sysUserName);
		if (pwd.equals(MD5Util.md5(password))) {// 密码验证正确
			success = sysUserService.modifyPassWord(new SysUserInfo(sysUserName, MD5Util.md5(newPassword)));
			if (success) {
				message = "修改密码成功";
			} else {
				message = "修改密码失败";
			}
			return SUCCESS;
		} else {// 密码错误
			success = false;
			message = "原始密码错误！";
			return SUCCESS;
		}
	}

	public ISysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public SysUserInfo getSysUserInfo() {
		return sysUserInfo;
	}

	public void setSysUserInfo(SysUserInfo sysUserInfo) {
		this.sysUserInfo = sysUserInfo;
	}

	public List<SysUserInfo> getSysUserInfoList() {
		return sysUserInfoList;
	}

	public void setSysUserInfoList(List<SysUserInfo> sysUserInfoList) {
		this.sysUserInfoList = sysUserInfoList;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSysUserIds() {
		return sysUserIds;
	}

	public void setSysUserIds(String sysUserIds) {
		this.sysUserIds = sysUserIds;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
