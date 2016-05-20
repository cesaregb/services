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

import com.il.sod.db.model.entities.CalendarRoute;
import com.il.sod.db.model.repositories.CalendarRouteRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.RoutesMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.CalendarRouteDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/calendarRoutes")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/calendarRoutes", tags = { "routes" })
public class CalendarRouteService extends AbstractServiceMutations {

	@Autowired
	CalendarRouteRepository calendarRouteRepository;

	@POST
	@ApiOperation(value = "Create CalendarRoute", response = CalendarRouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveCalendarRoute(CalendarRouteDTO dto) throws SODAPIException {
		
		CalendarRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.saveEntity(calendarRouteRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update CalendarRoute", response = CalendarRouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateCalendarRoute(CalendarRouteDTO dto) throws SODAPIException {
		CalendarRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.updateEntity(calendarRouteRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update CalendarRoute", response = CalendarRouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateCalendarRouteById(@PathParam("id") String id, CalendarRouteDTO dto) throws SODAPIException {
		CalendarRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.updateEntity(calendarRouteRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@ApiOperation(value = "Create CalendarRoute", response = CalendarRouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteCalendarRoute(CalendarRouteDTO dto) throws SODAPIException {
		CalendarRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.deleteEntity(calendarRouteRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get CalendarRoute list", response = CalendarRouteDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getCalendarRouteList() throws SODAPIException {
		List<CalendarRoute> rentityList = this.getEntityList(calendarRouteRepository);
		List<CalendarRouteDTO> list = rentityList.stream().map((i) -> {
			CalendarRouteDTO dto = RoutesMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);

	}
}
