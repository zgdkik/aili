package org.hbhk.aili.rpc.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.hbhk.aili.solr.server.service.ISolrservice;
import org.hbhk.aili.solr.server.service.impl.Solrservice;
import org.junit.Test;


public class AppTest {
	ISolrservice solrservice = new Solrservice();
	SolrClient solrClient = new HttpSolrClient("http://localhost:8080/solr/hbhk_core");
	
	@org.junit.Before
	public void bf() throws Exception {
		solrservice.setSolrModelName(SolrTestModel.class.getName());
		solrservice.setSolrClient(solrClient);
	}
	
	@Test
	public void addIndex() throws Exception {
		List<SolrTestModel> solrTestModels = new ArrayList<SolrTestModel>();
		for (int n = 0; n < 100; n++) {
			SolrTestModel model =new SolrTestModel();
			Map<String, String> dynMap = new HashMap<String, String>();
			Map<String, List<String>> dynMapList = new HashMap<String, List<String>>();
			for (int i = 0; i < 3; i++) {
				dynMap.put("dynMap_"+i, "dynMap"+i);
				List<String> list = new ArrayList<String>();
				for (int j = 0; j < 3; j++) {
					list.add("l"+j);
				}
				dynMapList.put("dynMapList_"+i, list);
			}
			model.setId(""+n);
			model.setTitle("title"+n);
			model.setContent("content"+n);
			
			model.setDynMap(dynMap);
			model.setDynMapList(dynMapList);
			solrTestModels.add(model);
		}

		solrservice.addIndex(solrTestModels);
	}
	
	@Test
	public void queryIndex() throws Exception {
		List<SolrTestModel> solrTestModels = solrservice.queryList("itl","*", new String[]{"title"}, null);
		
		for (SolrTestModel stm : solrTestModels) {
			System.out.println(stm.getId()+" "+stm.getTitle()+"  "+stm.getContent());
			
		}
		
	}

}