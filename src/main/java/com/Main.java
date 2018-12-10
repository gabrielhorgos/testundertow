package com;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import javax.servlet.ServletException;

import static io.undertow.servlet.Servlets.*;

public class Main {

	private static Undertow server;

	public static void main(String[] args) {
		startContainer(8080);
	}

	private static void startContainer(int port) {
		try {

			DeploymentInfo servletBuilder = deployment().setClassLoader(Main.class.getClassLoader())
					.setContextPath("/rest").setDeploymentName("test.war").addServlets(
							servlet("MessageServlet", MyServlet.class).addInitParam("message", "Hello World")
									.addMapping("/*"),
							servlet("MyServlet", MyServlet.class).addInitParam("message", "MyServlet")
									.addMapping("/myservlet"));

			DeploymentManager manager = defaultContainer().addDeployment(servletBuilder);
			manager.deploy();

			HttpHandler servletHandler = manager.start();
			PathHandler path = Handlers.path(Handlers.redirect("/rest")).addPrefixPath("/rest", servletHandler);
			Undertow server = Undertow.builder().addHttpListener(8080, "localhost").setHandler(path).build();
			server.start();
		} catch (ServletException e) {
			throw new RuntimeException(e);
		}
	}

	public static void stopContainer() {
		server.stop();
	}

}
