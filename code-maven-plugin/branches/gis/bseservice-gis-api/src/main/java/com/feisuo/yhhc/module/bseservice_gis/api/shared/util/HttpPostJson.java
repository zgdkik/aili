package com.feisuo.yhhc.module.bseservice_gis.api.shared.util;

import net.sf.json.JSONObject;

 
public interface  HttpPostJson {


	public String postJson(String postUrl,JSONObject jsonObj) throws Exception;

}