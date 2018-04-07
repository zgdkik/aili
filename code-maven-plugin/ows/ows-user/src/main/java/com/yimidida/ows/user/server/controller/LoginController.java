package com.yimidida.ows.user.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.user.server.service.ILoginService;
import com.yimidida.ymdp.core.share.consts.AppConst;
import com.yimidida.ymdp.core.share.entity.ResultEntity;
import com.yimidida.ymdp.core.share.util.CookieUtil;

@Controller
@RequestMapping("/user")
public class LoginController extends AbstractController {
	
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private ILoginService loginService;
	

	@RequestMapping("/login")
	public String login(Model model) {
		return "admin/login";
	}

	@RequestMapping(value = "/login", params = { "userName", "password" })
	public String login(Model model, String userName, String password,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			loginService.login(userName, password, request, response);
			//根据用户类型返回 员工还是客户主界面
			return redirect("/admin/home");
		} catch (Exception e) {
			String msg = e.getMessage();
			log.error(msg, e);
			model.addAttribute("msg", msg);
			return "admin/login";
		}
		
	}

	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 存入用户编码
		session.removeAttribute(FrontendConstants.USER_SESSION_KEY);
		CookieUtil.removeCookie(response,
				CookieUtil.getCookie(request, AppConst.SEESION_CLUSTER_TOKEN));
		return  "/admin/login";
	}
	
	@RequestMapping("/getCaptcha")
	@ResponseBody
	public ResultEntity getCaptcha(String userName,String phone){
		loginService.getCaptcha(userName,phone);
	  return returnSuccess("验证码发送成功");
	}
	
	@RequestMapping("/retrievePassword")
	@ResponseBody
	public ResultEntity retrievePassword(String userName,String newPassword,String captcha){
		loginService.changePassword(userName, null, newPassword, captcha, "retrieve");
	  return returnSuccess("密码修改成功");
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public ResultEntity changePassword(String password,String newPassword){
		String userName = UserContext.getCurrentUser().getUserName();
		loginService.changePassword(userName, password, newPassword, null, "change");
	  return returnSuccess("密码修改成功");
	}
	
	@RequestMapping(value = "/forget")
	public String forget(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		
		return  "forget";
	}

}