package com.feisuo.sds.user.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.dao.IBaseDao;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;

import com.feisuo.sds.user.share.entity.UserEntity;
import com.feisuo.sds.user.share.vo.CurrentUserVo;
import com.feisuo.sds.user.share.vo.UserPhoneVo;

public interface IUserDao extends IBaseDao<UserEntity, String> {

	Pagination<UserEntity> queryUserInfoByPage(Page page,
			Map<String, Object> map);

	int deleteUserById(String id);

	UserEntity getUserInfoById(Map<String, Object> map);

	List<CurrentUserVo> getCurrentUserList(
			@Param("userNames") String... userNames);

	List<UserEntity> validate(Map<String, Object> paraMap);

	int updateStatusByUserName(Map<String, Object> paraMap);

	int frozenByNames(Map<String, Object> map);

	int unFrozenByNames(Map<String, Object> map);

	List<UserEntity> checkUserName(String userName);


	
	
	List<UserPhoneVo> getUserPhone(Map<String, Object> params);
}
