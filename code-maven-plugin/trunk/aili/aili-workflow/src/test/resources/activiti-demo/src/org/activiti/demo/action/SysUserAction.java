package org.activiti.demo.action;

import javax.annotation.Resource;

import org.activiti.demo.base.BaseAction;
import org.activiti.demo.domain.SysUser;
import org.activiti.demo.service.SysUserService;
import org.activiti.demo.utils.StringUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ch.qos.logback.classic.Logger;

/**
 * 
 * @Title: SysUserAction.java
 * @Description: org.activiti.demo.action
 * @Package org.activiti.demo.action
 * @author hncdyj123@163.com
 * @date 2013-3-14
 * @version V1.0
 * 
 */
@Controller("sysUserAction")
@Scope("protype")
@Namespace("/activiti/user")
@ParentPackage("json-default")
public class SysUserAction extends BaseAction {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(SysUserAction.class);
	@Resource(name = "sysUserService")
	private SysUserService sysUserService;
	private SysUser sysUser;

	@Action(value = "login", results = { @Result(type = "json", params = { "root", "result" }) })
	public String login() {
		if (sysUser == null) {
			sysUser = new SysUser();
		}
		try {
			SysUser loginUser = sysUserService.findSysUserByID(sysUser);
			if (loginUser != null && StringUtil.isEqualString(loginUser.getPassword(), sysUser.getPassword())) {
				super.getSession().put("user", loginUser);
			} else {
				super.setMessage("用户名不存在或密码错误!");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			super.setSuccess(false);
			return ERROR;
		}
		return SUCCESS;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}
