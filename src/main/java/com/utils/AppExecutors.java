package com.utils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AppExecutors class provides static access to application shared ExecutorServices to be used by asynchronous
 * methods (tasks implemented using CompletableFutures that run asynchronously).
 */
@ApplicationScoped
public class AppExecutors {

	private static final int AUTHENTICATION_EXECUTOR_POOL_SIZE = 5;
	private ExecutorService authenticationExecutor;

	@PostConstruct 
	private void init() {
		authenticationExecutor = Executors.newFixedThreadPool(AUTHENTICATION_EXECUTOR_POOL_SIZE);
	}

	public ExecutorService getAuthenticationExecutor() {
		return authenticationExecutor;
	}
}
