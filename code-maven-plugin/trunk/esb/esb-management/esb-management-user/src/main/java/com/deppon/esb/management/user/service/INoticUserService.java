package com.deppon.esb.management.user.service;

import java.util.Map;

import com.deppon.esb.management.user.domain.NoticUserInfo;

/**
 * The Interface INoticUserService.
 * 
 * @Description 预警用户服务
 * @author HuangHua
 * @date 2012-5-25下午4:59:24
 */
public interface INoticUserService {

	/**
	 * Query notic users.
	 * 
	 * @param userName
	 *            the user name
	 * @param telPhone
	 *            the tel phone
	 * @param mobilePhone
	 *            the mobile phone
	 * @param email
	 *            the email
	 * @param start
	 *            the start
	 * @param limit
	 *            the limit
	 * @return the map
	 */
	public Map<String, Object> queryNoticUsers(String userName, String telPhone, String mobilePhone, String email, int start, int limit);

	/**
	 * Delete notic users by ids.
	 * 
	 * @param paras
	 *            the paras
	 * @return the int
	 */
	public int deleteNoticUsersByIds(String[] paras);

	/**
	 * Update noticuser.
	 * 
	 * @param noticUserInfo
	 *            the notic user info
	 * @return the int
	 */
	public int updateNoticuser(NoticUserInfo noticUserInfo);

	/**
	 * 添加 notic user.
	 * 
	 * @param noticUserInfo
	 *            the notic user info
	 * @return the int
	 */
	public int addNoticUser(NoticUserInfo noticUserInfo);
	
	/**
	 * Query sys users.
	 * 
	 * @param userName
	 *            the user name
	 * @param telPhone
	 *            the tel phone
	 * @param mobilePhone
	 *            the mobile phone
	 * @param email
	 *            the email
	 * @param start
	 *            the start
	 * @param limit
	 *            the limit
	 * @return the map
	 */
	public Map<String, Object> querySysUsers(String userName, String telPhone, String mobilePhone, String email, int start, int limit);
	
}
