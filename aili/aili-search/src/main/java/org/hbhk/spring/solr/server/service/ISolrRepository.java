package org.hbhk.spring.solr.server.service;

import java.util.List;

import org.hbhk.aili.solr.share.model.SolrBase;
import org.hbhk.aili.solr.share.model.Sort;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ISolrRepository<T extends SolrBase> extends SolrCrudRepository<T, String> {
	
	 List<T> search(String keywords,Sort...sorts);
}
