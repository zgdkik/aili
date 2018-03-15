/**
 * IEnpDao.java
 * Created at 2016年1月6日
 * Created by lixiang
 * Copyright (C) 2016 NO.1 VAN, All rights reserved.
 */
package com.feisuo.sds.common.server.dao;

import java.util.List;

import org.mybatis.spring.dao.IBaseDao;

import com.feisuo.sds.common.share.vo.EnpInfoVo;

/**
 * ClassName: IEnpDao
 * Description: TODO
 * Author: lixiang
 * Date: 2016年1月6日
 */
public interface IEnpDao extends IBaseDao<EnpInfoVo, String>{
	List<EnpInfoVo> findByEnpName(String enpName);
}
