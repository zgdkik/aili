package com.yimidida.ymdp.dao.test.server.dao;

import org.apache.ibatis.annotations.Param;

import com.yimidida.ymdp.dao.server.IBaseDao;
import com.yimidida.ymdp.dao.test.server.model.Order;
import com.yimidida.ymdp.dao.test.server.model.Person;

public interface IPersonDao extends  IBaseDao<Person, Long> {

	Person selectPersonFetchOrder(@Param("id") Long id);
	
	Order selectOrdersFetchPerson(@Param("id") Long id);
}

