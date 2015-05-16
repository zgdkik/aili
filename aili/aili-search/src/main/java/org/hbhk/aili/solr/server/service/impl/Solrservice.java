package org.hbhk.aili.solr.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
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

	@Override
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

	@Override
	public void updateIndex(SolrBase t) {
		try {
			log.debug("修改索引开始...");
			solrServer.addBean(t);
			solrServer.commit();
			log.debug("修改索引结束...");
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updateIndex(List<SolrBase> list) {
		try {
			log.debug("批量修改索引开始...");
			List<String> ids = new ArrayList<String>();
			for (SolrBase solr : list) {
				ids.add(solr.getId());
			}
			solrServer.deleteById(ids);
			solrServer.addBeans(list);
			solrServer.commit();
			log.debug("批量修改索引结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}
	@Override
	public QueryResponse query(SolrParams params) {
		try {
			log.debug("查询索引开始...");
			QueryResponse response =  solrServer.query(params);
			log.debug("查询索引结束...");
			return response;
		} catch (SolrServerException e) {
			log.error("查询索引出现异常",e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delIndex(String id) {
		try {
			log.debug("删除索引出现开始...");
			solrServer.deleteById(id);
			solrServer.commit();
			log.debug("删除索引出现结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delIndex(List<String> ids) {
		try {
			log.debug("批量删除索引出现开始...");
			solrServer.deleteById(ids);
			solrServer.commit();
			log.debug("批量删除索引出现结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delAllIndex() {
		try {
			log.debug("删除所有索引开始...");
			solrServer.deleteByQuery("*:*");
			solrServer.commit();
			log.debug("删除所有索引结束...");
		} catch (SolrServerException e) {
			rollback(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			rollback(e);
			throw new RuntimeException(e);
		}
	}
	
	private void addFacets(SolrQuery solrQuery,String... facets){
		if(facets!=null && facets.length>0){
			//设置facet=on
			solrQuery.setFacet(true);
			//设置需要facet的字段		
			solrQuery.addFacetField(new String[] { "cpu", "videoCard" });
			//限制facet返回的数量
			solrQuery.setFacetLimit(10);
		}
	}
	
	@Override
	public List<SolrBase> queryList(String query, Class<SolrBase> cls,
			String... facets) {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		addFacets(solrQuery, facets);
	
		ModifiableSolrParams params = new ModifiableSolrParams();
		QueryResponse response = null;
		try {
			response = solrServer.query(params);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		//返回的facet列表
		List<FacetField> facetList = response.getFacetFields();
		return null;
	}

	@Override
	public List<SolrBase> queryList(String query, Map<String, String> filters,
			Class<SolrBase> cls, String... facets) {
		
		return null;
	}
	@Override
	public List<SolrBase> queryList(String query, String sort, int start,
			int size, Class<SolrBase> cls,String... facets) {
		log.debug("查询索引开始...");
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
			log.debug("查询索引结束...");
		} catch (SolrServerException e) {
			log.error("查询索引出现异常",e);
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public List<SolrBase> queryList(String query, Map<String, String> filters,
			String sort, Class<SolrBase> cls,String... facets) {

		return null;
	}

	@Override
	public Pagination<SolrBase> queryListWithPage(String query, String sort,
			int start, int size, Class<SolrBase> cls, String... facets) {
		
		Pagination<SolrBase> pagination = new Pagination<SolrBase>();
		pagination.setStart(start);
		pagination.setSize(size);
		
		return null;
	}
	
	@Override
	public Pagination<SolrBase> queryListWithPage(String query,
			Map<String, String> filters, String sort, int start, int size,
			Class<SolrBase> cls,String... facets) {
		return null;
	}

	public void rollback(Exception ex) {
		try {
			log.error("操作索引异常事务回滚:" + ex.getMessage(), ex);
			solrServer.rollback();
		} catch (SolrServerException e) {
			log.error(ex.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(ex.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	
}
