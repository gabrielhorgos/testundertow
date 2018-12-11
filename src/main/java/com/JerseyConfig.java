package com;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		register(ApiResource.class);
		register(JacksonJaxbJsonProvider.class);

		register(OpenApiResource.class);
		register(AcceptHeaderOpenApiResource.class);
	}
	
}
