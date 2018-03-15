package com.feisuo.sds.user.server.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.cache.server.templet.ICacheTemplet;
import org.hbhk.aili.core.server.filter.CookieClusterFilter;
import org.hbhk.aili.core.server.service.IClusterTokenGenerator;
import org.hbhk.aili.core.server.service.impl.DefaultClusterTokenGenerator;
import org.hbhk.aili.core.share.consts.AppConst;
import org.hbhk.aili.core.share.entity.Token;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.util.CookieUtil;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.share.constants.FrontendConstants;
import com.feisuo.sds.base.share.util.CheckMobileUtil;
import com.feisuo.sds.common.share.request.sms.SmsContent;
import com.feisuo.sds.common.share.response.ServiceResult;
import com.feisuo.sds.common.util.SmsUitl;
import com.feisuo.sds.common.util.UuidUtil;
import com.feisuo.sds.user.server.dao.ILoginLogDao;
import com.feisuo.sds.user.server.dao.IUserDao;
import com.feisuo.sds.user.server.service.ILoginService;
import com.feisuo.sds.user.server.service.IUserRoleService;
import com.feisuo.sds.user.server.service.IUserService;
import com.feisuo.sds.user.share.entity.LoginLogEntity;
import com.feisuo.sds.user.share.entity.UserEntity;
import com.feisuo.sds.user.share.vo.UserPhoneVo;

@Service
public class LoginService implements ILoginService {
	
	private  Logger logger = LoggerFactory.getLogger(getClass());
			

	@Autowired(required = false)
	private IClusterTokenGenerator clusterTokenGenerator;

	@Autowired
	private IUserService userService;

	@Autowired
	private ILoginLogDao loginLogDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
	private ICacheTemplet<String, Object> cacheTemplet;   
	
	public static  final String password_error_count_key = "password_error_count_key_";
	
	public static  final String password_error_wait_key = "password_error_wait_key_";
	
	public static  final String captcha_key = "captcha_key_";
	
	public static  final String captcha_key_day_count = "captcha_key_day_count_";

	@Override
	public UserEntity login(String userName, String password,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("登陆处理开始:"+userName);
		if (StringUtils.isEmpty(userName)) {
			throw new BusinessException("用户名不能为空");
		}
		if (StringUtils.isEmpty(password)) {
			throw new BusinessException("密码不能为空");
		}
		UserEntity user = null;
		HttpSession session = request.getSession();
		userName = userName.trim();
		// 验证用户,并进行密码校验
		List<UserEntity> list = userService.getUserByUserName(userName);
		if (list == null || list.size() == 0) {
			throw new BusinessException("用户不存在:" + userName);
		}
		//修改密码成功时 去掉
//		Integer errorWait = (Integer) cacheTemplet.get(password_error_wait_key+userName);
//		if(errorWait != null){
//			throw new BusinessException("连续输入三次密码错误,请找回密码或等2小时再试");
//		}
		user = list.get(0);
		password = DigestUtils.md5Hex(password);
		String pwd = user.getPassword();
		if (!password.equals(pwd)) {
//			Integer count = (Integer) cacheTemplet.get(password_error_count_key+userName);
//			if(count==null){
//				count = 1;
//			}else{
//				if(count >= 3){
//					cacheTemplet.set(password_error_wait_key+userName, count, 120*60);
//					throw new BusinessException("连续输入三次密码错误,请找回密码或等2小时再试");
//				}
//				count= count + 1;
//			}
//			cacheTemplet.set(password_error_count_key+userName, count, 10*60);
			throw new BusinessException("密码错误");
		}
		List<String> roleCodes = userRoleService.getRoleIdByUserName(userName);
		if(roleCodes==null || roleCodes.isEmpty()){
			throw new BusinessException("用户未配置角色");
		}
		// 保存用户登陆日志
		LoginLogEntity log = new LoginLogEntity();
		log.setId(UuidUtil.getUuid());
		log.setCreateTime(new Date());
		log.setIp(getIpAddr(request));
		log.setLoginMethod(CheckMobileUtil.check(request));
		log.setUserCode(userName);
		loginLogDao.insert(log);
		// 存入用户编码
		Token token = new Token();
		token.setSessionId(session.getId());
		token.setTimestamp(System.currentTimeMillis() + "");
		token.setUserCode(user.getUserName());
		session.setAttribute(FrontendConstants.USER_SESSION_KEY, token);
		if (clusterTokenGenerator == null) {
			clusterTokenGenerator = new DefaultClusterTokenGenerator();
		}
		String str = clusterTokenGenerator.encodeToken(request, token);
		CookieUtil.setCookie(response, AppConst.SEESION_CLUSTER_TOKEN, str,
				CookieClusterFilter.expire);
		CookieUtil.setCookie(response, AppConst.SESSION_COOKIE_KYE, str);
		logger.info("登陆处理结束:"+userName);
		return user;
	}

	/**
	 * 获取当前网络ip
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ipAddress = "unknown";
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			if (ipAddress == null || ipAddress.length() == 0
					|| "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0
					|| "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0
					|| "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if (ipAddress.equals("127.0.0.1")
						|| ipAddress.equals("0:0:0:0:0:0:0:1")) {
					// 根据网卡取本机配置的IP
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress = inet.getHostAddress();
				}
			}
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null && ipAddress.length() > 15) {
				// "***.***.***.***".length()
				// = 15
				if (ipAddress.indexOf(",") > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
			return ipAddress;
		} catch (Exception e) {
			return "unknown";
		}

	}

	@Override
	public void changePassword(String userName, String password,String newPassword, String captcha,String type){
		List<UserEntity> list = userService.getUserByUserName(userName);
		if(list==null || list.size()==0){
			throw new BusinessException("用户不存在");
		}
		UserEntity user = list.get(0);
		//密码找回
		if("retrieve".equals(type)){
			if(StringUtils.isEmpty(captcha)){
				throw new BusinessException("请输入验证码");
			}
			//验证码有效时间半个小时
			String serverCaptcha = (String) cacheTemplet.get(captcha_key+userName);
			if(StringUtils.isEmpty(serverCaptcha)){
				throw new BusinessException("验证码已经失效,请重新获取");
			}
			if(!serverCaptcha.equals(captcha)){
				throw new BusinessException("验证码错误");
			}
		}else{
			//修改密码 
			//验证数据密码是否正确
			String pwd = user.getPassword();
			password = DigestUtils.md5Hex(password);
			if(!pwd.equals(password)){
				throw new BusinessException("原始密码输入错误");
			}
		}
		UserEntity newUser = new UserEntity();
		newUser.setId(user.getId());
		String updateUser =null;
		if(UserContext.getCurrentUser()!=null){
			updateUser = UserContext.getCurrentUser().getUserName();
		}else{
			updateUser = "system";
		}
		newUser.setUpdateUser(updateUser);
		newUser.setUpdateTime(new Date());
		newPassword = DigestUtils.md5Hex(newPassword);
		newUser.setPassword(newPassword);
		userService.updateUser(newUser);
		//成功后清除缓存数据
		if("retrieve".equals(type)){
			String[] keys=new String[]{captcha_key+userName,password_error_count_key+userName,password_error_count_key+userName};
			cacheTemplet.invalid(keys);
		}
	}

	@Override
	public String getCaptcha(String userName,String phone) {
		if(userName==null || "".equals(userName.trim())){
			throw new BusinessException("请输入用户名");
		}
		Integer num = (Integer) cacheTemplet.get(captcha_key_day_count+userName);
		if(num!=null && num>5){
			throw new BusinessException("今天获取验证码已达到"+num+"次,请12小时后再试");
		}
		if(num == null){
			num = 0;
		}
		cacheTemplet.set(captcha_key_day_count+userName, num+1, 60*60*12);
		//验证用户和手机号是否绑定一致
		List<UserEntity> list = userService.getUserByUserName(userName);
		if(list==null || list.size()==0){
			throw new BusinessException("用户不存在");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		List<UserPhoneVo> up = userDao.getUserPhone(params);
		if(CollectionUtils.isEmpty(up)){
			throw new BusinessException("用户不存在");
		}
		String userPhone = up.get(0).getPhone();
		String userPhoneNumber = up.get(0).getPhoneNumber();
		if(StringUtils.isEmpty(userPhone) && StringUtils.isEmpty(userPhoneNumber)){
			throw new BusinessException("账号:"+userName+"无绑定手机号,请联系客服");
		}
		//获取验证码
		String captcha = getSixCaptcha();
		cacheTemplet.set(captcha_key+userName, captcha, 3*60);
	
		//发送短信
		SmsContent smsContent = new SmsContent();
		String body = "验证码：("+captcha+"),3分钟内有效。";
		Log.info("验证码:"+captcha);
		smsContent.setBody(body);
		if (userPhone != null && !"".equals(userPhone)) {
			smsContent.setPhoneNumber(userPhone);
		}else {
			smsContent.setPhoneNumber(userPhoneNumber);
		}
		smsContent.setSource("智送系统");
		ServiceResult result = SmsUitl.sendSms(smsContent,"forget-password");
		if(result == null){
			throw new BusinessException("短息发送失败");
		}
		return result.getErrMsg();
	}

	private String getSixCaptcha() {
		Random rad = new Random();
		String result = rad.nextInt(1000000) + "";
		if (result.length() != 6) {
			return getSixCaptcha();
		}
		return result;
	}
}
