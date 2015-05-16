package org.hbhk.aili.solr.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.hbhk.aili.solr.server.service.ISolrCallback;
import org.hbhk.aili.solr.server.service.ISolrservice;
import org.hbhk.aili.solr.share.model.Pagination;
import org.hbhk.aili.solr.share.model.SolrBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Solrservice implements ISolrservice<SolrBase> {

	private static Log log = LogFactory.getLog(Solrservice.class);

	@Autowired
	private SolrServer solrServer;

	public void addIndex(List<SolrBase> list) {
		try {
			log.debug("批量添加索引开始...");
			solrServer.addBeans(list);
			solrServer.commit();
			log.debug("批量添加索引结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}

	}

	public void addIndex(SolrBase t) {
		try {
			log.debug("添加索引开始...");
			solrServer.addBean(t);
			solrServer.commit();
			log.debug("添加索引结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updateIndex(SolrBase t) {

	}

	public void updateIndex(List<SolrBase> list) {
		try {
			List<String> ids = new ArrayList<String>();
			for (SolrBase solr : list) {
				ids.add(solr.getId());
			}
			solrServer.deleteById(ids);
			solrServer.addBeans(list);
			solrServer.commit();
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}

	public List<SolrBase> queryListWithPage(String query, String sort,
			int start, int size, Class<SolrBase> cls) {
		ModifiableSolrParams params = new ModifiableSolrParams();
		if (query.equals("")) {
			query = "*:*";
		}
		params.set("q", query);
		params.set("start", start);
		params.set("rows", size);
		params.set("sort", sort);
		List<SolrBase> list = new ArrayList<SolrBase>();
		try {
			QueryResponse response = solrServer.query(params);
			list = response.getBeans(cls);
		} catch (SolrServerException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public QueryResponse query(SolrParams params) {
		try {
			return solrServer.query(params);
		} catch (SolrServerException e) {
			throw new RuntimeException(e);
		}
	}

	public <T> T execute(ISolrCallback<T> action) {
		try {
			T t = action.doExecute(solrServer);
			solrServer.commit();
			return t;
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delIndex(String id) {

	}

	public void delAllIndex() {
		try {
			solrServer.deleteByQuery("*:*");
			solrServer.commit();
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}

	public void delIndex(List<String> ids) {
		try {
			solrServer.deleteById(ids);
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<SolrBase> queryList(String query,
			Map<String, String> filters, String sort,
			Class<SolrBase> cls) {

		return null;
	}

	public void rollback(Exception ex) {
		try {
			log.error("操作索引异常事务回滚:"+ex.getMessage(), ex);
			solrServer.rollback();
		} catch (SolrServerException e) {
			log.error(ex.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(ex.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Pagination<SolrBase> queryListWithPage(String query,
			Map<String, String> filters, String sort, int start, int size,
			Class<SolrBase> cls) {
		return null;
	}
}
