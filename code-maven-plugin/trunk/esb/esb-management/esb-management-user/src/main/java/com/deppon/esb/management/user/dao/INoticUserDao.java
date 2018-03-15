package com.deppon.esb.management.user.dao;

import java.util.List;
import java.util.Map;

import com.deppon.esb.management.user.domain.NoticUserInfo;

/**
 * The Interface INoticUserDao.
 * 
 * @Description NoticUser Dao层
 * @author HuangHua
 * @Date 2012-4-14
 */
public interface INoticUserDao {

	/**
	 * Query notic user count.
	 * 
	 * @param map
	 *            the map
	 * @return 记录条数
	 * @Description 通过用户名、电话、移动电话、电子邮件查询，都可为空
	 */
	public Integer queryNoticUserCount(Map<String, Object> map);

	/**
	 * Query notic users.
	 * 
	 * @param map
	 *            the map
	 * @return List<NoticUserInfo>
	 * @Description 通过用户名、电话、移动电话、电子邮件 分页查询预警用户，分页条件不能为空
	 */
	public List<NoticUserInfo> queryNoticUsers(Map<String, Object> map);

	/**
	 * Delete notic users by ids.
	 * 
	 * @param ids
	 *            the ids
	 * @return 删除条数
	 * @Description 通过ID删除预警用户
	 */
	public int deleteNoticUsersByIds(String[] ids);

	/**
	 * 添加 notic user.
	 * 
	 * @param noticUserInfo
	 *            the notic user info
	 * @return 添加记录数，默认为1
	 * @Description 添加预警用户
	 */
	public int addNoticUser(NoticUserInfo noticUserInfo);

	/**
	 * Update noticuser.
	 * 
	 * @param noticUserInfo
	 *            the notic user info
	 * @return 更新条数，默认为1
	 * @Description 更新预警用户
	 */
	public int updateNoticuser(NoticUserInfo noticUserInfo);
	
	/**
	 * Query notice user.
	 * 
	 * @param id
	 *            the id
	 * @return the notic user info
	 */
	public NoticUserInfo queryNoticeUser(String id);

	/**
	 * Query sys users.
	 * 
	 * @param map
	 *            the map
	 * @return 把查询出来的系统用户，封装成预警用户
	 * @Description 通过用户名、电话、移动电话、电子邮件 分页查询系统用户，分页条件不能为空
	 */
	public List<NoticUserInfo> querySysUsers(Map<String, Object> map);

	/**
	 * Query sys user count.
	 * 
	 * @param map
	 *            the map
	 * @return 查询条数
	 * @Description 通过用户名查询系统用户
	 */
	public Integer querySysUserCount(Map<String, Object> map);

	/**
	 * Query email by id.
	 * 
	 * @param ids
	 *            the ids
	 * @return String[] email，当没有查询到数据时，返回null
	 * @Description 通过用户id查询email,当没有查询到数据时，返回null
	 */
	public String[] queryEmailById(String[] ids);
	
	/**
	 * 查询需要2期告警的人员
	 * 
	 */
	List<NoticUserInfo> queryAlertUser();
	
}
