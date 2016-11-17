package com.il.sod.rest.api.web.impl;

import com.il.sod.config.Constants;
import com.il.sod.converter.services.SpecificObjectsConverterService;
import com.il.sod.db.dao.impl.ServiceDAO;
import com.il.sod.db.model.entities.*;
import com.il.sod.db.model.repositories.*;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.mapper.TaskMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.specifics.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	SpecRepository specRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	ServiceCategoryRepository serviceCategoryRepository;
	
	@Autowired
	ServiceDAO serviceDAO;
	
	@Autowired
	SpecificObjectsConverterService specificObjectsConverterService;
	
	@GET
	@Path("/orderTypes")
	@ApiOperation(value = "Get Address list", response = WServiceCategoryDTO.class, responseContainer = "List")
	public Response getOrderTypes() throws SODAPIException {
		List<ServiceCategory> entities = serviceCategoryRepository.findAll();
		List<WServiceCategoryDTO> result = entities.stream().map(i -> specificObjectsConverterService.map(i)).collect(Collectors.toList());
		return this.castEntityAsResponse(result);
	}

	@GET
	@Path("/public/orderTypes")
	@ApiOperation(value = "Get Address list", response = WServiceCategoryDTO.class, responseContainer = "List")
	public Response getOrderTypesPublic() throws SODAPIException {
		List<ServiceCategory> entities = serviceCategoryRepository.findAll();
		List<WServiceCategoryDTO> result = entities.stream().map(i -> specificObjectsConverterService.map(i)).collect(Collectors.toList());

		for (WServiceCategoryDTO cat : result){
			Set<WServiceTypeDTO> serviceTypes =  cat.getServiceTypes().stream().filter(st -> st.isCalculator()).collect(Collectors.toSet());
			cat.setServiceTypes(serviceTypes);
		}

		return this.castEntityAsResponse(result);
	}

	
	@GET
	@Path("/orders/{idClient}")
	@ApiOperation(value = "Get Client Orders list", response = OrderDTO.class, responseContainer = "List")
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
	public Response saveOrder(UIOrderDTO orderInputDto) throws SODAPIException {
		OrderDTO result = null;
		
		// get order type. 
		int orderType = 4;
		if (orderInputDto.getPickUpDate() != null && orderInputDto.getDeliveryDate() != null){
			orderType = 1;
		}else if (orderInputDto.getPickUpDate() != null){
			orderType = 2;
		}else if (orderInputDto.getDeliveryDate() != null){
			orderType = 3;
		}
		
		Order orderEntity = new Order();
		orderEntity.setComments(orderInputDto.getComments());
		orderEntity.setIdAddressPickup(orderInputDto.getIdAddressPickup());
		orderEntity.setPickUpDate(orderInputDto.getPickUpDate());
		orderEntity.setIdAddressDeliver(orderInputDto.getIdAddressDeliver());
		orderEntity.setDeliverDate(orderInputDto.getDeliveryDate());
		orderEntity.setTotalServices(orderInputDto.getTotalServices());
		orderEntity.setTotal(orderInputDto.getTotal());
		orderEntity.setStatus(Constants.ORDER_CREATED);
		// get client 
		Client client =  clientRepository.findOne(orderInputDto.getIdClient());
		orderEntity.setClient(client);

		// get order type 
		OrderType ot = this.getEntity(orderTypeRepository, orderType);
		orderEntity.setOrderType(ot);
		
		// add order tasks. 
		for (OrderTypeTask ott : ot.getOrderTypeTask()){
			OrderTask orderTask = TaskMapper.INSTANCE.map(ott);
			orderTask.setOrder(orderEntity);
			orderEntity.addOrderTask(orderTask);
		}
		
		// adding payment info in case of existing 
		if (orderInputDto.getPaymentInfo() != null && orderInputDto.getPaymentInfo().getTransactionInfo() != null ){
			PaymentInfo pi = new PaymentInfo();
			pi.setTransactionInfo(orderInputDto.getPaymentInfo().getTransactionInfo());
			pi.setOrder(orderEntity);
			pi.setType(orderInputDto.getPaymentInfo().getType());
			orderEntity.setPaymentInfo(pi);
		}
		
		for (UIServiceDTO servInput : orderInputDto.getServices()){
			Service service = new Service();
			ServiceType serviceType = serviceTypeRepository.findOne(servInput.getIdServiceType());
			service.setServiceType(serviceType);
			service.setName(serviceType.getName());
			service.setDescription(serviceType.getDescription());
			service.setPrice(servInput.getPrice());
			service.setDescription(servInput.getComments());
			
			// add service tasks. 
			for (ServiceTypeTask serviceTypeTask : serviceType.getServiceTypeTasks()){
//				ServiceTask serticeTask = TaskMapper.INSTANCE.map(serviceTypeTask);
				ServiceTask servTask = new ServiceTask();
				servTask.setTask(serviceTypeTask.getTask());
				servTask.setTime(serviceTypeTask.getTime());
				servTask.setSortingOrder(serviceTypeTask.getSortingOrder());
				servTask.setService(service);
				service.addServiceTask(servTask);
			}
			
			// get specs. 
			for (UISpecDTO specInput : servInput.getSpecs()){
				ServiceSpec serviceSpec = new ServiceSpec();
				serviceSpec.setQuantity(specInput.getQuantity());
				serviceSpec.setService(service);
				serviceSpec.setSpec(specRepository.findOne(specInput.getIdSpecs()));
				serviceSpec.setSelectedValue(specInput.getValue());
				serviceSpec.setSpecPrice(specInput.getPrice());
				// adding service spec
				service.addServiceSpec(serviceSpec);
			}

			if (!CollectionUtils.isEmpty(servInput.getProducts())){
				for (UIProductDTO product : servInput.getProducts()){
					ServiceProduct ss = new ServiceProduct();
					ss.setProduct(productRepository.findOne(product.getIdProduct()));
					ss.setQuantity(product.getQuantity());
					ss.setPrice(product.getPrice());
					service.addProduct(ss);
				}
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
