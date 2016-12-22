package com.il.sod.rest.api.web.impl;

import com.il.sod.converter.services.SpecificObjectsConverterService;
import com.il.sod.db.model.entities.Menu;
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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/app-utils")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/app-utils", tags = { "app-utils" })
public class AppUtilsService extends AbstractServiceMutations {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AppUtilsService.class);
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	SpecificObjectsConverterService specificObjectsConverterService;

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
		// TODO create enum for handling roles.
		final int accessL = (accessLevel.equals("admin"))?1:2;
		List<MenuDTO> dtos = entities.stream()
				.filter(i -> i.getAccessLevel() >= accessL)
				.map(i -> {return specificObjectsConverterService.map(i);})
				.collect(Collectors.toList());
		return this.castEntityAsResponse(dtos);
	}
}
