package com.il.sod.rest.api.web.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.repositories.AddressRepository;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.db.model.repositories.OrderRepository;
import com.il.sod.db.model.repositories.OrderTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AddressDTO;
import com.il.sod.rest.dto.web.NewOrderDTO;
import com.il.sod.rest.dto.web.OrderDetailsDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/app-orders")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/app-orders", tags = { "app-orders" })
public class AppOrdersService extends AbstractServiceMutations {
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderTypeRepository orderTypeRepository;
	
	@GET
	@Path("/by-client/{clientId}")
	@ApiOperation(value = "Get Address list", response = AddressDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getOrderByClient(@PathParam("clientId") String clientId) throws SODAPIException {
		throw new SODAPIException("not yet implemented!!");
	}
	
	@GET
	@Path("/{clientId}")
	@ApiOperation(value = "Get Address list", response = AddressDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getOrderTypes(@PathParam("clientId") String clientId) throws SODAPIException {
//		Client client = this.getEntity(clientRepository, Integer.valueOf(clientId));
//		List<AddressDTO> result = ClientMapper.INSTANCE.map(client.getAddresses());
//		return castEntityAsResponse(result);
		throw new SODAPIException("not yet implemented!!");
	}
	
	@GET
	@Path("/orderTypes")
	@ApiOperation(value = "Get Address list", response = OrderDetailsDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getOrderTypes() throws SODAPIException {
		
		
		
		List<OrderDetailsDTO> result = null;
		throw new SODAPIException("not yet implemented!!");
	}
	
	@POST
	@Path("/create")
	@ApiOperation(value = "Create new order", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response createOrder(NewOrderDTO order) throws SODAPIException {
		throw new SODAPIException("not yet implemented!!");
	}

}
