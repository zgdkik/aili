package com.deppon.esb.management.svccfg.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
public interface IServiceConfiguration {
	
	@POST
	@Path(value="/ESB_BAMP2ESB_ADD_CONFIG")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String addServiceConfig(String json) throws JsonGenerationException, JsonMappingException, IOException;
	
}
