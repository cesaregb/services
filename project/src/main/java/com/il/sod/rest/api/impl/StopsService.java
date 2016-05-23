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

import com.il.sod.db.model.entities.AddressRoute;
import com.il.sod.db.model.entities.Stop;
import com.il.sod.db.model.repositories.AddressRouteRepository;
import com.il.sod.db.model.repositories.StopRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.RoutesMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.StopDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/stops")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/stops", tags = { "routes" })
public class StopsService extends AbstractServiceMutations {

	@Autowired
	StopRepository stopRepository;
	
	@Autowired
	AddressRouteRepository addressRouteRepository;

	@POST
	@ApiOperation(value = "Create Stop", response = StopDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveStop(StopDTO dto) throws SODAPIException {
		
		if ( dto.getType() == 1 ){
			AddressRoute addressEntity = RoutesMapper.INSTANCE.map(dto.getAddress());
			this.saveEntity(addressRouteRepository, addressEntity);
			dto.setIdAddress(addressEntity.getIdAddressRoute());
		}
		
		Stop entity = RoutesMapper.INSTANCE.map(dto);
		assignDependencyToChilds(entity);
		this.saveEntity(stopRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Stop", response = StopDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateStop(StopDTO dto) throws SODAPIException {
		Stop entity = RoutesMapper.INSTANCE.map(dto);
		this.updateEntity(stopRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Stop", response = StopDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateStopById(@PathParam("id") String id, StopDTO dto) throws SODAPIException {
		Stop entity = RoutesMapper.INSTANCE.map(dto);
		this.updateEntity(stopRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@ApiOperation(value = "Create Stop", response = StopDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteStop(StopDTO dto) throws SODAPIException {
		Stop entity = RoutesMapper.INSTANCE.map(dto);
		this.deleteEntity(stopRepository, entity.getIdStops());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Stop list", response = StopDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getStopList() throws SODAPIException {
		List<Stop> rentityList = this.getEntityList(stopRepository);
		List<StopDTO> list = rentityList.stream().map((i) -> {
			StopDTO dto = RoutesMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);

	}
	
	private void assignDependencyToChilds(Stop entity) {
		if (entity.getRoute() != null){
			entity.getRoute().addStop(entity);
		}
	}
}
