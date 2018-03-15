package org.hbhk.aili.crud.server.service;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;

public interface ICrudDataService {

	Pagination<Map<String, Object>> getCrudDataPage(String code,
			Map<String, Object> params, Page page);

	List<Map<String, Object>> getCrudDataList(String code,
			Map<String, Object> params);

	Map<String, Object> getCrudData(String code, Map<String, Object> params);
}
