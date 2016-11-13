package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.Client;
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
@Path("/clients/phone-number")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/clients/phone-number", tags = { "clients" })
public class PhoneNumberService extends AbstractServiceMutations {
	@Autowired
	PhoneNumberRepository phoneNumberRepository;

	@Autowired
	ClientRepository clientRepository;

	@POST
	@ApiOperation(value = "Create Phone Number", response = PhoneNumberDTO.class)
	public Response savePhoneNumber(PhoneNumberDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);

		PhoneNumber entity = ClientMapper.INSTANCE.map(dto);
		this.saveEntity(phoneNumberRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Phone Number", response = PhoneNumberDTO.class)
	public Response updatePhoneNumber(PhoneNumberDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(PhoneNumberDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);

		PhoneNumber entity = ClientMapper.INSTANCE.map(dto);
		this.updateEntity(phoneNumberRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete PhoneNumber", response = GeneralResponseMessage.class)
	public Response deleteEntity(@PathParam("id") String id) throws SODAPIException {
		PhoneNumber entity = phoneNumberRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "PhoneNumber not found");
		}
		Client cEntity = entity.getClient();
		cEntity.removePhoneNumber(entity);
		this.saveEntity(clientRepository, cEntity);
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("PhoneNumber deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Phone Number list", response = PhoneNumberDTO.class, responseContainer = "List")
	public Response getPhoneNumberList() throws SODAPIException {
		List<PhoneNumber> rentityList = this.getEntityList(phoneNumberRepository);
		List<PhoneNumberDTO> list = rentityList.stream().map((i) -> {
			PhoneNumberDTO dto = ClientMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
