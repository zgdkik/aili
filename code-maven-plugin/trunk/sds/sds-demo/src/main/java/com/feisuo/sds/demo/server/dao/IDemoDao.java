package com.feisuo.sds.demo.server.dao;

import org.hbhk.aili.mybatis.server.dao.IBaseDao;

import com.feisuo.sds.demo.share.entity.DemoEntity;

public interface IDemoDao extends IBaseDao<DemoEntity, String> {
	
	String  getDemoByid(String id);

}
