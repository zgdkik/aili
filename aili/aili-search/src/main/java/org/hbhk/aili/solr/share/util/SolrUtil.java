package org.hbhk.aili.solr.share.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.hbhk.aili.solr.server.service.impl.Solrservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrUtil {
	
	private static Logger log = LoggerFactory.getLogger(Solrservice.class);
	
	public static final String KEYWORD="keyword:";
	
	public static void  addParams(ModifiableSolrParams params,String keyword,String[] fqs, String[] highlightField, String sort,String fl, Integer start,
			Integer size,Integer limit, String... facets){
		if (StringUtils.isEmpty(keyword)) {
			keyword = "*:*";
		}
		params.set("q", KEYWORD+safeSolrKeyword(keyword));
		if(start!=null){
			params.set("start", start);
		}
		if(fqs!=null && fqs.length>0){
			for (String fq : fqs) {
				params.add("fq", fq);
			}
		}
		if(size!=null){
			params.set("rows", size);
		}
		if(size!=null){
			params.set("sort", sort);
		}
		if(StringUtils.isEmpty(fl)){
			fl ="*";
		}
		params.set("fl", fl);  
		log.debug("查询属性\r"+"keyword:"+keyword+"\r fq:"+fqs+"\r sort:"+sort+"\r fl:"+fl+"\r start:"+start+"\r size:"+size);
		
		addFacets(params, highlightField,limit, facets);
		
	}
	
	
	public static void addFacets(ModifiableSolrParams params,String[] highlightField, Integer limit ,String... facets){
		SolrQuery solrQuery = new SolrQuery();
		log.debug("设置facet返回数量:"+limit+" facet字段:"+facets);
		log.debug("设置高亮字段:"+highlightField);
		if(limit!=null && facets!=null && facets.length>0){
			//设置facet=on
			solrQuery.setFacet(true);
			//设置需要facet的字段		
			solrQuery.addFacetField(facets);
			//限制facet返回的数量
			solrQuery.setFacetLimit(limit);
		}
		if(highlightField!=null && highlightField.length>0){
			//开启高亮组件  
			solrQuery.setHighlight(true);
			for (String hlf : highlightField) {
				//高亮字段  
				solrQuery.addHighlightField(hlf);
			}
			//标记，高亮关键字前缀  
			solrQuery.setHighlightSimplePre("<font color='red'>");
			//后缀  
			solrQuery.setHighlightSimplePost("</font>");
			//结果分片数，默认为1
			solrQuery.setHighlightSnippets(2);
			//每个分片的最大长度，默认为100 
			solrQuery.setHighlightFragsize(1000); 
		}
		params.add(solrQuery);
	}
	  public static String safeSolrKeyword(String keyword) {
	        //special chars in solr query expression:+ - && || ! ( ) { } [ ] ^ " ~ * ? : \
	        //'''P'''是一个占位符，这里用它为了避免\$，使分组捕获被转义，在下一语句中会将'''P'''替换为\
	        keyword = keyword.replaceAll("([+\\-&|!(){}\\[\\]\\^\"~\\*\\?\\\\:]{1})","'''P'''$1");
	        keyword = keyword.replace("'''P'''", "\\");
	        return keyword;
	    }

}
