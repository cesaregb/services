package com.il.sod.rest.api.web.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.repositories.*;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.dto.db.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SpecificObjectsMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.web.WServiceCategoryDTO;

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
	ClientRepository clientRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ServiceCategoryRepository serviceCategoryRepository;
	
	@GET
	@Path("/orderTypes")
	@ApiOperation(value = "Get Address list", response = WServiceCategoryDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getOrderTypes() throws SODAPIException {
		SpecificObjectsMapper.INSTANCE.setProductRepository(productRepository);
		List<ServiceCategory> entities = serviceCategoryRepository.findAll();
		List<WServiceCategoryDTO> result = entities.stream().map(i -> SpecificObjectsMapper.INSTANCE.map(i)).collect(Collectors.toList());
		return this.castEntityAsResponse(result);
	}

	@GET
	@Path("/orders/{idClient}")
	@ApiOperation(value = "Get Client Orders list", response = OrderDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getOrdersByClient(@PathParam("idClient") String idClient) throws SODAPIException {
		Client client = this.getEntity(clientRepository, Integer.valueOf(idClient));
		List<Order> rentityList = orderRepository.findByClient(client);
		List<OrderDTO> list = rentityList.stream().map((i) -> {
			OrderDTO dto = OrderMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
