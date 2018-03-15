package org.hbhk.aili.user.server.dao;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.mybatis.server.dao.IBaseDao;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.user.share.entity.UserEntity;
import org.hbhk.aili.user.share.vo.CurrentUserVo;

public interface IUserDao extends IBaseDao<UserEntity, String> {

	Pagination<UserEntity> queryUserInfoByPage(Page page,
			Map<String, Object> map);


	UserEntity getUserInfoById(Map<String, Object> map);

	List<CurrentUserVo> getCurrentUserList(Map<String, Object> params);




	
	
}
