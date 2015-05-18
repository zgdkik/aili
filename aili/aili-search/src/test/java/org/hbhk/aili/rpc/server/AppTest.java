package org.hbhk.aili.rpc.server;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.hbhk.aili.solr.server.service.ISolrservice;
import org.hbhk.aili.solr.server.service.impl.Solrservice;


public class AppTest {

	public static void main(String[] args) {
		ISolrservice solrservice = new Solrservice();
		SolrClient solrClient = new HttpSolrClient("http://localhost:8080/solr");
		solrservice.setSolrModelName(SolrTestModel.class.getName());
		solrservice.setSolrClient(solrClient);
		SolrTestModel model =new SolrTestModel();
		model.setId("12");
		solrservice.addIndex(model);
	}

}