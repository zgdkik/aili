package org.hbhk.aili.crud.server.service;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.crud.share.entity.CrudEntity;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;

public interface IReportService {

	Pagination<Map<String, Object>> getReportData(String code,
			Map<String, Object> params, Page page);

	List<String> getColumns(String code);

	CrudEntity getByCode(String code);
	
	List<String> validateSql(String sql);
}
