package org.hbhk.zlj.backend.server.service;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.zlj.backend.share.entity.CrudEntity;

public interface IReportService {

	Pagination<Map<String, Object>> getReportData(String code,
			Map<String, Object> params, Page page);

	List<String> getColumns(String code);

	CrudEntity getByCode(String code);
	
	List<String> validateSql(String sql);
}
