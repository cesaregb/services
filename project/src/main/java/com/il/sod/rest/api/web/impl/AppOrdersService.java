package com.il.sod.rest.api.web.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.config.Constants;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.PaymentInfo;
import com.il.sod.db.model.entities.Service;
import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.db.model.entities.ServiceSpec;
import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.entities.Spec;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.db.model.repositories.OrderRepository;
import com.il.sod.db.model.repositories.OrderTypeRepository;
import com.il.sod.db.model.repositories.ProductRepository;
import com.il.sod.db.model.repositories.ServiceCategoryRepository;
import com.il.sod.db.model.repositories.ServiceTypeRepository;
import com.il.sod.db.model.repositories.SpecRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.mapper.SpecificObjectsMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.web.InpServiceDTO;
import com.il.sod.rest.dto.web.InputSpecDTO;
import com.il.sod.rest.dto.web.NewOrderDTO;
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
	final static Logger LOGGER = LoggerFactory.getLogger(AppOrdersService.class);
	
	@Autowired
	OrderTypeRepository orderTypeRepository;
	
	@Autowired
	ServiceTypeRepository serviceTypeRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SpecRepository specRepository;
	
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
	
	@POST
	@ApiOperation(value = "Save order", response = OrderDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveOrder(NewOrderDTO orderInputDto) throws SODAPIException {
		OrderDTO result = null;
		// get order type. 
		int orderType = 1;
		if (orderInputDto.getPickUpDate() != null){
			orderType++;
		}
		if (orderInputDto.getDeliveryDate() != null){
			orderType++;
		}
		
		Order orderEntity = new Order();
		orderEntity.setComments(orderInputDto.getComments());
		orderEntity.setIdAddressPickup(orderInputDto.getIdAddressPickup());
		orderEntity.setPickUpDate(orderInputDto.getPickUpDate());
		orderEntity.setIdAddressDeliver(orderInputDto.getIdAddressDeliver());
		orderEntity.setDeliverDate(orderInputDto.getDeliveryDate());
		orderEntity.setPrice(orderInputDto.getPrice());
		orderEntity.setStatus(Constants.ORDER_CREATED);
		// get client 
		Client client =  clientRepository.findOne(orderInputDto.getIdClient());
		orderEntity.setClient(client);

		// get order type 
		OrderType ot = this.getEntity(orderTypeRepository, orderType);
		orderEntity.setOrderType(ot);
		
		// adding payment info in case of existing 
		if (orderInputDto.getPaymentInfo() != null && orderInputDto.getPaymentInfo().getTransactionInfo() != null ){
			PaymentInfo pi = new PaymentInfo();
			pi.setTransactionInfo(orderInputDto.getPaymentInfo().getTransactionInfo());
			pi.setType(orderInputDto.getPaymentInfo().getType());
			orderEntity.addPaymentInfo(pi);
		}
		
		for (InpServiceDTO servInput : orderInputDto.getServices()){
			Service service = new Service();
			ServiceType serviceType = serviceTypeRepository.findOne(servInput.getIdServiceType());
			service.setServiceType(serviceType);
			service.setPrice(servInput.getPrice());
			service.setDescription(servInput.getComments());
			
			// get specs. 
			for (InputSpecDTO specInput : servInput.getSpecs()){
				ServiceSpec serviceSpec = new ServiceSpec();
				serviceSpec.setQuantity(specInput.getQuantity());
				serviceSpec.setService(service);
				Spec spec = specRepository.findOne(specInput.getIdSpec());
				serviceSpec.setSpec(spec);
				serviceSpec.setSelectedValue(specInput.getValue());
				// adding service spec
				service.addServiceSpec(serviceSpec);
			}
			// adding service 
			orderEntity.addService(service);
		}
		
		// cast order 
		this.saveEntity(orderRepository, orderEntity);
		LOGGER.info("Order Saved!");
		
		result = OrderMapper.INSTANCE.map(orderEntity);
		LOGGER.info("Object mapper, response:" + this.castEntityAsString(result));
		
		return castEntityAsResponse(result, Response.Status.CREATED);
	}

}
