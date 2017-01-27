package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.Route;
import com.il.sod.db.model.repositories.RoutesRepository;
import com.il.sod.services.utils.ConvertUtils;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.RoutesMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.RouteDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

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
	public Response saveRoute(RouteDTO dto) throws SODAPIException {

		Route entity = RoutesMapper.INSTANCE.map(dto);
		this.saveEntity(routesRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Route", response = RouteDTO.class)
	public Response updateRoute(RouteDTO dto) throws SODAPIException {
		Route entity = RoutesMapper.INSTANCE.map(dto);
		this.updateEntity(routesRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Route", response = GeneralResponseMessage.class)
	public Response deleteEntity(@PathParam("id") String id) throws SODAPIException {
		Route entity = routesRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Route not found");
		}
		this.deleteEntity(routesRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Route list", response = RouteDTO.class, responseContainer = "List")
	public Response getRouteList() throws SODAPIException {
		List<Route> rentityList = this.getEntityList(routesRepository);
		List<RouteDTO> list = rentityList.stream().map((i) -> {
			RouteDTO dto = RoutesMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);

	}

	@GET
	@Path("/byId/{id}")
	@ApiOperation(value = "Get Route by id", response = RouteDTO.class)
	public Response getRouteById(@PathParam("id") String id) throws SODAPIException {
		RouteDTO dto = RoutesMapper.INSTANCE.map(this.getEntity(routesRepository, Integer.valueOf(id)));
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);
	}
}
