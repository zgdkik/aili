package org.hbhk.aili.rpc.server;

import org.hbhk.aili.solr.server.service.ISolrservice;
import org.hbhk.aili.solr.server.service.impl.Solrservice;
import org.hbhk.aili.solr.share.model.SolrBase;


public class AppTest {

	public static void main(String[] args) {
		ISolrservice<SolrBase> solrservice = new Solrservice();
		
		solrservice.queryList("", null,SolrBase.class);
	}

}