package org.hbhk.aili.rest.server.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("user1")
public interface IUserService {

	@Path("register1")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	String registerUser();
}
