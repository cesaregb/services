package com.il.sod.rest.api.impl;

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
	public Response saveAddressRoute(AddressRouteDTO dto) throws SODAPIException {

		AddressRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.saveEntity(addressRouteRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update AddressRoute", response = AddressRouteDTO.class)
	public Response updateAddressRoute(AddressRouteDTO dto) throws SODAPIException {

		AddressRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.updateEntity(addressRouteRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@ApiOperation(value = "Create AddressRoute", response = AddressRouteDTO.class)
	public Response deleteAddressRoute(AddressRouteDTO dto) throws SODAPIException {
		AddressRoute entity = RoutesMapper.INSTANCE.map(dto);
		this.deleteEntity(addressRouteRepository, entity.getIdAddressRoute());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
				Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete AddressRoute", response = GeneralResponseMessage.class)
	public Response deleteEntity(@PathParam("id") String id) throws SODAPIException {

		AddressRoute entity = addressRouteRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "AddressRoute not found");
		}
		this.deleteEntity(addressRouteRepository, Integer.valueOf(id));
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("AddressRoute deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get AddressRoute list", response = AddressRouteDTO.class, responseContainer = "List")
	public Response getAddressRouteList() throws SODAPIException {
		List<AddressRoute> rentityList = this.getEntityList(addressRouteRepository);
		List<AddressRouteDTO> list = rentityList.stream().map((i) -> {
			AddressRouteDTO dto = RoutesMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@GET
	@Path("/byId/{id}")
	@ApiOperation(value = "Get Stop by id", response = AddressDTO.class)
	public Response getById(@PathParam("id") String id) throws SODAPIException {
		AddressRoute entity = this.getEntity(addressRouteRepository, Integer.valueOf(id));
		AddressRouteDTO dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto);
	}

}
