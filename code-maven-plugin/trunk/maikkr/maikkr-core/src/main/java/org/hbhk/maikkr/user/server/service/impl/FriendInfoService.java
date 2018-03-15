package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.user.server.dao.IFriendInfoDao;
import org.hbhk.maikkr.user.server.service.IFriendInfoService;
import org.hbhk.maikkr.user.share.pojo.FriendInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * FriendInfoManager
 * @author  何波
 *
 */
@Transactional
@Service 
public class FriendInfoService implements IFriendInfoService {
	public  Logger log = LoggerFactory.getLogger(FriendInfoService.class);
	@Autowired
	private IFriendInfoDao friendInfoDao;

	/**
	 * 保存FriendInfo
	 */
	public FriendInfo save(FriendInfo model){
		model.setCreateTime(new Date());
		model.setId(UUIDUitl.getUuid());
		return friendInfoDao.save(model);
	}
	
	/**
	 *修改FriendInfo
	 */
	public FriendInfo update(FriendInfo model){
		model.setUpdateTime(new Date());
		return friendInfoDao.update(model);
	}
	
	
	public FriendInfo getOne(FriendInfo model){
		return friendInfoDao.getOne(model);
	}
	
	public List<FriendInfo> get(FriendInfo model){
		return friendInfoDao.get(model);
	}
	
	public List<FriendInfo> get(FriendInfo model,Page page){
		return friendInfoDao.get(model,page);
	}

	/**
	 * 获取所有FriendInfo列表
	 * @return
	 */
	public List<FriendInfo> findAllFriendInfoList(){
	
		return friendInfoDao.findAllFriendInfoList();
	};
	
	/**
	 * 通过ids获取FriendInfo列表
	 * @param ids
	 * @return
	 */
	public List<FriendInfo> findFriendInfoListByIds(List<Long> ids){
	
		return friendInfoDao.findFriendInfoListByIds(ids);
	};
	
	/**
	 * 通过参数map获取FriendInfo列表
	 * @param paraMap
	 * @return
	 */
	public List<FriendInfo> findFriendInfoListByQueryMap(Map<String, Object> paraMap){
	
		return friendInfoDao.findFriendInfoListByQueryMap(paraMap);
	};
	
	/**
	 * 分页获取FriendInfo列表
	 * @param start
	 * @param size
	 * @param paraMap
	 * @param sorts 
	 * @return
	 */
	public Pagination<FriendInfo> findFriendInfoListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> paraMap){
	
		return friendInfoDao.findFriendInfoListByQueryMapWithPage(page,sorts,paraMap);
	};
	
	
	
	/**
	 * 获取有效的FriendInfo列表
	 * status =1
	 * @param ids
	 * @return
	 */
	public List<FriendInfo> findAllEffectFriendInfoList(){
	
		return friendInfoDao.findAllEffectFriendInfoList();
	};
	
	/**
	 * 通过参数map获取有效的FriendInfo列表
	 * 强制加上status =1
	 * @param paraMap
	 * @return
	 */
	public List<FriendInfo> findEffectFriendInfoListByQueryMap(Map<String, Object> paraMap){
	
		return friendInfoDao.findEffectFriendInfoListByQueryMap(paraMap);
	}

	
	
}
