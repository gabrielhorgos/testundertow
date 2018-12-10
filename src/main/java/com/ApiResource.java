package com;

import org.glassfish.jersey.process.internal.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/hello")
public class ApiResource {
	
	@GET
	public Response sayHello() {
		return Response.ok().entity("hello there").build();
	}
}
