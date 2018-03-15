package org.hbhk.hls.user.server.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.hls.user.share.entity.UserEntity;

public interface ILoginService {

	UserEntity login(String userName, String password, HttpServletRequest request,
			HttpServletResponse response);

	void changePassword(String userName, String password,String newPassword, String captcha,String type);
	
	String getCaptcha(String userName,String phone);
}
