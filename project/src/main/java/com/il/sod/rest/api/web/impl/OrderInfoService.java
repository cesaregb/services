package com.il.sod.rest.api.web.impl;

import com.il.sod.converter.services.SpecificObjectsConverterService;
import com.il.sod.db.dao.impl.ServiceDAO;
import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.db.model.repositories.*;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.serve.WServiceCategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/order-info")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/app-orders", tags = { "app-orders" })
public class OrderInfoService extends AbstractServiceMutations {
	final static Logger LOGGER = LoggerFactory.getLogger(OrderInfoService.class);
	
	@Autowired
	OrderTypeRepository orderTypeRepository;
	
	@Autowired
	ServiceTypeRepository serviceTypeRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	SpecRepository specRepository;
	
	@Autowired
	ServiceCategoryRepository serviceCategoryRepository;
	
	@Autowired
	ServiceDAO serviceDAO;
	
	@Autowired
	SpecificObjectsConverterService specificObjectsConverterService;
	
	@GET
	@Path("/byId/{idOrder}")
	@ApiOperation(value = "Get Address list", response = WServiceCategoryDTO.class, responseContainer = "List")
	public Response getOrderTypes(@PathParam("idOrder") String idOrder) throws SODAPIException {


		List<ServiceCategory> entities = serviceCategoryRepository.findAll();
		List<WServiceCategoryDTO> result = entities.stream().map(i -> specificObjectsConverterService.map(i)).collect(Collectors.toList());
		return this.castEntityAsResponse(result);
	}
	

}
