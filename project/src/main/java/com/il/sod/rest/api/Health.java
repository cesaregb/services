package com.il.sod.rest.api;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.services.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@RestController
@RequestMapping(value = "/health", produces = "application/json")
public class Health extends AbstractService {
	final static Logger LOGGER = LoggerFactory.getLogger(Health.class);
	
	@Autowired
    private Environment env;
	
	@Context
	SecurityContext securityContext;
	
	@Autowired
	MyService myService;
	
	@Value("${db.url}")
	private String dbUrl;
	
	@Value("${db.driver}")
	private String dbDriver;
	
	@PermitAll
	@RequestMapping(method = RequestMethod.GET)
	public GeneralResponseMessage checkHealth() throws SODAPIException{
		LOGGER.info("********************************");
		LOGGER.info("Calling health method: ");
		LOGGER.info("dbUrl: " + dbUrl + " == " + " dbDriver: " + dbDriver + " == " + System.getProperty("spring.profiles.active"));
		LOGGER.info("myValue: " + env.getProperty("myValue"));
//		LOGGER.info("env: " + env.getRequiredProperty("db.driver")  + " == " + env.getRequiredProperty("db.url"));
		LOGGER.info("********************************");
		
		return new GeneralResponseMessage(true, "from the resource: " + dbUrl);
	}	
	
//	@RequestMapping(method = RequestMethod.GET)
//	@RequestMapping(value = "/abcdef")
//	@ApiOperation(value = "Validate API + MODEL Healt")
//	public GeneralResponseMessage throwException() throws SODAPIException{
//		LOGGER.info("Testing throwing exception!");
//		throw new SODAPIException(HttpStatus.BAD_REQUEST, "Expected Exception!");
//	}
//
//	@RolesAllowed("BASIC_AUTH")
//	@RequestMapping(method = RequestMethod.GET)
//	@RequestMapping(value = "/admin_service")
//	@ApiOperation(value = "Test")
//	public GeneralResponseMessage secureMethod(InputStream is) throws SODAPIException{
//		TestDto tst = ConvertUtils.getJsonISAsObject(is, TestDto.class);
//		return new GeneralResponseMessage(true, "ok your ar an admin!! good for you!! " + tst.getVal());
//	}
}