package com.api.resources.definition;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

/**
 * Interface that defines the general contract of the API resource.
 */
public interface ApiInterface {

	Response sayHello();

	Response sayCustomHello(@NotNull String name);
}
