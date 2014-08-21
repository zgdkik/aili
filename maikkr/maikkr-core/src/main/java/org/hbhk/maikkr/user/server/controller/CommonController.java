package org.hbhk.maikkr.user.server.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.core.share.util.EncryptUtil;
import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.aili.support.server.email.IEmailService;
import org.hbhk.aili.support.server.email.impl.EmailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class CommonController extends BaseController {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IUserService userService;
	@Autowired
	private IEmailService emailService;
	@RequestMapping("/findPwd")
	@ResponseBody
	public ResponseEntity findPwd(String user, String email) {
		UserInfo quser = new UserInfo();
		quser.setMail(user);
		if (userService.getUser(quser) == null) {
			return returnException("用户名不存在");
		}
		quser.setRemail(email);
		if ((quser=userService.getUser(quser)) == null) {
			return returnException("用户名与绑定邮箱不一致");
		}
		UserInfo puser = new UserInfo();
		puser.setId(quser.getId());
		String pwd = EncryptUtil.encodeSHA1("123456");
		puser.setPassword(pwd);
		userService.update(puser);
		EmailInfo qemail = new EmailInfo();
		qemail.setSubject("买客买家网-密码找回");
		qemail.setEmail(email);
		qemail.setContext("你的用户名["+user+"]重置密码:123456\r"+"官网地址:www.maikkr.com");
		try {
			emailService.sendEmail(qemail);
		} catch (MessagingException e) {
			log.error("send email error", e);
		} catch (IOException e) {
			log.error("send email error", e);
		}
		return returnSuccess("找回成功,系统重新生成密码发到了你的邮箱，请注意查收");
	}

}
