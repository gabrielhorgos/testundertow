package com.utils;

import java.util.function.Function;

/**
 * Fetches a property from the Environment, System Properties, then uses a
 * fallback. Useful in Cloud Native (Docker / Kubernetes) deployments.
 *
 */
public interface DeploymentConfiguration {

	Function<String, String> ENV = (key) -> System.getenv().getOrDefault(key, System.getProperty(key));

	static String getProperty(String key, String fallback) {
		String value = ENV.apply(key);
		if (value == null)
			return fallback;
		return value;
	}
}
