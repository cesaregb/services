package com.il.sod.rest.api.security.impl;

import com.il.sod.config.Constants;
import com.il.sod.config.JWTSingleton;
import com.il.sod.converter.services.SpecificObjectsConverterService;
import com.il.sod.db.model.entities.Menu;
import com.il.sod.db.model.repositories.EmployeeRepository;
import com.il.sod.db.model.repositories.MenuRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.db.MenuDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/auth", tags = { "auth" })
public class SecurityService extends AbstractServiceMutations {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SpecificObjectsConverterService specificObjectsConverterService;

	@POST
	@RolesAllowed("BASIC_AUTH")
	@Path("/app/{appId}")
	@ApiOperation(value = "Get Menu Options", response = MenuDTO.class, responseContainer = "List")
	public Response authClient(@PathParam("appId") String appId) throws SODAPIException {
		String compactJws = JWTSingleton.INSTANCE.createJWT(appId, Constants.BASIC_AUTH, 1);
		return this.castEntityAsResponse(compactJws);
	}

	@GET
	@Path("/menu")
	@ApiOperation(value = "Get Menu Options", response = MenuDTO.class, responseContainer = "List")
	public Response getMenu() throws SODAPIException {
		List<Menu> entities = menuRepository.findAllByOrderByOrderAsc();
		List<MenuDTO> dtos = entities.stream().map(i -> {return specificObjectsConverterService.map(i);}).collect(Collectors.toList());
		return this.castEntityAsResponse(dtos);
	}
	
	@GET
	@Path("/menu/{accessLevel}")
	@ApiOperation(value = "Get Menu Options", response = MenuDTO.class, responseContainer = "List")
	public Response getMenu(@PathParam("accessLevel") String accessLevel) throws SODAPIException {
		List<Menu> entities = menuRepository.findAll();
		int accessL = Integer.valueOf(accessLevel);
		List<MenuDTO> dtos = entities.stream()
				.filter(i -> i.getAccessLevel() >= accessL)
				.map(i -> {return specificObjectsConverterService.map(i);})
				.collect(Collectors.toList());
		return this.castEntityAsResponse(dtos);
	}

	static class UserPassword{
		private String user;
		private char[] password;
	}
}
