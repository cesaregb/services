package com.il.sod.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.il.sod.config.jersey.AuthenticationFilter;
import com.il.sod.config.jersey.CORSResponseFilter;
import com.il.sod.config.jersey.CustomLoggingFilter;
import com.il.sod.config.jersey.JacksonObjectMapperProvider;
import com.il.sod.rest.util.PropertyHandler;
import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {
  private static final String SERVICES_PACKAGE = "com.il.sod.rest.api";

  final static Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

  public static final String PARAM_API_HOST = "rest.api.host"; // localhost
  public static final String PARAM_API_BASEPATH = "rest.api.basepath"; // api
  public static final String PARAM_VERSION = "rest.api.version";
  public static final String PARAM_PORT = "rest.api.port"; // 8080

  public ApplicationConfig() {
    register(CORSResponseFilter.class);
    register(AuthenticationFilter.class);
    register(CustomLoggingFilter.class);
    register(JacksonObjectMapperProvider.class);
    register(JacksonJaxbJsonProvider.class);
    packages("io.swagger.jaxrs.listing,"
            + "com.il.sod.exception,"
            + SERVICES_PACKAGE);

    LOGGER.info("[ApplicationConfig] TestToken: >> {} << ", JWTSingleton.INSTANCE.createJWT("Test-Requests", Constants.BASIC_AUTH, 1));
  }

  /* Swagger configuratoin */
  @PostConstruct
  public static void initializeSwaggerConfiguration() {
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setSchemes(new String[]{"http"});

    String host = PropertyHandler.getInstance().getValue(PARAM_API_HOST) + ":"
            + PropertyHandler.getInstance().getValue(PARAM_PORT);
    beanConfig.setHost(host);
    beanConfig.setBasePath(PropertyHandler.getInstance().getValue(PARAM_API_BASEPATH));
    beanConfig.setVersion(PropertyHandler.getInstance().getValue(PARAM_VERSION) + " Profile: " + App.getProfile());
    beanConfig.setTitle("SOD Services");
    beanConfig.setDescription("Services for handlign the be");
    beanConfig.setResourcePackage(SERVICES_PACKAGE);
    beanConfig.setScan(true);
//		Json.mapper().setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
  }
}
