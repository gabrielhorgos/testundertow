package com.api.resources;

import com.model.Message;
import com.service.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/")
@Produces({"application/json", "application/xml"})
public class ApiResource {
	
	@Inject
	private ApiService service;
	
	@GET
	@Path("hello")
	@Operation(summary = "Returns a greetings message.", tags = { "Hello Api", },
			responses = {
					@ApiResponse(responseCode = "200", description = "Returns a \"Hello there!\" message.",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = Message.class)))})

	public Response sayHello() {
		return Response.ok().entity(new Message("Hello there!")).build();
	}
	
	@GET
	@Path("customHello")
	@Operation(summary = "Returns a customized greetings message.", tags = { "Using CDI service API", },
			responses = {
					@ApiResponse(responseCode = "200", description = "Creates a customized greetings message by using" 
							+ " the provided name parameter.",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = Message.class))),
					@ApiResponse(responseCode = "400", description = "Parameter supplied must not be null."),})
	public Response sayCustomHello(@Parameter(
			description = "The name used for creating the custom hello message.",
			schema = @Schema(
					type = "String",
					example = "John"),
			required = true) 
			@QueryParam("name") String name) {

		try {
			return Response.ok().entity(service.buildHelloMessage(name)).build();
		} catch (Exception e) {
			return Response.status(400).entity(new Message(e.getMessage())).build();
		}
	}
}
