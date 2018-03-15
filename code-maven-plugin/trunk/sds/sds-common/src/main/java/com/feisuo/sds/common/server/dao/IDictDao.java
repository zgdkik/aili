package com.feisuo.sds.common.server.dao;


import org.mybatis.spring.dao.IBaseDao;

import com.feisuo.sds.common.share.entity.DictEntity;
import com.feisuo.sds.common.share.entity.DictValueEntity;

public interface IDictDao extends IBaseDao<DictEntity, String> {

     int editDictValueStatus(DictValueEntity dictValueEntity);
}
