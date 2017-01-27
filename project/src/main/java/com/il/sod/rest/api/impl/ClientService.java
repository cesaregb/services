package com.il.sod.rest.api.impl;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.services.cruds.ClientSv;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@RolesAllowed("ADMIN")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "clients", tags = {"clients"})
@Path("/clients")
public class ClientService extends AbstractServiceMutations {

	private final static Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	ClientSv clientSv;

	@POST
	@ApiOperation(value = "Create Client", response = ClientDTO.class)
	public Response saveClient(ClientDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(clientSv.saveClient(dto), Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Client", response = ClientDTO.class)
	public Response updateClient(ClientDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(clientSv.updateClient(dto), Response.Status.OK);
	}

	@PUT
	@Path("/reactivate/{email}")
	@ApiOperation(value = "Reactivate Client", response = ClientDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class)})
	public Response reactivateClient(@PathParam("email") String email) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(clientSv.reactivateClient(email), Response.Status.OK);
	}


	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Client", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String clientId) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(
				new GeneralResponseMessage(clientSv.deleteItem(Integer.valueOf(clientId)),
						"Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@Path("/byId/{clientId}")
	@ApiOperation(value = "Get Client by id", response = ClientDTO.class)
	public Response getClient(@PathParam("clientId") String clientId) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(clientSv.getClient(clientId), Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Client list by filter UNIQUE[ phone, idAddress, email, token]", response = ClientDTO.class)
	public Response getClientsByFilter(@Context UriInfo uriInfo,
	                                   @QueryParam("idAddress") String idAddress,
	                                   @QueryParam("phone") String phone,
	                                   @QueryParam("token") String token,
	                                   @QueryParam("email") String email,
	                                   @QueryParam("lastName") String lastName,
	                                   @QueryParam("name") String name,
	                                   @QueryParam("twitter") String twitter,
	                                   @QueryParam("loginID") String loginID,
	                                   @QueryParam("rfc") String rfc,
	                                   @QueryParam("razonSocial") String razonSocial,
	                                   @QueryParam("idClientType") String idClientType) throws SODAPIException {


		return ConvertUtils.castEntityAsResponse( clientSv.getClientsByFilter(uriInfo.getQueryParameters(), idAddress, phone, token),
				Response.Status.OK);
	}

}