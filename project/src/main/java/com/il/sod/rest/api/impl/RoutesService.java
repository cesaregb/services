package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.Route;
import com.il.sod.db.model.repositories.RoutesRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.RoutesMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.RouteDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/routes")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/routes", tags = { "routes" })
public class RoutesService extends AbstractServiceMutations {

	@Autowired
	RoutesRepository routesRepository;

	@POST
	@ApiOperation(value = "Create Route", response = RouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveRoute(RouteDTO dto) throws SODAPIException {
		
		Route entity = RoutesMapper.INSTANCE.map(dto);
		this.saveEntity(routesRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Route", response = RouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateRoute(RouteDTO dto) throws SODAPIException {
		Route entity = RoutesMapper.INSTANCE.map(dto);
		this.updateEntity(routesRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Route", response = RouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateRouteById(@PathParam("id") String id, RouteDTO dto) throws SODAPIException {
		Route entity = RoutesMapper.INSTANCE.map(dto);
		this.updateEntity(routesRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@ApiOperation(value = "Create Route", response = RouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteRoute(RouteDTO dto) throws SODAPIException {
		Route entity = RoutesMapper.INSTANCE.map(dto);
		this.deleteEntity(routesRepository, entity.getIdRoutes());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Route list", response = RouteDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getRouteList() throws SODAPIException {
		List<Route> rentityList = this.getEntityList(routesRepository);
		List<RouteDTO> list = rentityList.stream().map((i) -> {
			RouteDTO dto = RoutesMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);

	}
	
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Get Route by id", response = RouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getRouteById(@PathParam("id") String id) throws SODAPIException {
		RouteDTO dto = RoutesMapper.INSTANCE.map(this.getEntity(routesRepository, Integer.valueOf(id)));
		return castEntityAsResponse(dto, Response.Status.OK);
	}
}
