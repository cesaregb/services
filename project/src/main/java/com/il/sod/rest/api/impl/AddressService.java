package com.il.sod.rest.api.impl;

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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/clients/address", tags = { "clients" })
@Path("/clients/address")
public class AddressService extends AbstractServiceMutations {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	protected ClientRepository clientRepository;

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
		
		Address entity = this.getEntity(addressRepository, dto.getIdAddress());
		entity = ClientMapper.INSTANCE.map(dto, entity);
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
	@Path("/{id}")
	@ApiOperation(value = "Delete Address", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteEntityC(@PathParam("id") String id) throws SODAPIException {
		Address entity = addressRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Address not found");
		}
		Client cEntity = entity.getClient();
		cEntity.removeAddress(entity);
		this.saveEntity(clientRepository, cEntity);
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Address deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Address list", response = AddressDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getAddressList(@Context UriInfo uriInfo,
								   @QueryParam("idClient") String idClient
								   // not implemented...
//								   @QueryParam("prefered") Boolean prefered,
//								   @QueryParam("factura") Boolean factura,
//								   @QueryParam("address") String address,
//								   @QueryParam("address2") String address2,
//								   @QueryParam("city") String city,
//								   @QueryParam("country") String country,
//								   @QueryParam("zipcode") String zipcode,
//								   @QueryParam("state") String state
									) throws SODAPIException {


		List<Address> rentityList = null;
		if (StringUtils.isEmpty(idClient)){
			Client client = this.getEntity(clientRepository, Integer.valueOf(idClient));
			rentityList = new ArrayList<>(client.getAddresses());
		}else{
			rentityList = this.getEntityList(addressRepository);
		}

		List<AddressDTO> list = rentityList.stream().map(ClientMapper.INSTANCE::map).collect(Collectors.toList());
		return castEntityAsResponse(list);

	}

	@GET
	@Path("/byId/{id}")
	@ApiOperation(value = "Get Stop by id", response = AddressDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getById(@PathParam("id") String id) throws SODAPIException {
		Address entity = this.getEntity(addressRepository, Integer.valueOf(id));
		AddressDTO dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto);
	}

}
