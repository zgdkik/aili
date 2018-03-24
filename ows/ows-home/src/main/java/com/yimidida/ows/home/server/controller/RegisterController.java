package com.yimidida.ows.home.server.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.util.MD5Util;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.home.server.service.IOrderInfoService;
import com.yimidida.ows.home.server.service.IOwsUserService;
import com.yimidida.ows.home.server.service.IUserLoginService;
import com.yimidida.ows.home.server.service.IUserPeopleService;
import com.yimidida.ows.home.server.service.impl.UserLoginService;
import com.yimidida.ows.home.server.util.Guid;
import com.yimidida.ows.home.server.util.SendPhoneMsg;
import com.yimidida.ows.home.share.entity.OwsUser;
import com.yimidida.ows.home.share.entity.UserLogin;
import com.yimidida.ows.home.share.entity.UserPeople;
import com.yimidida.ows.home.share.vo.OwsUserInfo;
import com.yimidida.ymdp.core.share.entity.ResultEntity;
import com.yimidida.ymdp.core.share.util.JsonUtil;

@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController {
	@Autowired IOwsUserService owsUserService;
	@Autowired IUserPeopleService userPeopleService;
	@Autowired IUserLoginService userLoginService;
	
	
	//注册短信
	@RequestMapping("getPhoneCode")
	@ResponseBody
	public Object getPhoneCode(String phone,String phonecode,HttpSession session) {
		if (phone == null) {
			return 0;//无号码
		}
		String sphonecode = String.valueOf(session.getAttribute("phonecode"));
		if(phonecode!=null){
			if(!phonecode.toUpperCase().equals(sphonecode)){
				return 3;
			}
		}
		String code = getCode().toString();
		String content = "注册验证码:" + code
				+ ",有效时间为30分钟,如不是您本人操作请忽略此短信,谢谢!";// 发送的内容
		if(SendPhoneMsg.sendPhoneCode(phone, content)){
			//返回00则发送成功
			session.setAttribute(phone, code);
			System.out.println("注册验证码："+code);
			return 1;//成功
		}else{
			return 2;//失败
		}
		
	}
	//手机注册验证码（找回密码也用此验证码）
	@RequestMapping("/createCode/phonecertpic")
	public String phonecertpic(Model model,String type,String title) {
		
		return "createCode/phonecertpic";
	}
	
	//邮箱注册验证码
	@RequestMapping("/createCode/mailcertpic")
	public String mailcertpic(Model model,String type,String title) {
		
		return "createCode/mailcertpic";
	}
	//检查手机号/用户名
	@RequestMapping("/checkPhone")
	@ResponseBody
	public ResultEntity checkPhone(String phone,String compCode) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("compCode", "ddwl");
		params.put("userName", phone);
		List<OwsUser> list=owsUserService.get(params);
		int flag=0;
		if(list.size()>0){
			flag=1;
		}
		return returnSuccess(flag);
	}
	//邮箱唯一性检查
	@RequestMapping("/checkMail")
	@ResponseBody
	public ResultEntity checkMail(String phone,String mail) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("compCode", "ddwl");
		params.put("email", mail);
		List<OwsUser> list=owsUserService.get(params);
		int flag=0;
		if(list.size()>0){
			flag=1;
		}
		return returnSuccess(flag);
	}
	
	//检查验证码
	@RequestMapping("/checkPhoneCode")
	@ResponseBody
	public ResultEntity checkPhoneCode(HttpSession session,String phonecode) {
		String sessionCode=session.getAttribute("phonecode").toString();
		int flag=0;
		if(phonecode.equalsIgnoreCase(sessionCode)){
			flag=1;
		}else{
		}
		return returnSuccess(flag);
	}
	//检查验证码
	@RequestMapping("/checkMailCode")
	@ResponseBody
	public ResultEntity checkMailCode(HttpSession session,String mailCode) {
		String sessionCode=session.getAttribute("mailcode").toString();
		int flag=0;
		if(mailCode.equalsIgnoreCase(sessionCode)){
			flag=1;
		}else{
		}
		return returnSuccess(flag);
	}
	//检查手机短信验证码
	@RequestMapping("/checkSendCode")
	@ResponseBody
	public ResultEntity checkSendCode(HttpSession session,String phoneCode,String phoneNo) {
		String sessionCode="";
		if(session.getAttribute(phoneNo)!=null){
			sessionCode=session.getAttribute(phoneNo).toString();
		}
		int flag=0;
		if(phoneCode.equalsIgnoreCase(sessionCode)){
			flag=1;
		}
		return returnSuccess(flag);
	}
	
	
	//生成6位验证码
	private Integer getCode() {
		return (int) ((Math.random() * 9 + 1) * 100000);
	}
	//手机注册
	@RequestMapping("/mobileRegist")
	@ResponseBody
	public ResultEntity mobileRegist(OwsUser user,
			HttpSession session) {
		user.setCreateTime(new Date());
		user.setId(UuidUtil.getUuid());
		user.setPassword(MD5Util.encode(user.getPassword()));
		int flag=owsUserService.insert(user);
		return returnSuccess(flag);
	}
	//登录
	@RequestMapping("/login")
	@ResponseBody
	public ResultEntity login(String userName,String password,
			HttpSession session) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("password", MD5Util.encode(password));
		//获取用户信息
		List<OwsUser> users=owsUserService.get(params);
		
		int state=0;
		if(users.size()>0){
			OwsUser user=users.get(0);
			session.setAttribute("user", user);
			state=1;
		}
		return returnSuccess(state);
	}
	
//	//登录
//	@RequestMapping("/loginForWx")
//	@ResponseBody
//	public ResultEntity loginForWx(HttpSession session,String userName,String password,
//			String openid) {
//		Map<String, Object> params=new HashMap<String, Object>();
//		params.put("userName", userName);
//		params.put("password", MD5Util.encode(password));
//		//查询用户信息
//		List<OwsUser> users=owsUserService.get(params);
//		String msg="登录成功";
//		if(users.size()<1){
//			msg="用户名或密码错误";
//			return returnSuccess(msg);
//		}else{
//			String userId=users.get(0).getId();
//			Map<String, Object> userParam=new HashMap<String, Object>();
//			Map<String, Object> loginParam=new HashMap<String, Object>();
//			userParam.put("userId", userId);//获取当前用户的id
//			loginParam.put("openid", openid);
//			//查询用户联系人信息
//			List<UserPeople> peopleList = userPeopleService.getByType(userParam);
//			//查询登录信息
//			List<UserLogin> loginList = userLoginService.get(loginParam);
//			UserLogin userLogin=new UserLogin();
//			if(loginList.size()>0){
//				userLogin=loginList.get(0);
//				userLogin.setIsLoginout(0);
//				if(StringUtils.isNotEmpty(userId)){
//					userLogin.setUserId(userId);
//				}
//				userLoginService.update(userLogin);
//			}else{
//				userLogin.setId(UuidUtil.getUuid());
//				userLogin.setIsLoginout(0);
//				userLogin.setLoginType(2);
//				userLogin.setOpenid(openid);
//				userLogin.setUserId(userId);
//				userLoginService.insert(userLogin);
//			}
//			OwsUserInfo userinfo=new OwsUserInfo();
//			users.get(0).setOpenid(openid);
//			userinfo.setUser(users.get(0));
//			userinfo.setPeople(peopleList);
//			return returnSuccess(userinfo);
//		}
//		
//	}
	//登录
		@RequestMapping("/loginForWx")
		@ResponseBody
		public ResultEntity loginForWx(HttpSession session,String userName,String password,
				String openid) {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("userName", userName);
			params.put("password", MD5Util.encode(password));
			//查询用户信息
			List<OwsUser> users=owsUserService.get(params);
			String msg="登录成功";
			if(users.size()<1){
				msg="用户名或密码错误";
				return returnSuccess(msg);
			}else{
				String userId=users.get(0).getId();
				Map<String, Object> userParam=new HashMap<String, Object>();
				userParam.put("userId", userId);//获取当前用户的id
				//查询用户联系人信息
				List<UserPeople> peopleList = userPeopleService.getByType(userParam);
				//查询登录信息
				OwsUserInfo userinfo=new OwsUserInfo();
				users.get(0).setOpenid(openid);
				userinfo.setUser(users.get(0));
				userinfo.setPeople(peopleList);
				return returnSuccess(userinfo);
			}
			
		}
	
	
	
	//根据openid获取用户信息 微信
	@RequestMapping("/getUserByOpenid")
	@ResponseBody
	public ResultEntity getUserByOpenid(String openid) {
		//判断 openid是否存在
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("openid", openid);
		List<UserLogin> logins=userLoginService.get(params);
		String msg="";
		if(logins.size()<1){
			msg="该微信不存在登录记录";
			return returnSuccess(msg);
		}else{
			UserLogin userLogin=logins.get(0);
			if(userLogin.getIsLoginout()==1){
				msg="该用户已注销";
				return returnSuccess(msg);
			}else{
				Map<String, Object> userParams=new HashMap<String, Object>();
				Map<String, Object> peopleParams=new HashMap<String, Object>();
				userParams.put("id", userLogin.getUserId());
				List<OwsUser> users=owsUserService.get(userParams);
				OwsUser user=users.get(0);
				user.setOpenid(openid);
				//查询用户联系人信息
				peopleParams.put("userId", user.getId());
				List<UserPeople> peopleList = userPeopleService.getByType(peopleParams);
				OwsUserInfo userinfo=new OwsUserInfo();
				userinfo.setUser(user);
				userinfo.setPeople(peopleList);
				return returnSuccess(userinfo);
				
			}
		}
	
	}
			
	//注销
	@RequestMapping("/logout")
	@ResponseBody
	public ResultEntity login(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("traceOrderNo");
		return returnSuccess();
	}
	//注销
	@RequestMapping("/logoutForWx")
	@ResponseBody
	public ResultEntity logoutForWx(HttpSession session,String userId) {
		Map<String, Object> loginParam=new HashMap<String, Object>();
		loginParam.put("userId", userId);
		List<UserLogin> loginList = userLoginService.get(loginParam);
		int flag=0;
		if(loginList.size()>0){
			UserLogin userLogin=loginList.get(0);
			userLogin.setIsLoginout(1);
			flag=userLoginService.update(userLogin);
		}
		if(flag==1){
			return returnSuccess();
		}else{
			return returnException();
		}
		
	}
	
	//微信注册
	@RequestMapping("/registerForWx")
	@ResponseBody
	public ResultEntity registerForWx(HttpServletRequest request){
		OwsUser user=JsonUtil.parseJson(request.getParameter("jsonData"), OwsUser.class);
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("compCode", user.getCompCode());
		params.put("userName", user.getUserName());
		List<OwsUser> list=owsUserService.get(params);
		int flag=0;
		if(list.size()>0){
			flag=1;//用户名已存在
		}else{
			user.setId(UuidUtil.getUuid());
			user.setPassword(MD5Util.encode(user.getPassword()));
			user.setCreateTime(new Date());
			if(owsUserService.insert(user)!=1){
				flag=2;//保存失败
			}
		}
		return returnSuccess(flag);
		
	}

	//找回密码页面 查询用户是否存在
	@RequestMapping(value="findone",method = RequestMethod.POST)
	@ResponseBody
	public Object findUserInfoByLoginName(String username,String findcode,HttpSession session) {
		//1成功 2失败 3验证码错误
		String code = session.getAttribute("phonecode").toString();
		if(!findcode.toUpperCase().equals(code)){
			return 3;
		}
		OwsUser u = owsUserService.findUserInfoByLoginName(username);
		if (u != null) {
			session.setAttribute("owsUser", u);
			return 1;
		} else {
			return 2 ;
		}
	}
	//找回密码短信
	@RequestMapping(value="getFindCode",method = RequestMethod.POST)
	@ResponseBody
	public Object getFindCode(HttpSession session) throws IOException {
		OwsUser user = (OwsUser)session.getAttribute("owsUser");
		if (user==null) {
			return 0;//无号码
		}else{
			String code = getCode().toString();
			String content = "密码找回验证码:" + code
					+ ",有效时间为30分钟,如不是您本人操作请忽略此短信,谢谢!";
			// 发送的内容
			if(SendPhoneMsg.sendPhoneCode(user.getUserName(), content)){
				//返回00则发送成功
				session.setAttribute(user.getUserName(), code);
				System.out.println("找回验证码："+code);
				return 1;//成功
			}else{
				return 2;//失败
			}
		}
	}
	
	//发送邮件
	@RequestMapping(value="sendMail",method = RequestMethod.POST)
	public void sendMail(HttpSession session,HttpServletRequest request){
		OwsUser user = (OwsUser)session.getAttribute("owsUser");
		String userName = user.getUserName();
		String mail = user.getEmail();
		String secretKey = Guid.getGuid(); // 密钥
        Timestamp outTime = new Timestamp(System.currentTimeMillis() + 60 * 60 * 1000);// 60分钟后过期
        //讲失效时间和密钥保存到数据库
        Map mailMap = new HashMap();
        mailMap.put("outTime", outTime);
        mailMap.put("mailKey", secretKey);
        mailMap.put("userName", userName);
        try {
        	String key =userName + "$" + secretKey;  
            String digitalSignature = MD5Util.encode(key);// 用户名时间戳密钥加密
            //生成URL路径
            String path = request.getRequestURI();
            String[] pathStr_1 = path.split("/");
//          String pathStr = "/"+pathStr_1[1]+"/";
            String pathStr = "";
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":"
                    + request.getServerPort() + pathStr;
            String resetPassHref = basePath + "/ddwlGw/forgetPassPord_t?sid="
                    + digitalSignature +"&username="+userName+"&i=2";
            //内容
            StringBuffer emailContent = new StringBuffer();
            emailContent.append("<div style='width:500px;height:400px;border:1px solid green;padding:15px;margin: 0 auto;font-size: 13px;font-family: 微软雅黑;border: 3px solid #fcb814;border-radius: 10px;'>");
            emailContent.append("<img src='"+basePath+"/images/logo.png'><hr style='border-top:1px solid #fcb814'>");
            emailContent.append("<p style='font-size: 16px;'>尊敬的大道物流会员：</p>");
            emailContent.append("<p>您当前正使用邮箱找回密码,请<a href="+resetPassHref+" target='_BLANK'>点击重置密码</a>或者复制以下链接并打开进行重置密码。</p>");
            emailContent.append("<p><a href="+resetPassHref+" target='_BLANK'>"+resetPassHref+"</a></p>");
            emailContent.append("<p>友情提示：以上链接的有效性为60分钟，为了您的隐私安全请勿将链接告诉其他人。此邮件为大道物流官方发出，请勿回复！</p><br><br>");
            emailContent.append("<hr style='border-top:1px solid #ef7f1b'><p>大道物流官网：www.ddwl.com.cn<br>大道客服电话：027-83550339</p>");
            emailContent.append("</p></div>");
            
            if(new com.yimidida.ows.home.server.util.MailUtil().sendMail(mail, emailContent.toString())){
            	owsUserService.modifyMailTime(mailMap);
            	System.out.println("邮件发送成功"+mail);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
	//解析url并检查有效时间
	@RequestMapping(value="sendMailCheckLink",method = RequestMethod.POST)
	@ResponseBody
	public String sendMailCheckLink(HttpServletRequest request){
		String sid=request.getParameter("sid");
		String username=request.getParameter("username");
		if (StringUtils.isBlank(sid) || StringUtils.isBlank(username)) {
            return "2";
        }
		OwsUser us = owsUserService.checkUserName(username);
		if(us!=null){
			if(us.getOutTime()!=null&&us.getMailKey()!=null){
				String outTime=us.getOutTime().substring(0,19);
				try {
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = sdf.parse(outTime);
					if(date.getTime() <= System.currentTimeMillis()){ //表示已经过期
						return "2";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String key = us.getUserName()+"$"+us.getMailKey();
				String digitalSignature =MD5Util.encode(key);// 数字签名加密
				if(!digitalSignature.equals(sid)) {
					return "2";
				}else {
					//链接验证通过 转到修改密码页面
					return "1";
				}
			}else{
				return "2";
			}
		}else{
			return "2";
		}
	}
	
	/**
	 * 邮箱重置密码
	 * @param username
	 * @param newpassword
	 * @return
	 */
	@RequestMapping(value="resetPass",method = RequestMethod.POST)
	@ResponseBody
	public Object resetPass(String username, String newpassword,String sid) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(newpassword) ||StringUtils.isBlank(sid)) {
			return 2;
		}
	//	OwsUser us = owsUserService.checkUserName(username);
//		String key = us.getUserName()+"$"+us.getMailKey();
//		String digitalSignature =MD5Util.encode(key);// 数字签名加密
//		if(!digitalSignature.equals(sid)) {
//			return 2;
//		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("newPassword", MD5Util.encode(newpassword));
		map.put("username", username);
		try {
			if (owsUserService.modifyPassword(map)) {
				Map mailMap = new HashMap();
				mailMap.put("outDate", null);
		        mailMap.put("validateCode", null);
		        mailMap.put("username", username);
		        owsUserService.modifyMailTime(mailMap);
		        return 1;
			} else {
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	
	
	@RequestMapping(value="checkFindCode",method = RequestMethod.POST)
	@ResponseBody
	public Object checkFindCode(String code,HttpSession session){
		OwsUser user = (OwsUser)session.getAttribute("owsUser");
		if (code==null||"".equals(code)||user==null) {
			return 2;
		}
		String phone = user.getUserName();
		String userp = session.getAttribute(phone).toString();
		if(code.equals(userp)){
			session.removeAttribute("owsUser");
			session.setAttribute("owsUserTwo", user);
			return 1;
		}else{
			return 2;
		}
	}
	
	//手机重置密码
	@RequestMapping(value="modifyPass", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyPass(String newpassword,HttpSession session) {
		OwsUser user = (OwsUser)session.getAttribute("owsUserTwo");
		if (user==null || user.getUserName()==null ||"".equals(user.getUserName()) ||newpassword==null ||"".equals(newpassword)) {
			return 2;
		}
		String username = user.getUserName();
		Map<String, String> map = new HashMap<String, String>();
		map.put("newPassword", MD5Util.encode(newpassword));
		map.put("username", username);
		try {
			if (owsUserService.modifyPassword(map)) {
				session.removeAttribute("owsUserTwo");
				session.removeAttribute(username);
				//成功
				return 1;
			} else {
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}
}
