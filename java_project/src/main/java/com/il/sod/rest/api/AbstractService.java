package com.il.sod.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.il.sod.config.jersey.JacksonObjectMapperProvider;
import com.il.sod.mapper.BaseMapper;
import com.il.sod.services.cruds.EntityServicesBase;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

@Component
@SwaggerDefinition(tags = {@Tag(name = "clients", description = "Client Layer services"),
        @Tag(name = "app-utils", description = "Utilities of the site - store"),
        @Tag(name = "app-orders", description = "Services used by web-app"),
        @Tag(name = "employees", description = "Employee services"),
        @Tag(name = "specs", description = "Specs are the 'type of generics' that uses a service "),
//		@Tag(name = "asset", description = "Assets  - Business assets"),
        @Tag(name = "supplies", description = "Supplies used for specs"),
        @Tag(name = "tasks", description = "Task used in orders or services"),
        @Tag(name = "orders", description = "Order Layer Services"),
        @Tag(name = "services", description = "Service Layer Services"),
        @Tag(name = "payment", description = "Payment Services Client and Orders"),
//		@Tag(name = "routes", description = "Route Information,... not user related..."),
        @Tag(name = "products", description = "Products, similar to specs but different!! LOL "),
        @Tag(name = "priceAdjustments", description = "PriceAdjustments, priceAdjustment handling  "),
        @Tag(name = "auth", description = "Auth services "),
        @Tag(name = "health", description = "Validate API + MODEL Healt")})
@Path("/v1")
@SuppressWarnings("all")
public abstract class AbstractService
        extends EntityServicesBase /* hack to migrate logic to services..  */ {
  final static Logger ABS_LOGGER = LoggerFactory.getLogger(AbstractService.class);

  protected ObjectMapper mapper;
  protected MapperFacade converter = BaseMapper.MAPPER_FACTORY.getMapperFacade();

  protected AbstractService() {
    mapper = JacksonObjectMapperProvider.MAPPER;
  }
}
