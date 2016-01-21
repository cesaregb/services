package com.il.sod.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;
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
		System.setProperty("spring.profiles.active", "dev");
		System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		context.setInitParameter("spring.profiles.active", "dev");
		
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		context.addEventListener(new ContextLoaderListener());
//		context.setInitParameter("contextConfigLocation", "classpath*:**/applicationContext.xml");
		context.setInitParameter("contextConfigLocation", "com.il.sod.config.SpringConfig");
		context.addEventListener(new RequestContextListener());

		server.setHandler(context);
		ApplicationConfig ac = new ApplicationConfig();
//		ctx.getEnvironment().setActiveProfiles("dev");
//		ac.property("contextConfig", ctx);
		
		ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(ac));
		jerseyServlet.setInitOrder(1);
		
    
		context.addServlet(jerseyServlet, PropertyHandler.getInstance().getValue(ApplicationConfig.PARAM_API_BASEPATH) + "/*"); // /api/*
		ApplicationConfig.initializeSwaggerConfiguration();
		
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
	
	
	
	/**
     * Set a default profile if it has not been set.
     * Please use -Dspring.profiles.active=dev
     */
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		super.onStartup(servletContext);
//		servletContext.setInitParameter("spring.profiles.active", getProfile());
//		//Set multiple active profile
//		//servletContext.setInitParameter("spring.profiles.active", "dev, testdb");
//	}
//	
	public static String getProfile(){
		String profile = System.getProperty("spring.profiles.active");
        if (profile != null) {
        	LOGGER.info("Running with Spring profile(s) : {}", profile);
            return profile;
        }
        LOGGER.warn("No Spring profile configured, running with default configuration");
        return Constants.SPRING_PROFILE_DEVELOPMENT;
	}
}


