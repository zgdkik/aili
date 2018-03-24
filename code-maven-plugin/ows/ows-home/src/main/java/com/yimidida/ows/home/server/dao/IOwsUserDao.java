package com.yimidida.ows.home.server.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.home.share.entity.OwsUser;

//个人信息DAO层
public interface IOwsUserDao extends IBaseDao<OwsUser, String> {

	/**
	 * 根据id修改账户密码
	 * @param id
	 * @param newPassword
	 */
	int changePassWord(@Param("id")String id, @Param("newPassWord")String newPassWord);

	/**
	 * 修改邮箱
	 * @param id
	 * @param newEmail
	 * @return
	 */
	int changeEmail(OwsUser o);

	/**
	 * 修改手机号
	 * @param id
	 * @param newMobile
	 */
	int changeMobile(String id, String newMobile);
	
	
	//根据登录名查询（手机/邮箱/用户名）
	java.util.List<OwsUser> queryfindUserInfoByLoginName(String userName);
	
	
	//更新邮箱找回的失效时间和密钥
	int modifyMailTime(Map map);

	/**
	 * 根据用户id更新用户的个人信息
	 * @param o
	 */
	int updateDetailsByuserId(OwsUser o);
	
	//验证用户名
	OwsUser querycheckUserName(String userName);
	
	//修改密码
	int modifyPassword(Map<String, String> map);
	/**
	 * 根据用户id更新用户的手机号码
	 * @param id
	 * @return
	 */
	void updatePhoneById(OwsUser o);
	
	/**
	 *  添加会员卡号、添加新字段会员卡号、身份证号码
	 * @param user
	 */
	void updateMemberCardById(OwsUser user);
}
