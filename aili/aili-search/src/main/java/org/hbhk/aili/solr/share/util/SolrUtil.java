package org.hbhk.aili.solr.share.util;

import java.util.Map;
import java.util.Set;

import org.springframework.data.solr.core.query.Criteria;

public class SolrUtil {

	
	public static Criteria createSearchConditions(String keywords) {
		String[] words = keywords.split("");
        Criteria conditions = null;
        for (String word: words) {
            if (conditions == null) {
                conditions = new Criteria("keywords").contains(word);
            }else {
                conditions = conditions.or(new Criteria("keywords").contains(word));
            }
        }
        return conditions;
    }
	
	public static Criteria createAndSearchConditions(Map<String, Object> params) {
		Set<String> fields = params.keySet();
        Criteria conditions = null;
        for (String word: fields) {
            if (conditions == null) {
                conditions = new Criteria("keywords").contains(word);
            }else {
                conditions = conditions.or(new Criteria("keywords").contains(word));
            }
        }
        return conditions;
    }
}
