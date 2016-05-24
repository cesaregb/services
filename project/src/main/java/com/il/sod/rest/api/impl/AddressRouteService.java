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
import com.il.sod.db.model.repositories.AddressRouteRepository;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.RoutesMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AddressDTO;
import com.il.sod.rest.dto.db.AddressRouteDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/addressRoute")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/addressRoute", tags = { "routes" })
public class AddressRouteService extends AbstractServiceMutations {

	@Autowired
	AddressRouteRepository addressRouteRepository;

	@Autowired
	ClientRepository clientRepository;

	@POST
	@ApiOperation(value = "Create AddressRoute", response = AddressRouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveAddressRoute(AddressRouteDTO dto) throws SODAPIException {

		AddressRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.saveEntity(addressRouteRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update AddressRoute", response = AddressRouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAddressRoute(AddressRouteDTO dto) throws SODAPIException {

		AddressRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.updateEntity(addressRouteRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update AddressRoute", response = AddressRouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAddressRouteById(@PathParam("id") String id, AddressRouteDTO dto) throws SODAPIException {

		AddressRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.updateEntity(addressRouteRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@ApiOperation(value = "Create AddressRoute", response = AddressRouteDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteAddressRoute(AddressRouteDTO dto) throws SODAPIException {
		AddressRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.deleteEntity(addressRouteRepository, entity.getIdAddressRoute());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get AddressRoute list", response = AddressRouteDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getAddressRouteList() throws SODAPIException {
		List<AddressRoute> rentityList = this.getEntityList(addressRouteRepository);
		List<AddressRouteDTO> list = rentityList.stream().map((i) -> {
			AddressRouteDTO dto = RoutesMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}
	
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Get Stop by id", response = AddressDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getById(@PathParam("id") String id) throws SODAPIException {
		AddressRoute entity = this.getEntity(addressRouteRepository, Integer.valueOf(id));
		AddressRouteDTO dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto);
	}

}
