package com.il.sod.rest.api;

import com.il.sod.db.dao.SocialNetworkServiceDAO;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.TestDto;
import com.il.sod.services.MyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.InputStream;

@Component
@Path("/health")
//@Api(value="healt", tags={"healt"})
@Produces(MediaType.APPLICATION_JSON)
public class Health extends AbstractService {
	final static Logger LOGGER = LoggerFactory.getLogger(Health.class);
	
	@Autowired
    private Environment env;
	
	@Context
	SecurityContext securityContext;
	
	@Autowired
	MyService myService;
	
	@Autowired
	private SocialNetworkServiceDAO shopServiceDAO;
	
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
				response=GeneralResponseMessage.class)})
	public GeneralResponseMessage checkHealth() throws SODAPIException{
		LOGGER.info("********************************");
		LOGGER.info("Calling health method: ");
		LOGGER.info("dbUrl: " + dbUrl + " == " + " dbDriver: " + dbDriver + " == " + System.getProperty("spring.profiles.active"));
		LOGGER.info("myValue: " + env.getProperty("myValue"));
//		LOGGER.info("env: " + env.getRequiredProperty("db.driver")  + " == " + env.getRequiredProperty("db.url"));
		LOGGER.info("shopServiceDAO: " + shopServiceDAO.findById(6) );
		LOGGER.info("********************************");
		
		return new GeneralResponseMessage(true, "from the resource: " + dbUrl);
	}	
	
	@GET
	@Path("/abcdef")
	@ApiOperation(value = "Validate API + MODEL Healt")
	public GeneralResponseMessage throwException() throws SODAPIException{	
		LOGGER.info("Testing throwing exception!");
		throw new SODAPIException(Response.Status.BAD_REQUEST, "Expected Exception!");
	}
	
	@RolesAllowed("BASIC_AUTH")
	@GET
	@Path("/admin_service")
	@ApiOperation(value = "Test")
	public GeneralResponseMessage secureMethod(InputStream is) throws SODAPIException{
		TestDto tst = this.getJsonISAsObject(is, TestDto.class);
		return new GeneralResponseMessage(true, "ok your ar an admin!! good for you!! " + tst.getVal());
	}	
}