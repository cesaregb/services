package com.il.sod.rest.api.impl;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractService;
import com.il.sod.rest.dto.UserDto;
import com.il.sod.services.MyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="health")
public class Security extends AbstractService {
	@Context
	SecurityContext securityContext;
	
	@Autowired
	MyService myService;
	
	@POST
	@ApiOperation(value = "Authenticate User")
	public UserDto checkHealth(UserDto user) throws SODAPIException{
		if (user.getName() == "cesar" && user.getPassword() == "abc"){
			user.setAuth(true);
			user.setToken("1");
			// tie token to user. 
			return user;
		}else{
			throw new SODAPIException(403, "User not valid ");
		}
	}	
}