package com.il.sod.rest.api.web.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.converter.OrderDetailsConverter;
import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.OrderTypeTask;
import com.il.sod.db.model.repositories.AddressRepository;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.db.model.repositories.OrderRepository;
import com.il.sod.db.model.repositories.OrderTypeRepository;
import com.il.sod.db.model.repositories.ProductRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.TaskMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.rest.dto.web.OrderDetailsDTO;
import com.il.sod.rest.dto.web.ServiceDetailsDTO;

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
	OrderTypeRepository orderTypeRepository;
	
	@Autowired
	ProductRepository productRepository;
	
//	@GET
//	@Path("/by-client/{clientId}")
//	@ApiOperation(value = "Get Address list", response = AddressDTO.class, responseContainer = "List")
//	@ApiResponses(value = {
//			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
//			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
//	public Response getOrderByClient(@PathParam("clientId") String clientId) throws SODAPIException {
//		throw new SODAPIException("not yet implemented!!");
//	}
	
//	@GET
//	@Path("/{clientId}")
//	@ApiOperation(value = "Get Address list", response = AddressDTO.class, responseContainer = "List")
//	@ApiResponses(value = {
//			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
//			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
//	public Response getOrderTypes(@PathParam("clientId") String clientId) throws SODAPIException {
//		throw new SODAPIException("not yet implemented!!");
//	}
	
	@GET
	@Path("/orderTypes")
	@ApiOperation(value = "Get Address list", response = OrderDetailsDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getOrderTypes() throws SODAPIException {
		List<OrderDetailsDTO> result = new ArrayList<>();
		OrderDetailsConverter converter = new OrderDetailsConverter();
		converter.setRepository(productRepository);
		
		// get all orderTypes.. 
		List<OrderType> orderTypeList = orderTypeRepository.findAll();
		
		for (OrderType ot : orderTypeList){
			// order type details.. 
			OrderDetailsDTO orderDetailsDTO = converter.getOrderDetailFromOrderType(ot);

			// set task for order 
			List<TaskDTO> taskList = new ArrayList<>();
			for (OrderTypeTask ott : ot.getOrderTypeTasks()){
				taskList.add(TaskMapper.INSTANCE.map(ott.getTask()));
			}
			orderDetailsDTO.setTasks(taskList);
			
			// get services list.. 
			List<ServiceDetailsDTO> services = converter.getServiceDtlListFromOrderType(ot);
			
			
			orderDetailsDTO.setServices(services);
			
			
			
			result.add(orderDetailsDTO);
		}
		
		return this.castEntityAsResponse(result);
	}

//	@POST
//	@Path("/create")
//	@ApiOperation(value = "Create new order", response = GeneralResponseMessage.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
//			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
//	public Response createOrder(NewOrderDTO order) throws SODAPIException {
//		throw new SODAPIException("not yet implemented!!");
//	}

}