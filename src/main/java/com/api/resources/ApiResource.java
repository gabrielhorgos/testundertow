package com.api.resources;

import com.api.resources.definition.OpenApiInterface;
import com.model.Message;
import com.service.ApiService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/")
@Produces({"application/json", "application/xml"})
public class ApiResource implements OpenApiInterface {
	
	@Inject
	private ApiService service;
	
	@Override
	@GET
	@Path("hello")
	public Response sayHello() {
		return Response.ok().entity(new Message("Hello there!")).build();
	}

	@Override
	@GET
	@Path("customHello")
	public Response sayCustomHello(@QueryParam("name") String name) {
		try {
			return Response.ok().entity(service.buildHelloMessage(name)).build();
		} catch (Exception e) {
			return Response.status(400).entity(new Message(e.getMessage())).build();
		}
	}
}
