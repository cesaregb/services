package com.il.sod.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import com.il.sod.rest.util.PropertyHandler;

public class App {
	final static Logger LOGGER = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		
		Integer port = Integer.valueOf(PropertyHandler.getInstance().getValue(ApplicationConfig.PARAM_PORT));
		Server server = new Server(port);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		context.setContextPath("/");
		
		// Setup Spring context
		context.setInitParameter("spring.profiles.active", getProfile());
		context.addEventListener(new ContextLoaderListener());
		context.setInitParameter("contextConfigLocation", "classpath*:**/applicationContext.xml");
		context.addEventListener(new RequestContextListener());

		server.setHandler(context);		
		ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(new ApplicationConfig()));
		jerseyServlet.setInitOrder(1);
		context.addServlet(jerseyServlet, PropertyHandler.getInstance().getValue(ApplicationConfig.PARAM_API_BASEPATH) + "/*"); // /api/*
		
//		ApplicationConfig.initializeSwaggerConfiguration();
		
		ServletHolder staticServlet = context.addServlet(DefaultServlet.class, "/*");
		staticServlet.setInitParameter("resourceBase", "src/main/webapp");
		staticServlet.setInitParameter("pathInfoOnly", "true");
	   
		try {
			server.start();
			server.join();
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		}
	}
	
	public static String getProfile(){
		String profile = (System.getProperty("spring.profiles.active") != null)
				? System.getProperty("spring.profiles.active") : System.getenv(Constants.ENV_APP_PROFILE);
        if (profile != null) {
        	LOGGER.info("Running with Spring profile(s) : {}", profile);
            return profile;
        }
        LOGGER.warn("No Spring profile configured, running with default configuration");
        return Constants.SPRING_PROFILE_LOCAL;
	}
}


