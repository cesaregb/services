package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT; import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.PhoneNumber;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.db.model.repositories.PhoneNumberRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.PhoneNumberDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/phone-number")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/phone-number", tags = { "clients" })
public class PhoneNumberService extends AbstractServiceMutations {
	@Autowired
	PhoneNumberRepository phoneNumberRepository;

	@Autowired
	ClientRepository clientRepository;

	@POST
	@ApiOperation(value = "Create Phone Number", response = PhoneNumberDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response savePhoneNumber(PhoneNumberDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);

		PhoneNumber entity = ClientMapper.INSTANCE.map(dto);
		this.saveEntity(phoneNumberRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Phone Number", response = PhoneNumberDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updatePhoneNumber(PhoneNumberDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);

		PhoneNumber entity = ClientMapper.INSTANCE.map(dto);
		this.updateEntity(phoneNumberRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Phone Number", response = PhoneNumberDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updatePhoneNumberById(@PathParam("id") String id, PhoneNumberDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);

		PhoneNumber entity = ClientMapper.INSTANCE.map(dto);
		this.updateEntity(phoneNumberRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@ApiOperation(value = "Create Phone Number", response = PhoneNumberDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deletePhoneNumber(PhoneNumberDTO dto) throws SODAPIException {
		PhoneNumber entity = ClientMapper.INSTANCE.map(dto);
		this.deleteEntity(phoneNumberRepository, entity.getIdPhoneNumber());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Phone Number list", response = PhoneNumberDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getPhoneNumberList() throws SODAPIException {
		List<PhoneNumber> rentityList = this.getEntityList(phoneNumberRepository);
		List<PhoneNumberDTO> list = rentityList.stream().map((i) -> {
			PhoneNumberDTO dto = ClientMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
