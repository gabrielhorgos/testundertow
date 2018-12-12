package com.api.resources;

import com.api.resources.definition.RestApiInterface;
import com.model.Message;
import com.service.ApiService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@RequestScoped
public class ApiResource implements RestApiInterface {
	
	@Inject
	private ApiService service;
	
	@Override
	public Response sayHello() {
		return Response.ok().entity(new Message("Hello there!")).build();
	}

	@Override
	public Response sayCustomHello(String name) {
		try {
			return Response.ok().entity(service.buildHelloMessage(name)).build();
		} catch (Exception e) {
			return Response.status(400).entity(new Message(e.getMessage())).build();
		}
	}
}
