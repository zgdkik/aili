package org.hbhk.maikkr.user.server.dao;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.annotation.NativeUpdate;
import org.hbhk.aili.orm.server.annotation.QueryParam;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.user.share.pojo.FriendInfo;

/**
 * FriendInfoDao
 * @author  何波
 *
 */
public interface IFriendInfoDao extends GenericEntityDao<FriendInfo,String>{

	/**
	 * 获取所有FriendInfo列表
	 * @return
	 */
	@NativeQuery(model = FriendInfo.class)
	List<FriendInfo> findAllFriendInfoList();
	
	/**
	 * 通过ids获取FriendInfo列表
	 * @param ids
	 * @return
	 */
	@NativeQuery(model = FriendInfo.class)
	List<FriendInfo> findFriendInfoListByIds(@QueryParam("ids")List<Long> ids);
	
	/**
	 * 通过参数map获取FriendInfo列表
	 * @param paraMap
	 * @return
	 */
	@NativeQuery(model = FriendInfo.class)
	List<FriendInfo> findFriendInfoListByQueryMap(@QueryParam Map<String, Object> paraMap);
	
	/**
	 * 分页获取FriendInfo列表
	 * @param start
	 * @param size
	 * @param paraMap
	 * @param sorts 
	 * @return
	 */
	@NativeQuery(model = FriendInfo.class)
	Pagination<FriendInfo> findFriendInfoListByQueryMapWithPage(Page page,Sort[] sorts,@QueryParam Map<String, Object> paraMap);
	
	
	
	/**
	 * 通过ids批量启用或禁用FriendInfo
	 * 设置status =0 或 1
	 * @param ids
	 * @return
	 */
	@NativeUpdate
	void updateStatusFriendInfoByIds(@QueryParam("ids")List<Long> ids,@QueryParam("state")Integer status);
	
	/**
	 * 获取有效的FriendInfo列表
	 * status =1
	 * @param ids
	 * @return
	 */
	@NativeQuery(model = FriendInfo.class)
	List<FriendInfo> findAllEffectFriendInfoList();
	
	/**
	 * 通过参数map获取有效的FriendInfo列表
	 * 强制加上status =1
	 * @param paraMap
	 * @return
	 */
	@NativeQuery(model = FriendInfo.class)
	List<FriendInfo> findEffectFriendInfoListByQueryMap(@QueryParam Map<String, Object> paraMap);
	
	/**
	 * 分页获取有效的FriendInfo列表
	 * 强制加上status =1
	 * @param start
	 * @param size
	 * @param paraMap
	 * @param sorts 
	 * @return
	 */
	@NativeQuery(model = FriendInfo.class)
	Pagination<FriendInfo> findEffectFriendInfoListByQueryMapWithPage(Page page,Sort[] sorts,@QueryParam Map<String, Object> paraMap);
	
	
}
