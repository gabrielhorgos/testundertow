package com.api.resources.definition;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

/**
 * Interface that defines the general contract of the API resource.
 */
public interface ApiInterface {

	Response sayHello();

	void sayCustomHello(String name, AsyncResponse asyncResponse);
}
