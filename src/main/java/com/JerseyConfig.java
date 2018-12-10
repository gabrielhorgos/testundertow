package com;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		register(ApiResource.class);
		register(JacksonJaxbJsonProvider.class);
	}
	
}
