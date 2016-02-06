package com.il.sod.rest.api.impl;

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

import com.il.sod.db.dao.IDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/cleints")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="clients", tags={"clients"})
public class ClientService extends AbstractService {
	final static Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);
	
	private IDAO<Client, Integer> genericDaoImpl;
	
	@Autowired
	public ClientService(ClientRepository clientRepository, IDAO<Client, Integer> genericDaoImpl){
		genericDaoImpl.setRepository(clientRepository);
		this.genericDaoImpl = genericDaoImpl;
	}
	
	@POST
	@ApiOperation(value = "Create Client", response=Client.class)
	@ApiResponses(value = { 
		@ApiResponse(code=400, message="Invalid input supplied"),
		@ApiResponse(code=404, message="Info not found")})
	public Response saveClient(Client entity) throws SODAPIException{
		genericDaoImpl.create(entity);
		return castEntityAsResponse(entity, Response.Status.CREATED);
	}
	
	@PUT
	@ApiOperation(value = "Update Client", response=Client.class)
	@ApiResponses(value = { 
			@ApiResponse(code=400, message="Invalid input supplied"),
			@ApiResponse(code=404, message="Info not found")})
	public Response updateClient(Client entity) throws SODAPIException{
		genericDaoImpl.update(entity);
		return castEntityAsResponse(entity, Response.Status.CREATED);
	}
	
	@DELETE
	@ApiOperation(value = "Update Client", response=Client.class)
	@ApiResponses(value = { 
			@ApiResponse(code=400, message="Invalid input supplied"),
			@ApiResponse(code=404, message="Info not found")})
	public Response deleteClient(Client entity) throws SODAPIException{
		genericDaoImpl.delete(entity.getIdClient());
		return castEntityAsResponse(entity, Response.Status.CREATED);
	}	
	
	@GET
	@ApiOperation(value = "Get Client list", response=Client.class, responseContainer = "List")
	public Response getClientList() throws SODAPIException{	
		return castEntityAsResponse(genericDaoImpl.findAll());
	}
	
	@GET
	@Path("/{clientId}")
	@ApiOperation(value = "Get Client list", response=Client.class)
	public Response getClient(@PathParam("clientId") String clientId) throws SODAPIException{	
		return castEntityAsResponse(genericDaoImpl.findById(Integer.valueOf(clientId)));
	}
	
}