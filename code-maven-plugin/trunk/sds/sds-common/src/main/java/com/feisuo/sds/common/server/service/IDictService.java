package com.feisuo.sds.common.server.service;

import com.feisuo.sds.base.server.service.IBaseService;
import com.feisuo.sds.common.share.entity.DictEntity;
import com.feisuo.sds.common.share.entity.DictValueEntity;

public interface IDictService extends IBaseService<DictEntity, String> {
	
	int editDictValueStatusService(DictValueEntity dictValueEntity);

}
