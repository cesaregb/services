package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT; import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.Address;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.AddressRepository;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AddressDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/address", tags = { "clients" })
public class AddressService extends AbstractServiceMutations {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	ClientRepository clientRepository;

	@POST
	@ApiOperation(value = "Create Address", response = AddressDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveAddress(AddressDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);

		Address entity = ClientMapper.INSTANCE.map(dto);
		this.saveEntity(addressRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Address", response = AddressDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAddress(AddressDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);

		Address entity = ClientMapper.INSTANCE.map(dto);
		this.updateEntity(addressRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Address", response = AddressDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAddressById(@PathParam("id") String id, AddressDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);

		Address entity = ClientMapper.INSTANCE.map(dto);
		this.updateEntity(addressRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@ApiOperation(value = "Create Address", response = AddressDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteAddress(AddressDTO dto) throws SODAPIException {
		Address entity = ClientMapper.INSTANCE.map(dto);
		this.deleteEntity(addressRepository, entity.getIdAddress());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Address list", response = AddressDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getAddressList() throws SODAPIException {
		List<Address> rentityList = this.getEntityList(addressRepository);
		List<AddressDTO> list = rentityList.stream().map((i) -> {
			AddressDTO dto = ClientMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);

	}

	@GET
	@Path("/{clientId}")
	@ApiOperation(value = "Get Address list", response = AddressDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getAddressListByClient(@PathParam("clientId") String clientId) throws SODAPIException {

		Client client = this.getEntity(clientRepository, Integer.valueOf(clientId));
		Set<AddressDTO> result = ClientMapper.INSTANCE.map(client.getAddresses());
		return castEntityAsResponse(result);
	}

}
