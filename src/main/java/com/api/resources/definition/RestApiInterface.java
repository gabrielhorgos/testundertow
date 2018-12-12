package com.api.resources.definition;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Interface adding JAX-RS API specification. Defines request mapping, method type, media-type, 
 * parameters and request body etc. 
 * To be implemented by all API resources (concrete implementations).
 */
@Path("/")
@Produces({"application/json", "application/xml"})
public interface RestApiInterface extends OpenApiInterface {

	@Override
	@GET
	@Path("hello")
	Response sayHello();

	@Override
	@GET
	@Path("customHello")
	Response sayCustomHello(@QueryParam("name") String name);
}
