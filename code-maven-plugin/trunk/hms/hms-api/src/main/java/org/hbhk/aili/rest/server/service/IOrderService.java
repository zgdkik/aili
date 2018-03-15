package org.hbhk.aili.rest.server.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hbhk.aili.rest.share.entity.Order;

@Path("order1")
public interface IOrderService {
	
	@Path("save1")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	Boolean  save();
	
	@Path("get1")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	List<Order>  get(Map<String, Object> p);
}
