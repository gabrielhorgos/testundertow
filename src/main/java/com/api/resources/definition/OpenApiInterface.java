package com.api.resources.definition;

import com.model.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.core.Response;

/**
 * Interface extending ApiInterface and decorating methods with OpenApi documentation.
 */
public interface OpenApiInterface extends ApiInterface {

	@Override
	@Operation(summary = "Returns a greetings message.", tags = { "Hello Api", },
			responses = {
					@ApiResponse(responseCode = "200", description = "Returns a \"Hello there!\" message.",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = Message.class)))})
	Response sayHello();

	@Override
	@Operation(summary = "Returns a customized greetings message.", tags = { "Using CDI service API", },
			responses = {
					@ApiResponse(responseCode = "200", description = "Creates a customized greetings message by using"
							+ " the provided name parameter.",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = Message.class))),
					@ApiResponse(responseCode = "400", description = "Parameter supplied must not be null."),})
	Response sayCustomHello(@Parameter(description = "The name used for creating the custom hello message.",
			schema = @Schema(type = "String", example = "John"), required = true) 
			String name);
}
