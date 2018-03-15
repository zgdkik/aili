package com.feisuo.sds.demo.server.service;

import org.hbhk.aili.mybatis.server.support.Pagination;

import com.feisuo.sds.base.server.service.IBaseService;
import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.demo.share.entity.DemoEntity;

public interface IDemoService extends IBaseService<DemoEntity, String> {

	DemoEntity getDemo(String id);

	Pagination<DemoEntity> getPage(QueryPageVo queryPageVo);

	void save(DemoEntity demo);

}
