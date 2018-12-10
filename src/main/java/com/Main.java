package com;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class Main {

	private static Undertow server;
	
	public static void main(String[] args) {
		startContainer(8080);
	}

	private static void startContainer(int port) {
		Undertow server = Undertow.builder()
				.addHttpListener(port, "localhost")
				.setHandler(new HttpHandler() {
					@Override public void handleRequest(HttpServerExchange httpServerExchange)
							throws Exception {
						httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
						httpServerExchange.getResponseSender().send("Hello World");
					}
				}).build();
		server.start();
	}

	public static void stopContainer() {
		server.stop();
	}

}
