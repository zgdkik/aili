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
import org.hbhk.maikkr.user.share.pojo.MsgInfo;

/**
 * MsgInfoDao
 * @author  何波
 *
 */
public interface IMsgInfoDao extends GenericEntityDao<MsgInfo,String>{

	/**
	 * 获取所有MsgInfo列表
	 * @return
	 */
	@NativeQuery(model = MsgInfo.class)
	List<MsgInfo> findAllMsgInfoList();
	
	/**
	 * 通过ids获取MsgInfo列表
	 * @param ids
	 * @return
	 */
	@NativeQuery(model = MsgInfo.class)
	List<MsgInfo> findMsgInfoListByIds(@QueryParam("ids")List<Long> ids);
	
	/**
	 * 通过参数map获取MsgInfo列表
	 * @param paraMap
	 * @return
	 */
	@NativeQuery(model = MsgInfo.class)
	List<MsgInfo> findMsgInfoListByQueryMap(@QueryParam Map<String, Object> paraMap);
	
	/**
	 * 分页获取MsgInfo列表
	 * @param start
	 * @param size
	 * @param paraMap
	 * @param sorts 
	 * @return
	 */
	@NativeQuery(model = MsgInfo.class)
	Pagination<MsgInfo> findMsgInfoListByQueryMapWithPage(Page page,Sort[] sorts,@QueryParam Map<String, Object> paraMap);
	
	
	
	/**
	 * 通过ids批量启用或禁用MsgInfo
	 * 设置status =0 或 1
	 * @param ids
	 * @return
	 */
	@NativeUpdate
	void updateStatusMsgInfoByIds(@QueryParam("ids")List<Long> ids,@QueryParam("state")Integer status);
	
	/**
	 * 获取有效的MsgInfo列表
	 * status =1
	 * @param ids
	 * @return
	 */
	@NativeQuery(model = MsgInfo.class)
	List<MsgInfo> findAllEffectMsgInfoList();
	
	/**
	 * 通过参数map获取有效的MsgInfo列表
	 * 强制加上status =1
	 * @param paraMap
	 * @return
	 */
	@NativeQuery(model = MsgInfo.class)
	List<MsgInfo> findEffectMsgInfoListByQueryMap(@QueryParam Map<String, Object> paraMap);
	
	/**
	 * 分页获取有效的MsgInfo列表
	 * 强制加上status =1
	 * @param start
	 * @param size
	 * @param paraMap
	 * @param sorts 
	 * @return
	 */
	@NativeQuery(model = MsgInfo.class)
	Pagination<MsgInfo> findEffectMsgInfoListByQueryMapWithPage(Page page,Sort[] sorts,@QueryParam Map<String, Object> paraMap);
	
	
}
