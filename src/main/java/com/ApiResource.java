package com;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/")
public class ApiResource {
	
	@Inject
	private Service service;
	
	@GET
	@Path("hello")
	public Response sayHello() {
		return Response.ok().entity("hello there").build();
	}
	
	@GET
	@Path("msg")
	public Response getMessage() {
		return Response.ok().entity(service.getMessage()).build();
	}
}
