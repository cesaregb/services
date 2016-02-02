package com.il.sod.rest.api.impl;

import java.io.InputStream;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.il.sod.db.dao.IDAO;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractService;
import com.il.sod.rest.dto.SimpleResponseMessage;
import com.il.sod.rest.dto.TestDto;
import com.il.sod.services.MyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="health")
public class Health extends AbstractService {
	final static Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);
	
	@Autowired
    private Environment env;
	
	@Context
	SecurityContext securityContext;
	
	@Autowired
	MyService myService;
	
	@Autowired
	private IDAO shopServiceDAO;
	
	@Value("${db.url}")
	private String dbUrl;
	
	@Value("${db.driver}")
	private String dbDriver;
	
	@PermitAll
	@GET
	@ApiOperation(value = "Validate API + MODEL Healt")
	@ApiResponses(value = { 
		@ApiResponse(code=400, message="Invalid input supplied"),
		@ApiResponse(code=404, message="Info not found", 
				response=SimpleResponseMessage.class)})
	public SimpleResponseMessage checkHealth() throws SODAPIException{
		LOGGER.info("********************************");
		LOGGER.info("Calling health method: ");
		LOGGER.info("dbUrl: " + dbUrl + " == " + " dbDriver: " + dbDriver + " == " + System.getProperty("spring.profiles.active"));
		LOGGER.info("myValue: " + env.getProperty("myValue"));
//		LOGGER.info("env: " + env.getRequiredProperty("db.driver")  + " == " + env.getRequiredProperty("db.url"));
		LOGGER.info("shopServiceDAO: " + shopServiceDAO.findById(6) );
		LOGGER.info("********************************");
		
		SimpleResponseMessage obj = new SimpleResponseMessage(SimpleResponseMessage.TYPE_SUCCES);
		obj.setMessage(myService.getMyValue("from the resource: " + dbUrl));
		return obj;
	}	
	
	@GET
	@Path("/exception")
	@ApiOperation(value = "Validate API + MODEL Healt")
	public SimpleResponseMessage throwException() throws SODAPIException{	
		LOGGER.info("Testing throwing exception!");
		throw new SODAPIException(511, "Expected Exception!");
	}
	
	@POST
	@Path("/secureIS")
	@ApiOperation(value = "Test")
	public SimpleResponseMessage secureMethodIS(InputStream is) throws SODAPIException{	
		TestDto srm = getAuthObject(is, TestDto.class);
		SimpleResponseMessage obj = new SimpleResponseMessage(SimpleResponseMessage.TYPE_SUCCES);
		obj.setMessage(srm.getVal());
		return obj;
	}	
	
	@RolesAllowed("ADMIN")
	@GET
	@Path("/admin_service")
	@ApiOperation(value = "Test")
	public SimpleResponseMessage secureMethod() throws SODAPIException{	
		SimpleResponseMessage obj = new SimpleResponseMessage(SimpleResponseMessage.TYPE_SUCCES);
		obj.setMessage("Ok you are an admin!");
		return obj;
	}	
	
}