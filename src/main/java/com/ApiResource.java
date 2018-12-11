package com;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
	@Operation(summary = "The API Resource", tags = { "Hello Api", },
			responses = {
					@ApiResponse(responseCode = "200", description = "Returns a \"hello there\"",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = String.class)))})

	public Response sayHello() {
		return Response.ok().entity("hello there").build();
	}
	
	
	@GET
	@Path("msg")
	@Operation(summary = "The API Resource", tags = { "Using CDI service API", },
			responses = {
					@ApiResponse(responseCode = "200", description = "Calls a service to return the message",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = String.class)))})
	public Response getMessage() {
		return Response.ok().entity(service.getMessage()).build();
	}
}
