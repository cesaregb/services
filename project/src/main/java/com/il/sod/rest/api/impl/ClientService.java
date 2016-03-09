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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.api.AbstractService;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "clients", tags = { "clients" })
public class ClientService extends AbstractServiceMutations {
	final static Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

	@Autowired
	ClientRepository clientRepository;

	@PUT
	@ApiOperation(value = "Create Client", response = ClientDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveClient(ClientDTO dto) throws SODAPIException {
		try{
			Client entity = ClientMapper.INSTANCE.map(dto);
			assignDependencyToChilds(entity);
			this.saveEntity(clientRepository, entity);
			dto = ClientMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		}catch(Exception e){
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Client", response = ClientDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateClient(ClientDTO dto) throws SODAPIException {
		Client entity = ClientMapper.INSTANCE.map(dto);
		if (entity.getIdClient() == 0) {
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().error().setMessage("Client id cannot be null"),
					Response.Status.BAD_REQUEST);
		}
		assignDependencyToChilds(entity);
		this.updateEntity(clientRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@ApiOperation(value = "Delete Client", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteClient(ClientDTO dto) throws SODAPIException {
		Client entity = ClientMapper.INSTANCE.map(dto);
		if (entity.getIdClient() == 0) {
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().error().setMessage("Client id cannot be null"),
					Response.Status.BAD_REQUEST);
		}
		assignDependencyToChilds(entity);
		this.deleteEntity(clientRepository, entity.getIdClient());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Client deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Client list", response = ClientDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getClientList() throws SODAPIException {
		List<Client> r = this.getEntityList(clientRepository);
		List<ClientDTO> list = r.stream().map(ClientMapper.INSTANCE::map).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@GET
	@Path("/{clientId}")
	@ApiOperation(value = "Get Client list", response = ClientDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getClient(@PathParam("clientId") String clientId) throws SODAPIException {
		ClientDTO dto = ClientMapper.INSTANCE.map(this.getEntity(clientRepository, Integer.valueOf(clientId)));
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	private void assignDependencyToChilds(Client entity) {
		if (entity.getAddresses() != null)
			entity.getAddresses().stream().filter(a -> a != null).forEach(a -> a.setClient(entity));
		if (entity.getAccessKeys() != null)
			entity.getAccessKeys().stream().filter(a -> a != null).forEach(a -> a.setClient(entity));
		if (entity.getPhoneNumbers() != null)
			entity.getPhoneNumbers().stream().filter(a -> a != null).forEach(a -> a.setClient(entity));
		if (entity.getOrders() != null)
			entity.getOrders().stream().filter(a -> a != null).forEach(a -> a.setClient(entity));
	}
}