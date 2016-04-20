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

import com.il.sod.db.dao.IClientDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
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
	final static Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private IClientDAO clientDAO;

	@POST
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

	@PUT
	@Path("{clientId}")
	@ApiOperation(value = "Update Client", response = ClientDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateClient(@PathParam("clientId") String clientId, ClientDTO dto) throws SODAPIException {
		
		System.out.println("******************");
		System.out.println("dto: " + this.castEntityAsString(dto));
		System.out.println("******************");
		
		if (dto.getIdClient() != Integer.valueOf(clientId) || dto.getIdClient() == 0){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Client id should match with object and should be different than 0 ");
		}
		
		Client entity = clientRepository.findOne(Integer.valueOf(clientId));
		entity = ClientMapper.INSTANCE.map(dto, entity);
		
		System.out.println("******************");
		System.out.println("entity: " + this.castEntityAsString(entity));
		System.out.println("******************");
		assignDependencyToChilds(entity);
		this.updateEntity(clientRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("{clientId}")
	@ApiOperation(value = "Delete Client", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteClient(@PathParam("clientId") String clientId, ClientDTO dto) throws SODAPIException {
		
		if (dto.getIdClient() != Integer.valueOf(clientId) || dto.getIdClient() == 0){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Client id should match with object and should be different than 0 ");
		}
		Client entity = clientRepository.findOne(Integer.valueOf(clientId));
		entity = ClientMapper.INSTANCE.map(dto, entity);
		
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
	
	@GET
	@Path("/email/{email}")
	@ApiOperation(value = "Get Client list by email", response = ClientDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getClientByEmail(@PathParam("email") String email) throws SODAPIException {
		ClientDTO dto = new ClientDTO();
		List<Client> clients = clientDAO.findByEmail(email);
		if (clients != null && !clients.isEmpty()) {
			Client client = clients.get(0);
			dto = ClientMapper.INSTANCE.map(client);
		}else{
			throw new SODAPIException(Response.Status.NO_CONTENT, "No employee found");
		}
		return castEntityAsResponse(dto, Response.Status.OK);
	}
	
	@GET
	@Path("/token/{payment-token}")
	@ApiOperation(value = "Get Client list by payment token", response = ClientDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getClientByToken(@PathParam("payment-token") String token) throws SODAPIException {
		List<Client> lClients = clientDAO.findByToken(token);
		List<ClientDTO> lResult = lClients.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toList());
		return castEntityAsResponse(lResult, Response.Status.OK);
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
		if (entity.getClientPaymentInfos() != null)
			entity.getClientPaymentInfos().stream().filter(a -> a != null).forEach(a -> a.setClient(entity));
	}
}