package org.hbhk.maikkr.user.server.service;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.user.share.pojo.FriendInfo;

/**
 * FriendInfoService
 * @author  何波
 *
 */
public interface IFriendInfoService extends ICommonService<FriendInfo>  {

	

	/**
	 * 获取所有FriendInfo列表
	 * @return
	 */
	List<FriendInfo> findAllFriendInfoList();
	
	/**
	 * 通过ids获取FriendInfo列表
	 * @param ids
	 * @return
	 */
	List<FriendInfo> findFriendInfoListByIds(List<Long> ids);
	
	/**
	 * 通过参数map获取FriendInfo列表
	 * @param paraMap
	 * @return
	 */
	List<FriendInfo> findFriendInfoListByQueryMap(Map<String, Object> paraMap);
	
	/**
	 * 分页获取FriendInfo列表
	 * @param start
	 * @param size
	 * @param paraMap
	 * @param sorts 
	 * @return
	 */
	Pagination<FriendInfo> findFriendInfoListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> paraMap);
	
	/**
	 * 获取有效的FriendInfo列表
	 * status =1
	 * @param ids
	 * @return
	 */
	List<FriendInfo> findAllEffectFriendInfoList();
	
	/**
	 * 通过参数map获取有效的FriendInfo列表
	 * 强制加上status =1
	 * @param paraMap
	 * @return
	 */
	List<FriendInfo> findEffectFriendInfoListByQueryMap(Map<String, Object> paraMap);
	
}
