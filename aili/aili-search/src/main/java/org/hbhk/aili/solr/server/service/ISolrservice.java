package org.hbhk.aili.solr.server.service;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.hbhk.aili.solr.share.model.Pagination;
import org.hbhk.aili.solr.share.model.SolrBase;

public interface ISolrservice<T extends SolrBase> {
	
	void updateIndex(T t);

	void updateIndex(List<T> list);

	void addIndex(T t);

	void addIndex(List<T> list);

	void delAllIndex();

	void delIndex(String id);
	
	void delIndex(List<String> ids);

	List<T> queryListWithPage(String query, String sort,
			int start, int size, Class<T> cls);

	List<T> queryList(String query,Map<String, String> filters, String sort, Class<T> cls);
	QueryResponse query(SolrParams params);
	
	Pagination<T> queryListWithPage(String query,Map<String, String> filters, String sort,
			int start, int size, Class<T> cls);
}
