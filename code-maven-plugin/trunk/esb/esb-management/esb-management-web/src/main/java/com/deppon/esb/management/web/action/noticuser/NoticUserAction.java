package com.deppon.esb.management.web.action.noticuser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.user.domain.NoticUserInfo;
import com.deppon.esb.management.user.service.INoticUserService;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;

/**
 * @Description 预警用户管理Action
 * @author HuangHua
 * @Date 2012-4-10
 * 
 */
@Controller("noticUserAction")
@Scope("prototype")
public class NoticUserAction extends ESBActionSupport {

	private static final long serialVersionUID = 7541110987313302990L;

	@Resource
	public INoticUserService noticUserService;

	private String noticUserId;
	private String userName;
	private String telPhone;// 电话，用于发送语音通知
	private String mobilePhone;// 移动电话，用于接收短信通知
	private String email;// 邮件
	private NoticUserInfo noticUserInfo;
	private List<NoticUserInfo> noticUserInfoList;
	private String noticUserIds;

	@SuppressWarnings("unchecked")
	public String queryNoticUsers() {
		Map<String, Object> result = new HashMap<String, Object>();
		// result = noticUserService.queryNoticUsers(start,limit);
		result = noticUserService.queryNoticUsers("".equals(userName) ? null : userName, "".equals(telPhone) ? null : telPhone,
				"".equals(mobilePhone) ? null : mobilePhone, "".equals(email) ? null : email, start, limit);
		noticUserInfoList = (List<NoticUserInfo>) result.get("noticUserInfoList");
		resultCount = (Integer) result.get("resultCount");
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String querySystemUsers() {
		Map<String, Object> result = new HashMap<String, Object>();
		// result = noticUserService.queryNoticUsers(start,limit);
		result = noticUserService.querySysUsers("".equals(userName) ? null : userName, "".equals(telPhone) ? null : telPhone,
				"".equals(mobilePhone) ? null : mobilePhone, "".equals(email) ? null : email, start, limit);
		noticUserInfoList = (List<NoticUserInfo>) result.get("noticUserInfoList");
		resultCount = (Integer) result.get("resultCount");
		return SUCCESS;
	}

	public String deleteNoticUsersByIds() {
		if (noticUserIds == null || "".equals(noticUserIds)) {
			message = "参数不能为空！";
			return SUCCESS;
		}
		String[] paras = noticUserIds.split(",");
		int count = noticUserService.deleteNoticUsersByIds(paras);
		message = "成功删除[" + count + "]条数据！";
		success = true;
		return SUCCESS;
	}

	public String updateNoticUser() {
		int count = noticUserService.updateNoticuser(noticUserInfo);
		if (count < 1) {
			message = "修改数据失败!";
		} else {
			message = "修改数据成功！";
			success = true;
		}
		return SUCCESS;
	}

	public String addNoticUser() {
		int count = noticUserService.addNoticUser(noticUserInfo);
		if (count < 1) {
			message = "插入数据失败！";
		} else {
			message = "添加记录成功！";
			success = true;
		}
		return SUCCESS;
	}

	public String getNoticUserId() {
		return noticUserId;
	}

	public void setNoticUserId(String noticUserId) {
		this.noticUserId = noticUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public NoticUserInfo getNoticUserInfo() {
		return noticUserInfo;
	}

	public void setNoticUserInfo(NoticUserInfo noticUserInfo) {
		this.noticUserInfo = noticUserInfo;
	}

	public List<NoticUserInfo> getNoticUserInfoList() {
		return noticUserInfoList;
	}

	public void setNoticUserInfoList(List<NoticUserInfo> noticUserInfoList) {
		this.noticUserInfoList = noticUserInfoList;
	}

	public String getNoticUserIds() {
		return noticUserIds;
	}

	public void setNoticUserIds(String noticUserIds) {
		this.noticUserIds = noticUserIds;
	}

}
