package com.yimidida.ows.base.server.spring.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SdsObjectMapper  extends ObjectMapper{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4877990471030352417L;
	public SdsObjectMapper() {
		configure(SerializationFeature.WRAP_ROOT_VALUE, true);
	}
}
