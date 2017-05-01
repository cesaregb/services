package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.repositories.OrderTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderTypeDTO;
import com.il.sod.rest.dto.db.OrderTypeTaskDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/orders/order-type", produces = MediaType.APPLICATION_JSON)
@Api(value = "/orders/order-type", tags = {"orders"})
public class OrderTypeService extends AbstractServiceMutations {

	@Autowired
	private OrderTypeRepository orderTypeRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Order Type", response = OrderTypeDTO.class)
	public ResponseEntity<> saveOrderType(OrderTypeDTO dto) throws SODAPIException {
		OrderType entity = OrderMapper.INSTANCE.map(dto);
		this.saveEntity(orderTypeRepository, entity);
		dto = OrderMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Order Type", response = OrderTypeDTO.class)
	public ResponseEntity<> updateOrderType(OrderTypeDTO dto) throws SODAPIException {
		OrderType entity = OrderMapper.INSTANCE.map(dto);
		entity = this.updateEntity(orderTypeRepository, entity);
		dto = OrderMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	public ResponseEntity<> deleteItem(@PathVariable("id") String id) throws SODAPIException {
		OrderType entity = orderTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(orderTypeRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Order Type list", response = OrderTypeDTO.class, responseContainer = "List")
	public ResponseEntity<> getOrderTypeList() throws SODAPIException {
		List<OrderType> rentityList = this.getEntityList(orderTypeRepository);
		List<OrderTypeDTO> list = rentityList.stream()
				.map(OrderMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());

		list.forEach(System.out::println);
		return ConvertUtils.castEntityAsResponse(list);
	}

	/**
	 * Update task type tasks
	 * @param dto
	 * @return
	 * @throws SODAPIException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	@ApiOperation(value = "Update order type tasks", response = OrderTypeTaskDTO.class)
	public ResponseEntity<> updateOrterTypeTasks(OrderTypeDTO dto) throws SODAPIException {
		OrderType orderType = orderTypeRepository.findOne(dto.getIdOrderType());
		if (orderType == null){
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		// remove existing order type tasks
		// hopefully will cascade the deletes!!!
		orderType.setOrderTypeTask(null);
		orderType = this.saveEntity(orderTypeRepository, orderType);

		// add new order type tasks (probably are the same...)
		// validation made on request time.
		for (OrderTypeTaskDTO ott: dto.getOrderTypeTask()) {
			orderType.addOrderTypeTask(OrderMapper.INSTANCE.map(ott));
		}
		orderType = this.saveEntity(orderTypeRepository, orderType);
		dto = OrderMapper.INSTANCE.map(orderType);

		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}
}
