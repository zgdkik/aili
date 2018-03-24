package com.yimidida.ows.home.server.service;


import java.security.acl.Owner;
import java.util.Map;
import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.OwsUser;

//个人信息接口
public interface IOwsUserService extends IBaseService<OwsUser, String> {

	/**
	 * 根据id修改账户密码
	 * 
	 * @param id
	 * @param newPassword
	 */
	int changePassWord(String id, String newPassWord);	
	/**
	 * 修改邮箱
	 * 
	 * @param newEmail
	 * @return
	 */
	int changeEmail(OwsUser o);

	/**
	 * 根据登录名获取对象
	 * @param userName
	 * @return
	 */
	OwsUser findUserInfoByLoginName(String userName);
	
	boolean modifyMailTime(Map map);
	/**
	 * 根据用户id更新用户的个人信息
	 * @param o
	 * @return
	 */
	int updateDetailsByuserId(OwsUser o);
	
	OwsUser checkUserName(String userName);
	
	boolean modifyPassword(Map<String, String> map);
	
	/**
	 * 更换新的手机号码
	 * @param id
	 */
	void updatePhoneById(OwsUser o);
	
	/**
	 * 添加会员卡号、添加新字段会员卡号、身份证号码
	 * @param user
	 */
	void updateMemberCardById(OwsUser user);

}
