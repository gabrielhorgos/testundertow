package com;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jboss.weld.environment.servlet.Listener;

import javax.servlet.ServletException;

import static io.undertow.servlet.Servlets.*;

public class Main {

	private static Undertow server;

	public static void main(String[] args) {
		startContainer(8080);
	}

	private static void startContainer(int port) {
		try {
			PathHandler path = Handlers.path();

			Undertow server = Undertow.builder()
					.addHttpListener(8080, "localhost")
					.setHandler(path)
					.build();
			
			server.start();

			DeploymentInfo servletBuilder = deployment()
					.setClassLoader(Main.class.getClassLoader())
					.setContextPath("/")
					.addListeners(listener(Listener.class))
					.setResourceManager(new ClassPathResourceManager(Main.class.getClassLoader()))
					.addServlets(servlet("jerseyServlet", ServletContainer.class)
							.setLoadOnStartup(1)
							.addInitParam("javax.ws.rs.Application", ApplicationConfig.class.getName())
							.addMapping("/api/*"))
					.setDeploymentName("test.war");

			DeploymentManager manager = defaultContainer().addDeployment(servletBuilder);
			manager.deploy();

			path.addPrefixPath("/", manager.start());
			
		} catch (ServletException e) {
			throw new RuntimeException(e);
		}
	}

	public static void stopContainer() {
		server.stop();
	}

}
